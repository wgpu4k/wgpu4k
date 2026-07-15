# Safe Native Callbacks (Non-Android) Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Migrer `wgpu4k` vers `wgpu4k-native` v29 et garantir des callbacks natifs sûrs, annulables, pompés hors render thread et terminés exactement une fois sur JVM et Kotlin/Native hors Android.

**Architecture:** Un `NativeCallbackOperation<T>` centralise la reprise exactement une fois et le cleanup d'une registration ponctuelle. Un `DeviceEventPump` par `Device` sérialise les polls non bloquants, conserve une référence native et protège les downcalls concurrents avec `Device.close()`. Les callbacks qui transfèrent un handle natif conservent leur registration jusqu'à la livraison inline afin de pouvoir libérer un résultat devenu indélivrable.

**Tech Stack:** Kotlin Multiplatform 2.3.21, kotlinx.coroutines 1.10.2, Kotest 6.0.7, kffi/wgpu4k-native v29, Gradle 9.5.0.

## Global Constraints

- Utiliser exactement `io.ygdrasil:wgpu4k-native:v29.0.0-SNAPSHOT`.
- Résoudre le snapshot depuis `https://central.sonatype.com/repository/maven-snapshots/` dans le build principal et `generator`.
- Ne modifier aucun fichier dans `androidMain` ou `androidNativeMain` et ne lancer aucune tâche Android.
- Utiliser `CallbackPolicy.ONCE` pour les callbacks ponctuels et `CallbackPolicy.REPEATING` pour `onUncapturedError`.
- Utiliser explicitement `WGPUCallbackMode_AllowProcessEvents` pour queue/map et `WGPUCallbackMode_AllowSpontaneous` pour les callbacks inline de v29.
- Ne jamais appeler `wgpuDeviceCreateComputePipelineAsync`, `wgpuDeviceCreateRenderPipelineAsync` ou `wgpuShaderModuleGetCompilationInfo` avec wgpu-native 29.0.0.0.
- Préserver les signatures publiques existantes.
- Propager `CancellationException`; ne jamais l'encapsuler dans `Result.failure`.
- Préfixer toutes les commandes shell par `rtk` conformément à `AGENTS.md`.
- Référence de conception : `docs/superpowers/specs/2026-07-15-non-android-native-callbacks-design.md`.

## File Structure

**Nouveaux composants :**

- `wgpu4k/src/commonNativeMain/kotlin/internal/NativeCallbackOperation.kt` — terminaison atomique d'une continuation, cleanup de registration et valeur indélivrable.
- `wgpu4k/src/commonNativeMain/kotlin/internal/DeviceEventPump.kt` — registre d'opérations, poll non bloquant, protection du handle et exécution des fallbacks synchrones.
- `wgpu4k/src/jvmTest/kotlin/internal/NativeCallbackOperationTest.kt` — courses callback/annulation et cleanup exactement une fois.
- `wgpu4k/src/jvmTest/kotlin/internal/DeviceEventPumpTest.kt` — activité idle, polls, fermeture et downcalls concurrents.
- `wgpu4k/src/jvmTest/kotlin/mapper/UncapturedErrorCallbackTest.kt` — répétition et fermeture de la registration d'erreur non capturée.

**Fichiers fonctionnels modifiés :**

- `wgpu4k/src/commonNativeMain/kotlin/Device.native.kt`
- `wgpu4k/src/commonNativeMain/kotlin/Queue.native.kt`
- `wgpu4k/src/commonNativeMain/kotlin/Buffer.native.kt`
- `wgpu4k/src/commonNativeMain/kotlin/Adapter.native.kt`
- `wgpu4k/src/commonNativeMain/kotlin/WGPU.kt`
- `wgpu4k/src/commonNativeMain/kotlin/ShaderModule.native.kt`
- `wgpu4k/src/commonNativeMain/kotlin/DeviceExt.native.kt`
- `wgpu4k/src/commonNativeMain/kotlin/mapper/DeviceDescriptor.kt`

**Configuration et migration mécanique :**

- `settings.gradle.kts`
- `generator/build.gradle.kts`
- `gradle/libs.versions.toml`
- `wgpu4k/build.gradle.kts`
- Tous les fichiers Kotlin hors `androidMain` et `androidNativeMain` qui importent actuellement `ffi.*`; seuls leurs imports passent à `io.ygdrasil.kffi.*`.

---

### Task 1: Primitive de terminaison exactement une fois

**Files:**
- Modify: `gradle/libs.versions.toml`
- Modify: `wgpu4k/build.gradle.kts`
- Create: `wgpu4k/src/commonNativeMain/kotlin/internal/NativeCallbackOperation.kt`
- Test: `wgpu4k/src/jvmTest/kotlin/internal/NativeCallbackOperationTest.kt`

**Interfaces:**
- Consumes: `CancellableContinuation<Result<T>>`, une fonction idempotente de fermeture de registration.
- Produces: `NativeCallbackOperation<T>.attach(AutoCloseable)`, `.succeed(T, (T) -> Unit)`, `.fail(Throwable)`, `.isFinished` et un hook `onFinished` exécuté exactement une fois.

- [ ] **Step 1: Ajouter kotlinx-coroutines-test au catalogue et au source set JVM test**

Dans `gradle/libs.versions.toml`, ajouter :

```toml
coroutines-test = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-test", version.ref = "coroutines" }
```

Dans `wgpu4k/build.gradle.kts`, ajouter sous `jvmTest.dependencies` :

```kotlin
implementation(libs.coroutines.test)
```

- [ ] **Step 2: Écrire les tests rouges de terminaison, annulation et valeur indélivrable**

Créer `wgpu4k/src/jvmTest/kotlin/internal/NativeCallbackOperationTest.kt` :

```kotlin
package io.ygdrasil.webgpu.internal

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.test.runTest

class NativeCallbackOperationTest : FreeSpec({
    "only the first result is delivered and cleanup runs once" {
        runTest {
            var registrationCloses = 0
            var attachmentCloses = 0
            var finishes = 0
            lateinit var operation: NativeCallbackOperation<Int>
            val result = async(start = CoroutineStart.UNDISPATCHED) {
                suspendCancellableCoroutine<Result<Int>> { continuation ->
                    operation = NativeCallbackOperation(
                        continuation = continuation,
                        closeRegistration = { registrationCloses++ },
                        onFinished = { finishes++ },
                    )
                    operation.attach(AutoCloseable { attachmentCloses++ })
                }
            }

            operation.succeed(7) shouldBe true
            operation.succeed(8) shouldBe false
            result.await().getOrThrow() shouldBe 7
            registrationCloses shouldBe 1
            attachmentCloses shouldBe 1
            finishes shouldBe 1
        }
    }

    "cancellation closes callback state and rejects a late value" {
        runTest {
            var registrationCloses = 0
            var undelivered: Int? = null
            lateinit var operation: NativeCallbackOperation<Int>
            val result = async(start = CoroutineStart.UNDISPATCHED) {
                suspendCancellableCoroutine<Result<Int>> { continuation ->
                    operation = NativeCallbackOperation(
                        continuation = continuation,
                        closeRegistration = { registrationCloses++ },
                    )
                }
            }

            result.cancelAndJoin()
            operation.succeed(9) { undelivered = it } shouldBe false
            registrationCloses shouldBe 1
            undelivered shouldBe 9
        }
    }

    "an attachment added after cancellation is closed immediately" {
        runTest {
            var attachmentCloses = 0
            lateinit var operation: NativeCallbackOperation<Unit>
            val result = async(start = CoroutineStart.UNDISPATCHED) {
                suspendCancellableCoroutine<Result<Unit>> { continuation ->
                    operation = NativeCallbackOperation(
                        continuation = continuation,
                        closeRegistration = {},
                    )
                }
            }

            result.cancelAndJoin()
            operation.attach(AutoCloseable { attachmentCloses++ }) shouldBe false
            attachmentCloses shouldBe 1
        }
    }
})
```

- [ ] **Step 3: Vérifier que les tests échouent parce que la primitive n'existe pas**

Run:

```bash
rtk ./gradlew :wgpu4k:jvmTest --tests io.ygdrasil.webgpu.internal.NativeCallbackOperationTest
```

Expected: `FAILED` avec `Unresolved reference 'NativeCallbackOperation'`.

- [ ] **Step 4: Implémenter la primitive minimale**

Créer `wgpu4k/src/commonNativeMain/kotlin/internal/NativeCallbackOperation.kt` :

```kotlin
@file:OptIn(
    kotlinx.coroutines.InternalCoroutinesApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)

package io.ygdrasil.webgpu.internal

import kotlinx.coroutines.CancellableContinuation
import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.AtomicReference

internal class NativeCallbackOperation<T>(
    private val continuation: CancellableContinuation<Result<T>>,
    private val closeRegistration: () -> Unit,
    private val onFinished: () -> Unit = {},
) {
    private val finished = AtomicBoolean(false)
    private val attachment = AtomicReference<AutoCloseable?>(null)

    internal val isFinished: Boolean
        get() = finished.load()

    init {
        continuation.invokeOnCancellation {
            if (finished.compareAndSet(expectedValue = false, newValue = true)) cleanup()
        }
    }

    internal fun attach(value: AutoCloseable): Boolean {
        check(attachment.compareAndSet(expectedValue = null, newValue = value)) {
            "Native callback operation already has an attachment"
        }
        if (finished.load()) {
            attachment.exchange(null)?.close()
            return false
        }
        return true
    }

    internal fun succeed(value: T, onUndelivered: (T) -> Unit = {}): Boolean =
        complete(Result.success(value), onUndelivered)

    internal fun fail(error: Throwable): Boolean = complete(Result.failure(error))

    private fun complete(result: Result<T>, onUndelivered: (T) -> Unit = {}): Boolean {
        if (!finished.compareAndSet(expectedValue = false, newValue = true)) {
            result.getOrNull()?.let(onUndelivered)
            return false
        }
        cleanup()
        val token = continuation.tryResume(result, idempotent = null) { _, rejected, _ ->
            rejected.getOrNull()?.let(onUndelivered)
        }
        if (token == null) {
            result.getOrNull()?.let(onUndelivered)
            return false
        }
        continuation.completeResume(token)
        return true
    }

    private fun cleanup() {
        try {
            attachment.exchange(null)?.close()
        } finally {
            try {
                closeRegistration()
            } finally {
                onFinished()
            }
        }
    }
}
```

- [ ] **Step 5: Vérifier les tests verts**

Run:

```bash
rtk ./gradlew :wgpu4k:jvmTest --tests io.ygdrasil.webgpu.internal.NativeCallbackOperationTest
```

Expected: `3 tests completed, 3 passed`.

- [ ] **Step 6: Commit**

```bash
rtk git add gradle/libs.versions.toml wgpu4k/build.gradle.kts wgpu4k/src/commonNativeMain/kotlin/internal/NativeCallbackOperation.kt wgpu4k/src/jvmTest/kotlin/internal/NativeCallbackOperationTest.kt
rtk git commit -m "test: add cancellable native callback operation"
```

---

### Task 2: Pump par device et fallback de resource native

**Files:**
- Create: `wgpu4k/src/commonNativeMain/kotlin/internal/DeviceEventPump.kt`
- Test: `wgpu4k/src/jvmTest/kotlin/internal/DeviceEventPumpTest.kt`

**Interfaces:**
- Consumes: `NativeCallbackOperation<T>` de Task 1.
- Produces: `DeviceEventPump.tryRegister(Boolean, (Throwable) -> Unit): Ticket?`, `Ticket.runNativeCall`, `DeviceEventPump.runResourceTask`, `DeviceEventPump.close`.

- [ ] **Step 1: Écrire les tests rouges du pump**

Créer `wgpu4k/src/jvmTest/kotlin/internal/DeviceEventPumpTest.kt` avec les cas suivants :

```kotlin
package io.ygdrasil.webgpu.internal

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.async
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class DeviceEventPumpTest : FreeSpec({
    "idle pump does not poll and concurrent tickets share one loop" {
        runTest {
            var polls = 0
            var retains = 0
            var releases = 0
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = { polls++ },
                retain = { retains++ },
                release = { releases++ },
            )
            runCurrent()
            polls shouldBe 0
            retains shouldBe 1

            val firstTicket = pump.tryRegister(needsPolling = true) {}
            val secondTicket = pump.tryRegister(needsPolling = true) {}
            runCurrent()
            polls shouldBe 1
            firstTicket!!.close()
            advanceTimeBy(1)
            runCurrent()
            polls shouldBe 2
            secondTicket!!.close()
            advanceTimeBy(1)
            runCurrent()
            polls shouldBe 2

            pump.close()
            runCurrent()
            releases shouldBe 1
        }
    }

    "one device close failure is delivered to every pending operation" {
        runTest {
            val failures = mutableListOf<String>()
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = {}, retain = {}, release = {},
            )
            pump.tryRegister(true) { failures += it.message.orEmpty() }
            pump.tryRegister(false) { failures += it.message.orEmpty() }

            pump.close()
            failures shouldBe listOf("Device is closed", "Device is closed")
            pump.tryRegister(true) {} shouldBe null
        }
    }

    "a poll failure terminates pending operations instead of hanging" {
        runTest {
            var failure: Throwable? = null
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = { error("device lost") },
                retain = {},
                release = {},
            )
            pump.tryRegister(needsPolling = true) { failure = it }

            runCurrent()
            failure?.message shouldBe "Device event pump failed"
            failure?.cause?.message shouldBe "device lost"
            pump.tryRegister(needsPolling = true) {} shouldBe null
        }
    }

    "resource tasks map synchronous success and failure to Result" {
        runTest {
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = {}, retain = {}, release = {},
            )
            val success = async {
                pump.runResourceTask(create = { "pipeline" }, dispose = {})
            }
            runCurrent()
            success.await().getOrThrow() shouldBe "pipeline"

            val failure = async {
                pump.runResourceTask<String>(
                    create = { error("creation failed") },
                    dispose = {},
                )
            }
            runCurrent()
            failure.await().exceptionOrNull()?.message shouldBe "creation failed"

            pump.close()
            runCurrent()
        }
    }

    "base native reference outlives an active downcall" {
        val entered = CountDownLatch(1)
        val leave = CountDownLatch(1)
        val released = CountDownLatch(1)
        val releases = AtomicInteger(0)
        val executor = Executors.newSingleThreadExecutor()
        val pump = DeviceEventPump(
            poll = {}, retain = {}, release = {
                releases.incrementAndGet()
                released.countDown()
            },
        )
        val ticket = pump.tryRegister(false) {}!!
        val future = executor.submit {
            ticket.runNativeCall {
                entered.countDown()
                leave.await(5, TimeUnit.SECONDS)
            }
        }
        entered.await(5, TimeUnit.SECONDS) shouldBe true

        pump.close()
        releases.get() shouldBe 0
        leave.countDown()
        future.get(5, TimeUnit.SECONDS) shouldBe true
        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS) shouldBe true
        released.await(5, TimeUnit.SECONDS) shouldBe true
        releases.get() shouldBe 1
    }
})
```

- [ ] **Step 2: Vérifier l'échec initial**

Run:

```bash
rtk ./gradlew :wgpu4k:jvmTest --tests io.ygdrasil.webgpu.internal.DeviceEventPumpTest
```

Expected: `FAILED` avec `Unresolved reference 'DeviceEventPump'`.

- [ ] **Step 3: Implémenter `DeviceEventPump`**

Créer `wgpu4k/src/commonNativeMain/kotlin/internal/DeviceEventPump.kt` avec cette structure exacte :

```kotlin
@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.webgpu.internal

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.AtomicReference
import kotlin.coroutines.coroutineContext

internal class DeviceEventPump(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val poll: () -> Unit,
    retain: () -> Unit,
    private val release: () -> Unit,
    private val pollDelayMillis: Long = 1L,
) : AutoCloseable {
    private data class Pending(
        val needsPolling: Boolean,
        val onTerminated: (Throwable) -> Unit,
    )

    private data class State(
        val closed: Boolean = false,
        val nextId: Long = 1L,
        val pending: Map<Long, Pending> = emptyMap(),
        val activeNativeCalls: Int = 0,
    )

    private val state = AtomicReference(State())
    private val released = AtomicBoolean(false)
    private val wakeUp = Channel<Unit>(Channel.CONFLATED)
    private val scopeJob = SupervisorJob()
    private val scope = CoroutineScope(scopeJob + dispatcher)

    init {
        retain()
        scopeJob.invokeOnCompletion { releaseIfQuiescent() }
        scope.launch { pollLoop() }
    }

    internal fun tryRegister(
        needsPolling: Boolean,
        onTerminated: (Throwable) -> Unit,
    ): Ticket? {
        while (true) {
            val current = state.load()
            if (current.closed) return null
            val id = current.nextId
            val updated = current.copy(
                nextId = id + 1,
                pending = current.pending + (id to Pending(needsPolling, onTerminated)),
            )
            if (state.compareAndSet(current, updated)) {
                wakeUp.trySend(Unit)
                return Ticket(this, id)
            }
        }
    }

    internal fun launch(block: suspend () -> Unit): Job? {
        if (state.load().closed) return null
        val job = scope.launch { block() }
        if (state.load().closed) job.cancel()
        return job
    }

    override fun close() {
        terminate(IllegalStateException("Device is closed"))
    }

    private suspend fun pollLoop() {
        while (coroutineContext.isActive) {
            val current = state.load()
            if (current.closed) return
            if (current.pending.values.none(Pending::needsPolling)) {
                if (wakeUp.receiveCatching().getOrNull() == null) return
                continue
            }
            try {
                poll()
            } catch (failure: Throwable) {
                terminate(IllegalStateException("Device event pump failed", failure))
                return
            }
            delay(pollDelayMillis)
        }
    }

    private fun terminate(cause: Throwable) {
        var callbacks: List<(Throwable) -> Unit> = emptyList()
        while (true) {
            val current = state.load()
            if (current.closed) return
            callbacks = current.pending.values.map(Pending::onTerminated)
            if (state.compareAndSet(current, current.copy(closed = true, pending = emptyMap()))) break
        }
        callbacks.forEach { it(cause) }
        wakeUp.close()
        scope.cancel()
        releaseIfQuiescent()
    }

    private fun remove(id: Long) {
        while (true) {
            val current = state.load()
            if (id !in current.pending) return
            if (state.compareAndSet(current, current.copy(pending = current.pending - id))) {
                wakeUp.trySend(Unit)
                return
            }
        }
    }

    private fun beginNativeCall(id: Long): Boolean {
        while (true) {
            val current = state.load()
            if (current.closed || id !in current.pending) return false
            if (state.compareAndSet(current, current.copy(activeNativeCalls = current.activeNativeCalls + 1))) {
                return true
            }
        }
    }

    private fun endNativeCall() {
        while (true) {
            val current = state.load()
            check(current.activeNativeCalls > 0)
            if (state.compareAndSet(current, current.copy(activeNativeCalls = current.activeNativeCalls - 1))) {
                releaseIfQuiescent()
                return
            }
        }
    }

    private fun releaseIfQuiescent() {
        val current = state.load()
        if (current.closed && current.activeNativeCalls == 0 && scopeJob.isCompleted) {
            if (released.compareAndSet(expectedValue = false, newValue = true)) release()
        }
    }

    internal class Ticket internal constructor(
        private val owner: DeviceEventPump,
        private val id: Long,
    ) : AutoCloseable {
        private val closed = AtomicBoolean(false)

        internal fun runNativeCall(block: () -> Unit): Boolean {
            if (!owner.beginNativeCall(id)) return false
            return try {
                block()
                true
            } finally {
                owner.endNativeCall()
            }
        }

        override fun close() {
            if (closed.compareAndSet(expectedValue = false, newValue = true)) owner.remove(id)
        }
    }
}

internal suspend fun <T> DeviceEventPump.runResourceTask(
    create: () -> T,
    dispose: (T) -> Unit,
): Result<T> = suspendCancellableCoroutine { continuation ->
    val operation = NativeCallbackOperation(continuation, closeRegistration = {})
    val ticket = tryRegister(needsPolling = false) { operation.fail(it) }
    if (ticket == null) {
        operation.fail(IllegalStateException("Device is closed"))
        return@suspendCancellableCoroutine
    }
    operation.attach(ticket)
    if (operation.isFinished) return@suspendCancellableCoroutine
    if (launch {
            var created: Result<T>? = null
            val started = ticket.runNativeCall { created = runCatching(create) }
            if (!started) {
                operation.fail(IllegalStateException("Device is closed"))
            } else {
                created!!.fold(
                    onSuccess = { operation.succeed(it, dispose) },
                    onFailure = operation::fail,
                )
            }
        } == null
    ) {
        operation.fail(IllegalStateException("Device is closed"))
    }
}
```

- [ ] **Step 4: Lancer les deux suites internes**

```bash
rtk ./gradlew :wgpu4k:jvmTest --tests 'io.ygdrasil.webgpu.internal.*'
```

Expected: les 8 tests passent, sans thread restant ni timeout.

- [ ] **Step 5: Commit**

```bash
rtk git add wgpu4k/src/commonNativeMain/kotlin/internal/DeviceEventPump.kt wgpu4k/src/jvmTest/kotlin/internal/DeviceEventPumpTest.kt
rtk git commit -m "feat: add per-device native event pump"
```

---

### Task 3: Migration v29 et branchement de tous les callbacks wgpu4k

**Files:**
- Modify: `settings.gradle.kts`
- Modify: `generator/build.gradle.kts`
- Modify: `gradle/libs.versions.toml`
- Modify: tous les `.kt` sous `wgpu4k/src/commonNativeMain`, `wgpu4k/src/nativeMain`, `wgpu4k/src/jvmMain` et `wgpu4k/src/jvmTest` qui importent `ffi.*`
- Modify: les huit fichiers fonctionnels listés dans la section File Structure

Manifest exact des fichiers dont l'import `ffi.*` doit être migré dans cette tâche :

```text
wgpu4k/src/jvmTest/kotlin/WGPUTest.kt
wgpu4k/src/jvmMain/kotlin/Queue.native.jvm.kt
wgpu4k/src/jvmMain/kotlin/mapper/NativeAdress.jvm.kt
wgpu4k/src/nativeMain/kotlin/Queue.native.native.kt
wgpu4k/src/nativeMain/kotlin/mapper/NativeAdress.native.kt
wgpu4k/src/commonNativeMain/kotlin/Adapter.native.kt
wgpu4k/src/commonNativeMain/kotlin/BindGroup.native.kt
wgpu4k/src/commonNativeMain/kotlin/BindGroupLayout.native.kt
wgpu4k/src/commonNativeMain/kotlin/Buffer.native.kt
wgpu4k/src/commonNativeMain/kotlin/CommandBuffer.native.kt
wgpu4k/src/commonNativeMain/kotlin/CommandEncoder.native.kt
wgpu4k/src/commonNativeMain/kotlin/ComputePassEncoder.native.kt
wgpu4k/src/commonNativeMain/kotlin/ComputePipeline.native.kt
wgpu4k/src/commonNativeMain/kotlin/Device.native.kt
wgpu4k/src/commonNativeMain/kotlin/Pipeline.native.kt
wgpu4k/src/commonNativeMain/kotlin/QuerySet.native.kt
wgpu4k/src/commonNativeMain/kotlin/Queue.native.kt
wgpu4k/src/commonNativeMain/kotlin/QueueExt.native.kt
wgpu4k/src/commonNativeMain/kotlin/RenderBundle.native.kt
wgpu4k/src/commonNativeMain/kotlin/RenderBundleEncoder.native.kt
wgpu4k/src/commonNativeMain/kotlin/RenderPassEncoder.native.kt
wgpu4k/src/commonNativeMain/kotlin/Sampler.native.kt
wgpu4k/src/commonNativeMain/kotlin/ShaderModule.native.kt
wgpu4k/src/commonNativeMain/kotlin/Surface.native.kt
wgpu4k/src/commonNativeMain/kotlin/Texture.native.kt
wgpu4k/src/commonNativeMain/kotlin/TextureView.native.kt
wgpu4k/src/commonNativeMain/kotlin/WGPU.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/BindGroupDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/BindGroupLayoutDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/BufferDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/Color.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/ComputePassDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/ComputePipelineDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/DeviceDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/InstanceDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/ListInt.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/NativeAdress.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/Origin3D.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/PipelineLayoutDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/QuerySetDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/RenderBundleDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/RenderBundleEncoderDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/RenderPassDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/RenderPipelineDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/SamplerDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/ShaderModuleDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/Size3D.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/String.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/TexelCopyBufferInfo.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/TexelCopyTextureInfo.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/TextureDataLayout.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/TextureDescriptor.kt
wgpu4k/src/commonNativeMain/kotlin/mapper/TextureViewDescriptor.kt
```

**Interfaces:**
- Consumes: `NativeCallbackOperation`, `DeviceEventPump`, factories `register`/`allocate` de wgpu4k-native v29.
- Produces: callbacks ponctuels sûrs, `Device` idempotent, pump queue/map, ownership de `onUncapturedError`, fallbacks de pipeline et erreur contrôlée de compilation info.

- [ ] **Step 1: Basculer les repositories et la version**

Dans `settings.gradle.kts`, ajouter dans `dependencyResolutionManagement.repositories` :

```kotlin
maven("https://central.sonatype.com/repository/maven-snapshots/")
```

Dans `generator/build.gradle.kts`, ajouter la même ligne à `repositories`.

Dans `gradle/libs.versions.toml`, remplacer :

```toml
wgpu4k-native = "v27.0.4"
```

par :

```toml
wgpu4k-native = "v29.0.0-SNAPSHOT"
```

- [ ] **Step 2: Vérifier que v29 expose les incompatibilités attendues**

```bash
rtk ./gradlew :wgpu4k:compileKotlinJvm --refresh-dependencies
```

Expected: échec avec des imports `ffi.*` non résolus et les anciennes factories de callback.

- [ ] **Step 3: Migrer mécaniquement les imports hors Android**

Remplacer chaque import `ffi.X` par `io.ygdrasil.kffi.X` uniquement dans les source sets hors Android. Vérifier ensuite :

```bash
rtk rg -n '^import ffi\.' wgpu4k --glob '*.kt' --glob '!**/androidMain/**' --glob '!**/androidNativeMain/**'
```

Expected: aucune sortie.

Ne pas modifier les occurrences encore présentes dans les source sets Android.

Ajouter l'opt-in suivant en tête de `Device.native.kt` et `Buffer.native.kt` :

```kotlin
@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)
```

Ajouter les deux opt-ins en tête de `Adapter.native.kt` :

```kotlin
@file:OptIn(
    kotlinx.coroutines.InternalCoroutinesApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)
```

Dans les fichiers de callback concernés, importer explicitement les extensions v29 :

```kotlin
import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.wgpu.allocate
import io.ygdrasil.wgpu.register
```

- [ ] **Step 4: Brancher le pump sur `Device`, `Queue` et `Buffer`**

Dans `Device.native.kt` :

- ajouter un paramètre constructeur `uncapturedErrorRegistration: AutoCloseable? = null` ;
- créer `internal val eventPump` avec `wgpuDevicePoll(handler, 0u, null)`, `wgpuDeviceAddRef` et `wgpuDeviceRelease` ;
- créer `private val closed = AtomicBoolean(false)` et `internal val isClosed` ;
- construire la queue avec `Queue(queueHandle, this, "")` ;
- implémenter `close()` par CAS, fermeture de la registration répétée, fermeture du pump, puis release du handle principal ;
- remplacer les deux créations async de pipeline par :

Remplacer la déclaration de classe par :

```kotlin
actual class Device(
    val handler: WGPUDevice,
    label: String,
    private val uncapturedErrorRegistration: AutoCloseable? = null,
) : GPUDevice {
```

Ajouter ces propriétés au début du corps de classe et remplacer la propriété `queue` existante :

```kotlin
    private val closed = AtomicBoolean(false)

    internal val isClosed: Boolean
        get() = closed.load()

    internal val eventPump = DeviceEventPump(
        poll = {
            check(wgpuDevicePoll(handler, 0u, null) == WGPUStatus_Success) {
                "wgpuDevicePoll failed"
            }
        },
        retain = { wgpuDeviceAddRef(handler) },
        release = { wgpuDeviceRelease(handler) },
    )

    actual override val queue: GPUQueue by lazy {
        Queue(
            handler = wgpuDeviceGetQueue(handler) ?: error("fail to get device queue"),
            device = this,
            label = "",
        )
    }
```

Remplacer uniquement la méthode `close()` existante par :

```kotlin
actual override fun close() {
    if (!closed.compareAndSet(expectedValue = false, newValue = true)) return
    uncapturedErrorRegistration?.close()
    eventPump.close()
    wgpuDeviceRelease(handler)
}
```

```kotlin
actual override suspend fun createComputePipelineAsync(
    descriptor: GPUComputePipelineDescriptor,
): Result<GPUComputePipeline> = eventPump.runResourceTask(
    create = { createComputePipeline(descriptor) },
    dispose = GPUComputePipeline::close,
)

actual override suspend fun createRenderPipelineAsync(
    descriptor: GPURenderPipelineDescriptor,
): Result<GPURenderPipeline> = eventPump.runResourceTask(
    create = { createRenderPipeline(descriptor) },
    dispose = GPURenderPipeline::close,
)
```

Réécrire `popErrorScope()` comme suit :

```kotlin
actual override suspend fun popErrorScope(): Result<GPUError?> =
    suspendCancellableCoroutine { continuation ->
        lateinit var operation: NativeCallbackOperation<GPUError?>
        val registration = WGPUPopErrorScopeCallback.register(
            policy = CallbackPolicy.ONCE,
            callback = WGPUPopErrorScopeCallback { status, type, message, _ ->
                val text = message.data?.toKString(message.length)
                if (status == WGPUPopErrorScopeStatus_Success) {
                    operation.succeed(errorOf(type, text))
                } else {
                    operation.fail(
                        IllegalStateException(
                            "popErrorScope failed with status: $status and message: $text"
                        )
                    )
                }
            },
        )
        operation = NativeCallbackOperation(continuation, registration::close)
        val ticket = eventPump.tryRegister(needsPolling = false) { operation.fail(it) }
        if (ticket == null) {
            operation.fail(IllegalStateException("Device is closed"))
            return@suspendCancellableCoroutine
        }
        operation.attach(ticket)
        val started = ticket.runNativeCall {
            memoryScope { scope ->
                val callbackInfo = WGPUPopErrorScopeCallbackInfo.allocate(
                    scope,
                    WGPUCallbackMode_AllowSpontaneous,
                    registration,
                )
                wgpuDevicePopErrorScope(handler, callbackInfo)
            }
        }
        if (!started) operation.fail(IllegalStateException("Device is closed"))
    }
```

Dans `Queue.native.kt`, changer le constructeur en :

```kotlin
actual class Queue(
    val handler: WGPUQueue,
    private val device: Device,
    label: String,
) : GPUQueue
```

Remplacer `onSubmittedWorkDone()` par :

```kotlin
actual override suspend fun onSubmittedWorkDone(): Result<Unit> =
    suspendCancellableCoroutine { continuation ->
        lateinit var operation: NativeCallbackOperation<Unit>
        val registration = WGPUQueueWorkDoneCallback.register(
            policy = CallbackPolicy.ONCE,
            callback = WGPUQueueWorkDoneCallback { status, message, _ ->
                if (status == WGPUQueueWorkDoneStatus_Success) {
                    operation.succeed(Unit)
                } else {
                    operation.fail(
                        IllegalStateException(
                            "onSubmittedWorkDone failed with status: $status and message: ${message.data?.toKString(message.length)}"
                        )
                    )
                }
            },
        )
        operation = NativeCallbackOperation(continuation, registration::close)
        val ticket = device.eventPump.tryRegister(needsPolling = true) { operation.fail(it) }
        if (ticket == null) {
            operation.fail(IllegalStateException("Device is closed"))
            return@suspendCancellableCoroutine
        }
        operation.attach(ticket)
        val started = ticket.runNativeCall {
            memoryScope { scope ->
                val callbackInfo = WGPUQueueWorkDoneCallbackInfo.allocate(
                    scope,
                    WGPUCallbackMode_AllowProcessEvents,
                    registration,
                )
                wgpuQueueOnSubmittedWorkDone(handler, callbackInfo)
            }
        }
        if (!started) operation.fail(IllegalStateException("Device is closed"))
    }
```

Dans `Buffer.native.kt`, supprimer le poll bloquant et ajouter ces champs :

```kotlin
private val closed = AtomicBoolean(false)
private val pendingMap = AtomicReference<NativeCallbackOperation<Unit>?>(null)
```

Remplacer `mapAsync()` et `close()` par :

```kotlin
actual override suspend fun mapAsync(
    mode: GPUMapMode,
    offset: GPUSize64,
    size: GPUSize64?,
): Result<Unit> {
    if (closed.load()) return Result.failure(IllegalStateException("Buffer is closed"))
    val mappedSize = size ?: (this.size - offset)
    return suspendCancellableCoroutine { continuation ->
        lateinit var operation: NativeCallbackOperation<Unit>
        val registration = WGPUBufferMapCallback.register(
            policy = CallbackPolicy.ONCE,
            callback = WGPUBufferMapCallback { status, message, _ ->
                if (status == WGPUMapAsyncStatus_Success) {
                    operation.succeed(Unit)
                } else {
                    operation.fail(
                        IllegalStateException(
                            "mapAsync failed with status: $status and message: ${message.data?.toKString(message.length)}"
                        )
                    )
                }
            },
        )
        val operationHolder = AtomicReference<NativeCallbackOperation<Unit>?>(null)
        operation = NativeCallbackOperation(
            continuation = continuation,
            closeRegistration = registration::close,
            onFinished = {
                operationHolder.load()?.let { pendingMap.compareAndSet(it, null) }
            },
        )
        operationHolder.store(operation)

        if (closed.load()) {
            operation.fail(IllegalStateException("Buffer is closed"))
            return@suspendCancellableCoroutine
        }
        if (!pendingMap.compareAndSet(expectedValue = null, newValue = operation)) {
            operation.fail(IllegalStateException("Buffer map is already pending"))
            return@suspendCancellableCoroutine
        }
        if (operation.isFinished) {
            pendingMap.compareAndSet(operation, null)
            return@suspendCancellableCoroutine
        }

        val ticket = device.eventPump.tryRegister(needsPolling = true) { operation.fail(it) }
        if (ticket == null) {
            operation.fail(IllegalStateException("Device is closed"))
            return@suspendCancellableCoroutine
        }
        operation.attach(ticket)
        val started = ticket.runNativeCall {
            memoryScope { scope ->
                val callbackInfo = WGPUBufferMapCallbackInfo.allocate(
                    scope,
                    WGPUCallbackMode_AllowProcessEvents,
                    registration,
                )
                wgpuBufferMapAsync(handler, mode.value, offset, mappedSize, callbackInfo)
            }
        }
        if (!started) operation.fail(IllegalStateException("Device is closed"))
    }
}

actual override fun close() {
    if (!closed.compareAndSet(expectedValue = false, newValue = true)) return
    pendingMap.load()?.fail(IllegalStateException("Buffer is closed"))
    wgpuBufferRelease(handler)
}
```

- [ ] **Step 5: Migrer les callbacks inline et le callback répétitif**

Dans `mapper/DeviceDescriptor.kt`, remplacer le mapping direct par :

```kotlin
internal data class MappedDeviceDescriptor(
    val value: WGPUDeviceDescriptor,
    val uncapturedErrorRegistration: CallbackRegistration<WGPUUncapturedErrorCallback>?,
)

internal fun dispatchUncapturedError(
    userCallback: GPUUncapturedErrorCallback,
    type: WGPUErrorType,
    message: String?,
) {
    errorOf(type, message)?.let(userCallback::onUncapturedError)
}

internal fun registerUncapturedError(
    userCallback: GPUUncapturedErrorCallback,
): CallbackRegistration<WGPUUncapturedErrorCallback> =
    WGPUUncapturedErrorCallback.register(
        policy = CallbackPolicy.REPEATING,
        callback = WGPUUncapturedErrorCallback { _, type, message, _ ->
            dispatchUncapturedError(
                userCallback,
                type,
                message.data?.toKString(message.length),
            )
        },
    )

internal fun MemoryAllocator.mapDeviceDescriptor(input: GPUDeviceDescriptor): MappedDeviceDescriptor {
    val output = WGPUDeviceDescriptor.allocate(this)
    map(input.label, output.label)
    mapRequiredFeatures(input, output)
    input.requiredLimits?.let { output.requiredLimits = mapRequiredLimits(it) }
    map(input.defaultQueue, output.defaultQueue)

    val registration = input.onUncapturedError?.let(::registerUncapturedError)
    registration?.let {
        output.uncapturedErrorCallbackInfo = WGPUUncapturedErrorCallbackInfo.allocate(this, it)
    }
    return MappedDeviceDescriptor(output, registration)
}
```

Dans `Adapter.native.kt`, réécrire `requestDevice()` avec :

- `suspendCancellableCoroutine` et `WGPURequestDeviceCallback.register(ONCE)` ;
- `WGPUCallbackMode_AllowSpontaneous` ;
- un `AtomicBoolean callbackDelivered` ;
- `scope.mapDeviceDescriptor(descriptor)` et transfert de sa registration au constructeur `Device` en cas de succès ;
- `tryResume(..., onCancellation = { _, result, _ -> result.getOrNull()?.close() })` ;
- release explicite d'un handle natif reçu mais indélivrable ;
- fermeture de la registration de requête et de la registration répétée au retour du downcall si aucun callback inline n'a été livré.

Ne pas fermer la registration de requête depuis `invokeOnCancellation`: elle doit rester routable pendant le downcall inline afin de libérer un éventuel `WGPUDevice` transféré après annulation.

Le corps de méthode doit suivre cette séquence exacte :

```kotlin
actual override suspend fun requestDevice(
    descriptor: GPUDeviceDescriptor?,
): Result<GPUDevice> = suspendCancellableCoroutine { continuation ->
    val callbackClaimed = AtomicBoolean(false)
    var uncapturedErrorRegistration: CallbackRegistration<WGPUUncapturedErrorCallback>? = null
    lateinit var requestRegistration: CallbackRegistration<WGPURequestDeviceCallback>

    requestRegistration = WGPURequestDeviceCallback.register(
        policy = CallbackPolicy.ONCE,
        callback = WGPURequestDeviceCallback { status, device, message, _ ->
            if (!callbackClaimed.compareAndSet(expectedValue = false, newValue = true)) {
                device?.let(::wgpuDeviceRelease)
            } else {
                try {
                    if (status == WGPURequestDeviceStatus_Success && device != null) {
                        val ownedErrorRegistration = uncapturedErrorRegistration
                        uncapturedErrorRegistration = null
                        val wrapped = Device(
                            handler = device,
                            label = descriptor?.label.orEmpty(),
                            uncapturedErrorRegistration = ownedErrorRegistration,
                        )
                        val result = Result.success<GPUDevice>(wrapped)
                        val token = continuation.tryResume(result, idempotent = null) { _, rejected, _ ->
                            rejected.getOrNull()?.close()
                        }
                        if (token == null) wrapped.close() else continuation.completeResume(token)
                    } else {
                        device?.let(::wgpuDeviceRelease)
                        val text = message.data?.toKString(message.length)
                        val token = continuation.tryResume(
                            Result.failure<GPUDevice>(
                                IllegalStateException(
                                    "requestDevice failed with status: $status and message: $text"
                                )
                            )
                        )
                        if (token != null) continuation.completeResume(token)
                    }
                } finally {
                    requestRegistration.close()
                    uncapturedErrorRegistration?.close()
                    uncapturedErrorRegistration = null
                }
            }
        },
    )

    if (!continuation.isActive) {
        requestRegistration.close()
        return@suspendCancellableCoroutine
    }

    memoryScope { scope ->
        val mapped = descriptor?.let(scope::mapDeviceDescriptor)
        uncapturedErrorRegistration = mapped?.uncapturedErrorRegistration
        if (!continuation.isActive) {
            requestRegistration.close()
            uncapturedErrorRegistration?.close()
            uncapturedErrorRegistration = null
            return@memoryScope
        }
        val callbackInfo = WGPURequestDeviceCallbackInfo.allocate(
            scope,
            WGPUCallbackMode_AllowSpontaneous,
            requestRegistration,
        )
        wgpuAdapterRequestDevice(handler, mapped?.value, callbackInfo)
    }

    if (callbackClaimed.compareAndSet(expectedValue = false, newValue = true)) {
        requestRegistration.close()
        uncapturedErrorRegistration?.close()
        uncapturedErrorRegistration = null
        if (continuation.isActive) {
            val token = continuation.tryResume(
                Result.failure<GPUDevice>(
                    IllegalStateException("requestDevice callback was not delivered inline")
                )
            )
            if (token != null) continuation.completeResume(token)
        }
    }
}
```

Dans `WGPU.kt`, conserver l'API synchrone et remplacer `requestAdapter()` par :

```kotlin
fun requestAdapter(
    surface: NativeSurface,
    powerPreference: GPUPowerPreference? = null,
): Adapter? = memoryScope { scope ->
    val options = WGPURequestAdapterOptions.allocate(scope).apply {
        compatibleSurface = surface.handler
        powerPreference?.let { this.powerPreference = it.value }
    }
    var fetchedAdapter: WGPUAdapter? = null
    var failure: IllegalStateException? = null
    val registration = WGPURequestAdapterCallback.register(
        policy = CallbackPolicy.ONCE,
        callback = WGPURequestAdapterCallback { status, adapter, message, _ ->
            if (status == WGPURequestAdapterStatus_Success && adapter != null) {
                fetchedAdapter = adapter
            } else {
                adapter?.let(::wgpuAdapterRelease)
                failure = IllegalStateException(
                    "requestAdapter failed with status: $status and message: ${message.data?.toKString(message.length)}"
                )
            }
        },
    )
    try {
        val callbackInfo = WGPURequestAdapterCallbackInfo.allocate(
            scope,
            WGPUCallbackMode_AllowSpontaneous,
            registration,
        )
        wgpuInstanceRequestAdapter(handler, options, callbackInfo)
    } finally {
        registration.close()
    }
    failure?.let { throw it }
    fetchedAdapter?.let(::Adapter)
}
```

- [ ] **Step 6: Neutraliser les APIs natives non implémentées et le poll bloquant**

Dans `ShaderModule.native.kt`, remplacer `getCompilationInfo()` par :

```kotlin
actual override suspend fun getCompilationInfo(): Result<GPUCompilationInfo> = Result.failure(
    UnsupportedOperationException(
        "Shader compilation info is not supported by wgpu-native 29.0.0.0"
    )
)
```

Dans `DeviceExt.native.kt`, remplacer le poll bloquant par :

```kotlin
actual suspend fun GPUDevice.poll(): Result<Unit> {
    val device = this as Device
    if (device.isClosed) return Result.failure(IllegalStateException("Device is closed"))
    return device.queue.onSubmittedWorkDone()
}
```

Supprimer tous les imports et appels des trois fonctions natives non implémentées.

- [ ] **Step 7: Compiler et tester le module wgpu4k hors Android**

```bash
rtk ./gradlew :wgpu4k:jvmTest :wgpu4k:compileKotlinJvm :wgpu4k:compileKotlinMacosArm64
```

Expected: `BUILD SUCCESSFUL`; aucune tâche Android dans le graphe demandé.

- [ ] **Step 8: Auditer les callbacks et committer**

```bash
rtk rg -n 'suspendCoroutine|Callback\.allocate\(' wgpu4k/src/commonNativeMain/kotlin
rtk rg -n 'wgpuDeviceCreateComputePipelineAsync|wgpuDeviceCreateRenderPipelineAsync|wgpuShaderModuleGetCompilationInfo' wgpu4k/src/commonNativeMain/kotlin
```

Expected: aucune sortie pour les implémentations de callback et aucun appel aux fonctions non implémentées.

```bash
rtk git add settings.gradle.kts generator/build.gradle.kts gradle/libs.versions.toml wgpu4k/src/commonNativeMain wgpu4k/src/nativeMain wgpu4k/src/jvmMain wgpu4k/src/jvmTest
rtk git commit -m "fix: make native callbacks safe with wgpu4k v29"
```

---

### Task 4: Migrer les consommateurs non-Android de kffi

**Files:**
- Modify: `wgpu4k-toolkit/src/linuxMain/kotlin/glfw.linux.kt`
- Modify: `wgpu4k-toolkit/src/iosMain/kotlin/context.ios.kt`
- Modify: `wgpu4k-toolkit/src/mingwMain/kotlin/glfw.kt`
- Modify: `wgpu4k-toolkit/src/jvmMain/kotlin/glfw.kt`
- Modify: `wgpu4k-toolkit/src/macosMain/kotlin/glfw.macos.kt`
- Modify: `wgpu4k-scenes/src/commonNativeMain/kotlin/AssetManager.commonNative.kt`
- Modify: `wgpu4k-scenes/src/jvmMain/kotlin/headless/render.jvm.kt`
- Modify: `wgpu4k-scenes/src/jvmMain/kotlin/headless/HeadlessContext.jvm.kt`
- Modify: `examples/glfw/src/main/kotlin/GlfwMain.kt`

**Interfaces:**
- Consumes: packages `io.ygdrasil.kffi.*` de v29.
- Produces: consommateurs JVM/macOS compilables sans modifier les backends Android.

- [ ] **Step 1: Faire échouer les compilations consommatrices avant migration**

```bash
rtk ./gradlew :wgpu4k-toolkit:compileKotlinJvm :wgpu4k-scenes:compileKotlinJvm :examples:glfw:compileKotlin
```

Expected: échecs `Unresolved reference 'ffi'` dans les fichiers listés.

- [ ] **Step 2: Remplacer uniquement les imports listés**

Appliquer la correspondance exacte suivante dans les neuf fichiers :

```text
ffi.LibraryLoader -> io.ygdrasil.kffi.LibraryLoader
ffi.NativeAddress -> io.ygdrasil.kffi.NativeAddress
ffi.globalMemory -> io.ygdrasil.kffi.globalMemory
```

Ne toucher à aucun source set Android.

- [ ] **Step 3: Vérifier les compilations JVM et macOS des consommateurs**

```bash
rtk ./gradlew :wgpu4k-toolkit:compileKotlinJvm :wgpu4k-toolkit:compileKotlinMacosArm64 :wgpu4k-scenes:compileKotlinJvm :wgpu4k-scenes:compileKotlinMacosArm64 :examples:glfw:compileKotlin
```

Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 4: Vérifier que seuls les imports Android historiques restent**

```bash
rtk rg -n '^import ffi\.' --glob '*.kt'
```

Expected: sorties uniquement sous `androidMain` ou `androidNativeMain`.

- [ ] **Step 5: Commit**

```bash
rtk git add wgpu4k-toolkit/src wgpu4k-scenes/src examples/glfw/src
rtk git commit -m "refactor: migrate non-Android consumers to kffi v29"
```

---

### Task 5: Vérification finale des courses et du périmètre

**Files:**
- Modify: `wgpu4k/src/jvmTest/kotlin/internal/DeviceEventPumpTest.kt`
- Create: `wgpu4k/src/jvmTest/kotlin/mapper/UncapturedErrorCallbackTest.kt`
- No Android files.

**Interfaces:**
- Consumes: toutes les tâches précédentes.
- Produces: preuve reproductible des critères d'acceptation hors Android.

- [ ] **Step 1: Ajouter le test de resource créée après fermeture**

Ajouter les imports suivants à `DeviceEventPumpTest` :

```kotlin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
```

Ajouter ensuite ce cas au `FreeSpec` :

```kotlin
"a resource created after device close is disposed" {
    val entered = CountDownLatch(1)
    val proceed = CountDownLatch(1)
    val released = CountDownLatch(1)
    val disposed = AtomicInteger(0)
    val releases = AtomicInteger(0)
    val executor = Executors.newSingleThreadExecutor()
    val dispatcher = executor.asCoroutineDispatcher()
    try {
        withTimeout(5_000) {
            coroutineScope {
                val pump = DeviceEventPump(
                    dispatcher = dispatcher,
                    poll = {},
                    retain = {},
                    release = {
                        releases.incrementAndGet()
                        released.countDown()
                    },
                )
                val result = async {
                    pump.runResourceTask(
                        create = {
                            entered.countDown()
                            check(proceed.await(5, TimeUnit.SECONDS))
                            Any()
                        },
                        dispose = { disposed.incrementAndGet() },
                    )
                }

                try {
                    withContext(Dispatchers.IO) {
                        entered.await(5, TimeUnit.SECONDS)
                    } shouldBe true
                    pump.close()
                    result.await().exceptionOrNull()?.message shouldBe "Device is closed"
                } finally {
                    proceed.countDown()
                }

                withContext(Dispatchers.IO) {
                    released.await(5, TimeUnit.SECONDS)
                } shouldBe true
                disposed.get() shouldBe 1
                releases.get() shouldBe 1
            }
        }
    } finally {
        dispatcher.close()
        executor.shutdownNow()
        executor.awaitTermination(5, TimeUnit.SECONDS)
    }
}
```

- [ ] **Step 2: Tester la répétition et la fermeture de `onUncapturedError`**

Créer `wgpu4k/src/jvmTest/kotlin/mapper/UncapturedErrorCallbackTest.kt` :

```kotlin
package io.ygdrasil.webgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.webgpu.GPUUncapturedErrorCallback
import io.ygdrasil.wgpu.WGPUErrorType_Internal
import io.ygdrasil.wgpu.WGPUErrorType_Validation

class UncapturedErrorCallbackTest : FreeSpec({
    "repeating callback dispatches every error and its registration closes" {
        val messages = mutableListOf<String>()
        val callback = GPUUncapturedErrorCallback { messages += it.message }

        dispatchUncapturedError(callback, WGPUErrorType_Validation, "first")
        dispatchUncapturedError(callback, WGPUErrorType_Internal, "second")
        messages shouldBe listOf("first", "second")

        val registration = registerUncapturedError(callback)
        registration.policy shouldBe CallbackPolicy.REPEATING
        registration.isClosed shouldBe false
        registration.close()
        registration.isClosed shouldBe true
    }
})
```

- [ ] **Step 3: Exécuter toutes les suites JVM pertinentes**

```bash
rtk ./gradlew :wgpu4k:jvmTest :wgpu4k-toolkit:jvmTest
```

Expected: `BUILD SUCCESSFUL`, avec les tests de callback et de pump tous verts.

- [ ] **Step 4: Exécuter les compilations hôte hors Android**

```bash
rtk ./gradlew :wgpu4k:compileKotlinJvm :wgpu4k:compileKotlinMacosArm64 :wgpu4k-toolkit:compileKotlinJvm :wgpu4k-toolkit:compileKotlinMacosArm64 :wgpu4k-scenes:compileKotlinJvm :wgpu4k-scenes:compileKotlinMacosArm64 :examples:glfw:compileKotlin
```

Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 5: Audit statique final**

```bash
rtk rg -n 'suspendCoroutine|Callback\.allocate\(' wgpu4k/src/commonNativeMain/kotlin
rtk rg -n 'wgpuDevicePoll\([^\n]*true|wgpuDevicePoll\([^\n]*1u' wgpu4k/src/commonNativeMain/kotlin
rtk rg -n 'wgpuDeviceCreateComputePipelineAsync|wgpuDeviceCreateRenderPipelineAsync|wgpuShaderModuleGetCompilationInfo' wgpu4k/src/commonNativeMain/kotlin
rtk rg -n '^import ffi\.' --glob '*.kt' --glob '!**/androidMain/**' --glob '!**/androidNativeMain/**'
rtk git diff --check
```

Expected: aucune occurrence interdite et aucune erreur de whitespace.

- [ ] **Step 6: Commit des tests finaux**

```bash
rtk git add wgpu4k/src/jvmTest/kotlin/internal/DeviceEventPumpTest.kt wgpu4k/src/jvmTest/kotlin/mapper/UncapturedErrorCallbackTest.kt
rtk git commit -m "test: cover callback cleanup and device close races"
```

- [ ] **Step 7: Vérifier l'état Git**

```bash
rtk git status --short
rtk git log -5 --oneline
```

Expected: worktree propre et commits des Tasks 1 à 5 visibles.

@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.webgpu.internal

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class BufferLifecycleTest : FreeSpec({
    "close after map admission prevents the native downcall and fails the map once" {
        val releases = AtomicInteger(0)
        val failures = AtomicInteger(0)
        val downcalls = AtomicInteger(0)
        val lifecycle = BufferLifecycle(release = { releases.incrementAndGet() })
        val ticket = lifecycle.registerMap { failures.incrementAndGet() }.getOrThrow()

        lifecycle.close()
        lifecycle.close()

        ticket.runNativeCall { downcalls.incrementAndGet() } shouldBe false
        failures.get() shouldBe 1
        downcalls.get() shouldBe 0
        releases.get() shouldBe 1
    }

    "close during a map downcall fails the map and defers release until the downcall returns" {
        val entered = CountDownLatch(1)
        val leave = CountDownLatch(1)
        val releases = AtomicInteger(0)
        val failures = AtomicInteger(0)
        val executor = Executors.newSingleThreadExecutor()
        val lifecycle = BufferLifecycle(release = { releases.incrementAndGet() })
        val ticket = lifecycle.registerMap { failures.incrementAndGet() }.getOrThrow()
        val future = executor.submit<Boolean> {
            ticket.runNativeCall {
                entered.countDown()
                leave.await(5, TimeUnit.SECONDS)
            }
        }
        entered.await(5, TimeUnit.SECONDS) shouldBe true

        lifecycle.close()
        failures.get() shouldBe 1
        releases.get() shouldBe 0
        ticket.runNativeCall { error("downcall after close") } shouldBe false

        leave.countDown()
        future.get(5, TimeUnit.SECONDS) shouldBe true
        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS) shouldBe true
        releases.get() shouldBe 1
    }

    "only one map can be pending and registration after close fails distinctly" {
        val lifecycle = BufferLifecycle(release = {})
        val ticket = lifecycle.registerMap {}.getOrThrow()

        lifecycle.registerMap {}.exceptionOrNull()?.message shouldBe "Buffer map is already pending"
        ticket.close()
        lifecycle.registerMap {}.isSuccess shouldBe true

        lifecycle.close()
        lifecycle.registerMap {}.exceptionOrNull()?.message shouldBe "Buffer is closed"
    }
})

@file:OptIn(io.ygdrasil.webgpu.WGPULowLevelApi::class)

package io.ygdrasil.webgpu

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ygdrasil.kffi.NativeAddress
import io.ygdrasil.wgpu.WGPUQueue
import java.lang.foreign.MemorySegment
import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.startCoroutineUninterceptedOrReturn
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility

class NativeConstructorCompatibilityTest : FreeSpec({
    "legacy queue constructor fails work-done immediately without a Device" {
        val queue = Queue(
            WGPUQueue(NativeAddress(MemorySegment.NULL)),
            "legacy",
        )
        val block: suspend () -> Result<Unit> = queue::onSubmittedWorkDone
        val returned = block.startCoroutineUninterceptedOrReturn(
            object : Continuation<Result<Unit>> {
                override val context = EmptyCoroutineContext

                override fun resumeWith(result: Result<Result<Unit>>) {
                    error("legacy Queue unexpectedly suspended: $result")
                }
            }
        )

        returned shouldNotBe COROUTINE_SUSPENDED
        (returned as Result<*>).exceptionOrNull()?.message shouldBe
            "onSubmittedWorkDone requires a Queue created with its Device"
    }

    "legacy low-level two-argument constructors remain in the JVM ABI" {
        fun Class<*>.hasLegacyConstructor(): Boolean = declaredConstructors.any { constructor ->
            val parameters = constructor.parameterTypes
            parameters.size == 3 &&
                parameters[0].name == "io.ygdrasil.kffi.JvmNativeAddress" &&
                parameters[1] == String::class.java &&
                parameters[2].name == "kotlin.jvm.internal.DefaultConstructorMarker"
        }

        Device::class.java.hasLegacyConstructor() shouldBe true
        Queue::class.java.hasLegacyConstructor() shouldBe true
    }

    "owned three-argument constructors are internal implementation paths" {
        fun KClass<*>.ownedConstructorVisibility(): KVisibility? =
            constructors.single { it.parameters.size == 3 }.visibility

        Device::class.ownedConstructorVisibility() shouldBe KVisibility.INTERNAL
        Queue::class.ownedConstructorVisibility() shouldBe KVisibility.INTERNAL
    }

    "device queue acquisition is eager rather than a post-close lazy downcall" {
        Device::class.java.declaredFields.any { it.name == "queue\$delegate" } shouldBe false
    }
})

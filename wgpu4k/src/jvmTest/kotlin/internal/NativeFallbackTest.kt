@file:OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)

package io.ygdrasil.webgpu.internal

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.webgpu.unsupportedCompilationInfo
import kotlinx.coroutines.async
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest

class NativeFallbackTest : FreeSpec({
    "compute pipeline fallback reports synchronous success and failure" {
        runTest {
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = {}, retain = {}, release = {},
            )

            val success = async {
                pump.runComputePipelineFallback(create = { "compute" }, dispose = {})
            }
            runCurrent()
            success.await().getOrThrow() shouldBe "compute"

            val failure = async {
                pump.runComputePipelineFallback<String>(
                    create = { error("compute creation failed") },
                    dispose = {},
                )
            }
            runCurrent()
            failure.await().exceptionOrNull()?.message shouldBe "compute creation failed"
            pump.close()
            runCurrent()
        }
    }

    "render pipeline fallback reports synchronous success and failure" {
        runTest {
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = {}, retain = {}, release = {},
            )

            val success = async {
                pump.runRenderPipelineFallback(create = { "render" }, dispose = {})
            }
            runCurrent()
            success.await().getOrThrow() shouldBe "render"

            val failure = async {
                pump.runRenderPipelineFallback<String>(
                    create = { error("render creation failed") },
                    dispose = {},
                )
            }
            runCurrent()
            failure.await().exceptionOrNull()?.message shouldBe "render creation failed"
            pump.close()
            runCurrent()
        }
    }

    "compilation info fallback returns a controlled failure" {
        val failure = unsupportedCompilationInfo().exceptionOrNull()

        failure shouldBe UnsupportedOperationException(
            "Shader compilation info is not supported by wgpu-native 29.0.0.0"
        )
    }
})

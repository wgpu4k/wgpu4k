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
        val registered = registerUncapturedError(callback)
        val registration = registered.registration

        try {
            registered.dispatch(WGPUErrorType_Validation, "first")
            registered.dispatch(WGPUErrorType_Internal, "second")
            messages shouldBe listOf("first", "second")

            registration.policy shouldBe CallbackPolicy.REPEATING
            registration.isClosed shouldBe false
        } finally {
            registered.close()
        }
        registration.isClosed shouldBe true
    }
})

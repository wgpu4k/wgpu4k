package io.ygdrasil.webgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.webgpu.GPUUncapturedErrorCallback
import io.ygdrasil.wgpu.WGPUErrorType
import io.ygdrasil.wgpu.WGPUErrorType_Internal
import io.ygdrasil.wgpu.WGPUErrorType_Validation

class UncapturedErrorCallbackTest : FreeSpec({
    "repeating callback dispatches every error and its registration closes" {
        val messages = mutableListOf<String>()
        val callback = GPUUncapturedErrorCallback { messages += it.message }

        dispatchUncapturedError(callback, WGPUErrorType_Validation, "first")
        dispatchUncapturedError(callback, WGPUErrorType_Internal, "second")
        messages shouldBe listOf("first", "second")

        val registered = registerUncapturedError(callback)
        val registration = registered.registration
        registration.policy shouldBe CallbackPolicy.REPEATING
        registration.isClosed shouldBe false
        registered.close()
        registration.isClosed shouldBe true
    }
})

private fun dispatchUncapturedError(
    userCallback: GPUUncapturedErrorCallback,
    type: WGPUErrorType,
    message: String?,
) {
    errorOf(type, message)?.let(userCallback::onUncapturedError)
}

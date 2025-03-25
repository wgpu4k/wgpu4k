package io.ygdrasil.webgpu

actual suspend fun GPUDevice.poll(): Result<Unit> {
    return runCatching {
        (queue as Queue).handler.onSubmittedWorkDone().wait<Unit>()
    }
}
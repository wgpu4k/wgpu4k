package io.ygdrasil.webgpu

actual suspend fun GPUDevice.poll(): Result<Unit> {
    val device = this as Device
    if (device.isClosed) return Result.failure(IllegalStateException("Device is closed"))
    return device.queue.onSubmittedWorkDone()
}

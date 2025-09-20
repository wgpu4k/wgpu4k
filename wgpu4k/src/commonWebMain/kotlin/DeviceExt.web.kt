package io.ygdrasil.webgpu

import js.promise.await

actual suspend fun GPUDevice.poll(): Result<Unit> {
    return runCatching {
        (queue as Queue).handler.onSubmittedWorkDone()
            .await()
    }
}
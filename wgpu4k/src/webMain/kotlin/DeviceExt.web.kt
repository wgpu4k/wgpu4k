@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import js.promise.await
import kotlin.js.ExperimentalWasmJsInterop

actual suspend fun GPUDevice.poll(): Result<Unit> {
    return runCatching {
        (queue as Queue).handler.onSubmittedWorkDone()
            .await()
    }
}
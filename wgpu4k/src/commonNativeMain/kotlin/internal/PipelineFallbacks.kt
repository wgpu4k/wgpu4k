package io.ygdrasil.webgpu.internal

internal suspend fun <T> DeviceEventPump.runComputePipelineFallback(
    create: () -> T,
    dispose: (T) -> Unit,
): Result<T> = runResourceTask(create, dispose)

internal suspend fun <T> DeviceEventPump.runRenderPipelineFallback(
    create: () -> T,
    dispose: (T) -> Unit,
): Result<T> = runResourceTask(create, dispose)

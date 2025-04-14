package io.ygdrasil.webgpu

/**
 * This annotation marks an API as low-level and requires caution in its usage.
 * APIs annotated with [WGPULowLevelApi] provide direct access to low-level
 * WebGPU functionalities and should be used only by experienced developers
 * who fully understand their implications.
 *
 * To use an API annotated with [WGPULowLevelApi], you must explicitly opt-in
 * to ensure that you are aware of the risks and responsibilities associated
 * with such direct access.
 */
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This API provides low-level access and must be handled with care."
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class WGPULowLevelApi()

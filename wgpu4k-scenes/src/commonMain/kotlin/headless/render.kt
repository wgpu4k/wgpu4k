@file:Suppress("DEPRECATION")

package io.ygdrasil.webgpu.examples.headless

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.Extent3D
import io.ygdrasil.webgpu.GPUBufferUsage
import io.ygdrasil.webgpu.GPUMapMode
import io.ygdrasil.webgpu.GPUTextureAspect
import io.ygdrasil.webgpu.Origin3D
import io.ygdrasil.webgpu.TexelCopyBufferInfo
import io.ygdrasil.webgpu.TexelCopyTextureInfo
import io.ygdrasil.webgpu.TextureRenderingContext
import io.ygdrasil.webgpu.autoClosableContext
import io.ygdrasil.webgpu.examples.Scene
import io.ygdrasil.webgpu.examples.loadScenes
import io.ygdrasil.webgpu.mapInto
import io.ygdrasil.webgpu.poll
import korlibs.image.bitmap.Bitmap32
import korlibs.image.format.PNG
import korlibs.image.format.writeBitmap
import korlibs.io.file.std.localVfs

private val logger = KotlinLogging.logger {}

suspend fun captureScene() {
    val context = getHeadlessContext()
    initLog()
    val (sceneName, frame, screenshotPath) = getSceneParameter()
    val renderingContext = context.renderingContext
    val availableScenes = loadScenes(context)
    availableScenes.findWithName(sceneName).let { scene ->
        logger.info { "Rendering frame $frame of $sceneName" }
        autoClosableContext {
            context.bind()
            scene.bind()
            scene.initialize()

            scene.frame = frame
            with(scene) { render() }

            if (renderingContext is TextureRenderingContext) {
                val textureData = IntArray(renderingContext.width.toInt() * renderingContext.height.toInt())
                val outputStagingBuffer = context.device.createBuffer(
                    BufferDescriptor(
                        size = (textureData.size * Int.SIZE_BYTES).toULong(),
                        usage = setOf(GPUBufferUsage.CopyDst, GPUBufferUsage.MapRead),
                        mappedAtCreation = false,
                    )
                )
                val commandEncoder = context.device.createCommandEncoder().bind()
                commandEncoder.copyTextureToBuffer(
                    TexelCopyTextureInfo(
                        texture = renderingContext.getCurrentTexture(),
                        mipLevel = 0u,
                        origin = Origin3D(),
                        aspect = GPUTextureAspect.All,
                    ),
                    TexelCopyBufferInfo(
                        buffer = outputStagingBuffer,
                        offset = 0u,
                        // This needs to be a multiple of 256. Normally we would need to pad
                        // it but we here know it will work out anyways.
                        bytesPerRow = renderingContext.width * 4u,
                        rowsPerImage = renderingContext.height,
                    ),
                    Extent3D(
                        width = renderingContext.width,
                        height = renderingContext.height
                    )
                )
                logger.info { "Copying texture to staging buffer" }
                context.device.queue.submit(listOf(commandEncoder.finish()))
                logger.info { "Mapping ..." }
                outputStagingBuffer.mapAsync(setOf(GPUMapMode.Read))
                outputStagingBuffer.mapInto(buffer = textureData)
                val image = Bitmap32(
                    width = renderingContext.width.toInt(),
                    height = renderingContext.height.toInt(),
                    textureData
                )

                val path = screenshotPath ?: error("screenshot path not set")
                val screenshotsVfs = localVfs(path)["jvm"].also { it.mkdirs() }.jail()
                val outputPath = "$sceneName-$frame.png"
                logger.info { "saving to $outputPath" }
                screenshotsVfs[outputPath]
                    .also { logger.info { "will output texture to ${it.absolutePath}" } }
                    .writeBitmap(image, PNG)
            } else {
                // Complete async work
                context.device.poll()
            }
        }
    }

}

private fun List<Scene>.findWithName(sceneName: String): Scene = first {
    it::class.simpleName == sceneName
}

expect fun getSceneParameter(): SceneParameter

expect fun initLog()

data class SceneParameter(val sceneName: String, val frame: Int, val screenshotPath: String?)
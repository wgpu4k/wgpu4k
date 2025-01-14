package io.ygdrasil.webgpu.examples.headless

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.BufferUsage
import io.ygdrasil.webgpu.ImageCopyBuffer
import io.ygdrasil.webgpu.ImageCopyTexture
import io.ygdrasil.webgpu.MapMode
import io.ygdrasil.webgpu.Origin3D
import io.ygdrasil.webgpu.Size3D
import io.ygdrasil.webgpu.TextureAspect
import io.ygdrasil.webgpu.TextureRenderingContext
import io.ygdrasil.webgpu.autoClosableContext
import io.ygdrasil.webgpu.examples.Scene
import io.ygdrasil.webgpu.examples.loadScenes
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

        autoClosableContext {
            context.bind()
            scene.bind()
            scene.initialize()

            val textureData = IntArray(renderingContext.width.toInt() * renderingContext.height.toInt())
            val outputStagingBuffer = context.device.createBuffer(
                BufferDescriptor(
                    size = (textureData.size * Int.SIZE_BYTES).toULong(),
                    usage = setOf(BufferUsage.CopyDst, BufferUsage.MapRead),
                    mappedAtCreation = false,
                )
            )

            scene.frame = frame
            with(scene) { render() }

            if (renderingContext is TextureRenderingContext) {
                val commandEncoder = context.device.createCommandEncoder().bind()
                commandEncoder.copyTextureToBuffer(
                    ImageCopyTexture(
                        texture = renderingContext.getCurrentTexture(),
                        mipLevel = 0u,
                        origin = Origin3D.Zero,
                        aspect = TextureAspect.All,
                    ),
                    ImageCopyBuffer(
                        buffer = outputStagingBuffer,
                        offset = 0u,
                        // This needs to be a multiple of 256. Normally we would need to pad
                        // it but we here know it will work out anyways.
                        bytesPerRow = renderingContext.width * 4u,
                        rowsPerImage = renderingContext.height,
                    ),
                    Size3D(
                        width = renderingContext.width,
                        height = renderingContext.height
                    )
                )

                context.device.queue.submit(listOf(commandEncoder.finish()))
                outputStagingBuffer.map(setOf(MapMode.Read))
                // Complete async work
                context.device.poll()
                outputStagingBuffer.mapInto(buffer = textureData)
                val image = Bitmap32(
                    width = renderingContext.width.toInt(),
                    height = renderingContext.height.toInt(),
                    textureData
                )

                val path = screenshotPath ?: error("screenshot path not set")
                val screenshotsVfs = localVfs(path)["jvm"].also { it.mkdirs() }.jail()
                val outputPath = "$sceneName-$frame.png"
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
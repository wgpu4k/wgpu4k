package io.ygdrasil.wgpu.examples.headless

import io.ygdrasil.wgpu.BufferDescriptor
import io.ygdrasil.wgpu.BufferUsage
import io.ygdrasil.wgpu.ImageCopyBuffer
import io.ygdrasil.wgpu.ImageCopyTexture
import io.ygdrasil.wgpu.MapMode
import io.ygdrasil.wgpu.Origin3D
import io.ygdrasil.wgpu.Size3D
import io.ygdrasil.wgpu.TextureAspect
import io.ygdrasil.wgpu.TextureRenderingContext
import io.ygdrasil.wgpu.autoClosableContext
import io.ygdrasil.wgpu.examples.Scene
import io.ygdrasil.wgpu.examples.loadScenes
import korlibs.image.bitmap.Bitmap32
import korlibs.image.format.PNG
import korlibs.image.format.writeBitmap
import korlibs.io.file.std.localVfs


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

            val textureData = IntArray(renderingContext.width * renderingContext.height)
            val outputStagingBuffer = context.device.createBuffer(
                BufferDescriptor(
                    size = (textureData.size * Int.SIZE_BYTES).toLong(),
                    usage = setOf(BufferUsage.copydst, BufferUsage.mapread),
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
                        mipLevel = 0,
                        origin = Origin3D.Zero,
                        aspect = TextureAspect.all,
                    ),
                    ImageCopyBuffer(
                        buffer = outputStagingBuffer,
                        offset = 0,
                        // This needs to be a multiple of 256. Normally we would need to pad
                        // it but we here know it will work out anyways.
                        bytesPerRow = renderingContext.width * 4,
                        rowsPerImage = renderingContext.height,
                    ),
                    Size3D(
                        width = renderingContext.width,
                        height = renderingContext.height
                    )
                )

                context.device.queue.submit(listOf(commandEncoder.finish()))
                outputStagingBuffer.map(setOf(MapMode.read))
                // Complete async work
                context.device.poll()
                outputStagingBuffer.mapInto(buffer = textureData)
                val image = Bitmap32(width = renderingContext.width, height = renderingContext.height, textureData)

                val path = screenshotPath ?: error("screenshot path not set")
                val screenshotsVfs = localVfs(path)["jvm"].also { it.mkdirs() }.jail()
                val outputPath = "$sceneName-$frame.png"
                screenshotsVfs[outputPath]
                    .also { println("will output texture to ${it.absolutePath}") }
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
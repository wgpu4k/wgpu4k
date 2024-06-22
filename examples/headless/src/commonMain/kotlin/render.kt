import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.scenes.basic.HelloTriangleScene
import korlibs.image.bitmap.Bitmap32
import korlibs.image.format.PNG
import korlibs.image.format.writeBitmap
import korlibs.io.file.std.localVfs


suspend fun captureScene() {
    val context = getHeadlessContext()
    initLog()
    val renderingContext = context.renderingContext
    HelloTriangleScene(context).let { scene ->

        autoClosableContext {
            context.bind()
            scene.bind()
            scene.initialize()

            val textureData = ByteArray(renderingContext.width * renderingContext.height * 4)
            val outputStagingBuffer = context.device.createBuffer(
                BufferDescriptor(
                    size = textureData.size.toLong(),
                    usage = setOf(BufferUsage.copydst, BufferUsage.mapread),
                    mappedAtCreation = false,
                )
            )

            with(scene) { render() }

            if (context.renderingContext is TextureRenderingContext) {
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

                context.device.queue.submit(arrayOf(commandEncoder.finish()))
                outputStagingBuffer.map(setOf(MapMode.read))
                // Complete async work
                context.device.poll()
                outputStagingBuffer.mapInto(buffer = textureData, offset = 0)
                println(textureData.map { it.toString() }.joinToString(","))
                println("creating image of size ${renderingContext.width} ${renderingContext.height}")
                val image = Bitmap32(width = renderingContext.width, height = renderingContext.height)
                byteArrayToIntArrayBigEndian(input = textureData, output = image.ints)

                val path = "/Users/chaos/"
                val screenshotsVfs = localVfs(path).also { it.mkdirs() }.jail()
                screenshotsVfs["image.png"].writeBitmap(image, PNG)
            } else {
                // Complete async work
                context.device.poll()
            }
        }
    }

}

fun byteArrayToIntArrayBigEndian(input: ByteArray, output: IntArray) {
    output.indices.forEach { index ->
        output[index] = ((input[index * 4].toInt() and 0xFF) shl 24) or
                ((input[index * 4 + 1].toInt() and 0xFF) shl 16) or
                ((input[index * 4 + 2].toInt() and 0xFF) shl 8) or
                (input[index * 4 + 3].toInt() and 0xFF)
    }
}

expect fun initLog()
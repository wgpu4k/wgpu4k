import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.scenes.basic.HelloTriangleScene


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
        }
    }

}

expect fun initLog()
package io.ygdrasil.wgpu.examples.scenes.graphics.techniques

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.Application

val MAT4X4_BYTES = 64

class WhaleScene : Application.Scene() {

    data class Settings(
        val cameraX: Double = 0.0,
        val cameraY: Double = -5.1,
        val cameraZ: Double = -14.6,
        val objectScale: Int = 1,
        val angle: Double = 0.2,
        val speed: Int = 50,
    )

    override fun Application.initialiaze() {
        val depthTexture = device.createTexture(
            TextureDescriptor(
                size = Size3D(renderingContext.width, renderingContext.height),
                format = TextureFormat.depth24plus,
                usage = setOf(TextureUsage.renderattachment)
            )
        )

        val cameraBuffer = device.createBuffer(
            BufferDescriptor(
                size = MAT4X4_BYTES * 3L,
                usage = setOf(BufferUsage.uniform, BufferUsage.copydst)
            )
        )
        TODO("Not yet implemented")
    }

    override fun Application.render() {
        TODO("Not yet implemented")
    }

}
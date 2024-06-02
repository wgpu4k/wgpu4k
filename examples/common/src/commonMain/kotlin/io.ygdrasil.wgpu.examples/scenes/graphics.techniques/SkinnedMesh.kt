package io.ygdrasil.wgpu.examples.scenes.graphics.techniques

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.BufferBindingLayout
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.helper.BindGroupClusterDescriptor
import io.ygdrasil.wgpu.examples.helper.createBindGroupCluster

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

        val cameraBGCluster = device.createBindGroupCluster(
            listOf(
                BindGroupClusterDescriptor(
                    bindings = 0,
                    visibilities = setOf(ShaderStage.vertex),
                    bindingType = BufferBindingLayout(type = BufferBindingType.uniform)
                )
            ),
            listOf(listOf(BindGroupDescriptor.BufferBinding(cameraBuffer))),
            "Camera"
        )

        val generalUniformsBuffer = device.createBuffer(
            BufferDescriptor(
                size = UInt.SIZE_BYTES * 2L,
                usage = setOf(BufferUsage.uniform, BufferUsage.copydst)
            )
        )

        val generalUniformsBGCluster = device.createBindGroupCluster(
            listOf(
                BindGroupClusterDescriptor(
                    bindings = 0,
                    visibilities = setOf(ShaderStage.vertex, ShaderStage.fragment),
                    bindingType = BufferBindingLayout(type = BufferBindingType.uniform)
                )
            ),
            listOf(listOf(BindGroupDescriptor.BufferBinding(generalUniformsBuffer))),
            "General"
        )


        val nodeUniformsBindGroupLayout = device.createBindGroupLayout(
            BindGroupLayoutDescriptor(
                label = "NodeUniforms.bindGroupLayout",
                entries = arrayOf(
                    BindGroupLayoutDescriptor.Entry(
                        binding = 0,
                        bindingType = BufferBindingLayout(type = BufferBindingType.uniform),
                        visibility = setOf(ShaderStage.vertex)
                    )
                )
            )
        )
        TODO("Not yet implemented")
    }

    override fun Application.render() {
        TODO("Not yet implemented")
    }

}
package io.ygdrasil.wgpu.examples.scenes.graphics.techniques

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.BufferBindingLayout
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.helper.*
import io.ygdrasil.wgpu.examples.scenes.shader.gltfWGSL
import korlibs.io.async.runBlockingNoJs
import korlibs.io.file.std.resourcesVfs

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

        val generalUniformsBGCLuster = device.createBindGroupCluster(
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

        val whaleScene = runBlockingNoJs {
            resourcesVfs["assets/gltf/whale.glb"].readGLB()
        }

        whaleScene.meshes[0].buildRenderPipeline(
            device,
            gltfWGSL,
            gltfWGSL,
            renderingContext.textureFormat,
            depthTexture.format,
            listOf(
                cameraBGCluster.bindGroupLayout,
                generalUniformsBGCLuster.bindGroupLayout,
                nodeUniformsBindGroupLayout,
                createSharedBindGroupLayout(device),
            )
        );

        TODO("Not yet implemented")
    }

    override fun Application.render() {
        TODO("Not yet implemented")
    }

}


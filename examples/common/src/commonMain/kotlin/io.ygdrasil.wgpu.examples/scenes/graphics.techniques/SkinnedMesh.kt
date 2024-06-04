package io.ygdrasil.wgpu.examples.scenes.graphics.techniques

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.BufferBindingLayout
import io.ygdrasil.wgpu.RenderPassDescriptor.ColorAttachment
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.autoClosableContext
import io.ygdrasil.wgpu.examples.helper.*
import io.ygdrasil.wgpu.examples.scenes.shader.gltfWGSL
import korlibs.io.async.runBlockingNoJs
import korlibs.io.file.std.resourcesVfs
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.math.PI

val MAT4X4_BYTES = 64

class WhaleScene : Application.Scene() {

    private lateinit var generalUniformsBGCLuster: BindGroupCluster
    private lateinit var cameraBGCluster: BindGroupCluster
    private lateinit var whaleScene: GLTF2
    private lateinit var cameraBuffer: Buffer
    private lateinit var gltfRenderPassDescriptor: RenderPassDescriptor
    private lateinit var projectionMatrix: Matrix4
    private val settings = Settings()

    data class Settings(
        val cameraX: Double = 0.0,
        val cameraY: Double = -5.1,
        val cameraZ: Double = -14.6,
        val objectScale: Int = 1,
        val angle: Double = 0.2,
        val speed: Int = 50,
    )

    override fun Application.initialiaze() = with(autoClosableContext) {
        val depthTexture = device.createTexture(
            TextureDescriptor(
                size = Size3D(renderingContext.width, renderingContext.height),
                format = TextureFormat.depth24plus,
                usage = setOf(TextureUsage.renderattachment)
            )
        ).bind()

        cameraBuffer = device.createBuffer(
            BufferDescriptor(
                size = MAT4X4_BYTES * 3L,
                usage = setOf(BufferUsage.uniform, BufferUsage.copydst)
            )
        ).bind()

        cameraBGCluster = device.createBindGroupCluster(
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
        ).bind()

        generalUniformsBGCLuster = device.createBindGroupCluster(
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
        ).bind()

        whaleScene = runBlockingNoJs {
            resourcesVfs["assets/gltf/whale.glb"].readGLB()
        }

        whaleScene.meshes[0].buildRenderPipeline(
            whaleScene,
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
        )

        val aspect = renderingContext.width / renderingContext.height.toDouble()
        val fox = Angle.fromRadians((2 * PI) / 5)
        projectionMatrix = Matrix4.perspective(fox, aspect, .1, 100.0)

        // Pass Descriptor for GLTFs
        gltfRenderPassDescriptor = RenderPassDescriptor(
            colorAttachments = arrayOf(
                ColorAttachment(
                    view = dummyTexture.createView(), // Assigned later

                    clearValue = arrayOf(0.3, 0.3, 0.3, 1.0),
                    loadOp = LoadOp.clear,
                    storeOp = StoreOp.store
                )
            ),
            depthStencilAttachment = RenderPassDescriptor.RenderPassDepthStencilAttachment(
                view = depthTexture.createView().bind(),
                depthLoadOp = LoadOp.clear,
                depthClearValue = 1.0f,
                depthStoreOp = StoreOp.store
            )
        )

        TODO("Not yet implemented")
    }

    override fun Application.render() = autoClosableContext {

        // Calculate camera matrices
        val projectionMatrix = getProjectionMatrix()
        val viewMatrix = getViewMatrix()
        val modelMatrix = getModelMatrix(frame)

        // Write to mvp to camera buffer
        device.queue.writeBuffer(
            cameraBuffer,
            0,
            projectionMatrix,
            0,
            projectionMatrix.size.toLong()
        )

        device.queue.writeBuffer(
            cameraBuffer,
            64,
            viewMatrix,
            0,
            viewMatrix.size.toLong()
        )

        device.queue.writeBuffer(
            cameraBuffer,
            128,
            modelMatrix,
            0,
            modelMatrix.size.toLong()
        )

        // The difference between these two render passes is just the presence of depthTexture
        val gltfRenderPassDescriptor = gltfRenderPassDescriptor.copy(colorAttachments = arrayOf(gltfRenderPassDescriptor.colorAttachments[0]
            .copy(view = renderingContext.getCurrentTexture().createView().bind()
        )))

        // Update node matrices
        for (scene in whaleScene.scenes) {
            //scene.root.updateWorldMatrix(device)
        }

        // Calculate bone transformation
        // TODO const t = (frame / 20000) * settings.speed;
        // Updates skins (we index into skins in the renderer, which is not the best approach but hey)
        // TODO animWhaleSkin(whaleScene.skins[0], Math.sin(t) * settings.angle);
        // Node 6 should be the only node with a drawable mesh so hopefully this works fine
        // TODO whaleScene.skins[0].update(device, 6, whaleScene.nodes);

        val commandEncoder = device.createCommandEncoder().bind()

        val passEncoder = commandEncoder.beginRenderPass(gltfRenderPassDescriptor).bind()
        whaleScene.scenes.forEach { scene ->
            /* TODO scene.root.renderDrawables(
                passEncoder, listOf(
                    cameraBGCluster.bindGroups[0],
                    generalUniformsBGCLuster.bindGroups[0]
                )
            )*/
        }
        passEncoder.end()

        device.queue.submit(arrayOf(commandEncoder.finish()))

        renderingContext.present()
    }


    fun getProjectionMatrix(): FloatArray {
        return projectionMatrix.copyToColumns()
    }

    fun getViewMatrix(): FloatArray {
        return Matrix4.IDENTITY
            .translated(
                settings.cameraX.toFloat(), settings.cameraY.toFloat(), settings.cameraZ.toFloat()
            ).copyToColumns()
    }

    fun getModelMatrix(frame: Int): FloatArray {
        return Matrix4.IDENTITY.scaled(
            settings.objectScale.toFloat(),
            settings.objectScale.toFloat(),
            settings.objectScale.toFloat()
        ).rotated(
            Angle.fromRadians((frame / 100) * 0.5f),
            0,
            1,
            0
        ).copyToColumns()
    }

}


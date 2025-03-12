package io.ygdrasil.webgpu.examples.helper.glb

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.BindGroup
import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.BindGroupEntry
import io.ygdrasil.webgpu.BindGroupLayout
import io.ygdrasil.webgpu.BindGroupLayoutDescriptor
import io.ygdrasil.webgpu.BindGroupLayoutEntry
import io.ygdrasil.webgpu.BufferBinding
import io.ygdrasil.webgpu.BufferBindingLayout
import io.ygdrasil.webgpu.BufferBindingType
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.BufferUsage
import io.ygdrasil.webgpu.ColorTargetState
import io.ygdrasil.webgpu.CompareFunction
import io.ygdrasil.webgpu.DepthStencilState
import io.ygdrasil.webgpu.Device
import io.ygdrasil.webgpu.FragmentState
import io.ygdrasil.webgpu.GPUAddressMode
import io.ygdrasil.webgpu.GPUBindGroup
import io.ygdrasil.webgpu.GPUBindGroupLayout
import io.ygdrasil.webgpu.GPUBuffer
import io.ygdrasil.webgpu.GPUBufferBindingType
import io.ygdrasil.webgpu.GPUBufferUsage
import io.ygdrasil.webgpu.GPUDevice
import io.ygdrasil.webgpu.GPUFilterMode
import io.ygdrasil.webgpu.GPUIndexFormat
import io.ygdrasil.webgpu.GPUPrimitiveTopology
import io.ygdrasil.webgpu.GPURenderBundle
import io.ygdrasil.webgpu.GPURenderBundleEncoder
import io.ygdrasil.webgpu.GPUSampler
import io.ygdrasil.webgpu.GPUShaderStage
import io.ygdrasil.webgpu.GPUTexture
import io.ygdrasil.webgpu.GPUTextureFormat
import io.ygdrasil.webgpu.GPUVertexFormat
import io.ygdrasil.webgpu.IndexFormat
import io.ygdrasil.webgpu.PipelineLayoutDescriptor
import io.ygdrasil.webgpu.PrimitiveState
import io.ygdrasil.webgpu.RenderBundleEncoderDescriptor
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.SamplerBindingLayout
import io.ygdrasil.webgpu.SamplerDescriptor
import io.ygdrasil.webgpu.ShaderStage
import io.ygdrasil.webgpu.TextureBindingLayout
import io.ygdrasil.webgpu.TextureFormat
import io.ygdrasil.webgpu.VertexAttribute
import io.ygdrasil.webgpu.VertexBufferLayout
import io.ygdrasil.webgpu.VertexState
import io.ygdrasil.webgpu.mapFrom
import korlibs.memory.getS8Array
import kotlin.math.max

private val logger = KotlinLogging.logger {}

class GLTFPrimitive(
    val indices: GLTFAccessor?,
    val positions: GLTFAccessor,
    val normals: GLTFAccessor?,
    val texcoords: List<GLTFAccessor>,
    val material: GLTFMaterial,
    val topology: GLTFRenderMode,
) {

    // Build the primitive render commands into the bundle
    fun buildRenderBundle(
        device: GPUDevice,
        shaderCache: ShaderCache,
        bindGroupLayouts: Array<GPUBindGroupLayout>,
        bundleEncoder: GPURenderBundleEncoder,
        swapChainFormat: GPUTextureFormat,
        depthFormat: GPUTextureFormat,
    ) {

        val shaderModule = shaderCache.getShader(
            normals != null,
            texcoords.size > 0,
            material.baseColorTexture != null
        )

        val vertexBuffers = mutableListOf(
            VertexBufferLayout(
                arrayStride = positions.byteStride.toULong(),
                attributes = listOf(
                    VertexAttribute(
                        format = GPUVertexFormat.Float32x3,
                        offset = 0u,
                        shaderLocation = 0u
                    )
                )
            )
        )

        if (normals != null) {
            vertexBuffers.add(
                VertexBufferLayout(
                    arrayStride = normals.byteStride.toULong(),
                    attributes = listOf(
                        VertexAttribute(
                            format = GPUVertexFormat.Float32x3,
                            offset = 0u,
                            shaderLocation = 1u
                        )
                    )
                )
            )
        }

        // TODO: Multi-texturing
        if (texcoords.size > 0) {
            vertexBuffers.add(
                VertexBufferLayout(
                    arrayStride = texcoords[0].byteStride.toULong(),
                    attributes = listOf(
                        VertexAttribute(
                            format = GPUVertexFormat.Float32x2,
                            offset = 0u,
                            shaderLocation = 2u
                        )
                    )
                )
            )
        }

        val layout = device.createPipelineLayout(
            PipelineLayoutDescriptor(
                bindGroupLayouts = listOf(
                    bindGroupLayouts[0],
                    bindGroupLayouts[1],
                    material.bindGroupLayout
                )
            )
        )

        val vertexStage = VertexState(
            module = shaderModule,
            entryPoint = "vertex_main",
            buffers = vertexBuffers
        )

        val fragmentStage = FragmentState(
            module = shaderModule,
            entryPoint = "fragment_main",
            targets = listOf(
                ColorTargetState(
                    format = swapChainFormat
                )
            ),
        )

        val primitive = if (topology == GLTFRenderMode.TRIANGLE_STRIP) {
            PrimitiveState(
                topology = GPUPrimitiveTopology.TriangleStrip,
                stripIndexFormat = if (indices?.componentType == GLTFComponentType.UNSIGNED_SHORT.value) GPUIndexFormat.Uint16 else GPUIndexFormat.Uint32,
            )
        } else {
            PrimitiveState(
                topology = GPUPrimitiveTopology.TriangleList
            )
        }

        val pipelineDescriptor = RenderPipelineDescriptor(
            layout = layout,
            vertex = vertexStage,
            fragment = fragmentStage,
            primitive = primitive,
            depthStencil = DepthStencilState(
                format = depthFormat,
                depthWriteEnabled = true,
                depthCompare = CompareFunction.Less
            )
        )

        logger.debug { "Pipeline descriptor: $pipelineDescriptor" }

        val renderPipeline = device.createRenderPipeline(pipelineDescriptor)

        bundleEncoder.setBindGroup(2u, material.bindGroup)
        bundleEncoder.setPipeline(renderPipeline)
        bundleEncoder.setVertexBuffer(
            0u,
            positions.view.gpuBuffer ?: error("fail to get buffer"),
            positions.byteOffset.toULong()
        )
        if (normals != null) {
            bundleEncoder.setVertexBuffer(
                1u, normals.view.gpuBuffer ?: error("fail to get buffer"), normals.byteOffset.toULong()
            )
        }
        if (texcoords.size > 0) {
            bundleEncoder.setVertexBuffer(
                2u,
                texcoords[0].view.gpuBuffer ?: error("fail to get buffer"),
                texcoords[0].byteOffset.toULong()
            )
        }
        if (indices != null) {
            val indexFormat =
                if (indices.componentType == GLTFComponentType.UNSIGNED_SHORT.value) IndexFormat.Uint16 else IndexFormat.Uint32

            bundleEncoder.setIndexBuffer(
                indices.view.gpuBuffer ?: error("fail to get buffer"),
                indexFormat,
                indices.byteOffset.toULong()
            )
            bundleEncoder.drawIndexed(indices.count.toUInt())
        } else {
            bundleEncoder.draw(positions.count.toUInt())
        }
    }
}

class GLTFMaterial(material: GLTF2.Material? = null, textures: List<GLTFTexture> = listOf()) {
    var baseColorFactor = floatArrayOf(1f, 1f, 1f, 1f)
    var baseColorTexture: GLTFTexture? = null
    private var emissiveFactor = floatArrayOf(0f, 0f, 0f, 1f)
    private var metallicFactor = 1.0f
    private var roughnessFactor = 1.0f
    lateinit var bindGroup: GPUBindGroup
    lateinit var bindGroupLayout: GPUBindGroupLayout

    init {
        if (material?.pbrMetallicRoughness != null) {
            val it = material.pbrMetallicRoughness
            baseColorFactor = it.baseColorFactor ?: baseColorFactor
            if (it.baseColorTexture != null) {
                baseColorTexture = textures[it.baseColorTexture.index]
            }
            metallicFactor = it.metallicFactor ?: metallicFactor
            roughnessFactor = it.roughnessFactor?: roughnessFactor

        }
        if (material?.emissiveFactor != null) {
            val it = material.emissiveFactor
            emissiveFactor = floatArrayOf(it[0], it[1], it[2], 1f)
        }
    }

    fun upload(device: GPUDevice) {
        logger.debug { "Uploading material for node" }
        val buffer = device.createBuffer(
            BufferDescriptor(
                size = (2uL * 4uL * 4uL) * Float.SIZE_BYTES.toULong(),
                setOf(GPUBufferUsage.Uniform),
                mappedAtCreation = true
            )
        )
        buffer.mapFrom(baseColorFactor)
        buffer.mapFrom(emissiveFactor, 4uL * Float.SIZE_BYTES.toULong())
        buffer.mapFrom(floatArrayOf(metallicFactor, roughnessFactor), 8uL * Float.SIZE_BYTES.toULong())
        buffer.unmap()

        val layoutEntries = mutableListOf(
            BindGroupLayoutEntry(
                binding = 0u,
                visibility = setOf(GPUShaderStage.Fragment),
                buffer = BufferBindingLayout(
                    type = GPUBufferBindingType.Uniform
                ),
            )
        )
        val bindGroupEntries = mutableListOf(
            BindGroupEntry(
                binding = 0u,
                resource = BufferBinding(
                    buffer = buffer
                )
            )
        )

        baseColorTexture?.let {
            layoutEntries.add(
                BindGroupLayoutEntry(
                    binding = 1u,
                    visibility = setOf(ShaderStage.Fragment),
                    sampler = SamplerBindingLayout(),
                )
            )
            layoutEntries.add(
                BindGroupLayoutEntry(
                    binding = 2u,
                    visibility = setOf(GPUShaderStage.Fragment),
                    texture = TextureBindingLayout(),
                )
            )

            bindGroupEntries.add(
                BindGroupEntry(binding = 1u, resource = it.sampler)
            )
            bindGroupEntries.add(
                BindGroupEntry(binding = 2u, resource = it.imageView)
            )
        }

        val bindGroupLayout = device.createBindGroupLayout(
            BindGroupLayoutDescriptor(
                entries = layoutEntries
            )
        )

        bindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = bindGroupLayout,
                entries = bindGroupEntries
            )
        )

        this.bindGroupLayout = bindGroupLayout
    }
}

class GLTFTexture(sampler: GLTFSampler, image: GPUTexture) {
    val sampler = sampler.sampler
    val imageView = image.createView()
}

class GLTFBufferView(bufferView: GLTF2.BufferView, buffer: GLTF2.Buffer) {
    private var length = bufferView.byteLength
    private var byteOffset = bufferView.byteOffset
    var byteStride: Int = bufferView.byteStride
    var buffer: ByteArray
    var needsUpload = false
    var gpuBuffer: GPUBuffer? = null
    private val usage = mutableSetOf<BufferUsage>()

    init {
        this.buffer = buffer.buffer.getS8Array(byteOffset, length)
    }

    internal fun addUsage(usage: BufferUsage) {
        this.usage.add(usage)
    }

    fun upload(device: GPUDevice) {
        // Note: must align to 4 byte size when mapped at creation is true
        val buf = device.createBuffer(
            BufferDescriptor(
                size = alignTo(this.buffer.size, 4).toULong(),
                usage = this.usage,
                mappedAtCreation = true
            )
        )
        buf.mapFrom(buffer)
        buf.unmap()
        gpuBuffer = buf
        needsUpload = false
    }
}

class GLTFMesh(
    val name: String,
    val primitives: List<GLTFPrimitive>,
)

class GLTFAccessor(val view: GLTFBufferView, accessor: GLTF2.Accessor) {
    var count: Int = accessor.count
    var componentType: Int = accessor.componentType
    var gltfType: String = accessor.type.name
    var numComponents: Int = gltfTypeNumComponents(accessor.type.name)
    var numScalars: Int = count * numComponents
    var byteOffset: Int = accessor.byteOffset

    val byteStride: Int
        get() {
            val elementSize = gltfTypeSize(GLTFComponentType.of(componentType), gltfType)
            return max(elementSize, view.byteStride)
        }
}

class GLBModel(val nodes: List<GLTFNode>) {

    fun buildRenderBundles(
        device: Device,
        shaderCache: ShaderCache,
        viewParamsLayout: BindGroupLayout,
        viewParamsBindGroup: BindGroup,
        swapChainFormat: GPUTextureFormat,
    ): List<GPURenderBundle> {
        val renderBundles = mutableListOf<GPURenderBundle>()
        nodes.forEach { node ->
            val bundle = node.buildRenderBundle(
                device,
                shaderCache,
                viewParamsLayout,
                viewParamsBindGroup,
                swapChainFormat,
                TextureFormat.Depth24PlusStencil8
            )
            renderBundles.add(bundle)
        }
        return renderBundles
    }
}

class GLTFNode(val name: String, val mesh: GLTFMesh, val transform: FloatArray) {
    lateinit var gpuUniforms: GPUBuffer
    lateinit var bindGroup: GPUBindGroup

    fun upload(device: GPUDevice) {
        logger.debug { "Uploading uniform buffer for node $name" }
        gpuUniforms = device.createBuffer(
            BufferDescriptor(
                size = 4uL * 4uL * 4uL,
                usage = setOf(GPUBufferUsage.Uniform),
                mappedAtCreation = true
            )
        )
        gpuUniforms.mapFrom(transform)
        gpuUniforms.unmap()
    }

    fun buildRenderBundle(
        device: GPUDevice,
        shaderCache: ShaderCache,
        viewParamsLayout: GPUBindGroupLayout,
        viewParamsBindGroup: GPUBindGroup,
        swapChainFormat: GPUTextureFormat,
        depthFormat: GPUTextureFormat,
    ): GPURenderBundle {
        val nodeParamsLayout = device.createBindGroupLayout(
            BindGroupLayoutDescriptor(
                entries = listOf(
                    BindGroupLayoutEntry(
                        binding = 0u,
                        visibility = setOf(GPUShaderStage.Vertex),
                        buffer = BufferBindingLayout(type = BufferBindingType.Uniform)
                    )
                )
            )
        )

        bindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = nodeParamsLayout,
                entries = listOf(
                    BindGroupEntry(
                        binding = 0u,
                        resource = BufferBinding(
                            buffer = gpuUniforms
                        )
                    )
                )
            )
        )

        val bindGroupLayouts = arrayOf(viewParamsLayout, nodeParamsLayout)

        val bundleEncoder = device.createRenderBundleEncoder(
            RenderBundleEncoderDescriptor(
                colorFormats = listOf(
                    swapChainFormat
                ),
                depthStencilFormat = depthFormat
            )
        )

        bundleEncoder.setBindGroup(0u, viewParamsBindGroup)
        bundleEncoder.setBindGroup(1u, bindGroup)

        for (primitive in mesh.primitives) {
            primitive.buildRenderBundle(
                device,
                shaderCache,
                bindGroupLayouts,
                bundleEncoder,
                swapChainFormat,
                depthFormat
            )
        }

        val renderBundle = bundleEncoder.finish()
        return renderBundle
    }
}


class GLTFSampler(private val device: GPUDevice, private val samplerNode: GLTF2.Sampler? = null) {

    val sampler = createSampler()

    private fun createSampler(): GPUSampler {
        val magFilter = when (samplerNode?.magFilter) {
            null, GLTFTextureFilter.LINEAR.value -> GPUFilterMode.Linear
            else -> GPUFilterMode.Nearest
        }
        val minFilter = when (samplerNode?.minFilter) {
            null, GLTFTextureFilter.LINEAR.value -> GPUFilterMode.Linear
            else -> GPUFilterMode.Nearest
        }

        val wrapS = when (samplerNode?.wrapS) {
            GLTFTextureFilter.REPEAT.value -> GPUAddressMode.Repeat
            GLTFTextureFilter.CLAMP_TO_EDGE.value -> GPUAddressMode.ClampToEdge
            null -> GPUAddressMode.Repeat
            else -> GPUAddressMode.MirrorRepeat
        }

        val wrapT = when (samplerNode?.wrapT) {
            GLTFTextureFilter.REPEAT.value -> GPUAddressMode.Repeat
            GLTFTextureFilter.CLAMP_TO_EDGE.value -> GPUAddressMode.ClampToEdge
            null -> GPUAddressMode.Repeat
            else -> GPUAddressMode.MirrorRepeat
        }

        return device.createSampler(
            SamplerDescriptor(
                magFilter = magFilter,
                minFilter = minFilter,
                addressModeU = wrapS,
                addressModeV = wrapT,
            )
        )
    }
}
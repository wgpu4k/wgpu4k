package glb

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupDescriptor.*
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.BufferBindingLayout
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.SamplerBindingLayout
import io.ygdrasil.wgpu.RenderPipelineDescriptor.FragmentState
import io.ygdrasil.wgpu.RenderPipelineDescriptor.VertexState.VertexBufferLayout
import io.ygdrasil.wgpu.examples.helper.GLTF2
import io.ygdrasil.wgpu.examples.helper.GLTFRenderMode
import io.ygdrasil.wgpu.internal.js.GPUSampler
import korlibs.memory.getS8Array
import org.khronos.webgl.ArrayBuffer
import kotlin.math.max

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
        device: Device,
        shaderCache: ShaderCache,
        bindGroupLayouts: Array<BindGroupLayout>,
        bundleEncoder: RenderBundleEncoder,
        swapChainFormat: String,
        depthFormat: String,
    ) {

        val shaderModule = shaderCache.getShader(
            normals != null,
            texcoords.size > 0,
            material.baseColorTexture != null
        )

        val vertexBuffers = mutableListOf(
            VertexBufferLayout(
                arrayStride = positions.byteStride.toLong(),
                attributes = arrayOf(
                    VertexBufferLayout.VertexAttribute(
                        format = VertexFormat.float32x3,
                        offset = 0,
                        shaderLocation = 0
                    )
                )
            )
        )

        if (normals != null) {
            vertexBuffers.add(
                VertexBufferLayout(
                    arrayStride = normals.byteStride.toLong(),
                    attributes = arrayOf(
                        VertexBufferLayout.VertexAttribute(
                            format = VertexFormat.float32x3,
                            offset = 0,
                            shaderLocation = 1
                        )
                    )
                )
            )
        }

        // TODO: Multi-texturing
        if (texcoords.size > 0) {
            vertexBuffers.add(
                VertexBufferLayout(
                    arrayStride = texcoords[0].byteStride.toLong(),
                    attributes = arrayOf(
                        VertexBufferLayout.VertexAttribute(
                            format = VertexFormat.float32x2,
                            offset = 0,
                            shaderLocation = 2
                        )
                    )
                )
            )
        }

        val layout = device.createPipelineLayout(
            PipelineLayoutDescriptor(
                bindGroupLayouts = arrayOf(
                    bindGroupLayouts[0],
                    bindGroupLayouts[1],
                    material.bindGroupLayout
                )
            )
        )

        var vertexStage = RenderPipelineDescriptor.VertexState(
            module = shaderModule,
            entryPoint = "vertex_main",
            buffers = vertexBuffers.toTypedArray()
        )

        var fragmentStage = FragmentState(
            module = shaderModule,
            entryPoint = "fragment_main",
            targets = arrayOf(
                FragmentState.ColorTargetState(
                    format = TextureFormat.of(swapChainFormat) ?: error("fail to get texture format $swapChainFormat")
                )
            ),
        )

        val primitive = if (topology == GLTFRenderMode.TRIANGLE_STRIP) {
            RenderPipelineDescriptor.PrimitiveState(
                topology = PrimitiveTopology.trianglestrip,
                stripIndexFormat = if (indices?.componentType == GLTFComponentType.UNSIGNED_SHORT.value) IndexFormat.uint16 else IndexFormat.uint32,
            )
        } else {
            RenderPipelineDescriptor.PrimitiveState(
                topology = PrimitiveTopology.trianglelist
            )
        }

        var pipelineDescriptor = RenderPipelineDescriptor(
            layout = layout,
            vertex = vertexStage,
            fragment = fragmentStage,
            primitive = primitive,
            depthStencil = RenderPipelineDescriptor.DepthStencilState(
                format = TextureFormat.of(depthFormat) ?: error("fail to get depth format $depthFormat"),
                depthWriteEnabled = true,
                depthCompare = CompareFunction.less
            )
        )

        var renderPipeline = device.createRenderPipeline(pipelineDescriptor)

        bundleEncoder.setBindGroup(2, material.bindGroup)
        bundleEncoder.setPipeline(renderPipeline)
        bundleEncoder.setVertexBuffer(
            0,
            positions.view.gpuBuffer ?: error("fail to get buffer"),
            positions.byteOffset.toLong()
        )
        if (normals != null) {
            bundleEncoder.setVertexBuffer(
                1, normals.view.gpuBuffer ?: error("fail to get buffer"), normals.byteOffset.toLong()
            )
        }
        if (texcoords.size > 0) {
            bundleEncoder.setVertexBuffer(
                2,
                texcoords[0].view.gpuBuffer ?: error("fail to get buffer"),
                texcoords[0].byteOffset.toLong()
            )
        }
        if (indices != null) {
            val indexFormat =
                if (indices.componentType == GLTFComponentType.UNSIGNED_SHORT.value) IndexFormat.uint16 else IndexFormat.uint32

            bundleEncoder.setIndexBuffer(
                indices.view.gpuBuffer ?: error("fail to get buffer"),
                indexFormat,
                indices.byteOffset.toLong()
            )
            bundleEncoder.drawIndexed(indices.count)
        } else {
            bundleEncoder.draw(positions.count)
        }
    }
}

class GLTFMaterial(material: dynamic, textures: List<GLTFTexture> = listOf()) {
    var baseColorFactor = floatArrayOf(1f, 1f, 1f, 1f)
    var baseColorTexture: GLTFTexture? = null
    private var emissiveFactor = floatArrayOf(0f, 0f, 0f, 1f)
    private var metallicFactor = 1.0f
    private var roughnessFactor = 1.0f
    lateinit var bindGroup: BindGroup
    lateinit var bindGroupLayout: BindGroupLayout

    init {
        if (material["pbrMetallicRoughness"]) {
            val it = material["pbrMetallicRoughness"]
            baseColorFactor = it["baseColorFactor"] ?: baseColorFactor
            if (it["baseColorTexture"]) {
                baseColorTexture = textures[it["baseColorTexture"]["index"]]
            }
            metallicFactor = it["metallicFactor"] ?: metallicFactor
            roughnessFactor = it["roughnessFactor"] ?: roughnessFactor

        }
        if (material["emissiveFactor"]) {
            val it = material["emissiveFactor"]
            emissiveFactor = floatArrayOf(it[0], it[1], it[2], 1f)
        }
    }

    fun upload(device: Device) {
        val buf = device.createBuffer(
            BufferDescriptor(
                size = 3 * 4 * 4,
                setOf(BufferUsage.uniform),
                mappedAtCreation = true
            )
        )
        buf.mapFrom(baseColorFactor)
        buf.mapFrom(emissiveFactor, 4 * Float.SIZE_BYTES)
        buf.mapFrom(floatArrayOf(metallicFactor, roughnessFactor), 8 * Float.SIZE_BYTES)
        buf.unmap()

        val layoutEntries = mutableListOf(
            Entry(
                binding = 0,
                visibility = setOf(ShaderStage.fragment),
                bindingType = BufferBindingLayout(
                    type = BufferBindingType.uniform
                ),
            )
        )
        val bindGroupEntries = mutableListOf(
            BindGroupEntry(
                binding = 0,
                resource = BufferBinding(
                    buffer = buf
                )
            )
        )

        baseColorTexture?.let {
            layoutEntries.add(
                Entry(
                    binding = 1,
                    visibility = setOf(ShaderStage.fragment),
                    bindingType = SamplerBindingLayout(),
                )
            )
            layoutEntries.add(
                Entry(
                    binding = 2,
                    visibility = setOf(ShaderStage.fragment),
                    bindingType = Entry.TextureBindingLayout(),
                )
            )

            bindGroupEntries.add(
                BindGroupEntry(binding = 1, resource = SamplerBinding(Sampler(it.sampler)))
            )
            bindGroupEntries.add(
                BindGroupEntry(binding = 2, resource = TextureViewBinding(it.imageView))
            )
        }

        val bindGroupLayout = device.createBindGroupLayout(
            BindGroupLayoutDescriptor(
                entries = layoutEntries.toTypedArray()
            )
        )

        bindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = bindGroupLayout,
                entries = bindGroupEntries.toTypedArray()
            )
        )

        this.bindGroupLayout = bindGroupLayout
    }
}

class GLTFTexture(sampler: GLTFSampler, image: Texture) {
    val gltfsampler = sampler
    val sampler = sampler.sampler
    val imageView = image.createView()
}


class GLTFBuffer(
    val arrayBuffer: ArrayBuffer,
    val size: Int,
    val byteOffset: Int,
) {
    init {
        println("byteOffset $byteOffset")
        println("size $size")
    }
}

class GLTFBufferView(bufferView: GLTF2.BufferView, buffer: GLTF2.Buffer) {
    private var length = bufferView.byteLength
    private var byteOffset = bufferView.byteOffset
    var byteStride: Int = bufferView.byteStride
    var buffer: ByteArray
    var needsUpload = false
    var gpuBuffer: Buffer? = null
    private val usage = mutableSetOf<BufferUsage>()

    init {
        this.buffer = buffer.buffer.getS8Array(byteOffset, length)
    }

    internal fun addUsage(usage: BufferUsage) {
        this.usage.add(usage)
    }

    fun upload(device: Device) {
        // Note: must align to 4 byte size when mapped at creation is true
        val buf = device.createBuffer(
            BufferDescriptor(
                size = alignTo(this.buffer.size, 4).toLong(),
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

class GLBModel(val nodes: Array<GLTFNode>) {

    fun buildRenderBundles(
        device: Device,
        shaderCache: ShaderCache,
        viewParamsLayout: BindGroupLayout,
        viewParamsBindGroup: BindGroup,
        swapChainFormat: String,
    ): Array<RenderBundle> {
        val renderBundles = mutableListOf<RenderBundle>()
        nodes.forEach { node ->
            val bundle = node.buildRenderBundle(
                device,
                shaderCache,
                viewParamsLayout,
                viewParamsBindGroup,
                swapChainFormat,
                "depth24plus-stencil8"
            )
            renderBundles.add(bundle)
        }
        return renderBundles.toTypedArray()
    }
}

class GLTFNode(val name: String, val mesh: GLTFMesh, val transform: DoubleArray) {
    lateinit var gpuUniforms: Buffer
    lateinit var bindGroup: BindGroup

    fun upload(device: Device) {
        val buf = device.createBuffer(
            BufferDescriptor(
                size = 4 * 4 * 4,
                usage = setOf(BufferUsage.uniform),
                mappedAtCreation = true
            )
        )
        buf.mapFrom(transform.map { it.toFloat() }.toFloatArray())
        buf.unmap()
        gpuUniforms = buf
    }

    fun buildRenderBundle(
        device: Device,
        shaderCache: ShaderCache,
        viewParamsLayout: BindGroupLayout,
        viewParamsBindGroup: BindGroup,
        swapChainFormat: String,
        depthFormat: String,
    ): RenderBundle {
        val nodeParamsLayout = device.createBindGroupLayout(
            BindGroupLayoutDescriptor(
                entries = arrayOf(
                    Entry(
                        binding = 0,
                        visibility = setOf(ShaderStage.vertex),
                        bindingType = BufferBindingLayout(type = BufferBindingType.uniform)
                    )
                )
            )
        )

        bindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = nodeParamsLayout,
                entries = arrayOf(
                    BindGroupEntry(
                        binding = 0,
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
                colorFormats = arrayOf(
                    TextureFormat.of(swapChainFormat) ?: error("fail to get texture format $swapChainFormat")
                ),
                depthStencilFormat = TextureFormat.of(depthFormat) ?: error("fail to get texture format $depthFormat")
            )
        )

        bundleEncoder.setBindGroup(0, viewParamsBindGroup)
        bundleEncoder.setBindGroup(1, bindGroup)

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


class GLTFSampler(private val device: Device, private val samplerNode: GLTF2.Sampler? = null) {

    val sampler = createSampler()

    private fun createSampler(): GPUSampler {
        val magFilter = when (samplerNode?.magFilter) {
            null, GLTFTextureFilter.LINEAR.value -> FilterMode.linear
            else -> FilterMode.nearest
        }
        val minFilter = when (samplerNode?.minFilter) {
            null, GLTFTextureFilter.LINEAR.value -> FilterMode.linear
            else -> FilterMode.nearest
        }

        val wrapS = when (samplerNode?.wrapS) {
            GLTFTextureFilter.REPEAT.value -> AddressMode.repeat
            GLTFTextureFilter.CLAMP_TO_EDGE.value -> AddressMode.clamptoedge
            null -> AddressMode.repeat
            else -> AddressMode.mirrorrepeat
        }

        val wrapT = when (samplerNode?.wrapT) {
            GLTFTextureFilter.REPEAT.value -> AddressMode.repeat
            GLTFTextureFilter.CLAMP_TO_EDGE.value -> AddressMode.clamptoedge
            null -> AddressMode.repeat
            else -> AddressMode.mirrorrepeat
        }

        return device.createSampler(
            SamplerDescriptor(
                magFilter = magFilter,
                minFilter = minFilter,
                addressModeU = wrapS,
                addressModeV = wrapT,
            )
        ).handler
    }
}
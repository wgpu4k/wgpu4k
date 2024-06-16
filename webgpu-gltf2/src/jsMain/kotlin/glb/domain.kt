
package glb

import GLBShaderCache
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupDescriptor.*
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.BufferBindingLayout
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.SamplerBindingLayout
import io.ygdrasil.wgpu.RenderPipelineDescriptor.FragmentState
import io.ygdrasil.wgpu.RenderPipelineDescriptor.VertexState.VertexBufferLayout
import io.ygdrasil.wgpu.examples.helper.GLTFRenderMode
import io.ygdrasil.wgpu.internal.js.GPUBuffer
import io.ygdrasil.wgpu.internal.js.GPUTexture
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import kotlin.math.max

class GLTFPrimitive(
    val indices: GLTFAccessor?,
    val positions: GLTFAccessor,
    val normals: GLTFAccessor?,
    val texcoords: Array<GLTFAccessor>,
    val material: GLTFMaterial,
    _topology: Int,
) {
    val topology: GLTFRenderMode = GLTFRenderMode.of(_topology) ?: error("fail to get topology")

    // Build the primitive render commands into the bundle
    fun buildRenderBundle(
        device: Device,
        shaderCache: GLBShaderCache,
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
            module = ShaderModule(shaderModule),
            entryPoint = "vertex_main",
            buffers = vertexBuffers.toTypedArray()
        )

        var fragmentStage = FragmentState(
            module = ShaderModule(shaderModule),
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
            Buffer(positions.view.gpuBuffer ?: error("fail to get buffer")),
            positions.byteOffset.toLong()
        )
        if (normals != null) {
            bundleEncoder.setVertexBuffer(
                1, Buffer(normals.view.gpuBuffer ?: error("fail to get buffer")), normals.byteOffset.toLong()
            )
        }
        if (texcoords.size > 0) {
            bundleEncoder.setVertexBuffer(
                2,
                Buffer(texcoords[0].view.gpuBuffer ?: error("fail to get buffer")),
                texcoords[0].byteOffset.toLong()
            )
        }
        if (indices != null) {
            val indexFormat = if(indices.componentType == GLTFComponentType.UNSIGNED_SHORT.value) IndexFormat.uint16 else IndexFormat.uint32

            bundleEncoder.setIndexBuffer(
                Buffer(indices.view.gpuBuffer ?: error("fail to get buffer")),
                indexFormat,
                indices.byteOffset.toLong()
            )
            bundleEncoder.drawIndexed(indices.count)
        } else {
            bundleEncoder.draw(positions.count)
        }
    }
}

class GLTFMaterial(material: dynamic, textures: Array<GLTFTexture> = arrayOf()) {
    var baseColorFactor = floatArrayOf(1f, 1f, 1f, 1f)
    var baseColorTexture: GLTFTexture? = null
    private var emissiveFactor = floatArrayOf(0f, 0f, 0f, 1f)
    private var metallicFactor = 1.0f
    private var roughnessFactor = 1.0f
    private lateinit var gpuBuffer: GPUBuffer
    lateinit var bindGroup: BindGroup
    lateinit var bindGroupLayout: BindGroupLayout

    init { // equivalent of constructor -> initializer block in Kotlin
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
        gpuBuffer = buf.handler

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
                    buffer = Buffer(gpuBuffer)
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
                BindGroupEntry(binding = 2, resource = TextureViewBinding(TextureView(it.imageView)))
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

class GLTFTexture(sampler: GLTFSampler, val image: GPUTexture) {
    val gltfsampler = sampler
    val sampler = sampler.sampler
    val imageView = image.createView()
}


class GLTFBuffer(
    val arrayBuffer: ArrayBuffer,
    val size: Int,
    val byteOffset: Int
)

class GLTFBufferView(buffer: GLTFBuffer, view: dynamic) {
    var length = view["byteLength"]
    var byteOffset = buffer.byteOffset
    var byteStride = 0
    var buffer: ByteArray
    var needsUpload = false
    var gpuBuffer: GPUBuffer? = null
    private val usage = mutableSetOf<BufferUsage>()

    init {
        if (view["byteOffset"] !== undefined) {
            this.byteOffset += view["byteOffset"] as Int
        }
        if (view["byteStride"] !== undefined) {
            this.byteStride = view["byteStride"]
        }
        this.buffer = Uint8Array(buffer.arrayBuffer, this.byteOffset, this.length).unsafeCast<ByteArray>()
    }

    fun addUsage(usage: Int) {
        this.usage.add(BufferUsage.of(usage) ?: error("bad usage"))
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
        this.gpuBuffer = buf.handler
        this.needsUpload = false
    }
}

class GLTFMesh(
    val name: String,
    val primitives: Array<GLTFPrimitive>
)

class GLTFAccessor(val view: GLTFBufferView, accessor: dynamic) {
    var count: Int = accessor["count"]
    var componentType: Int = accessor["componentType"]
    var gltfType: String = accessor["type"]
    var numComponents: Int = gltfTypeNumComponents(accessor["type"])
    var numScalars: Int = count * numComponents
    var byteOffset: Int = 0

    init {
        if (accessor["byteOffset"] != null) {
            byteOffset = accessor["byteOffset"] as Int
        }
    }

    val byteStride: Int
        get() {
            val elementSize = gltfTypeSize(GLTFComponentType.of(componentType), gltfType)
            return max(elementSize, view.byteStride)
        }
}

class GLBModel(val nodes: Array<GLTFNode>) {

    fun buildRenderBundles(
        device: Device,
        shaderCache: GLBShaderCache,
        viewParamsLayout: BindGroupLayout,
        viewParamsBindGroup: BindGroup,
        swapChainFormat: String
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
    lateinit var gpuUniforms: GPUBuffer
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
        gpuUniforms = buf.handler
    }

    fun buildRenderBundle(
        device: Device,
        shaderCache: GLBShaderCache,
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
                            buffer = Buffer(gpuUniforms)
                        )
                    )
                )
            )
        )

        val bindGroupLayouts = arrayOf(viewParamsLayout, nodeParamsLayout)

        val bundleEncoder = device.createRenderBundleEncoder(
            RenderBundleEncoderDescriptor(
                colorFormats = arrayOf(TextureFormat.of(swapChainFormat) ?: error("fail to get texture format $swapChainFormat")),
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
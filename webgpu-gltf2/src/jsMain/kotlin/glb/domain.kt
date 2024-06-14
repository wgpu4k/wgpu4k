@file:OptIn(ExperimentalJsExport::class)

package glb

import GLBShaderCache
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupDescriptor.*
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.BufferBindingLayout
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.SamplerBindingLayout
import io.ygdrasil.wgpu.internal.js.*
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import kotlin.math.max

@JsExport
class GLTFMaterial(material: dynamic, textures: Array<GLTFTexture> = arrayOf()) {
    var baseColorFactor = floatArrayOf(1f, 1f, 1f, 1f)
    var baseColorTexture: GLTFTexture? = null
    private var emissiveFactor = floatArrayOf(0f, 0f, 0f, 1f)
    private var metallicFactor = 1.0f
    private var roughnessFactor = 1.0f
    private lateinit var gpuBuffer: GPUBuffer
    lateinit var bindGroup: GPUBindGroup
    lateinit var bindGroupLayout: GPUBindGroupLayout

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

    fun upload(_device: GPUDevice) {
        val device = Device(_device)
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
        ).handler

        this.bindGroupLayout = bindGroupLayout.handler
    }
}

@JsExport
class GLTFTexture(sampler: dynamic, val image: dynamic) {
    val gltfsampler = sampler
    val sampler = sampler.sampler
    val imageView = image.createView()
}

@JsExport
class GLTFBuffer(
    val arrayBuffer: ArrayBuffer,
    val size: Int,
    val byteOffset: Int
)

@JsExport
class GLTFBufferView(buffer: GLTFBuffer, view: dynamic) {
    var length = view["byteLength"]
    var byteOffset = buffer.byteOffset
    var byteStride = 0
    var buffer: ByteArray
    var needsUpload = false
    var gpuBuffer: dynamic? = null
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

    fun upload(_device: dynamic) {
        val device = Device(_device)
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

@JsExport
class GLTFMesh(
    val name: dynamic,
    val primitives: dynamic
)

@JsExport
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
            return max(elementSize, view.byteStride as Int)
        }
}

@JsExport
class GLBModel(val nodes: Array<GLTFNode>) {

    fun buildRenderBundles(
        device: GPUDevice,
        shaderCache: GLBShaderCache,
        viewParamsLayout: GPUBindGroupLayout,
        viewParamsBindGroup: GPUBindGroup,
        swapChainFormat: String
    ): Array<GPURenderBundle> {
        val renderBundles = mutableListOf<GPURenderBundle>()
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

@JsExport
class GLTFNode(val name: String, val mesh: dynamic, val transform: DoubleArray) {
    lateinit var gpuUniforms: GPUBuffer
    lateinit var bindGroup: BindGroup

    fun upload(_device: GPUDevice) {
        val device = Device(_device)
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
        _device: GPUDevice,
        shaderCache: GLBShaderCache,
        viewParamsLayout: GPUBindGroupLayout,
        viewParamsBindGroup: GPUBindGroup,
        swapChainFormat: String,
        depthFormat: String,
    ): GPURenderBundle {
        val device = Device(_device)
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
                        resource = BindGroupDescriptor.BufferBinding(
                            buffer = Buffer(gpuUniforms)
                        )
                    )
                )
            )
        )

        val bindGroupLayouts = arrayOf(viewParamsLayout, nodeParamsLayout.handler)

        val bundleEncoder = device.createRenderBundleEncoder(
            RenderBundleEncoderDescriptor(
                colorFormats = arrayOf(TextureFormat.of(swapChainFormat) ?: error("fail to get texture format $swapChainFormat")),
                depthStencilFormat = TextureFormat.of(depthFormat) ?: error("fail to get texture format $depthFormat")
            )
        )

        bundleEncoder.setBindGroup(0, BindGroup(viewParamsBindGroup))
        bundleEncoder.setBindGroup(1, bindGroup)

        for (primitive in mesh.primitives) {
            primitive.buildRenderBundle(
                _device,
                shaderCache,
                bindGroupLayouts,
                bundleEncoder.handler,
                swapChainFormat,
                depthFormat
            )
        }

        val renderBundle = bundleEncoder.finish()
        return renderBundle.handler
    }
}
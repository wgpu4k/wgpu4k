@file:OptIn(ExperimentalJsExport::class)

package glb

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupDescriptor.*
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.BufferBindingLayout
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.SamplerBindingLayout
import io.ygdrasil.wgpu.internal.js.GPUBindGroup
import io.ygdrasil.wgpu.internal.js.GPUBuffer
import io.ygdrasil.wgpu.internal.js.GPUDevice

@JsExport
class GLTFMaterial(material: dynamic, textures: dynamic) {
    var baseColorFactor = floatArrayOf(1f, 1f, 1f, 1f)
    var baseColorTexture: dynamic? = null
    private var emissiveFactor = floatArrayOf(0f, 0f, 0f, 1f)
    private var metallicFactor = 1.0f
    private var roughnessFactor = 1.0f
    private lateinit var gpuBuffer: GPUBuffer
    lateinit var bindGroup: GPUBindGroup

    init { // equivalent of constructor -> initializer block in Kotlin
        material["pbrMetallicRoughness"]?.let { it ->
            baseColorFactor = it["baseColorFactor"] ?: baseColorFactor
            baseColorTexture = it["baseColorTexture"]?.let { tex -> textures[tex["index"]] }
            metallicFactor = it["metallicFactor"] ?: metallicFactor
            roughnessFactor = it["roughnessFactor"] ?: roughnessFactor
        }
        material["emissiveFactor"]?.let { it ->
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
        buf.mapFrom(emissiveFactor, 4)
        buf.mapFrom(floatArrayOf(metallicFactor, roughnessFactor), 8)
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

        baseColorTexture?.let { it ->
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
    }
}
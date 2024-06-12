package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.*
import io.ygdrasil.wgpu.internal.js.*

// TODO: add unit test
fun map(input: BindGroupLayoutDescriptor): GPUBindGroupLayoutDescriptor = object : GPUBindGroupLayoutDescriptor {
    override var label: String? = input.label ?: undefined
    override var entries: Array<GPUBindGroupLayoutEntry> = input.entries.map { map(it) }.toTypedArray()
}

private fun map(input: BindGroupLayoutDescriptor.Entry): GPUBindGroupLayoutEntry = object : GPUBindGroupLayoutEntry {
    override var binding: GPUIndex32 = input.binding
    override var visibility: GPUShaderStageFlags = input.visibility.toFlagInt()
    override var buffer: GPUBufferBindingLayout? = if (input.bindingType is BufferBindingLayout) {
        object : GPUBufferBindingLayout {
            override var type: String? = input.bindingType.type.stringValue
            override var hasDynamicOffset: Boolean? = input.bindingType.hasDynamicOffset
            override var minBindingSize: GPUSize64? = input.bindingType.minBindingSize
        }
    } else undefined
    override var sampler: GPUSamplerBindingLayout? = if (input.bindingType is SamplerBindingLayout) {
        object : GPUSamplerBindingLayout {
            override var type: String? = input.bindingType.type.stringValue
        }
    } else undefined
    override var texture: GPUTextureBindingLayout?  = if (input.bindingType is TextureBindingLayout) {
        object : GPUTextureBindingLayout {
            override var sampleType: String? = input.bindingType.sampleType.name
            override var viewDimension: String? = input.bindingType.viewDimension.name
            override var multisampled: Boolean? = input.bindingType.multisampled
        }
    } else undefined
    override var storageTexture: GPUStorageTextureBindingLayout? = if (input.bindingType is StorageTextureBindingLayout) {
        object : GPUStorageTextureBindingLayout {
            override var access: String? = input.bindingType.access.name
            override var format: String = input.bindingType.format.name
            override var viewDimension: String? = input.bindingType.viewDimension.name
        }
    } else undefined
    override var externalTexture: GPUExternalTextureBindingLayout? = undefined
}
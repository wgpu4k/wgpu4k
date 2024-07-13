package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.BindGroupLayoutDescriptor
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.*
import io.ygdrasil.wgpu.internal.js.*
import io.ygdrasil.wgpu.toFlagInt

// TODO: add unit test
internal fun map(input: BindGroupLayoutDescriptor): GPUBindGroupLayoutDescriptor =
    createJsObject<GPUBindGroupLayoutDescriptor>().apply {
        if (input.label != null) label = input.label
        entries = input.entries.map { map(it) }.toTypedArray()
    }

private fun map(input: BindGroupLayoutDescriptor.Entry): GPUBindGroupLayoutEntry =
    createJsObject<GPUBindGroupLayoutEntry>().apply {
        binding = input.binding
        visibility = input.visibility.toFlagInt()
        if (input.bindingType is BufferBindingLayout) {
            buffer = createJsObject<GPUBufferBindingLayout>().apply {
                type = input.bindingType.type.stringValue
                hasDynamicOffset = input.bindingType.hasDynamicOffset
                minBindingSize = input.bindingType.minBindingSize
            }
        }
        if (input.bindingType is SamplerBindingLayout) {
            sampler = createJsObject<GPUSamplerBindingLayout>().apply {
                type = input.bindingType.type.stringValue
            }
        }
        if (input.bindingType is TextureBindingLayout) {
            texture = createJsObject<GPUTextureBindingLayout>().apply {
                sampleType = input.bindingType.sampleType.stringValue
                viewDimension = input.bindingType.viewDimension.stringValue
                multisampled = input.bindingType.multisampled
            }
        }
        if (input.bindingType is StorageTextureBindingLayout) {
            storageTexture = createJsObject<GPUStorageTextureBindingLayout>().apply {
                access = input.bindingType.access.stringValue
                format = input.bindingType.format.actualName
                viewDimension = input.bindingType.viewDimension.stringValue
            }
        }
    }
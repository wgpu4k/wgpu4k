package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.BindGroupLayoutDescriptor
import io.ygdrasil.webgpu.BindGroupLayoutDescriptor.Entry.*
import io.ygdrasil.webgpu.internal.js.GPUBindGroupLayoutDescriptor
import io.ygdrasil.webgpu.internal.js.GPUBindGroupLayoutEntry
import io.ygdrasil.webgpu.internal.js.GPUBufferBindingLayout
import io.ygdrasil.webgpu.internal.js.GPUSamplerBindingLayout
import io.ygdrasil.webgpu.internal.js.GPUStorageTextureBindingLayout
import io.ygdrasil.webgpu.internal.js.GPUTextureBindingLayout
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.mapJsArray
import io.ygdrasil.webgpu.internal.js.toJsNumber
import io.ygdrasil.webgpu.toFlagInt

// TODO: add unit test
internal fun map(input: BindGroupLayoutDescriptor): GPUBindGroupLayoutDescriptor =
    createJsObject<GPUBindGroupLayoutDescriptor>().apply {
        if (input.label != null) label = input.label.toJsString()
        entries = input.entries.mapJsArray { map(it) }
    }

private fun map(input: BindGroupLayoutDescriptor.Entry): GPUBindGroupLayoutEntry =
    createJsObject<GPUBindGroupLayoutEntry>().apply {
        binding = input.binding
        visibility = input.visibility.toFlagInt()
        if (input.bindingType is BufferBindingLayout) {
            buffer = createJsObject<GPUBufferBindingLayout>().apply {
                type = input.bindingType.type.value
                hasDynamicOffset = input.bindingType.hasDynamicOffset
                minBindingSize = input.bindingType.minBindingSize.toJsNumber()
            }
        }
        if (input.bindingType is SamplerBindingLayout) {
            sampler = createJsObject<GPUSamplerBindingLayout>().apply {
                type = input.bindingType.type.value
            }
        }
        if (input.bindingType is TextureBindingLayout) {
            texture = createJsObject<GPUTextureBindingLayout>().apply {
                sampleType = input.bindingType.sampleType.value
                viewDimension = input.bindingType.viewDimension.value
                multisampled = input.bindingType.multisampled
            }
        }
        if (input.bindingType is StorageTextureBindingLayout) {
            storageTexture = createJsObject<GPUStorageTextureBindingLayout>().apply {
                access = input.bindingType.access.value
                format = input.bindingType.format.value
                viewDimension = input.bindingType.viewDimension.value
            }
        }
    }
package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUBindGroupLayoutDescriptor
import io.ygdrasil.webgpu.GPUBindGroupLayoutEntry
import io.ygdrasil.webgpu.toFlagULong
import io.ygdrasil.wgpu.WGPUBindGroupLayoutDescriptor
import io.ygdrasil.wgpu.WGPUBindGroupLayoutEntry
import io.ygdrasil.wgpu.WGPUChainedStruct
import io.ygdrasil.wgpu.WGPUNativeSType_BindGroupEntryExtras

fun MemoryAllocator.map(input: GPUBindGroupLayoutDescriptor): WGPUBindGroupLayoutDescriptor =
    WGPUBindGroupLayoutDescriptor.allocate(this).also { output ->
        map(input.label, output.label)

        if (input.entries.isNotEmpty()) {
            output.entryCount = input.entries.size.toULong()
            val entries = WGPUBindGroupLayoutEntry.allocateArray(this, input.entries.size.toUInt()) { index, entry ->
                map(input.entries[index.toInt()], entry)

            }
            output.entries = entries
        }
    }

fun MemoryAllocator.map(input: GPUBindGroupLayoutEntry, output: WGPUBindGroupLayoutEntry) {

    output.binding = input.binding
    output.visibility = input.visibility.toFlagULong()

    when (val bindingType = input.bindingType) {
        is BindGroupLayoutDescriptor.Entry.BufferBindingLayout -> {
            val buffer = output.buffer
            buffer.hasDynamicOffset = bindingType.hasDynamicOffset
            buffer.minBindingSize = bindingType.minBindingSize
            buffer.type = bindingType.type.value

            val chain = WGPUChainedStruct.allocate(this)
            chain.sType = WGPUNativeSType_BindGroupEntryExtras
            buffer.nextInChain = chain.handler
        }

        is BindGroupLayoutDescriptor.Entry.SamplerBindingLayout -> {
            val sampler = output.sampler
            sampler.type = bindingType.type.value.toUInt()

            val chain = WGPUChainedStruct.allocate(this)
            chain.sType = WGPUNativeSType_BindGroupEntryExtras
            sampler.nextInChain = chain.handler
        }

        is BindGroupLayoutDescriptor.Entry.TextureBindingLayout -> {
            val texture = output.texture
            texture.multisampled = bindingType.multisampled
            texture.sampleType = bindingType.sampleType.value
            texture.viewDimension = bindingType.viewDimension.value

            val chain = WGPUChainedStruct.allocate(this)
            chain.sType = WGPUNativeSType_BindGroupEntryExtras
            texture.nextInChain = chain.handler
        }

        is BindGroupLayoutDescriptor.Entry.StorageTextureBindingLayout -> {
            val storageTexture = output.storageTexture
            storageTexture.access = bindingType.access.value
            storageTexture.format = bindingType.format.value
            storageTexture.viewDimension = bindingType.viewDimension.value

            val chain = WGPUChainedStruct.allocate(this)
            chain.sType = WGPUNativeSType_BindGroupEntryExtras
            storageTexture.nextInChain = chain.handler

        }
    }

}
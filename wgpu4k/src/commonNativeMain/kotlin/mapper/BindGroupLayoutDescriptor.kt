package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor
import io.ygdrasil.wgpu.toFlagULong
import webgpu.WGPUBindGroupLayoutDescriptor
import webgpu.WGPUBindGroupLayoutEntry
import webgpu.WGPUChainedStruct
import webgpu.WGPUNativeSType_BindGroupEntryExtras

fun MemoryAllocator.map(input: BindGroupLayoutDescriptor): WGPUBindGroupLayoutDescriptor =
    WGPUBindGroupLayoutDescriptor.allocate(this).also { output ->
        if (input.label != null) map(input.label, output.label)

        if (input.entries.isNotEmpty()) {
            output.entryCount = input.entries.size.toULong()
            val entries = WGPUBindGroupLayoutEntry.allocateArray(this, input.entries.size.toUInt()) { index, entry ->
                map(input.entries[index.toInt()], entry)

            }
            output.entries = entries
        }
    }

fun MemoryAllocator.map(input: BindGroupLayoutDescriptor.Entry, output: WGPUBindGroupLayoutEntry) {

    output.binding = input.binding
    output.visibility = input.visibility.toFlagULong()

    when (val bindingType = input.bindingType) {
        is BindGroupLayoutDescriptor.Entry.BufferBindingLayout -> {
            val buffer = output.buffer
            buffer.hasDynamicOffset = bindingType.hasDynamicOffset
            buffer.minBindingSize = bindingType.minBindingSize
            buffer.type = bindingType.type.uValue

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
            texture.sampleType = bindingType.sampleType.uValue
            texture.viewDimension = bindingType.viewDimension.uValue

            val chain = WGPUChainedStruct.allocate(this)
            chain.sType = WGPUNativeSType_BindGroupEntryExtras
            texture.nextInChain = chain.handler
        }

        is BindGroupLayoutDescriptor.Entry.StorageTextureBindingLayout -> {
            val storageTexture = output.storageTexture
            storageTexture.access = bindingType.access.uValue
            storageTexture.format = bindingType.format.uValue
            storageTexture.viewDimension = bindingType.viewDimension.uValue

            val chain = WGPUChainedStruct.allocate(this)
            chain.sType = WGPUNativeSType_BindGroupEntryExtras
            storageTexture.nextInChain = chain.handler

        }
    }

}
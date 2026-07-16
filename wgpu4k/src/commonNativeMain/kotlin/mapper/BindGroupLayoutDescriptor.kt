package io.ygdrasil.webgpu.mapper

import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUBindGroupLayoutDescriptor
import io.ygdrasil.webgpu.GPUBindGroupLayoutEntry
import io.ygdrasil.wgpu.WGPUBindGroupLayoutDescriptor
import io.ygdrasil.wgpu.WGPUBindGroupLayoutEntry
import io.ygdrasil.wgpu.WGPUChainedStruct
import io.ygdrasil.wgpu.WGPUSType_BindGroupEntryExtras
import io.ygdrasil.wgpu.toUInt

fun MemoryAllocator.map(input: GPUBindGroupLayoutDescriptor): WGPUBindGroupLayoutDescriptor =
    WGPUBindGroupLayoutDescriptor.allocate(this).also { output ->
        map(input.label, output.label)

        if (input.entries.isNotEmpty()) {
            output.entryCount = input.entries.size.toULong()
            val entries = WGPUBindGroupLayoutEntry.allocateArray(this, input.entries.size.toUInt()) { index, entry ->
                map(input.entries[index.toInt()], entry)

            }
            output.entries = WGPUBindGroupLayoutEntry(entries.handler)
        }
    }

fun MemoryAllocator.map(input: GPUBindGroupLayoutEntry, output: WGPUBindGroupLayoutEntry) {

    output.binding = input.binding
    output.visibility = input.visibility.value

    input.buffer?.let {  bindingType ->
        val buffer = output.buffer
        buffer.hasDynamicOffset = bindingType.hasDynamicOffset.toUInt()
        buffer.minBindingSize = bindingType.minBindingSize
        buffer.type = bindingType.type.value

        val chain = WGPUChainedStruct.allocate(this)
        chain.sType = WGPUSType_BindGroupEntryExtras
        buffer.nextInChain = chain
    }

    input.sampler?.let { bindingType ->
        val sampler = output.sampler
        sampler.type = bindingType.type.value

        val chain = WGPUChainedStruct.allocate(this)
        chain.sType = WGPUSType_BindGroupEntryExtras
        sampler.nextInChain = chain
    }

    input.texture?.let { bindingType ->
        val texture = output.texture
        texture.multisampled = bindingType.multisampled.toUInt()
        texture.sampleType = bindingType.sampleType.value
        texture.viewDimension = bindingType.viewDimension.value

        val chain = WGPUChainedStruct.allocate(this)
        chain.sType = WGPUSType_BindGroupEntryExtras
        texture.nextInChain = chain
    }

    input.storageTexture?.let { bindingType ->
        val storageTexture = output.storageTexture
        storageTexture.access = bindingType.access.value
        storageTexture.format = bindingType.format.value
        storageTexture.viewDimension = bindingType.viewDimension.value

        val chain = WGPUChainedStruct.allocate(this)
        chain.sType = WGPUSType_BindGroupEntryExtras
        storageTexture.nextInChain = chain
    }

}

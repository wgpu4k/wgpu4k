@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.BindGroupLayoutDescriptor
import io.ygdrasil.wgpu.internal.toUInt
import io.ygdrasil.wgpu.toFlagUInt
import kotlinx.cinterop.*
import webgpu.WGPUBindGroupLayoutDescriptor
import webgpu.WGPUBindGroupLayoutEntry
import webgpu.WGPUChainedStruct
import webgpu.WGPUSType_BindGroupEntryExtras

internal fun Arena.map(input: BindGroupLayoutDescriptor) = alloc<WGPUBindGroupLayoutDescriptor>().also { output ->
    if (input.label != null) output.label = input.label.cstr.getPointer(this)

    if (input.entries.isNotEmpty()) {
        output.entryCount = input.entries.size.toULong()
        val entries = allocArray<WGPUBindGroupLayoutEntry>(input.entries.size)
        input.entries.forEachIndexed { index, entry ->
            map(entry, entries[index])
        }
        output.entries = entries
    }
}

private fun Arena.map(input: BindGroupLayoutDescriptor.Entry, output: WGPUBindGroupLayoutEntry) {

    output.binding = input.binding.toUInt()
    output.visibility = input.visibility.toFlagUInt()

    when (val bindingType = input.bindingType) {
        is BindGroupLayoutDescriptor.Entry.BufferBindingLayout -> {
            val buffer = output.buffer
            buffer.hasDynamicOffset = bindingType.hasDynamicOffset.toUInt()
            buffer.minBindingSize = bindingType.minBindingSize.toULong()
            buffer.type = bindingType.type.value.toUInt()

            val chain = alloc<WGPUChainedStruct>()
            chain.sType = WGPUSType_BindGroupEntryExtras
            buffer.nextInChain = chain.ptr
        }

        is BindGroupLayoutDescriptor.Entry.SamplerBindingLayout -> {
            val sampler = output.sampler
            sampler.type = bindingType.type.value.toUInt()

            val chain = alloc<WGPUChainedStruct>()
            chain.sType = WGPUSType_BindGroupEntryExtras
            sampler.nextInChain = chain.ptr
        }

        is BindGroupLayoutDescriptor.Entry.TextureBindingLayout -> {
            val texture = output.texture
            texture.multisampled = bindingType.multisampled.toUInt()
            texture.sampleType = bindingType.sampleType.value.toUInt()
            texture.viewDimension = bindingType.viewDimension.value.toUInt()

            val chain = alloc<WGPUChainedStruct>()
            chain.sType = WGPUSType_BindGroupEntryExtras
            texture.nextInChain = chain.ptr
        }

        is BindGroupLayoutDescriptor.Entry.StorageTextureBindingLayout -> {
            val storageTexture = output.storageTexture
            storageTexture.access = bindingType.access.value.toUInt()
            storageTexture.format = bindingType.format.value.toUInt()
            storageTexture.viewDimension = bindingType.viewDimension.value.toUInt()

            val chain = alloc<WGPUChainedStruct>()
            chain.sType = WGPUSType_BindGroupEntryExtras
            storageTexture.nextInChain = chain.ptr

        }
    }

}
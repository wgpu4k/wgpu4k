package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUBindGroupLayoutDescriptor
import io.ygdrasil.webgpu.GPUBindGroupLayoutEntry
import io.ygdrasil.webgpu.WGPUBindGroupLayoutDescriptor
import io.ygdrasil.webgpu.WGPUBindGroupLayoutEntry
import io.ygdrasil.webgpu.WGPUBufferBindingLayout
import io.ygdrasil.webgpu.WGPUSamplerBindingLayout
import io.ygdrasil.webgpu.WGPUStorageTextureBindingLayout
import io.ygdrasil.webgpu.WGPUTextureBindingLayout
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.mapJsArray
import io.ygdrasil.webgpu.toFlagInt

// TODO: add unit test
internal fun map(input: GPUBindGroupLayoutDescriptor): WGPUBindGroupLayoutDescriptor =
    createJsObject<WGPUBindGroupLayoutDescriptor>().apply {
        label = input.label
        entries = input.entries.mapJsArray {
            val entry: WGPUBindGroupLayoutEntry = map(it)
            entry
        }
    }

private fun map(input: GPUBindGroupLayoutEntry): WGPUBindGroupLayoutEntry =
    createJsObject<WGPUBindGroupLayoutEntry>().apply {
        binding = input.binding
        visibility = input.visibility.toFlagInt()
        input.buffer?.let { input ->
            buffer = createJsObject<WGPUBufferBindingLayout>().apply {
                type = input.type.value
                hasDynamicOffset = input.hasDynamicOffset
                minBindingSize = input.minBindingSize
            }
        }
        input.sampler?.let { input ->
            sampler = createJsObject<WGPUSamplerBindingLayout>().apply {
                type = input.type.value
            }
        }
        input.texture?.let { input ->
            texture = createJsObject<WGPUTextureBindingLayout>().apply {
                sampleType = input.sampleType.value
                viewDimension = input.viewDimension.value
                multisampled = input.multisampled
            }
        }
        input.storageTexture?.let { input ->
            storageTexture = createJsObject<WGPUStorageTextureBindingLayout>().apply {
                access = input.access.value
                format = input.format.value
                viewDimension = input.viewDimension.value
            }
        }
    }
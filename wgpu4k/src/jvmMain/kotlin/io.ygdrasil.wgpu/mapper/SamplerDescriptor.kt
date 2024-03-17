package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.SamplerDescriptor
import io.ygdrasil.wgpu.internal.jvm.WGPUSamplerDescriptor
import io.ygdrasil.wgpu.mapper

internal val samplerDescriptorMapper = mapper<SamplerDescriptor, WGPUSamplerDescriptor> { }
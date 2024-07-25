@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.QuerySetDescriptor
import kotlinx.cinterop.Arena
import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.WGPUQuerySetDescriptor

internal fun Arena.map(input: QuerySetDescriptor): WGPUQuerySetDescriptor {
    TODO()
}
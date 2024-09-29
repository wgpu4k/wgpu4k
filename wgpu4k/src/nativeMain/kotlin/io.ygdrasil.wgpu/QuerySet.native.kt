@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.native.WGPUQuerySet

// TODO to implement
actual class QuerySet(internal val handler: WGPUQuerySet)
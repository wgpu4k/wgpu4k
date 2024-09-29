@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.native.WGPURenderBundle

actual class RenderBundle(internal val handler: WGPURenderBundle)
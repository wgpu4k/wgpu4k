@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.WGPURenderBundle

actual class RenderBundle(internal val handler: WGPURenderBundle)
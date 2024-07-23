@file:OptIn(ExperimentalForeignApi::class)

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi

expect fun nothing()

expect fun initwgpu(metalLayer: COpaquePointer)
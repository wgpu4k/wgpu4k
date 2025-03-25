package io.ygdrasil.webgpu


fun GPUVertexFormat.sizeInBytes(): Int = when (this) {
    GPUVertexFormat.Uint8x2, GPUVertexFormat.Sint8x2, GPUVertexFormat.Unorm8x2, GPUVertexFormat.Snorm8x2 -> 2
    GPUVertexFormat.Uint8x4, GPUVertexFormat.Sint8x4, GPUVertexFormat.Unorm8x4, GPUVertexFormat.Snorm8x4, GPUVertexFormat.Uint16x2, GPUVertexFormat.Sint16x2, GPUVertexFormat.Unorm16x2, GPUVertexFormat.Snorm16x2, GPUVertexFormat.Float16x2, GPUVertexFormat.Float32, GPUVertexFormat.Uint32, GPUVertexFormat.Sint32 -> 4
    GPUVertexFormat.Uint16x4, GPUVertexFormat.Sint16x4, GPUVertexFormat.Unorm16x4, GPUVertexFormat.Snorm16x4, GPUVertexFormat.Float16x4, GPUVertexFormat.Float32x2, GPUVertexFormat.Uint32x2, GPUVertexFormat.Sint32x2 -> 8
    GPUVertexFormat.Float32x3, GPUVertexFormat.Uint32x3, GPUVertexFormat.Sint32x3 -> 12
    GPUVertexFormat.Float32x4, GPUVertexFormat.Uint32x4, GPUVertexFormat.Sint32x4 -> 16
    else -> error("Unsupported vertex format $this")
}

fun GPUVertexFormat.components(): Int = when (this) {
    GPUVertexFormat.Float32, GPUVertexFormat.Uint32, GPUVertexFormat.Sint32 -> 1
    GPUVertexFormat.Uint8x2, GPUVertexFormat.Sint8x2, GPUVertexFormat.Unorm8x2, GPUVertexFormat.Snorm8x2, GPUVertexFormat.Uint16x2, GPUVertexFormat.Sint16x2, GPUVertexFormat.Unorm16x2, GPUVertexFormat.Snorm16x2, GPUVertexFormat.Float16x2, GPUVertexFormat.Float32x2, GPUVertexFormat.Uint32x2, GPUVertexFormat.Sint32x2 -> 2
    GPUVertexFormat.Float32x3, GPUVertexFormat.Uint32x3, GPUVertexFormat.Sint32x3 -> 3
    GPUVertexFormat.Uint8x4, GPUVertexFormat.Sint8x4, GPUVertexFormat.Unorm8x4, GPUVertexFormat.Snorm8x4, GPUVertexFormat.Uint16x4, GPUVertexFormat.Sint16x4, GPUVertexFormat.Unorm16x4, GPUVertexFormat.Snorm16x4, GPUVertexFormat.Float16x4, GPUVertexFormat.Float32x4, GPUVertexFormat.Uint32x4, GPUVertexFormat.Sint32x4 -> 4
    else -> error("Unsupported vertex format $this")
}
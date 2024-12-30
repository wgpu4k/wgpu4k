package io.ygdrasil.webgpu


fun VertexFormat.sizeInBytes(): Int = when (this) {
    VertexFormat.Uint8, VertexFormat.Sint8, VertexFormat.Unorm8, VertexFormat.Snorm8 -> 1
    VertexFormat.Uint8x2, VertexFormat.Sint8x2, VertexFormat.Unorm8x2, VertexFormat.Snorm8x2, VertexFormat.Uint16, VertexFormat.Sint16, VertexFormat.Unorm16, VertexFormat.Snorm16, VertexFormat.Float16 -> 2
    VertexFormat.Uint8x4, VertexFormat.Sint8x4, VertexFormat.Unorm8x4, VertexFormat.Snorm8x4, VertexFormat.Uint16x2, VertexFormat.Sint16x2, VertexFormat.Unorm16x2, VertexFormat.Snorm16x2, VertexFormat.Float16x2, VertexFormat.Float32, VertexFormat.Uint32, VertexFormat.Sint32, VertexFormat.Unorm8x4BGRA -> 4
    VertexFormat.Uint16x4, VertexFormat.Sint16x4, VertexFormat.Unorm16x4, VertexFormat.Snorm16x4, VertexFormat.Float16x4, VertexFormat.Float32x2, VertexFormat.Uint32x2, VertexFormat.Sint32x2 -> 8
    VertexFormat.Float32x3, VertexFormat.Uint32x3, VertexFormat.Sint32x3 -> 12
    VertexFormat.Float32x4, VertexFormat.Uint32x4, VertexFormat.Sint32x4, VertexFormat.Unorm1010102 -> 16
    else -> error("Unsupported vertex format $this")
}

fun VertexFormat.components(): Int = when (this) {
    VertexFormat.Uint8, VertexFormat.Sint8, VertexFormat.Unorm8, VertexFormat.Snorm8, VertexFormat.Uint16, VertexFormat.Sint16, VertexFormat.Unorm16, VertexFormat.Snorm16, VertexFormat.Float16, VertexFormat.Float32, VertexFormat.Uint32, VertexFormat.Sint32 -> 1
    VertexFormat.Uint8x2, VertexFormat.Sint8x2, VertexFormat.Unorm8x2, VertexFormat.Snorm8x2, VertexFormat.Uint16x2, VertexFormat.Sint16x2, VertexFormat.Unorm16x2, VertexFormat.Snorm16x2, VertexFormat.Float16x2, VertexFormat.Float32x2, VertexFormat.Uint32x2, VertexFormat.Sint32x2 -> 2
    VertexFormat.Float32x3, VertexFormat.Uint32x3, VertexFormat.Sint32x3 -> 3
    VertexFormat.Uint8x4, VertexFormat.Sint8x4, VertexFormat.Unorm8x4, VertexFormat.Snorm8x4, VertexFormat.Uint16x4, VertexFormat.Sint16x4, VertexFormat.Unorm16x4, VertexFormat.Snorm16x4, VertexFormat.Float16x4, VertexFormat.Float32x4, VertexFormat.Uint32x4, VertexFormat.Sint32x4, VertexFormat.Unorm1010102, VertexFormat.Unorm8x4BGRA -> 4
    else -> error("Unsupported vertex format $this")
}
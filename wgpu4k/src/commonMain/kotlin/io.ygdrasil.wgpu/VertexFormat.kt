package io.ygdrasil.wgpu

enum class VertexFormat(
    override val `value`: Int,
    val sizeInByte: Int,
) : EnumerationWithValue {
    uint8x2(1, 2),
    uint8x4(2, 4),
    sint8x2(3, 2),
    sint8x4(4, 4),
    unorm8x2(5, 2),
    unorm8x4(6, 4),
    snorm8x2(7, 2),
    snorm8x4(8, 4),
    uint16x2(9, 4),
    uint16x4(10, 8),
    sint16x2(11, 4),
    sint16x4(12, 8),
    unorm16x2(13, 4),
    unorm16x4(14, 8),
    snorm16x2(15, 4),
    snorm16x4(16, 8),
    float16x2(17, 4),
    float16x4(18, 8),
    float32(19, 4),
    float32x2(20, 8),
    float32x3(21, 12),
    float32x4(22, 16),
    uint32(23, 4),
    uint32x2(24, 8),
    uint32x3(25, 12),
    uint32x4(26, 16),
    sint32(27, 4),
    sint32x2(28, 8),
    sint32x3(29, 12),
    sint32x4(30, 16),
    ;

    companion object {
        fun of(`value`: Int): VertexFormat? = entries.find {
            it.value == value
        }
    }
}
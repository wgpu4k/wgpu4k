package io.ygdrasil.webgpu

enum class VertexFormat(
    override val `value`: Int,
    val sizeInByte: Int,
    /** The number of components of the format. */
    val components: Int,
) : EnumerationWithValue {
    uint8x2(1, 2, 2),
    uint8x4(2, 4, 4),
    sint8x2(3, 2, 2),
    sint8x4(4, 4, 4),
    unorm8x2(5, 2, 2),
    unorm8x4(6, 4, 4),
    snorm8x2(7, 2, 2),
    snorm8x4(8, 4, 4),
    uint16x2(9, 4, 2),
    uint16x4(10, 8, 4),
    sint16x2(11, 4, 2),
    sint16x4(12, 8, 4),
    unorm16x2(13, 4, 2),
    unorm16x4(14, 8, 4),
    snorm16x2(15, 4, 2),
    snorm16x4(16, 8, 4),
    float16x2(17, 4, 2),
    float16x4(18, 8, 4),
    float32(19, 4, 1),
    float32x2(20, 8, 2),
    float32x3(21, 12, 3),
    float32x4(22, 16, 4),
    uint32(23, 4, 1),
    uint32x2(24, 8, 2),
    uint32x3(25, 12, 3),
    uint32x4(26, 16, 4),
    sint32(27, 4, 1),
    sint32x2(28, 8, 2),
    sint32x3(29, 12, 3),
    sint32x4(30, 16, 4),
    ;

    companion object {
        fun of(`value`: Int): VertexFormat? = entries.find {
            it.value == value
        }
    }
}
package io.ygdrasil.wgpu

enum class VertexFormat(
    override val `value`: Int,
   // override val sizeInByte: Int,
) : EnumerationWithValue {
    uint8x2(1),
    uint8x4(2),
    sint8x2(3),
    sint8x4(4),
    unorm8x2(5),
    unorm8x4(6),
    snorm8x2(7),
    snorm8x4(8),
    uint16x2(9),
    uint16x4(10),
    sint16x2(11),
    sint16x4(12),
    unorm16x2(13),
    unorm16x4(14),
    snorm16x2(15),
    snorm16x4(16),
    float16x2(17),
    float16x4(18),
    float32(19),
    float32x2(20),
    float32x3(21),
    float32x4(22),
    uint32(23),
    uint32x2(24),
    uint32x3(25),
    uint32x4(26),
    sint32(27),
    sint32x2(28),
    sint32x3(29),
    sint32x4(30),
    ;

    companion object {
        fun of(`value`: Int): VertexFormat? = entries.find {
            it.value == value
        }
    }
}
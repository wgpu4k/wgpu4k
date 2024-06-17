package io.ygdrasil.wgpu.examples.helper.glb

import korlibs.io.lang.invalidOp
import korlibs.math.geom.AABB3D
import korlibs.math.geom.Vector3

enum class VarKind(val bytesSize: Int) {
    //BYTE(1), UNSIGNED_BYTE(1), SHORT(2), UNSIGNED_SHORT(2), INT(4), FLOAT(4) // @TODO: This cause problems on Kotlin/Native Objective-C header.h
    TBOOL(1), TBYTE(1), TUNSIGNED_BYTE(1), TSHORT(2), TUNSIGNED_SHORT(2), TINT(4), TFLOAT(4)
    //, TUNSIGNED_INT(4)
}


enum class VarType(val kind: VarKind, val elementCount: Int, val isMatrix: Boolean = false) {
    TVOID(VarKind.TBYTE, elementCount = 0),

    Mat2(VarKind.TFLOAT, elementCount = 4, isMatrix = true),
    Mat3(VarKind.TFLOAT, elementCount = 9, isMatrix = true),
    Mat4(VarKind.TFLOAT, elementCount = 16, isMatrix = true),

    //TODO: need to have a way of indicating Float/Int/UInt variations + more types of sampler to add
    Sampler1D(VarKind.TINT, elementCount = 1),
    Sampler2D(VarKind.TINT, elementCount = 1),
    Sampler3D(VarKind.TINT, elementCount = 1),
    SamplerCube(VarKind.TINT, elementCount = 1),

    Int1(VarKind.TINT, elementCount = 1),

    Float1(VarKind.TFLOAT, elementCount = 1),
    Float2(VarKind.TFLOAT, elementCount = 2),
    Float3(VarKind.TFLOAT, elementCount = 3),
    Float4(VarKind.TFLOAT, elementCount = 4),

    Short1(VarKind.TSHORT, elementCount = 1),
    Short2(VarKind.TSHORT, elementCount = 2),
    Short3(VarKind.TSHORT, elementCount = 3),
    Short4(VarKind.TSHORT, elementCount = 4),

    Bool1(VarKind.TBOOL, elementCount = 1),
    Bool2(VarKind.TBOOL, elementCount = 2),
    Bool3(VarKind.TBOOL, elementCount = 3),
    Bool4(VarKind.TBOOL, elementCount = 4),

    @Deprecated("Use UByte4 instead")
    Byte4(VarKind.TUNSIGNED_BYTE, elementCount = 4), // OLD: Is this right?

    SByte1(VarKind.TBYTE, elementCount = 1),
    SByte2(VarKind.TBYTE, elementCount = 2),
    SByte3(VarKind.TBYTE, elementCount = 3),
    SByte4(VarKind.TBYTE, elementCount = 4),

    UByte1(VarKind.TUNSIGNED_BYTE, elementCount = 1),
    UByte2(VarKind.TUNSIGNED_BYTE, elementCount = 2),
    UByte3(VarKind.TUNSIGNED_BYTE, elementCount = 3),
    UByte4(VarKind.TUNSIGNED_BYTE, elementCount = 4),

    SShort1(VarKind.TSHORT, elementCount = 1),
    SShort2(VarKind.TSHORT, elementCount = 2),
    SShort3(VarKind.TSHORT, elementCount = 3),
    SShort4(VarKind.TSHORT, elementCount = 4),

    UShort1(VarKind.TUNSIGNED_SHORT, elementCount = 1),
    UShort2(VarKind.TUNSIGNED_SHORT, elementCount = 2),
    UShort3(VarKind.TUNSIGNED_SHORT, elementCount = 3),
    UShort4(VarKind.TUNSIGNED_SHORT, elementCount = 4),

    SInt1(VarKind.TINT, elementCount = 1),
    SInt2(VarKind.TINT, elementCount = 2),
    SInt3(VarKind.TINT, elementCount = 3),
    SInt4(VarKind.TINT, elementCount = 4),
    ;

    fun withElementCount(length: Int): VarType {
        return when (kind) {
            VarKind.TBYTE -> BYTE(length)
            VarKind.TUNSIGNED_BYTE -> UBYTE(length)
            VarKind.TSHORT -> SHORT(length)
            VarKind.TUNSIGNED_SHORT -> USHORT(length)
            VarKind.TINT -> INT(length)
            VarKind.TFLOAT -> FLOAT(length)
            else -> TODO()
        }
    }

    val isSampler: Boolean get() = this == Sampler1D || this == Sampler2D || this == Sampler3D || this == SamplerCube

    val bytesSize: Int = kind.bytesSize * elementCount

    companion object {
        @Deprecated("", ReplaceWith("VarType.Sampler2D", "korlibs.graphics.shader.VarType"))
        val TextureUnit get() = Sampler2D

        fun BOOL(count: Int) =
            when (count) { 0 -> TVOID; 1 -> Bool1; 2 -> Bool2; 3 -> Bool3; 4 -> Bool4; else -> invalidOp; }

        fun BYTE(count: Int) =
            when (count) { 0 -> TVOID; 1 -> SByte1; 2 -> SByte2; 3 -> SByte3; 4 -> SByte4; else -> invalidOp; }

        fun UBYTE(count: Int) =
            when (count) { 0 -> TVOID; 1 -> UByte1; 2 -> UByte2; 3 -> UByte3; 4 -> UByte4; else -> invalidOp; }

        fun SHORT(count: Int) =
            when (count) { 0 -> TVOID; 1 -> SShort1; 2 -> SShort2; 3 -> SShort3; 4 -> SShort4; else -> invalidOp; }

        fun USHORT(count: Int) =
            when (count) { 0 -> TVOID; 1 -> UShort1; 2 -> UShort2; 3 -> UShort3; 4 -> UShort4; else -> invalidOp; }

        fun INT(count: Int) =
            when (count) { 0 -> TVOID; 1 -> SInt1; 2 -> SInt2; 3 -> SInt3; 4 -> SInt4; else -> invalidOp; }

        fun FLOAT(count: Int) =
            when (count) { 0 -> TVOID; 1 -> Float1; 2 -> Float2; 3 -> Float3; 4 -> Float4; else -> invalidOp; }

        fun MAT(count: Int) =
            when (count) { 0 -> TVOID; 1 -> Float1; 2 -> Mat2; 3 -> Mat3; 4 -> Mat4; else -> invalidOp; }
    }

}



/** Encoded in 2 bits */
inline class AGIndexType(val ordinal: Int) {
    val bytesSize: Int get() = when (this) {
        NONE -> 0
        UBYTE -> 1
        USHORT -> 2
        UINT -> 4
        else -> -1
    }

    companion object {
        val NONE = AGIndexType(0)
        val UBYTE = AGIndexType(1)
        val USHORT = AGIndexType(2)
        val UINT = AGIndexType(3) // https://developer.mozilla.org/en-US/docs/Web/API/WebGLRenderingContext/drawElements

        fun fromBytesSize(bytesSize: Int): AGIndexType = when (bytesSize) {
            0 -> NONE
            1 -> UBYTE
            2 -> USHORT
            4 -> UINT
            else -> AGIndexType(-1)
        }
        operator fun get(kind: VarKind): AGIndexType = fromBytesSize(kind.bytesSize)
    }

    override fun toString(): String = when (this) {
        NONE -> "null"
        UBYTE -> "UBYTE"
        USHORT -> "USHORT"
        UINT -> "UINT"
        else -> "-"
    }
}

fun List<AABB3D>.combineBounds(): AABB3D {
    var min = Vector3(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    var max = Vector3(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY)
    for (bounds in this) {
        min = Vector3.func { kotlin.math.min(min[it], bounds.min[it]) }
        max = Vector3.func { kotlin.math.max(max[it], bounds.max[it]) }
    }
    return AABB3D(min, max)
}
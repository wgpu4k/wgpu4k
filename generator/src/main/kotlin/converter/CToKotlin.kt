package converter

import domain.CLibraryModel

fun CLibraryModel.Type.variableType(): String = when(this) {
    is CLibraryModel.Reference.StructureField -> "val"
    else -> "var"
}


internal fun CLibraryModel.Primitive.toPrimitiveKotlinType(): String = when (this) {
    is CLibraryModel.Primitive.Int32 -> "Int"
    is CLibraryModel.Primitive.Int64 -> "Long"
    CLibraryModel.Primitive.Bool -> "Boolean"
    CLibraryModel.Primitive.UInt64 -> "ULong"
    CLibraryModel.Primitive.Float64 -> "Double"
    CLibraryModel.Primitive.Float32 -> "Float"
    CLibraryModel.Primitive.UInt32 -> "UInt"
    CLibraryModel.Primitive.UInt16 -> "UShort"
}

internal fun CLibraryModel.Primitive.toPrimitiveDefaultValue(): String = when (this) {
    is CLibraryModel.Primitive.Int32 -> "0"
    is CLibraryModel.Primitive.Int64 -> "0L"
    CLibraryModel.Primitive.Bool -> "0"
    CLibraryModel.Primitive.UInt64 -> "0uL"
    CLibraryModel.Primitive.Float64 -> "0.0"
    CLibraryModel.Primitive.Float32 -> "0f"
    CLibraryModel.Primitive.UInt32 -> "0u"
    CLibraryModel.Primitive.UInt16 -> "0u"
}



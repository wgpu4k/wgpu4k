package domain

import convertToKotlinCallbackStructureName
import convertToKotlinClassName
import converter.toPrimitiveKotlinType


data class CLibraryModel(
    val pointers: List<Pointer>,
    val functions: List<Function>,
    val enumerations: List<Enumeration>,
    val structures: List<Structure>,
    val callbacks: List<Callback>,
) {

    class Enumeration(val name: String, val values: List<Pair<String, Int>>, val size: Int = 32)

    sealed interface Type
    sealed class Reference(val name: String) : Type {
        object OpaquePointer : Reference("NativeAddress")
        class Pointer(name: String) : Reference(name)
        class Callback(name: String) : Reference(name)
        class Structure(name: String) : Reference(name)
        class StructureField(name: String) : Reference(name)
        class Enumeration(name: String) : Reference(name)
        object CString : Reference("CString")
    }

    class Array(val subType: Type, val isMutable: Boolean) : Type
    sealed interface Primitive : Type {
        object Bool : Primitive
        object UInt32 : Primitive
        object UInt16 : Primitive
        object Int32 : Primitive
        object UInt64 : Primitive
        object Int64 : Primitive
        object Float32 : Primitive
        object Float64 : Primitive
    }
    object Void : Type

    data class Pointer(val name: String)
    data class Function(val name: String, val returnType: Type, val args: List<Pair<String, Type>>)

    data class Structure(
        val name: String,
        val members: List<StructureField>,
        val size: Int? = null,
        val alignment: Int? = null,
        val padding: Int? = null,
    )

    data class StructureField(
        val name: String,
        val type: Type,
        val option: String,
        val alignment: Int? = type.getSize(),
        val size: Int? = type.getSize(),
        val padding: Int? = null
    )

    data class Callback(
        val name: String,
        val members: List<Pair<String, Type>>,
    )

}

internal fun CLibraryModel.Type.toFunctionKotlinType(): String = when (this) {
    is CLibraryModel.Reference.Callback -> "CallbackHolder<${name}>"
    is CLibraryModel.Reference -> name
    is CLibraryModel.Array -> "ArrayHolder<${subType.toFunctionKotlinType()}>"
    is CLibraryModel.Primitive -> this.toPrimitiveKotlinType()
    CLibraryModel.Void -> "Unit"
}

internal fun CLibraryModel.Type.toCallbackKotlinType(): String = when (this) {
    is CLibraryModel.Reference.Enumeration -> toFunctionKotlinType()
    is CLibraryModel.Reference -> "${toFunctionKotlinType()}?"
    else -> toFunctionKotlinType()
}


internal fun String?.toCType(isPointer: Boolean, isMutable: Boolean): CLibraryModel.Type {
    return when {
        this == null -> CLibraryModel.Void
        startsWith("struct.") -> when (isPointer) {
            true -> CLibraryModel.Reference.Structure(split(".").last().convertToKotlinClassName())
            else -> CLibraryModel.Reference.StructureField(split(".").last().convertToKotlinClassName())
        }
        startsWith("object.")
            -> CLibraryModel.Reference.Pointer(split(".").last().convertToKotlinClassName())
        startsWith("enum.") -> when (isPointer) {
            true -> CLibraryModel.Reference.OpaquePointer
            else -> CLibraryModel.Reference.Enumeration(split(".").last().convertToKotlinClassName())
        }
        startsWith("callback.")
            -> CLibraryModel.Reference.StructureField(split(".").last().convertToKotlinCallbackStructureName())
        startsWith("bitflag.") -> CLibraryModel.Primitive.UInt64
        equals("bool") -> CLibraryModel.Primitive.Bool
        equals("usize") -> when (isPointer) {
            true -> CLibraryModel.Reference.OpaquePointer
            else -> CLibraryModel.Primitive.UInt64
        }
        equals("uint64") -> when (isPointer) {
            true -> CLibraryModel.Reference.OpaquePointer
            else -> CLibraryModel.Primitive.UInt64
        }
        equals("uint32") ->  when (isPointer) {
            true -> CLibraryModel.Reference.OpaquePointer
            else -> CLibraryModel.Primitive.UInt32
        }
        equals("uint16") -> when (isPointer) {
            true -> CLibraryModel.Reference.OpaquePointer
            else -> CLibraryModel.Primitive.UInt16
        }
        equals("int32") -> when (isPointer) {
            true -> CLibraryModel.Reference.OpaquePointer
            else -> CLibraryModel.Primitive.Int32
        }
        equals("float32") -> when (isPointer) {
            true -> CLibraryModel.Reference.OpaquePointer
            else -> CLibraryModel.Primitive.Float32
        }
        equals("float64") -> when (isPointer) {
            true -> CLibraryModel.Reference.OpaquePointer
            else -> CLibraryModel.Primitive.Float64
        }
        equals("c_void") -> when (isPointer) {
            true -> CLibraryModel.Reference.OpaquePointer
            else -> CLibraryModel.Void
        }
        startsWith("array<") -> CLibraryModel.Array(substring(6, length - 1).toCType(false, false), isMutable)
        isString() -> CLibraryModel.Reference.StructureField("WGPUStringView")
        else -> error("unknown type $this")
    }
}

private fun String.isString()
    = equals("string") || equals("nullable_string") || equals("out_string") || equals("string_with_default_empty")

fun CLibraryModel.Type.getSize(): Int? = when (this) {
    is CLibraryModel.Array,
    is CLibraryModel.Reference.Callback,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    CLibraryModel.Primitive.UInt64,
    CLibraryModel.Primitive.Float64,
    CLibraryModel.Reference.CString,
    CLibraryModel.Primitive.Int64 -> 8
    CLibraryModel.Primitive.UInt16 -> 2
    is CLibraryModel.Reference.Enumeration,
    CLibraryModel.Primitive.Int32,
    CLibraryModel.Primitive.Float32,
    CLibraryModel.Primitive.Bool,
    CLibraryModel.Primitive.UInt32 -> 4
    is CLibraryModel.Reference.StructureField -> null
    CLibraryModel.Void -> null
}
package generator.function

import builder.templateBuilder
import domain.CLibraryModel

fun List<CLibraryModel.Function>.toJnaFunctionsInterface() = templateBuilder {

    appendBlock("internal interface FunctionsInterface: com.sun.jna.Library") {
        forEach { function ->
            val name = function.name
            val returnType = function.returnType.toAndroidNativeType()
            val args = function.args
                .map { (name, type) -> "${name}: ${type.toAndroidNativeType()}" }
                .joinToString(", ")

            appendLine("@Suppress(\"INAPPLICABLE_JVM_NAME\")")
            appendLine("@JvmName(\"${name}\")")
            appendLine("fun $name($args): $returnType")
        }
    }

    appendLine("internal val Functions = com.sun.jna.Native.load(\"wgpu4k\", FunctionsInterface::class.java)")
}

internal fun CLibraryModel.Type.toAndroidNativeType(): String = when (this) {
    is CLibraryModel.Primitive.Int32 -> "Int"
    is CLibraryModel.Primitive.Int64 -> "Long"
    is CLibraryModel.Void -> "Unit"
    CLibraryModel.Primitive.Bool -> "UInt"
    CLibraryModel.Primitive.UInt64 -> "ULong"
    CLibraryModel.Primitive.Float64 -> "Double"
    CLibraryModel.Primitive.Float32 -> "Float"
    is CLibraryModel.Reference.Enumeration,
    CLibraryModel.Primitive.UInt32 -> "UInt"
    CLibraryModel.Primitive.UInt16 -> "UShort"
    is CLibraryModel.Reference.StructureField -> "${name}.ByValue"
    is CLibraryModel.Reference.Structure -> "${name}.ByReference?"
    is CLibraryModel.Reference.Callback -> "com.sun.jna.Callback?"
    is CLibraryModel.Array,
    CLibraryModel.Reference.CString,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer -> "com.sun.jna.Pointer?"
}
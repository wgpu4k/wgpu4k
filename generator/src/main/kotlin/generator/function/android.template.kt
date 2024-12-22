package generator.function

import builder.templateBuilder
import domain.CLibraryModel
import domain.toFunctionKotlinType


fun List<CLibraryModel.Function>.toAndroidFunctions() = templateBuilder {
    forEach { function ->
        val name = function.name
        val returnType = function.returnType.toFunctionKotlinType() + function.returnType.optionalReturnType()
        val args = function.args
            .map { (name, type) -> "${name}: ${type.toFunctionKotlinType()}${type.optional()}" }
            .joinToString(", ")
        val argsCall = function.args
            .map { (name, type) -> type.toJvmArgCall(name) }
            .joinToString(", ")
        appendLine("actual fun $name($args): $returnType")
        appendLine("\t = webgpu.android.Functions.$name($argsCall)")

        when (function.returnType) {
            is CLibraryModel.Reference.Enumeration,
            is CLibraryModel.Reference.OpaquePointer -> null
            is CLibraryModel.Reference.StructureField -> ".let(${function.returnType.name}::ByValue)"
            is CLibraryModel.Reference -> {
                "?.let(::${function.returnType.name})"
            }
            is CLibraryModel.Primitive.Bool -> ".toBoolean()"
            else -> null
        }?.let { appendLine("\t$it") }

        newLine()
    }
}


private fun CLibraryModel.Type.toJvmArgCall(name: String) = when(this) {
    is CLibraryModel.Reference.StructureField -> "${name}.toCValue()"
    is CLibraryModel.Reference.Structure -> "${name}?.toReference()"
    is CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Enumeration -> name
    is CLibraryModel.Reference.Callback -> "$name?.callback"
    is CLibraryModel.Array,
    is CLibraryModel.Reference -> "$name?.handler"
    else -> name
}

internal fun CLibraryModel.Type.optionalReturnType(): String = when (this) {
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    is CLibraryModel.Array -> "?"
    else -> ""
}

internal fun CLibraryModel.Type.optional(): String = when (this) {
    CLibraryModel.Void,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    is CLibraryModel.Array -> "?"
    else -> ""
}
package generator.function

import builder.Builder
import builder.templateBuilder
import domain.CLibraryModel
import domain.toFunctionKotlinType

internal fun List<CLibraryModel.Function>.toNativeFunctionsInterface() = templateBuilder {
    forEach { function -> toNativeFunction(function) }
}

private fun Builder.toNativeFunction(function: CLibraryModel.Function) {
    val name = function.name
    val returnType = function.returnType.toFunctionKotlinType() + function.returnType.optionalReturnType()
    val args = function.args
        .map { (name, type) -> "${name}: ${type.toFunctionKotlinType()}${type.optional()}" }
        .joinToString(", ")
    val argsCall = function.args
        .map { (name, type) -> type.toNativeArgCall(name) }
        .joinToString(", ")
    appendBlock("actual fun $name($args): $returnType") {
        appendLine("${if (function.returnType is CLibraryModel.Void) "" else "return "}webgpu.native.$name($argsCall)")

        when (function.returnType) {
            is CLibraryModel.Reference.Enumeration -> null
            is CLibraryModel.Reference.OpaquePointer -> "?.let(::NativeAddress)"
            is CLibraryModel.Reference.StructureField -> ".let(${function.returnType.name}::ByValue)"
            is CLibraryModel.Reference -> {
                "?.let(::NativeAddress)?.let(::${function.returnType.name})"
            }
            is CLibraryModel.Primitive.Bool -> ".toBoolean()"
            else -> null
        }?.let { appendLine("\t$it") }

    }
    newLine()
}


private fun CLibraryModel.Type.toNativeArgCall(name: String) = when(this) {
    is CLibraryModel.Reference.StructureField -> "$name.toCValue()"
    is CLibraryModel.Reference.CString -> "$name?.toKString()"
    is CLibraryModel.Reference.OpaquePointer -> "$name?.pointer"
    is CLibraryModel.Reference.Enumeration -> name
    is CLibraryModel.Array,
    is CLibraryModel.Reference -> "$name?.handler?.reinterpret()"
    else -> name
}
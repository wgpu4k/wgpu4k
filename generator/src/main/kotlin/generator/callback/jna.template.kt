package generator.callback

import builder.templateBuilder
import domain.CLibraryModel
import domain.toCallbackKotlinType
import domain.toFunctionKotlinType
import domain.typeToJvmLayout

fun CLibraryModel.Callback.toJnaCallback() = templateBuilder {
    val jvmArgs = members
        .map { (name, type) -> "$name: ${type.toCallbackJvmType()}" }
        .joinToString(", ")

    appendBlock("actual interface ${name} : Callback") {
        val args = members
            .map { (name, type) -> "$name: ${type.toCallbackKotlinType()}" }
            .joinToString(", ")
        appendLine("actual fun invoke($args)")

        appendBlock("interface Function : com.sun.jna.Callback") {
            appendLine("fun apply($jvmArgs)")
        }

        appendBlock("actual companion object") {
            appendBlock("actual fun allocate(allocator: MemoryAllocator, callback: $name): CallbackHolder<$name>") {

                appendBlock("val callbackFunction = object : Function") {
                    appendBlock("override fun apply($jvmArgs)") {
                        val argsCall = members
                            .map { (name, type) -> type.toArgCall(name) }
                            .joinToString(", ")
                        appendLine("callback.invoke($argsCall)")
                    }
                }

                appendLine("return CallbackHolder(com.sun.jna.Pointer(0), callbackFunction)")
            }
        }
    }
    newLine()
}

private fun CLibraryModel.Type.toArgCall(name: String): String = when (this) {
    CLibraryModel.Primitive.Bool,
    is CLibraryModel.Reference.Enumeration,
    CLibraryModel.Primitive.UInt32 -> "$name.toUInt()"
    CLibraryModel.Primitive.Float32,
    CLibraryModel.Primitive.Float64,
    CLibraryModel.Primitive.Int32,
    CLibraryModel.Primitive.Int64 -> name
    CLibraryModel.Primitive.UInt16 ->  "$name.toUShort()"
    CLibraryModel.Primitive.UInt64 ->  "$name.toULong()"

    CLibraryModel.Reference.OpaquePointer -> "$name ?: com.sun.jna.Pointer(0)"

    is CLibraryModel.Reference.StructureField -> "$name.let { ${this.name}.ByValue(it) }"
    is CLibraryModel.Array -> "$name?.let(::ArrayHolder)"
    is CLibraryModel.Reference.Callback -> "$name?.let(::CallbackHolder)"
    is CLibraryModel.Reference -> "$name?.let { ${this.name}(it) }"
    CLibraryModel.Void -> error("unsupported type here")
}


private fun CLibraryModel.Type.toCallbackJvmType(): String = when (this) {
    CLibraryModel.Primitive.Float32 -> "Float"
    CLibraryModel.Primitive.Float64 -> "Double"
    CLibraryModel.Primitive.UInt16 -> "Short"
    CLibraryModel.Primitive.UInt64,
    CLibraryModel.Primitive.Int64 -> "Long"

    CLibraryModel.Primitive.Bool,
    CLibraryModel.Primitive.Int32,
    CLibraryModel.Primitive.UInt32,
    is CLibraryModel.Reference.Enumeration -> "Int"

    is CLibraryModel.Reference.StructureField -> "webgpu.android.${this.name}.ByValue"
    is CLibraryModel.Array,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure -> "com.sun.jna.Pointer?"

    CLibraryModel.Void -> error("unsupported type here")
}

package generator.callback

import builder.templateBuilder
import domain.CLibraryModel
import domain.toCallbackKotlinType
import domain.toFunctionKotlinType

fun CLibraryModel.Callback.toNativeCallback() = templateBuilder {
    val callbackName = name
    appendBlock("actual interface $callbackName : Callback") {
        val args = members
            .map { (name, type) -> "$name: ${type.toCallbackKotlinType()}" }
            .joinToString(", ")
        appendLine("actual fun invoke($args)")

        appendBlock("actual companion object") {
            appendBlock("actual fun allocate(allocator: MemoryAllocator, callback: $callbackName): CallbackHolder<$callbackName>") {
                val args = members
                    .map { (name, type) -> "$name: ${type.toNativeCallbackArg()}" }
                    .joinToString(", ")
                val argsCall = members
                    .map { (name, type) -> type.toNativeCallbackArgCall(name) }
                    .joinToString(", ")
                appendBlock("val actualCallback = kotlinx.cinterop.staticCFunction",  args) {
                    val lastArgName = members.last().first
                    appendLine("val address = $lastArgName?.reinterpret<LongVar>()?.pointed?.value?.let(::NativeAddress) ?: error(\"Missing callback address on last argument\")")
                    appendLine("val callback = findCallback<$callbackName>(address.reinterpret<COpaque>())")
                    appendLine("\t?: error(\"Callback not found with address \$address and type $callbackName\")")
                    appendLine("callback.invoke($argsCall)")
                }

                appendLine("registerCallback(actualCallback, callback)")
                appendLine("return CallbackHolder(actualCallback.let(::NativeAddress), actualCallback)")
            }
        }
    }
    newLine()
}

private fun CLibraryModel.Type.toNativeCallbackArgCall(name: String): String = when (this) {
    is CLibraryModel.Reference.Enumeration,
    is CLibraryModel.Primitive -> name
    is CLibraryModel.Reference.StructureField -> "$name.let { ${this.name}.ByValue(it) }"
    is CLibraryModel.Reference.Pointer -> "$name?.let(::NativeAddress)?.let(::${this.name})"
    is CLibraryModel.Reference.Structure -> "$name?.let(::NativeAddress)?.let { ${this.name}(it) }"
    CLibraryModel.Reference.CString -> "$name?.let(::NativeAddress)?.let(::CString)"
    CLibraryModel.Void -> error("unsupported type")
    else -> "$name?.let(::NativeAddress)"
}

private fun CLibraryModel.Type.toNativeCallbackArg(): String = when (this) {
    is CLibraryModel.Primitive -> toFunctionKotlinType()
    is CLibraryModel.Reference.StructureField -> "kotlinx.cinterop.CValue<webgpu.native.$name>"
    is CLibraryModel.Reference.Enumeration -> "UInt"
    CLibraryModel.Void -> error("unsupported type")
    else -> "COpaquePointer?"
}
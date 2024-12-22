package generator.callback

import builder.templateBuilder
import domain.CLibraryModel
import domain.toCallbackKotlinType
import domain.toFunctionKotlinType

fun CLibraryModel.Callback.toCommonCallback() = templateBuilder {
    appendBlock("expect interface ${name} : Callback") {
        val args = members
            .map { (name, type) -> "$name: ${type.toCallbackKotlinType()}" }
            .joinToString(", ")
        appendLine("fun invoke($args)")

        appendBlock("companion object") {
            appendLine("fun allocate(allocator: MemoryAllocator, callback: $name): CallbackHolder<$name>")
        }
    }
    newLine()
}
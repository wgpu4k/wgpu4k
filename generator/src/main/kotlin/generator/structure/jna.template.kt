package generator.structure

import builder.templateBuilder
import converter.toPrimitiveDefaultValue
import converter.toPrimitiveKotlinType
import converter.variableType
import domain.CLibraryModel


fun CLibraryModel.Structure.toJnaStructure() = templateBuilder {
    appendBlock("sealed class $name(pointer: com.sun.jna.Pointer? = null) : com.sun.jna.Structure(pointer)") {
        members.forEach { (name, type, _) ->
            appendLine("@JvmField var $name: ${type.toJnaType()}${type.toOptionalModifier()} = ${type.toDefaultValue()}")

        }

        appendLine("override fun getFieldOrder() = listOf(${members.map { (name, _, _) -> "\"$name\"" }.joinToString(", ")})")
        newLine()
        appendBlock("class ByReference(pointer: com.sun.jna.Pointer? = null) : ${name}(pointer), com.sun.jna.Structure.ByReference") {
            appendBlock("constructor(other: $name) : this(other.pointer)") {
                members.forEach { (name, _, _) ->
                    appendLine("this.$name = other.$name")
                }
            }
        }
        newLine()
        appendBlock("class ByValue(pointer: com.sun.jna.Pointer? = null) : ${name}(pointer), com.sun.jna.Structure.ByValue") {
            appendBlock("constructor(other: $name) : this(other.pointer)") {
                members.forEach { (name, _, _) ->
                    appendLine("this.$name = other.$name")
                }
            }
        }
    }
    newLine()
}

private fun CLibraryModel.Type.toDefaultValue(): String = when (this) {
    is CLibraryModel.Primitive.Bool -> "0"
    is CLibraryModel.Primitive.UInt64 -> "0L"
    is CLibraryModel.Reference.Enumeration,
    is CLibraryModel.Primitive.UInt32 -> "0"
    is CLibraryModel.Primitive.UInt16 -> "0"
    is CLibraryModel.Primitive -> toPrimitiveDefaultValue()
    is CLibraryModel.Reference.StructureField -> "${name}.ByValue()"
    //is CLibraryModel.Reference.Callback -> TODO()
    else -> "null"
}

private fun CLibraryModel.Type.toOptionalModifier(): String  = when (this) {
    is CLibraryModel.Primitive -> ""
    is CLibraryModel.Reference.StructureField -> ""
    is CLibraryModel.Reference.Enumeration -> ""
    //is CLibraryModel.Reference.Callback -> TODO()
    else -> "?"
}

private fun CLibraryModel.Type.toJnaType(): String  = when (this) {
    is CLibraryModel.Primitive.Bool -> "Int"
    is CLibraryModel.Primitive.UInt64 -> "Long"
    is CLibraryModel.Reference.Enumeration,
    is CLibraryModel.Primitive.UInt32 -> "Int"
    is CLibraryModel.Primitive.UInt16 -> "Short"
    is CLibraryModel.Primitive -> toPrimitiveKotlinType()
    is CLibraryModel.Reference.Structure -> "${name}.ByReference?"
    is CLibraryModel.Reference.StructureField -> "${name}.ByValue"
    is CLibraryModel.Reference.Callback -> "com.sun.jna.Callback"
    else -> "com.sun.jna.Pointer"
}

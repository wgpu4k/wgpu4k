package generator.structure

import builder.Builder
import builder.templateBuilder
import converter.variableType
import domain.CLibraryModel
import domain.toFunctionKotlinType
import domain.typeToJvmLayout

internal fun CLibraryModel.Structure.toJvmStructure() = templateBuilder {
    val structureName = name
    val structureSize = size ?: error("structure size should be know at this point")
    appendBlock("actual interface $structureName : CStructure") {

        toPanamaImplementation(this@toJvmStructure)
        newLine()
        members.forEach { (name, type, optional) ->
            appendLine("actual ${type.variableType()} $name: ${type.toFunctionKotlinType()}$optional")
        }
        newLine()
        appendBlock("actual companion object") {
            appendBlock("actual operator fun invoke(address: NativeAddress): $structureName") {
                appendLine("return ByReference(address)")
            }
            newLine()
            appendBlock("actual fun allocate(allocator: MemoryAllocator): $structureName") {
                appendLine("return allocator.allocate(${structureSize}L)")
                appendLine("\t.let { $structureName(it) }")
            }
            newLine()
            appendBlock("actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  $structureName) -> Unit): ArrayHolder<$structureName>") {
                appendLine("return allocator.allocate(${structureSize} * size.toLong())") {
                    appendBlock(".also") {
                        appendBlock("(0u until size).forEach", "index") {
                            appendLine("it.handler.asSlice(index.toLong() * ${structureSize}L)") {
                                appendLine(".let(::NativeAddress)")
                                appendLine(".let { $structureName(it) }")
                                appendLine(".let { provider(index, it) }")
                            }
                        }
                    }
                    appendLine(".let(::ArrayHolder)")
                }

            }

            newLine()
            // Generate layout
            appendLine("internal val LAYOUT = structLayout(")
            members.forEach { (name, type, _, _, _, padding) ->
                padding?.takeIf { it > 0 }
                    ?.let { "\tMemoryLayout.paddingLayout($it),"}
                    ?.let(::appendLine)
                typeToJvmLayout(type).let { "\t$it.withName(\"${name}\")," }
                    .let(::appendLine)
            }

            padding?.takeIf { it > 0 }
                ?.let { "\tMemoryLayout.paddingLayout($it)"}
                ?.let(::appendLine)
            appendLine(").withName(\"$structureName\")")
            newLine()

            // Write offset
            var offset = 0
            members.forEachIndexed { index, member ->
                offset += (member.padding ?: 0)
                appendLine("val ${member.name}Offset = ${offset}L")
                appendLine("val ${member.name}Layout = ${typeToJvmLayout(member.type)}")
                offset += (member.size ?: 0)
            }
        }
    }
}

private fun Builder.toPanamaImplementation(structure: CLibraryModel.Structure) {
    val structureName = structure.name
    newLine()
    appendLine("@JvmInline")
    appendBlock("value class ByReference(override val handler: NativeAddress) : $structureName") {
        structure.members.forEach { member ->
            val name = member.name
            val type = member.type
            val optional = member.option
            val variableType = type.variableType()
            appendLine("override $variableType $name: ${type.toFunctionKotlinType()}$optional")
            // Getter
            when (type) {
                CLibraryModel.Void,
                CLibraryModel.Reference.OpaquePointer,
                    -> "get(${name}Layout, ${name}Offset)"

                CLibraryModel.Reference.CString -> "get(${name}Layout, ${name}Offset).let(::CString)"
                is CLibraryModel.Array -> "get(${name}Layout, ${name}Offset).let(::ArrayHolder)"
                is CLibraryModel.Reference.Callback -> "get(${name}Layout, ${name}Offset).let(::CallbackHolder)"
                CLibraryModel.Primitive.Float32 -> "getFloat(${name}Offset)"
                CLibraryModel.Primitive.Float64 -> "getDouble(${name}Offset)"
                CLibraryModel.Primitive.Int64 -> "getLong(${name}Offset)"
                CLibraryModel.Primitive.UInt16 -> "getUShort(${name}Offset)"
                CLibraryModel.Primitive.UInt64 -> "getULong(${name}Offset)"
                CLibraryModel.Primitive.Bool -> "getInt(${name}Offset).toBoolean()"
                CLibraryModel.Primitive.Int32 -> "getInt(${name}Offset)"
                is CLibraryModel.Reference.Enumeration,
                CLibraryModel.Primitive.UInt32 -> "getUInt(${name}Offset)"
                is CLibraryModel.Reference.StructureField -> "handler.handler.asSlice(${name}Offset, ${member.size}L).let(::NativeAddress).let { ${type.name}(it) }"
                is CLibraryModel.Reference -> "get(${name}Layout, ${name}Offset).let { ${type.name}(it) }"
            }.let { appendLine("\tget() = $it") }

            // Setter
            when (type) {
                CLibraryModel.Void -> error("void is not allowed")
                is CLibraryModel.Reference.Enumeration,
                CLibraryModel.Primitive.Float32,
                CLibraryModel.Primitive.Float64,
                CLibraryModel.Primitive.Int64,
                CLibraryModel.Primitive.UInt16,
                CLibraryModel.Primitive.UInt64,
                CLibraryModel.Primitive.Bool,
                CLibraryModel.Primitive.Int32,
                CLibraryModel.Primitive.UInt32 -> "set(${name}Offset, newValue)"

                CLibraryModel.Reference.OpaquePointer -> "set(${name}Layout, ${name}Offset, newValue)"
                is CLibraryModel.Reference.Callback,
                CLibraryModel.Reference.CString,
                is CLibraryModel.Reference.Structure,
                is CLibraryModel.Array,
                is CLibraryModel.Reference.Pointer -> "set(${name}Layout, ${name}Offset, newValue?.handler)"

                is CLibraryModel.Reference.StructureField -> null
            }?.let { appendLine("\tset(newValue) = $it") }
        }
    }
}


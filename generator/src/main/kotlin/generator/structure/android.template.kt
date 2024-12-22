package generator.structure

import builder.Builder
import builder.templateBuilder
import converter.variableType
import domain.CLibraryModel
import domain.toFunctionKotlinType

fun CLibraryModel.Structure.toAndroidStructure() = templateBuilder {
    val structureName = name
    val structureSize = size ?: error("structure size should be know at this point")
    appendBlock("actual interface $structureName") {
        toAndroidImplementation(this@toAndroidStructure, "ByReference")
        toAndroidImplementation(this@toAndroidStructure, "ByValue")

        newLine()
        appendLine("fun toCValue() = (this as ByReference).let{ webgpu.android.${structureName}.ByValue(handle) }")
        appendLine("fun toReference() = (this as ByReference).handle")

        newLine()
        members.forEach { (name, type, optional) ->
            appendLine("actual ${type.variableType()} $name: ${type.toFunctionKotlinType()}$optional")
        }

        appendLine("actual val handler: NativeAddress")
        newLine()
        appendBlock("actual companion object") {
            appendBlock("actual operator fun invoke(address: NativeAddress): $structureName") {
                appendLine("return webgpu.android.$structureName.ByReference(address)")
                appendLine("\t.also { it.read() }")
                appendLine("\t.let(::ByReference)")
            }
            newLine()
            appendBlock("actual fun allocate(allocator: MemoryAllocator): $structureName") {
                appendLine("return $structureName.ByReference()")
                appendLine("\t.also { allocator.register(it) }")
            }
            newLine()
            appendBlock("actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  $structureName) -> Unit): ArrayHolder<$structureName>") {
                appendLine("val array = webgpu.android.$structureName.ByValue().toArray(size.toInt())")
                appendBlock("array.forEachIndexed", "index, structure") {
                    appendLine("(structure as webgpu.android.$structureName.ByValue)") {
                        appendLine(".also { provider(index.toUInt(), $structureName.ByValue(it)) }")
                        appendLine(".write()")
                    }
                }
                appendLine("val pointer = if (size == 0u) com.sun.jna.Pointer.NULL else array.first().pointer")
                appendLine("return ArrayHolder(pointer)")
            }
        }

    }
    newLine()
}

private fun Builder.toAndroidImplementation(structure: CLibraryModel.Structure, name: String) {
    val structureName = structure.name
    newLine()
    appendBlock("class $name(val handle: webgpu.android.$structureName.$name = webgpu.android.$structureName.$name(com.sun.jna.Pointer.NULL)) : $structureName") {
        structure.members.forEach { (name, type, optional) ->

            appendLine("override ${type.variableType()} $name: ${type.toFunctionKotlinType()}$optional")
            // Getter
            when (type) {
                CLibraryModel.Void,
                CLibraryModel.Reference.OpaquePointer,
                    -> "handle.$name"

                CLibraryModel.Reference.CString -> "handle.$name?.let(::CString)"
                is CLibraryModel.Array -> "handle.$name?.let(::ArrayHolder)"
                is CLibraryModel.Reference.Callback -> "handle.$name?.let{ CallbackHolder(com.sun.jna.Pointer(0), it) }"
                CLibraryModel.Primitive.Float32,
                CLibraryModel.Primitive.Float64 -> "handle.$name"

                CLibraryModel.Primitive.Int64 -> "handle.$name"
                CLibraryModel.Primitive.Int32 -> "handle.$name"
                CLibraryModel.Primitive.Bool -> "handle.$name.toBoolean()"
                CLibraryModel.Primitive.UInt16 -> "handle.$name.toUShort()"
                CLibraryModel.Primitive.UInt64 -> "handle.$name.toULong()"
                is CLibraryModel.Reference.Enumeration,
                CLibraryModel.Primitive.UInt32 -> "handle.$name.toUInt()"

                is CLibraryModel.Reference.StructureField -> "handle.$name.let{ ${type.name}.ByValue(it) }"
                is CLibraryModel.Reference.Structure -> "handle.$name?.let{ ${type.name}.ByReference(it) }"
                is CLibraryModel.Reference -> "handle.$name?.let{ ${type.name}(it) }"
            }.let { appendLine("\tget() = $it") }

            // Setter
            when (type) {
                CLibraryModel.Void,
                CLibraryModel.Primitive.Float32,
                CLibraryModel.Primitive.Float64,
                CLibraryModel.Primitive.Int64,
                CLibraryModel.Primitive.Int32 -> "handle.$name = newValue"

                CLibraryModel.Primitive.UInt16 -> "handle.$name = newValue.toShort()"
                is CLibraryModel.Reference.Enumeration,
                CLibraryModel.Primitive.Bool,
                CLibraryModel.Primitive.UInt32 -> "handle.$name = newValue.toInt()"

                CLibraryModel.Primitive.UInt64 -> "handle.$name = newValue.toLong()"

                CLibraryModel.Reference.OpaquePointer -> "handle.$name = newValue"
                is CLibraryModel.Reference.Callback -> "handle.$name = newValue?.callback"
                CLibraryModel.Reference.CString,
                is CLibraryModel.Reference.Pointer,
                is CLibraryModel.Array -> "handle.$name = newValue?.handler"

                is CLibraryModel.Reference.Structure -> "handle.$name = (newValue as? ${type.name}.ByReference)?.handle"

                is CLibraryModel.Reference.StructureField -> null
            }?.let { appendLine("\tset(newValue) { $it }") }
            newLine()
        }

        appendLine("override val handler: NativeAddress")
        appendLine("\tget() {")
        appendLine("\t\thandle.write()")
        appendLine("\t\treturn handle.getPointer()")
        appendLine("\t}")

    }

}


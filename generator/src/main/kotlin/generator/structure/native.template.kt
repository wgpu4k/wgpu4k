package generator.structure

import builder.Builder
import builder.templateBuilder
import converter.variableType
import domain.CLibraryModel
import domain.toFunctionKotlinType

internal fun CLibraryModel.Structure.toNativeStructure() = templateBuilder {
    val structureName = name
    appendBlock("actual interface $name") {

        toNativeImplementationByValue(this@toNativeStructure)
        toNativeImplementationByReference(this@toNativeStructure)
        newLine()
        members.forEach { (name, type, optional) ->
            appendLine("actual ${type.variableType()} $name: ${type.toFunctionKotlinType()}$optional")
        }
        appendLine("actual val handler: NativeAddress")
        newLine()
        appendBlock("actual companion object") {
            appendBlock("actual operator fun invoke(address: NativeAddress): $structureName") {
                appendLine("return ByReference(address)")
            }
            newLine()
            appendBlock("actual fun allocate(allocator: MemoryAllocator): $structureName") {
                appendLine("return allocator.allocate(sizeOf<webgpu.native.$structureName>())") {
                    appendLine(".let { $structureName(it) }")
                }
            }
            newLine()
            appendBlock("actual fun allocateArray(allocator: MemoryAllocator, size: UInt, provider: (UInt,  $structureName) -> Unit): ArrayHolder<$structureName>") {
                appendLine("return allocator.allocate(sizeOf<webgpu.native.$structureName>() * size.toLong())") {
                    appendBlock(".also") {
                        appendBlock("(0u until size).forEach", "index") {
                            appendLine("(it.rawValue + index.toLong() * sizeOf<webgpu.native.$structureName>())") {
                                appendLine(".let(::NativeAddress)")
                                appendLine(".let { $structureName(it) }")
                                appendLine(".let { provider(index, it) }")
                            }
                        }
                    }
                    appendLine(".let(::ArrayHolder)")
                }

            }
        }
        appendBlock("fun toCValue(): CValue<webgpu.native.$structureName>") {
            appendBlock("return cValue<webgpu.native.$structureName>") {
                members
                    .filter { (_, type, _) -> type is CLibraryModel.Reference.StructureField }
                    .forEach { (name, _, _) ->
                        appendLine("$name.adapt(this@$structureName.$name)")
                    }
                members
                    .filter { (_, type, _) -> type !is CLibraryModel.Reference.StructureField }
                    .forEach { (name, type, _) ->
                    val adapter = when (type) {
                        CLibraryModel.Reference.OpaquePointer -> "?.reinterpret()"
                        is CLibraryModel.Reference.Pointer,
                        is CLibraryModel.Reference.Structure,
                        CLibraryModel.Reference.CString,
                        is CLibraryModel.Reference.Callback,
                        is CLibraryModel.Array -> "?.handler?.reinterpret()"
                        CLibraryModel.Primitive.Bool -> ".toUInt()"
                        CLibraryModel.Primitive.Float32,
                        CLibraryModel.Primitive.Float64,
                        CLibraryModel.Primitive.Int32,
                        CLibraryModel.Primitive.Int64,
                        CLibraryModel.Primitive.UInt16,
                        CLibraryModel.Primitive.UInt32,
                        CLibraryModel.Primitive.UInt64,
                        is CLibraryModel.Reference.Enumeration -> ""
                        is CLibraryModel.Reference.StructureField,
                            CLibraryModel.Void -> error("$type is not allowed")
                    }
                    appendLine("$name = this@$structureName.$name$adapter")
                }
            }
        }
    }
    newLine()
    appendBlock("fun webgpu.native.$structureName.adapt(structure: $name)") {
        members
            .filter { (_, type, _) -> type is CLibraryModel.Reference.StructureField }
            .forEach { (name, _, _) ->
                appendLine("$name.adapt(structure.$name)")
            }
        members
            .filter { (_, type, _) -> type !is CLibraryModel.Reference.StructureField }
            .forEach { (name, type, _) ->
                val adapter = when (type) {
                    CLibraryModel.Reference.OpaquePointer -> "?.reinterpret()"
                    is CLibraryModel.Reference.Pointer,
                    is CLibraryModel.Reference.Structure,
                    CLibraryModel.Reference.CString,
                    is CLibraryModel.Reference.Callback,
                    is CLibraryModel.Array -> "?.handler?.reinterpret()"
                    CLibraryModel.Primitive.Bool -> ".toUInt()"
                    CLibraryModel.Primitive.Float32,
                    CLibraryModel.Primitive.Float64,
                    CLibraryModel.Primitive.Int32,
                    CLibraryModel.Primitive.Int64,
                    CLibraryModel.Primitive.UInt16,
                    CLibraryModel.Primitive.UInt32,
                    CLibraryModel.Primitive.UInt64,
                    is CLibraryModel.Reference.Enumeration -> ""
                    is CLibraryModel.Reference.StructureField,
                    CLibraryModel.Void -> error("$type is not allowed")
                }
                appendLine("$name = structure.$name$adapter")
            }
    }
    newLine()
}

private fun Builder.toNativeImplementationByValue(structure: CLibraryModel.Structure) {
    val structureName = structure.name
    appendBlock("value class ByValue(val handle: CValue<webgpu.native.$structureName>) : $structureName") {

        structure.members.forEach { (name, type, optional) ->
            val variableType = type.variableType()
            val nativeAccessor = "handle.useContents { "
            appendLine("override $variableType $name: ${type.toFunctionKotlinType()}$optional")
            // Getter
            when (type) {
                is CLibraryModel.Reference.OpaquePointer
                    -> "$nativeAccessor${name}?.let(::NativeAddress)"

                is CLibraryModel.Reference.Enumeration
                    -> "$nativeAccessor${name} ?: error(\"pointer of $structureName is null\")"

                is CLibraryModel.Primitive.Bool
                    -> "$nativeAccessor${name}.toBoolean() ?: error(\"pointer of $structureName is null\")"

                is CLibraryModel.Primitive
                    -> "$nativeAccessor${name} ?: error(\"pointer of $structureName is null\")"

                is CLibraryModel.Reference.CString
                    -> "$nativeAccessor${name}?.toCString()"

                is CLibraryModel.Reference.Pointer
                    -> "$nativeAccessor${name}" +
                        "?.let(::NativeAddress)" +
                        "?.let { ${type.name}(it) }"

                is CLibraryModel.Reference.StructureField
                    -> "$nativeAccessor${name}.rawPtr.toLong()" +
                        ".let(::NativeAddress)" +
                        ".let { ${type.name}(it) }"

                is CLibraryModel.Reference.Structure
                    -> "$nativeAccessor${name}" +
                        "?.let(::NativeAddress)" +
                        "?.let { ${type.name}(it) }"

                is CLibraryModel.Reference.Callback
                    -> "$nativeAccessor${name}" +
                        "?.let(::NativeAddress)" +
                        "?.let { CallbackHolder<${type.name}>(it) }"

                is CLibraryModel.Array
                    -> "$nativeAccessor${name}" +
                        "?.let(::NativeAddress)" +
                        "?.let { ArrayHolder<${type.subType.toFunctionKotlinType()}>(it) }"

                CLibraryModel.Void -> error("void is not allowed")
            }.let { appendLine("\tget() = $it }") }
            // Setter
            when (type) {
                is CLibraryModel.Reference.OpaquePointer
                    -> nativeAccessor +
                        "${name} = newValue?.reinterpret()"

                is CLibraryModel.Reference.Enumeration
                    -> nativeAccessor +
                        "${name} = newValue"

                is CLibraryModel.Reference.CString
                    -> nativeAccessor +
                        "${name} = newValue?.handler?.reinterpret()"

                is CLibraryModel.Primitive.Bool
                    -> nativeAccessor +
                        "${name} = newValue.toUInt()"

                is CLibraryModel.Primitive
                    -> nativeAccessor +
                        "${name} = newValue"

                is CLibraryModel.Reference.Pointer
                    -> nativeAccessor +
                        "${name} = newValue?.handler?.reinterpret()"

                is CLibraryModel.Reference.Structure
                    -> nativeAccessor +
                        "${name} = newValue?.handler?.reinterpret()"

                is CLibraryModel.Reference.Callback
                    -> nativeAccessor +
                        "${name} = newValue?.handler?.reinterpret()"

                is CLibraryModel.Array
                    -> nativeAccessor +
                        "${name} = newValue?.handler?.reinterpret()"

                is CLibraryModel.Reference.StructureField -> null

                CLibraryModel.Void -> error("void is not allowed")
            }?.let { appendLine("\tset(newValue) { $it } } \n") } ?: newLine()

        }
        appendLine("override val handler: NativeAddress")
        appendLine("\tget() = error(\"should not be call on CValue\")")
        newLine()
    }
}

private fun Builder.toNativeImplementationByReference(structure: CLibraryModel.Structure) {
    val structureName = structure.name
    appendBlock("value class ByReference(override val handler: NativeAddress) : $structureName") {

        structure.members.forEach { (name, type, optional) ->
            val variableType = type.variableType()
            val nativeAccessor = "handler.reinterpret<webgpu.native.$structureName>().pointed"
            appendLine("override $variableType $name: ${type.toFunctionKotlinType()}$optional")
            // Getter
            when (type) {
                is CLibraryModel.Reference.OpaquePointer
                    -> "$nativeAccessor.${name}?.let(::NativeAddress)"

                is CLibraryModel.Reference.Enumeration
                    -> "$nativeAccessor.${name} ?: error(\"pointer of $structureName is null\")"

                is CLibraryModel.Primitive.Bool
                    -> "$nativeAccessor.${name}.toBoolean() ?: error(\"pointer of $structureName is null\")"

                is CLibraryModel.Primitive
                    -> "$nativeAccessor.${name} ?: error(\"pointer of $structureName is null\")"

                is CLibraryModel.Reference.CString
                    -> "$nativeAccessor.${name}?.toCString()"

                is CLibraryModel.Reference.Pointer
                    -> "$nativeAccessor.${name}" +
                        "?.let(::NativeAddress)" +
                        "?.let { ${type.name}(it) }"

                is CLibraryModel.Reference.StructureField
                    -> "$nativeAccessor.${name}.rawPtr.toLong()" +
                        ".let(::NativeAddress)" +
                        ".let { ${type.name}(it) }"

                is CLibraryModel.Reference.Structure
                    -> "$nativeAccessor.${name}" +
                        "?.let(::NativeAddress)" +
                        "?.let { ${type.name}(it) }"

                is CLibraryModel.Reference.Callback
                    -> "$nativeAccessor.${name}" +
                        "?.let(::NativeAddress)" +
                        "?.let { CallbackHolder<${type.name}>(it) }"

                is CLibraryModel.Array
                    -> "$nativeAccessor.${name}" +
                        "?.let(::NativeAddress)" +
                        "?.let { ArrayHolder<${type.subType.toFunctionKotlinType()}>(it) }"

                CLibraryModel.Void -> error("void is not allowed")
            }.let { appendLine("\tget() = $it") }
            // Setter
            when (type) {
                is CLibraryModel.Reference.OpaquePointer
                    -> nativeAccessor +
                        ".let { it.${name} = newValue?.reinterpret() }"

                is CLibraryModel.Reference.Enumeration
                    -> nativeAccessor +
                        ".let { it.${name} = newValue }"

                is CLibraryModel.Reference.CString
                    -> nativeAccessor +
                        ".let { it.${name} = newValue?.handler?.reinterpret() }"

                is CLibraryModel.Primitive.Bool
                    -> nativeAccessor +
                        ".let { it.${name} = newValue.toUInt() }"

                is CLibraryModel.Primitive
                    -> nativeAccessor +
                        ".let { it.${name} = newValue }"

                is CLibraryModel.Reference.Pointer
                    -> nativeAccessor +
                        ".let { it.${name} = newValue?.handler?.reinterpret() }"

                is CLibraryModel.Reference.Structure
                    -> nativeAccessor +
                        ".let { it.${name} = newValue?.handler?.reinterpret() }"

                is CLibraryModel.Reference.Callback
                    -> nativeAccessor +
                        ".let { it.${name} = newValue?.handler?.reinterpret() }"

                is CLibraryModel.Array
                    -> nativeAccessor +
                        ".let { it.${name} = newValue?.handler?.reinterpret() }"

                is CLibraryModel.Reference.StructureField -> null

                CLibraryModel.Void -> error("void is not allowed")
            }?.let { appendLine("\tset(newValue) { $it } \n") } ?: newLine()
        }
    }
}

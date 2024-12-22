package domain

internal fun typeToJvmLayout(type: CLibraryModel.Type) = when (type) {
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Array,
    is CLibraryModel.Reference.Callback,
    CLibraryModel.Void,
    is CLibraryModel.Reference.Structure -> "ffi.C_POINTER"

    CLibraryModel.Primitive.UInt16 -> "ffi.C_SHORT"

    CLibraryModel.Primitive.Bool,
    CLibraryModel.Primitive.UInt32,
    CLibraryModel.Primitive.Int32 -> "ffi.C_INT"

    CLibraryModel.Primitive.UInt64,
    CLibraryModel.Primitive.Int64 -> "ffi.C_LONG"

    CLibraryModel.Primitive.Float32 -> "ffi.C_FLOAT"
    CLibraryModel.Primitive.Float64 -> "ffi.C_DOUBLE"

    is CLibraryModel.Reference.Enumeration -> "ffi.C_INT"
    is CLibraryModel.Reference.StructureField -> "${type.name}.LAYOUT"
}
package generator

import disclamer
import domain.CLibraryModel
import generator.structure.toAndroidStructure
import generator.structure.toJnaStructure
import java.io.File

private val headerAndroidJna = """
    $disclamer
    package webgpu.android
    
    import ffi.NativeAddress

    
""".trimIndent()

private val headerAndroid = """
    $disclamer
    package webgpu
    
    import ffi.NativeAddress
    import ffi.CallbackHolder
    import ffi.CString
    import ffi.ArrayHolder
    import ffi.C_LONG
    import ffi.C_POINTER
    import ffi.C_SHORT
    import ffi.C_INT
    import ffi.C_FLOAT
    import ffi.C_DOUBLE
    import ffi.CStructure
    import ffi.MemoryAllocator
    import ffi.toAddress
    import java.lang.foreign.AddressLayout
    import java.lang.foreign.MemoryLayout
    import java.lang.foreign.MemoryLayout.Companion.structLayout
    
    
""".trimIndent()

fun File.generateAndroidStructures(structures: List<CLibraryModel.Structure>) = resolve("webgpu").apply {
    resolve("Structures.android.kt").apply {
        writeText(headerAndroid)
        structures.map(CLibraryModel.Structure::toAndroidStructure)
            .forEach(::appendText)
    }
    resolve("android").resolve("Structures.kt").apply {
        writeText(headerAndroidJna)
        structures.map(CLibraryModel.Structure::toJnaStructure)
            .forEach(::appendText)
    }
}




package generator

import disclamer
import domain.CLibraryModel
import generator.function.toJvmFunctionsInterface
import jvmMainBasePath
import java.io.File

val jvmNativeFunctionsMainFile = jvmMainBasePath
    .resolve("webgpu")
    .resolve("jvm")
    .resolve("Functions.kt")


private val header = """
    $disclamer
    package webgpu
    
    import java.lang.foreign.MemorySegment
    import java.lang.foreign.Linker
    import ffi.findOrThrow
    import ffi.C_POINTER
    import ffi.C_INT
    import ffi.C_LONG
    import ffi.C_FLOAT
    import ffi.NativeAddress
    import java.lang.foreign.FunctionDescriptor
    
    
""".trimIndent()

internal fun File.generateJvmNativeFunctions(functions: List<CLibraryModel.Function>) {
    writeText(header)
    functions.toJvmFunctionsInterface()
        .let(::appendText)
}

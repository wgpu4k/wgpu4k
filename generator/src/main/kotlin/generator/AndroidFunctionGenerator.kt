package generator

import disclamer
import domain.CLibraryModel
import generator.function.toAndroidFunctions
import generator.function.toJnaFunctionsInterface
import java.io.File

private val jnaHeader = """
    $disclamer
    package webgpu.android

    
    
""".trimIndent()


private val header = """
    $disclamer
    package webgpu
    
    import ffi.CString
    import ffi.NativeAddress
    import ffi.CallbackHolder
    import ffi.ArrayHolder
    import ffi.adapt
    
    
    
""".trimIndent()

internal fun File.generateAndroidNativeFunctions(functions: List<CLibraryModel.Function>) = resolve("webgpu")
    .resolve("android")
    .resolve("Functions.kt").apply {
        writeText(jnaHeader)

        functions.toJnaFunctionsInterface()
            .let(::appendText)

    }


internal fun File.generateAndroidFunctions(functions: List<CLibraryModel.Function>) = resolve("webgpu")
    .resolve("Functions.android.kt").apply {
        writeText(header)

        functions.toAndroidFunctions()
            .let(::appendText)

    }
package generator

import commonMainBasePath
import disclamer
import domain.CLibraryModel
import domain.toFunctionKotlinType
import generator.function.toNativeFunctionsInterface
import generator.function.toJvmFunctions
import jvmMainBasePath
import java.io.File

val functionsCommonMainFile = commonMainBasePath
    .resolve("webgpu")
    .resolve("Functions.kt")

val functionsJvmMainFile = jvmMainBasePath
    .resolve("webgpu")
    .resolve("Functions.jvm.kt")

private val header = """
    $disclamer
    package webgpu
    
    import ffi.CString
    import ffi.NativeAddress
    import ffi.CallbackHolder
    import ffi.ArrayHolder
    
    
    
""".trimIndent()

private val jvmHeader = """
    $disclamer
    package webgpu
    
    import ffi.CString
    import ffi.NativeAddress
    import ffi.ArrayHolder
    import ffi.CallbackHolder
    import ffi.adapt
    
    
    
""".trimIndent()

private val nativeHeader = """
    @file:OptIn(ExperimentalForeignApi::class)
    $disclamer
    package webgpu
    
    import ffi.CString
    import ffi.NativeAddress
    import ffi.ArrayHolder
    import ffi.CallbackHolder
    import kotlinx.cinterop.ExperimentalForeignApi
    import kotlinx.cinterop.toCPointer
    
    
    
""".trimIndent()

internal fun File.generateCommonFunctions(functions: List<CLibraryModel.Function>) {

    writeText(header)

    functions.forEach { function ->
        writeFunction(function)
    }

}

private fun File.writeFunction(function: CLibraryModel.Function) {
    val name = function.name
    val returnType = function.returnType.toFunctionKotlinType() + function.returnType.optionalReturnType()
    val args = function.args
        .map { (name, type) -> "${name}: ${type.toFunctionKotlinType()}${type.optional()}" }
        .joinToString(", ")
    appendText("expect fun $name($args): $returnType\n")
}

private fun CLibraryModel.Type.optionalReturnType(): String = when (this) {
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    is CLibraryModel.Array -> "?"
    else -> ""
}

private fun CLibraryModel.Type.optional(): String = when (this) {
    CLibraryModel.Void,
    CLibraryModel.Reference.OpaquePointer,
    is CLibraryModel.Reference.Pointer,
    is CLibraryModel.Reference.Structure,
    CLibraryModel.Reference.CString,
    is CLibraryModel.Reference.Callback,
    is CLibraryModel.Array -> "?"
    else -> ""
}


internal fun File.generateNativeFunctions(functions: List<CLibraryModel.Function>) = resolve("webgpu")
    .resolve("Functions.native.kt").apply {
    writeText(nativeHeader)
    functions.toNativeFunctionsInterface()
        .let(::appendText)
}


internal fun File.generateJvmFunctions(functions: List<CLibraryModel.Function>) {
    writeText(jvmHeader)
    functions.toJvmFunctions()
        .let(::appendText)
}


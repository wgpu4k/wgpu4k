package generator

import commonMainBasePath
import disclamer
import domain.CLibraryModel
import generator.callback.toCommonCallback
import generator.callback.toJnaCallback
import generator.callback.toJvmCallback
import generator.callback.toNativeCallback
import java.io.File

private val header = """
    $disclamer
    package webgpu
    
    import ffi.Callback
    import ffi.CString
    import ffi.CallbackHolder
    import ffi.MemoryAllocator
    import ffi.NativeAddress
    
    
""".trimIndent()

private val nativeHeader = """
    $disclamer
    @file:OptIn(ExperimentalForeignApi::class)
    
    package webgpu
    
    import ffi.CString
    import ffi.Callback
    import ffi.CallbackHolder
    import ffi.MemoryAllocator
    import ffi.NativeAddress
    import ffi.findCallback
    import ffi.registerCallback
    import ffi.globalMemory
    import kotlinx.cinterop.COpaque
    import kotlinx.cinterop.COpaquePointer
    import kotlinx.cinterop.useContents
    import kotlinx.cinterop.ExperimentalForeignApi
    import kotlinx.cinterop.LongVar
    import kotlinx.cinterop.pointed
    import kotlinx.cinterop.reinterpret
    import kotlinx.cinterop.value
    
    
""".trimIndent()

internal fun File.generateCommonCallback(callbacks: List<CLibraryModel.Callback>) = resolve("webgpu")
    .resolve("Callbacks.kt").apply {

    writeText(header)
    callbacks.map(CLibraryModel.Callback::toCommonCallback)
        .forEach(::appendText)
}

internal fun File.generateJvmCallback(callbacks: List<CLibraryModel.Callback>) = resolve("webgpu")
    .resolve("Callbacks.jvm.kt").apply {

        writeText(header)
        callbacks.map(CLibraryModel.Callback::toJvmCallback)
            .forEach(::appendText)
    }

internal fun File.generateAndroidCallback(callbacks: List<CLibraryModel.Callback>) = resolve("webgpu")
    .resolve("Callbacks.android.kt").apply {

        writeText(header)
        callbacks.map(CLibraryModel.Callback::toJnaCallback)
            .forEach(::appendText)
    }

internal fun File.generateNativeCallback(callbacks: List<CLibraryModel.Callback>) = resolve("webgpu")
    .resolve("Callbacks.native.kt").apply {

        writeText(nativeHeader)
        callbacks.map(CLibraryModel.Callback::toNativeCallback)
            .forEach(::appendText)
    }
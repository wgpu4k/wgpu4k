package io.ygdrasil.wgpu.internal.jvm

import io.ygdrasil.wgpu.WGPU
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

internal fun exportAndLoadLibrary() {
    when (Platform.os) {
        Os.Windows -> System.loadLibrary("WGPU")
        else -> {
            val libraryPath = findLibraryPath()
            val libraryFile =
                extractResourceToTemp(libraryPath)?.toFile() ?: error("fail to find webgpu library")
            System.load(libraryFile.absolutePath)
        }
    }
}

private fun findLibraryPath(): String {
    val os = when (Platform.os) {
        Os.Linux -> "linux"
        Os.Windows -> "win32"
        Os.MacOs -> "darwin"
    }

    val libraryName = when (Platform.os) {
        Os.Linux -> "libWGPU.so"
        Os.Windows -> "WGPU.dll"
        Os.MacOs -> "libWGPU.dylib"
    }

    val architecture = when (Platform.architecture) {
        Architecture.X86_64 -> "x86-64"
        Architecture.AARCH64 -> when (Platform.os) {
            Os.Windows -> error("aarch64 not supported on windows")
            else -> "aarch64"
        }
    }

    return "/$os-$architecture/$libraryName"
}

private fun extractResourceToTemp(fileOnClasspath: String): Path? {
    // fetch file from the classpath
    val resourceAsStream: InputStream? = WGPU::class.java.getResourceAsStream(fileOnClasspath)

    if (resourceAsStream == null) {
        println("Could not find file $fileOnClasspath on the classpath")
        return null
    }

    // create a temp file
    val tempFile = Files.createTempFile("wgpu", "lib")
    println(tempFile.toFile().absolutePath)
    tempFile.toFile().deleteOnExit()

    // copy the file to the temp directory
    Files.copy(resourceAsStream, tempFile, StandardCopyOption.REPLACE_EXISTING)

    return tempFile
}
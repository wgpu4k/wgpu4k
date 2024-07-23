package io.ygdrasil.wgpu.internal.jvm

import io.ygdrasil.wgpu.WGPU
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.StandardCopyOption

internal fun exportAndLoadLibrary() {
    val libraryPath = findLibraryPath()
    when (Platform.os) {
        Os.Windows -> {
            val libraryFiles = inferWindowsWGPUPossiblePath()
            val libraryFile = libraryFiles.firstOrNull { it.exists() } ?: libraryFiles.firstOrNull { path ->
                extractResourceToTemp(libraryPath, path)
            } ?: error("Could not find temporary resource for path: $libraryPath")
            System.loadLibrary(libraryFile.nameWithoutExtension)
        }

        Os.MacOs -> {
            val libraryFiles = inferDarwinWGPUPossiblePath()
            val libraryFile = libraryFiles.firstOrNull { it.exists() } ?: libraryFiles.firstOrNull { path ->
                extractResourceToTemp(libraryPath, path)
            } ?: error("Could not find temporary resource for path: $libraryPath")
            System.loadLibrary(libraryFile.nameWithoutExtension.removePrefix("lib"))
        }

        Os.Linux -> {
            val libraryFile = generateTempFile()
            extractResourceToTemp(libraryPath, libraryFile)
            System.load(libraryFile.absolutePath)
        }
    }
}

private fun inferDarwinWGPUPossiblePath(): List<File> = inferWGPUPossiblePath("lib", "dylib")

private fun inferWindowsWGPUPossiblePath(): List<File> = inferWGPUPossiblePath("", "dll")

private fun inferWGPUPossiblePath(prefix: String, extension: String): List<File> {
    return libraryPaths().map { File(it) }
        .map { it.resolve("${prefix}WGPU.$extension") }
}

private fun libraryPaths() = System.getProperty("java.library.path").split(File.pathSeparator)

private fun listWritablePathOn(files: List<File>): List<File> {
    return files.filter { it.canWrite() }
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

private fun generateTempFile(): File {
    // create a temp file
    val tempFile = Files.createTempFile("wgpu", "lib").toFile()
    println(tempFile.absolutePath)
    return tempFile
}

private fun extractResourceToTemp(fileOnClasspath: String, target: File): Boolean {
    println("will extract library to path ${target.absolutePath}")

    try {
        // fetch file from the classpath
        val resourceAsStream: InputStream? = WGPU::class.java.getResourceAsStream(fileOnClasspath)

        if (resourceAsStream == null) {
            error("Could not find file $fileOnClasspath on the classpath")
        }

        // copy the file to the temp directory
        Files.copy(resourceAsStream, target.toPath(), StandardCopyOption.REPLACE_EXISTING)
        target.deleteOnExit()
    } catch (exception: IOException) {
        println("fail to extract to path ${target.absolutePath} with reason ${exception.message}")
        return false
    }

    return true
}
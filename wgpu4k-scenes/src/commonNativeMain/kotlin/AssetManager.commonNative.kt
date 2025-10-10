package io.ygdrasil.webgpu.examples

import korlibs.io.file.VfsFile
import korlibs.io.file.std.resourcesVfs
import korlibs.io.file.std.standardVfs

actual suspend fun String.asVsfFile(): VfsFile = resourcesVfs[this].takeIf { it.exists() }
    ?: standardVfs[this]
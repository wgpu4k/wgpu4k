package io.ygdrasil.webgpu.examples

import korlibs.io.file.VfsFile
import korlibs.io.file.std.resourcesVfs
import korlibs.io.file.std.rootLocalVfs

actual suspend fun String.asVsfFile(): VfsFile = resourcesVfs[this].takeIf { it.exists() }
    ?: rootLocalVfs[this].takeIf { it.exists() } ?: error("fail to get file from file system")
package io.ygdrasil.webgpu.examples

import korlibs.io.file.VfsFile
import korlibs.io.file.std.resourcesVfs

actual suspend fun String.asVsfFile(): VfsFile = resourcesVfs[this]
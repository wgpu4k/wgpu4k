package io.ygdrasil.webgpu.examples

import korlibs.io.file.Vfs
import korlibs.io.file.std.AndroidResourcesVfs

actual var androidVfs: Vfs? = AndroidResourcesVfs()
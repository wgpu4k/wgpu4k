package io.ygdrasil.webgpu

import io.kotest.core.spec.style.FreeSpec
import io.ygdrasil.webgpu.internal.jvm.Os
import io.ygdrasil.webgpu.internal.jvm.Platform
import io.ygdrasil.wgpu.wgpuSetLogLevel

class WGPUTest : FreeSpec({

    "test library loading"
        .config(enabled = Platform.os != Os.Linux) { // TODO find why this test fail on CI
            // Given nothing

            // When
            // The generated v29 loader is not part of kffi's public API, so keep this
            // compatibility smoke test reflective and isolated from production code.
            val loaderClass = Class.forName("ffi.LibraryLoader")
            val loader = loaderClass.getField("INSTANCE").get(null)
            loaderClass.getMethod("load").invoke(loader)

            // Then should not crash
            wgpuSetLogLevel(1u)

        }

})

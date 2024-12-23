package io.ygdrasil.wgpu

import io.kotest.core.spec.style.FreeSpec
import io.ygdrasil.wgpu.internal.jvm.Os
import io.ygdrasil.wgpu.internal.jvm.Platform
import webgpu.wgpuSetLogLevel

class WGPUTest : FreeSpec({

    "test library loading"
        .config(enabled = Platform.os != Os.Linux) { // TODO find why this test fail on CI
            // Given nothing

            // When
            loadLibrary()

            // Then should not crash
            wgpuSetLogLevel(1)

        }

})
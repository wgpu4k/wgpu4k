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
            // Then the first generated downcall should auto-bootstrap the native library.
            wgpuSetLogLevel(1u)

        }

})

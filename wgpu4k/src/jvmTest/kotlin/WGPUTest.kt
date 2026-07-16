package io.ygdrasil.webgpu

import io.kotest.core.spec.style.FreeSpec
import io.ygdrasil.wgpu.wgpuSetLogLevel

class WGPUTest : FreeSpec({

    "test library loading" {
        // Given nothing

        // When
        // Then the first generated downcall should auto-bootstrap the native library.
        wgpuSetLogLevel(1u)

    }

})

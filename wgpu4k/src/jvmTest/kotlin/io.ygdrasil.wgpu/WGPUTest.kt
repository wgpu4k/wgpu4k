package io.ygdrasil.wgpu

import io.kotest.core.spec.style.FreeSpec
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h

class WGPUTest: FreeSpec({

    "test library loading" {
        // Given nothing

        // When
        WGPU.loadLibrary()

        // Then should not crash
        wgpu_h.wgpuSetLogLevel(1)

    }

})
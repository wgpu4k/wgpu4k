package io.ygdrasil.wgpu.internal.jvm

import java.lang.foreign.Arena

internal fun <T> confined(block: (Arena) -> T) = Arena.ofConfined()
    .use { arena ->
        block(arena)
    }


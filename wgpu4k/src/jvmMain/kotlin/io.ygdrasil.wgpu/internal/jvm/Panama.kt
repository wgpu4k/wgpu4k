package io.ygdrasil.wgpu.internal.jvm

import com.sun.jna.Pointer
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment


fun <T> confined(block: (Arena) -> T) = Arena.ofConfined()
    .use { arena ->
        block(arena)
    }

fun Pointer.toMemory() = MemorySegment.ofAddress(Pointer.nativeValue(this))
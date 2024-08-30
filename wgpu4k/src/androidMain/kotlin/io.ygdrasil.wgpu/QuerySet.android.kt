package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import java.lang.foreign.MemorySegment

// TODO to implement
actual class QuerySet(val handler: Long) {

    internal val mhandler = MemorySegment(Pointer(handler), 0L)

}
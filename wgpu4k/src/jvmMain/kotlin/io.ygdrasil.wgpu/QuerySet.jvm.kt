package io.ygdrasil.wgpu

import java.lang.foreign.MemorySegment

actual class QuerySet(internal val handler: MemorySegment)
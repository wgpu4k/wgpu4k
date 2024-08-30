package java.lang.foreign

import com.sun.jna.Pointer
import java.util.function.Consumer

class MemorySegment(val pointer: Pointer) {

    fun get(layout: ValueLayout.OfDouble?, offest: Long ) : Double {
        return pointer.getDouble(offest)
    }

    fun set(layout: ValueLayout.OfDouble?, offest: Long, newValue: Double  ) {
        return pointer.setDouble(offest, newValue)
    }

    fun asSlice(offest: Long): MemorySegment = MemorySegment(pointer.share(offest))
    fun reinterpret(l: Long, arena: Arena, cleanup: Consumer<MemorySegment?>): MemorySegment = MemorySegment(pointer)
}
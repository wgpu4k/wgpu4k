package java.lang.foreign

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.toAddress
import java.util.function.Consumer

class MemorySegment(val pointer: Pointer, val size: Long) {

    fun get(layout: ValueLayout.OfDouble?, offest: Long ) : Double {
        return pointer.getDouble(offest)
    }

    fun set(layout: ValueLayout.OfDouble?, offest: Long, newValue: Double  ) {
        return pointer.setDouble(offest, newValue)
    }

    fun get(layout: ValueLayout.OfFloat?, offest: Long ) : Float {
        return pointer.getFloat(offest)
    }

    fun set(layout: ValueLayout.OfFloat?, offest: Long, newValue: Float  ) {
        return pointer.setFloat(offest, newValue)
    }
    fun get(layout: ValueLayout.OfInt?, offest: Long ) : Int {
        return pointer.getInt(offest)
    }
    fun set(layout: ValueLayout.OfInt?, offest: Long, newValue: Int  ) {
        return pointer.setInt(offest, newValue)
    }
    fun get(layout: ValueLayout.OfLong?, offest: Long ) : Long {
        return pointer.getLong(offest)
    }

    fun set(layout: ValueLayout.OfLong?, offest: Long, newValue: Long  ) {
        return pointer.setLong(offest, newValue )
    }

    fun get(layout: AddressLayout?, offest: Long ) : MemorySegment {
        return pointer.getLong(offest)
            .let { MemorySegment(Pointer(it), size - offest) }
    }

    fun set(layout: AddressLayout?, offest: Long, newValue: MemorySegment  ) {
        return pointer.setLong(offest, newValue.pointer.toAddress() )
    }

    fun asSlice(offest: Long): MemorySegment = MemorySegment(pointer.share(offest), size - offest)
    fun reinterpret(l: Long, arena: Arena, cleanup: Consumer<MemorySegment?>): MemorySegment = MemorySegment(
        pointer,
        size
    )
    fun fillWithZero() {
        repeat((size / 8).toInt()) {
            pointer.setLong(it * Long.SIZE_BYTES.toLong(), 0L) }
    }
}
// Generated by jextract
package io.ygdrasil.wgpu.internal.jna

import java.lang.foreign.AddressLayout
import java.lang.foreign.Arena
import java.lang.foreign.GroupLayout
import java.lang.foreign.MemoryLayout.Companion.paddingLayout
import java.lang.foreign.MemoryLayout.Companion.sequenceLayout
import java.lang.foreign.MemoryLayout.Companion.structLayout
import java.lang.foreign.MemoryLayout.PathElement.groupElement
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.ValueLayout
import java.util.function.Consumer

/**
 * {@snippet lang=c :
 * * struct WGPUChainedStruct {
 * *     const struct WGPUChainedStruct *next;
 * *     WGPUSType sType;
 * * }
 * * }
 */
object WGPUChainedStruct {
    private val `$LAYOUT` = structLayout(
        wgpu_h.C_POINTER.withName("next"),
        wgpu_h.C_INT.withName("sType"),
        paddingLayout(4)
    ).withName("WGPUChainedStruct")

    /**
     * The layout of this struct
     */
    fun layout(): GroupLayout {
        return `$LAYOUT`
    }

    private val `next$LAYOUT` = `$LAYOUT`.select(groupElement("next")) as AddressLayout?

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * const struct WGPUChainedStruct *next
     * * }
     */
    fun `next$layout`(): AddressLayout? {
        return `next$LAYOUT`
    }

    private const val `next$OFFSET`: Long = 0

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * const struct WGPUChainedStruct *next
     * * }
     */
    fun `next$offset`(): Long {
        return `next$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * const struct WGPUChainedStruct *next
     * * }
     */
    fun next(struct: MemorySegment): MemorySegment {
        return struct.get(`next$LAYOUT`, `next$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * const struct WGPUChainedStruct *next
     * * }
     */
    fun next(struct: MemorySegment, fieldValue: MemorySegment) {
        struct.set(`next$LAYOUT`, `next$OFFSET`, fieldValue)
    }

    private val `sType$LAYOUT` = `$LAYOUT`.select(groupElement("sType")) as ValueLayout.OfInt?

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * WGPUSType sType
     * * }
     */
    fun `sType$layout`(): ValueLayout.OfInt? {
        return `sType$LAYOUT`
    }

    private const val `sType$OFFSET`: Long = 8

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * WGPUSType sType
     * * }
     */
    fun `sType$offset`(): Long {
        return `sType$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * WGPUSType sType
     * * }
     */
    fun sType(struct: MemorySegment): Int {
        return struct.get(`sType$LAYOUT`, `sType$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * WGPUSType sType
     * * }
     */
    fun sType(struct: MemorySegment, fieldValue: Int) {
        struct.set(`sType$LAYOUT`, `sType$OFFSET`, fieldValue)
    }

    /**
     * Obtains a slice of `arrayParam` which selects the array element at `index`.
     * The returned segment has address `arrayParam.address() + index * layout().byteSize()`
     */
    fun asSlice(array: MemorySegment, index: Long): MemorySegment {
        return array.asSlice(layout().byteSize() * index)
    }

    /**
     * The size (in bytes) of this struct
     */
    fun sizeof(): Long {
        return layout().byteSize()
    }

    /**
     * Allocate a segment of size `layout().byteSize()` using `allocator`
     */
    fun allocate(allocator: SegmentAllocator): MemorySegment {
        return allocator.allocate(layout())
    }

    /**
     * Allocate an array of size `elementCount` using `allocator`.
     * The returned segment has size `elementCount * layout().byteSize()`.
     */
    fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment {
        return allocator.allocate(sequenceLayout(elementCount, layout()))
    }

    /**
     * Reinterprets `addr` using target `arena` and `cleanupAction` (if any).
     * The returned segment has size `layout().byteSize()`
     */
    fun reinterpret(addr: MemorySegment, arena: Arena, cleanup: Consumer<MemorySegment?>): MemorySegment {
        return reinterpret(addr, 1, arena, cleanup)
    }

    /**
     * Reinterprets `addr` using target `arena` and `cleanupAction` (if any).
     * The returned segment has size `elementCount * layout().byteSize()`
     */
    fun reinterpret(
        addr: MemorySegment,
        elementCount: Long,
        arena: Arena,
        cleanup: Consumer<MemorySegment?>
    ): MemorySegment {
        return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup)
    }
}


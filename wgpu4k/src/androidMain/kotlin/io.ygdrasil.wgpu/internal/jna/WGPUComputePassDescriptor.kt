// Generated by jextract
package io.ygdrasil.wgpu.internal.jna

import java.lang.foreign.AddressLayout
import java.lang.foreign.Arena
import java.lang.foreign.GroupLayout
import java.lang.foreign.MemoryLayout.Companion.sequenceLayout
import java.lang.foreign.MemoryLayout.Companion.structLayout
import java.lang.foreign.MemoryLayout.PathElement.groupElement
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.util.function.Consumer

/**
 * {@snippet lang=c :
 * * struct WGPUComputePassDescriptor {
 * *     const WGPUChainedStruct *nextInChain;
 * *     const char *label;
 * *     const WGPUComputePassTimestampWrites *timestampWrites;
 * * }
 * * }
 */
object WGPUComputePassDescriptor {
    private val `$LAYOUT` = structLayout(
        wgpu_h.C_POINTER.withName("nextInChain"),
        wgpu_h.C_POINTER.withName("label"),
        wgpu_h.C_POINTER.withName("timestampWrites")
    ).withName("WGPUComputePassDescriptor")

    /**
     * The layout of this struct
     */
    fun layout(): GroupLayout {
        return `$LAYOUT`
    }

    private val `nextInChain$LAYOUT`: AddressLayout = `$LAYOUT`.select(groupElement("nextInChain")) as AddressLayout

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * const WGPUChainedStruct *nextInChain
     * * }
     */
    fun `nextInChain$layout`(): AddressLayout {
        return `nextInChain$LAYOUT`
    }

    private const val `nextInChain$OFFSET`: Long = 0

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * const WGPUChainedStruct *nextInChain
     * * }
     */
    fun `nextInChain$offset`(): Long {
        return `nextInChain$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * const WGPUChainedStruct *nextInChain
     * * }
     */
    fun nextInChain(struct: MemorySegment): MemorySegment {
        return struct.get(`nextInChain$LAYOUT`, `nextInChain$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * const WGPUChainedStruct *nextInChain
     * * }
     */
    fun nextInChain(struct: MemorySegment, fieldValue: MemorySegment?) {
        struct.set(
            `nextInChain$LAYOUT`, `nextInChain$OFFSET`,
            fieldValue!!
        )
    }

    private val `label$LAYOUT`: AddressLayout = `$LAYOUT`.select(groupElement("label")) as AddressLayout

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * const char *label
     * * }
     */
    fun `label$layout`(): AddressLayout {
        return `label$LAYOUT`
    }

    private const val `label$OFFSET`: Long = 8

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * const char *label
     * * }
     */
    fun `label$offset`(): Long {
        return `label$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * const char *label
     * * }
     */
    fun label(struct: MemorySegment): MemorySegment {
        return struct.get(`label$LAYOUT`, `label$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * const char *label
     * * }
     */
    fun label(struct: MemorySegment, fieldValue: MemorySegment?) {
        struct.set(
            `label$LAYOUT`, `label$OFFSET`,
            fieldValue!!
        )
    }

    private val `timestampWrites$LAYOUT`: AddressLayout =
        `$LAYOUT`.select(groupElement("timestampWrites")) as AddressLayout

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * const WGPUComputePassTimestampWrites *timestampWrites
     * * }
     */
    fun `timestampWrites$layout`(): AddressLayout {
        return `timestampWrites$LAYOUT`
    }

    private const val `timestampWrites$OFFSET`: Long = 16

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * const WGPUComputePassTimestampWrites *timestampWrites
     * * }
     */
    fun `timestampWrites$offset`(): Long {
        return `timestampWrites$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * const WGPUComputePassTimestampWrites *timestampWrites
     * * }
     */
    fun timestampWrites(struct: MemorySegment): MemorySegment {
        return struct.get(`timestampWrites$LAYOUT`, `timestampWrites$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * const WGPUComputePassTimestampWrites *timestampWrites
     * * }
     */
    fun timestampWrites(struct: MemorySegment, fieldValue: MemorySegment?) {
        struct.set(
            `timestampWrites$LAYOUT`, `timestampWrites$OFFSET`,
            fieldValue!!
        )
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


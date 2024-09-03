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
 * * struct WGPUStorageTextureBindingLayout {
 * *     const WGPUChainedStruct *nextInChain;
 * *     WGPUStorageTextureAccess access;
 * *     WGPUTextureFormat format;
 * *     WGPUTextureViewDimension viewDimension;
 * * }
 * * }
 */
object WGPUStorageTextureBindingLayout {
    private val `$LAYOUT` = structLayout(
        wgpu_h.C_POINTER.withName("nextInChain"),
        wgpu_h.C_INT.withName("access"),
        wgpu_h.C_INT.withName("format"),
        wgpu_h.C_INT.withName("viewDimension"),
        paddingLayout(4)
    ).withName("WGPUStorageTextureBindingLayout")

    /**
     * The layout of this struct
     */
    @JvmStatic
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

    private val `access$LAYOUT` = `$LAYOUT`.select(groupElement("access")) as ValueLayout.OfInt

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * WGPUStorageTextureAccess access
     * * }
     */
    fun `access$layout`(): ValueLayout.OfInt {
        return `access$LAYOUT`
    }

    private const val `access$OFFSET`: Long = 8

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * WGPUStorageTextureAccess access
     * * }
     */
    fun `access$offset`(): Long {
        return `access$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * WGPUStorageTextureAccess access
     * * }
     */
    fun access(struct: MemorySegment): Int {
        return struct.get(`access$LAYOUT`, `access$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * WGPUStorageTextureAccess access
     * * }
     */
    fun access(struct: MemorySegment, fieldValue: Int) {
        struct.set(`access$LAYOUT`, `access$OFFSET`, fieldValue)
    }

    private val `format$LAYOUT` = `$LAYOUT`.select(groupElement("format")) as ValueLayout.OfInt

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * WGPUTextureFormat format
     * * }
     */
    fun `format$layout`(): ValueLayout.OfInt {
        return `format$LAYOUT`
    }

    private const val `format$OFFSET`: Long = 12

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * WGPUTextureFormat format
     * * }
     */
    fun `format$offset`(): Long {
        return `format$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * WGPUTextureFormat format
     * * }
     */
    fun format(struct: MemorySegment): Int {
        return struct.get(`format$LAYOUT`, `format$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * WGPUTextureFormat format
     * * }
     */
    fun format(struct: MemorySegment, fieldValue: Int) {
        struct.set(`format$LAYOUT`, `format$OFFSET`, fieldValue)
    }

    private val `viewDimension$LAYOUT` = `$LAYOUT`.select(groupElement("viewDimension")) as ValueLayout.OfInt

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * WGPUTextureViewDimension viewDimension
     * * }
     */
    fun `viewDimension$layout`(): ValueLayout.OfInt {
        return `viewDimension$LAYOUT`
    }

    private const val `viewDimension$OFFSET`: Long = 16

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * WGPUTextureViewDimension viewDimension
     * * }
     */
    fun `viewDimension$offset`(): Long {
        return `viewDimension$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * WGPUTextureViewDimension viewDimension
     * * }
     */
    fun viewDimension(struct: MemorySegment): Int {
        return struct.get(`viewDimension$LAYOUT`, `viewDimension$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * WGPUTextureViewDimension viewDimension
     * * }
     */
    fun viewDimension(struct: MemorySegment, fieldValue: Int) {
        struct.set(`viewDimension$LAYOUT`, `viewDimension$OFFSET`, fieldValue)
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


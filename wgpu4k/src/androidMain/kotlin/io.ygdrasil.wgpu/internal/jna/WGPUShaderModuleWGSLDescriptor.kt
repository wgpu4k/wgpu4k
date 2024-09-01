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
 * * struct WGPUShaderModuleWGSLDescriptor {
 * *     WGPUChainedStruct chain;
 * *     const char *code;
 * * }
 * * }
 */
object WGPUShaderModuleWGSLDescriptor {
    private val `$LAYOUT` = structLayout(
        WGPUChainedStruct.layout().withName("chain"),
        wgpu_h.C_POINTER.withName("code")
    ).withName("WGPUShaderModuleWGSLDescriptor")

    /**
     * The layout of this struct
     */
    fun layout(): GroupLayout {
        return `$LAYOUT`
    }

    private val `chain$LAYOUT` = `$LAYOUT`.select(groupElement("chain")) as GroupLayout

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * WGPUChainedStruct chain
     * * }
     */
    fun `chain$layout`(): GroupLayout {
        return `chain$LAYOUT`
    }

    private const val `chain$OFFSET`: Long = 0

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * WGPUChainedStruct chain
     * * }
     */
    fun `chain$offset`(): Long {
        return `chain$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * WGPUChainedStruct chain
     * * }
     */
    fun chain(struct: MemorySegment): MemorySegment {
        return struct.asSlice(`chain$OFFSET`, `chain$LAYOUT`.byteSize())
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * WGPUChainedStruct chain
     * * }
     */
    fun chain(struct: MemorySegment?, fieldValue: MemorySegment?) {
        MemorySegment.copy(
            fieldValue!!, 0L,
            struct!!, `chain$OFFSET`, `chain$LAYOUT`.byteSize()
        )
    }

    private val `code$LAYOUT`: AddressLayout = `$LAYOUT`.select(groupElement("code")) as AddressLayout

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * const char *code
     * * }
     */
    fun `code$layout`(): AddressLayout {
        return `code$LAYOUT`
    }

    private const val `code$OFFSET`: Long = 16

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * const char *code
     * * }
     */
    fun `code$offset`(): Long {
        return `code$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * const char *code
     * * }
     */
    fun code(struct: MemorySegment): MemorySegment {
        return struct.get(`code$LAYOUT`, `code$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * const char *code
     * * }
     */
    fun code(struct: MemorySegment, fieldValue: MemorySegment?) {
        struct.set(
            `code$LAYOUT`, `code$OFFSET`,
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

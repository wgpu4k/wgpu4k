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
 * * struct WGPUTextureViewDescriptor {
 * *     const WGPUChainedStruct *nextInChain;
 * *     const char *label;
 * *     WGPUTextureFormat format;
 * *     WGPUTextureViewDimension dimension;
 * *     uint32_t baseMipLevel;
 * *     uint32_t mipLevelCount;
 * *     uint32_t baseArrayLayer;
 * *     uint32_t arrayLayerCount;
 * *     WGPUTextureAspect aspect;
 * * }
 * * }
 */
object WGPUTextureViewDescriptor {
    private val `$LAYOUT` = structLayout(
        wgpu_h.C_POINTER.withName("nextInChain"),
        wgpu_h.C_POINTER.withName("label"),
        wgpu_h.C_INT.withName("format"),
        wgpu_h.C_INT.withName("dimension"),
        wgpu_h.C_INT.withName("baseMipLevel"),
        wgpu_h.C_INT.withName("mipLevelCount"),
        wgpu_h.C_INT.withName("baseArrayLayer"),
        wgpu_h.C_INT.withName("arrayLayerCount"),
        wgpu_h.C_INT.withName("aspect"),
        paddingLayout(4)
    ).withName("WGPUTextureViewDescriptor")

    /**
     * The layout of this struct
     */
    fun layout(): GroupLayout {
        return `$LAYOUT`
    }

    private val `nextInChain$LAYOUT` = `$LAYOUT`.select(groupElement("nextInChain")) as AddressLayout?

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * const WGPUChainedStruct *nextInChain
     * * }
     */
    fun `nextInChain$layout`(): AddressLayout? {
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
    fun nextInChain(struct: MemorySegment, fieldValue: MemorySegment) {
        struct.set(`nextInChain$LAYOUT`, `nextInChain$OFFSET`, fieldValue)
    }

    private val `label$LAYOUT` = `$LAYOUT`.select(groupElement("label")) as AddressLayout?

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * const char *label
     * * }
     */
    fun `label$layout`(): AddressLayout? {
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
    fun label(struct: MemorySegment, fieldValue: MemorySegment) {
        struct.set(`label$LAYOUT`, `label$OFFSET`, fieldValue)
    }

    private val `format$LAYOUT` = `$LAYOUT`.select(groupElement("format")) as ValueLayout.OfInt?

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * WGPUTextureFormat format
     * * }
     */
    fun `format$layout`(): ValueLayout.OfInt? {
        return `format$LAYOUT`
    }

    private const val `format$OFFSET`: Long = 16

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

    private val `dimension$LAYOUT` = `$LAYOUT`.select(groupElement("dimension")) as ValueLayout.OfInt?

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * WGPUTextureViewDimension dimension
     * * }
     */
    fun `dimension$layout`(): ValueLayout.OfInt? {
        return `dimension$LAYOUT`
    }

    private const val `dimension$OFFSET`: Long = 20

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * WGPUTextureViewDimension dimension
     * * }
     */
    fun `dimension$offset`(): Long {
        return `dimension$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * WGPUTextureViewDimension dimension
     * * }
     */
    fun dimension(struct: MemorySegment): Int {
        return struct.get(`dimension$LAYOUT`, `dimension$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * WGPUTextureViewDimension dimension
     * * }
     */
    fun dimension(struct: MemorySegment, fieldValue: Int) {
        struct.set(`dimension$LAYOUT`, `dimension$OFFSET`, fieldValue)
    }

    private val `baseMipLevel$LAYOUT` = `$LAYOUT`.select(groupElement("baseMipLevel")) as ValueLayout.OfInt?

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * uint32_t baseMipLevel
     * * }
     */
    fun `baseMipLevel$layout`(): ValueLayout.OfInt? {
        return `baseMipLevel$LAYOUT`
    }

    private const val `baseMipLevel$OFFSET`: Long = 24

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * uint32_t baseMipLevel
     * * }
     */
    fun `baseMipLevel$offset`(): Long {
        return `baseMipLevel$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * uint32_t baseMipLevel
     * * }
     */
    fun baseMipLevel(struct: MemorySegment): Int {
        return struct.get(`baseMipLevel$LAYOUT`, `baseMipLevel$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * uint32_t baseMipLevel
     * * }
     */
    fun baseMipLevel(struct: MemorySegment, fieldValue: Int) {
        struct.set(`baseMipLevel$LAYOUT`, `baseMipLevel$OFFSET`, fieldValue)
    }

    private val `mipLevelCount$LAYOUT` = `$LAYOUT`.select(groupElement("mipLevelCount")) as ValueLayout.OfInt?

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * uint32_t mipLevelCount
     * * }
     */
    fun `mipLevelCount$layout`(): ValueLayout.OfInt? {
        return `mipLevelCount$LAYOUT`
    }

    private const val `mipLevelCount$OFFSET`: Long = 28

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * uint32_t mipLevelCount
     * * }
     */
    fun `mipLevelCount$offset`(): Long {
        return `mipLevelCount$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * uint32_t mipLevelCount
     * * }
     */
    fun mipLevelCount(struct: MemorySegment): Int {
        return struct.get(`mipLevelCount$LAYOUT`, `mipLevelCount$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * uint32_t mipLevelCount
     * * }
     */
    fun mipLevelCount(struct: MemorySegment, fieldValue: Int) {
        struct.set(`mipLevelCount$LAYOUT`, `mipLevelCount$OFFSET`, fieldValue)
    }

    private val `baseArrayLayer$LAYOUT` = `$LAYOUT`.select(groupElement("baseArrayLayer")) as ValueLayout.OfInt?

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * uint32_t baseArrayLayer
     * * }
     */
    fun `baseArrayLayer$layout`(): ValueLayout.OfInt? {
        return `baseArrayLayer$LAYOUT`
    }

    private const val `baseArrayLayer$OFFSET`: Long = 32

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * uint32_t baseArrayLayer
     * * }
     */
    fun `baseArrayLayer$offset`(): Long {
        return `baseArrayLayer$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * uint32_t baseArrayLayer
     * * }
     */
    fun baseArrayLayer(struct: MemorySegment): Int {
        return struct.get(`baseArrayLayer$LAYOUT`, `baseArrayLayer$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * uint32_t baseArrayLayer
     * * }
     */
    fun baseArrayLayer(struct: MemorySegment, fieldValue: Int) {
        struct.set(`baseArrayLayer$LAYOUT`, `baseArrayLayer$OFFSET`, fieldValue)
    }

    private val `arrayLayerCount$LAYOUT` = `$LAYOUT`.select(groupElement("arrayLayerCount")) as ValueLayout.OfInt?

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * uint32_t arrayLayerCount
     * * }
     */
    fun `arrayLayerCount$layout`(): ValueLayout.OfInt? {
        return `arrayLayerCount$LAYOUT`
    }

    private const val `arrayLayerCount$OFFSET`: Long = 36

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * uint32_t arrayLayerCount
     * * }
     */
    fun `arrayLayerCount$offset`(): Long {
        return `arrayLayerCount$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * uint32_t arrayLayerCount
     * * }
     */
    fun arrayLayerCount(struct: MemorySegment): Int {
        return struct.get(`arrayLayerCount$LAYOUT`, `arrayLayerCount$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * uint32_t arrayLayerCount
     * * }
     */
    fun arrayLayerCount(struct: MemorySegment, fieldValue: Int) {
        struct.set(`arrayLayerCount$LAYOUT`, `arrayLayerCount$OFFSET`, fieldValue)
    }

    private val `aspect$LAYOUT` = `$LAYOUT`.select(groupElement("aspect")) as ValueLayout.OfInt?

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * WGPUTextureAspect aspect
     * * }
     */
    fun `aspect$layout`(): ValueLayout.OfInt? {
        return `aspect$LAYOUT`
    }

    private const val `aspect$OFFSET`: Long = 40

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * WGPUTextureAspect aspect
     * * }
     */
    fun `aspect$offset`(): Long {
        return `aspect$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * WGPUTextureAspect aspect
     * * }
     */
    fun aspect(struct: MemorySegment): Int {
        return struct.get(`aspect$LAYOUT`, `aspect$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * WGPUTextureAspect aspect
     * * }
     */
    fun aspect(struct: MemorySegment, fieldValue: Int) {
        struct.set(`aspect$LAYOUT`, `aspect$OFFSET`, fieldValue)
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

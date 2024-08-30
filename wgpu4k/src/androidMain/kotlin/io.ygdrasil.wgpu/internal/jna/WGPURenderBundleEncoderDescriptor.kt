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
import java.lang.foreign.ValueLayout
import java.util.function.Consumer

/**
 * {@snippet lang=c :
 * * struct WGPURenderBundleEncoderDescriptor {
 * *     const WGPUChainedStruct *nextInChain;
 * *     const char *label;
 * *     size_t colorFormatCount;
 * *     const WGPUTextureFormat *colorFormats;
 * *     WGPUTextureFormat depthStencilFormat;
 * *     uint32_t sampleCount;
 * *     WGPUBool depthReadOnly;
 * *     WGPUBool stencilReadOnly;
 * * }
 * * }
 */
object WGPURenderBundleEncoderDescriptor {
    private val `$LAYOUT` = structLayout(
        wgpu_h.C_POINTER.withName("nextInChain"),
        wgpu_h.C_POINTER.withName("label"),
        wgpu_h.C_LONG.withName("colorFormatCount"),
        wgpu_h.C_POINTER.withName("colorFormats"),
        wgpu_h.C_INT.withName("depthStencilFormat"),
        wgpu_h.C_INT.withName("sampleCount"),
        wgpu_h.C_INT.withName("depthReadOnly"),
        wgpu_h.C_INT.withName("stencilReadOnly")
    ).withName("WGPURenderBundleEncoderDescriptor")

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

    private val `colorFormatCount$LAYOUT` = `$LAYOUT`.select(groupElement("colorFormatCount")) as ValueLayout.OfLong

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * size_t colorFormatCount
     * * }
     */
    fun `colorFormatCount$layout`(): ValueLayout.OfLong {
        return `colorFormatCount$LAYOUT`
    }

    private const val `colorFormatCount$OFFSET`: Long = 16

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * size_t colorFormatCount
     * * }
     */
    fun `colorFormatCount$offset`(): Long {
        return `colorFormatCount$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * size_t colorFormatCount
     * * }
     */
    fun colorFormatCount(struct: MemorySegment): Long {
        return struct.get(`colorFormatCount$LAYOUT`, `colorFormatCount$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * size_t colorFormatCount
     * * }
     */
    fun colorFormatCount(struct: MemorySegment, fieldValue: Long) {
        struct.set(`colorFormatCount$LAYOUT`, `colorFormatCount$OFFSET`, fieldValue)
    }

    private val `colorFormats$LAYOUT`: AddressLayout = `$LAYOUT`.select(groupElement("colorFormats")) as AddressLayout

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * const WGPUTextureFormat *colorFormats
     * * }
     */
    fun `colorFormats$layout`(): AddressLayout {
        return `colorFormats$LAYOUT`
    }

    private const val `colorFormats$OFFSET`: Long = 24

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * const WGPUTextureFormat *colorFormats
     * * }
     */
    fun `colorFormats$offset`(): Long {
        return `colorFormats$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * const WGPUTextureFormat *colorFormats
     * * }
     */
    fun colorFormats(struct: MemorySegment): MemorySegment {
        return struct.get(`colorFormats$LAYOUT`, `colorFormats$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * const WGPUTextureFormat *colorFormats
     * * }
     */
    fun colorFormats(struct: MemorySegment, fieldValue: MemorySegment?) {
        struct.set(
            `colorFormats$LAYOUT`, `colorFormats$OFFSET`,
            fieldValue!!
        )
    }

    private val `depthStencilFormat$LAYOUT` = `$LAYOUT`.select(groupElement("depthStencilFormat")) as ValueLayout.OfInt

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * WGPUTextureFormat depthStencilFormat
     * * }
     */
    fun `depthStencilFormat$layout`(): ValueLayout.OfInt {
        return `depthStencilFormat$LAYOUT`
    }

    private const val `depthStencilFormat$OFFSET`: Long = 32

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * WGPUTextureFormat depthStencilFormat
     * * }
     */
    fun `depthStencilFormat$offset`(): Long {
        return `depthStencilFormat$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * WGPUTextureFormat depthStencilFormat
     * * }
     */
    fun depthStencilFormat(struct: MemorySegment): Int {
        return struct.get(`depthStencilFormat$LAYOUT`, `depthStencilFormat$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * WGPUTextureFormat depthStencilFormat
     * * }
     */
    fun depthStencilFormat(struct: MemorySegment, fieldValue: Int) {
        struct.set(`depthStencilFormat$LAYOUT`, `depthStencilFormat$OFFSET`, fieldValue)
    }

    private val `sampleCount$LAYOUT` = `$LAYOUT`.select(groupElement("sampleCount")) as ValueLayout.OfInt

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * uint32_t sampleCount
     * * }
     */
    fun `sampleCount$layout`(): ValueLayout.OfInt {
        return `sampleCount$LAYOUT`
    }

    private const val `sampleCount$OFFSET`: Long = 36

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * uint32_t sampleCount
     * * }
     */
    fun `sampleCount$offset`(): Long {
        return `sampleCount$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * uint32_t sampleCount
     * * }
     */
    fun sampleCount(struct: MemorySegment): Int {
        return struct.get(`sampleCount$LAYOUT`, `sampleCount$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * uint32_t sampleCount
     * * }
     */
    fun sampleCount(struct: MemorySegment, fieldValue: Int) {
        struct.set(`sampleCount$LAYOUT`, `sampleCount$OFFSET`, fieldValue)
    }

    private val `depthReadOnly$LAYOUT` = `$LAYOUT`.select(groupElement("depthReadOnly")) as ValueLayout.OfInt

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * WGPUBool depthReadOnly
     * * }
     */
    fun `depthReadOnly$layout`(): ValueLayout.OfInt {
        return `depthReadOnly$LAYOUT`
    }

    private const val `depthReadOnly$OFFSET`: Long = 40

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * WGPUBool depthReadOnly
     * * }
     */
    fun `depthReadOnly$offset`(): Long {
        return `depthReadOnly$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * WGPUBool depthReadOnly
     * * }
     */
    fun depthReadOnly(struct: MemorySegment): Int {
        return struct.get(`depthReadOnly$LAYOUT`, `depthReadOnly$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * WGPUBool depthReadOnly
     * * }
     */
    fun depthReadOnly(struct: MemorySegment, fieldValue: Int) {
        struct.set(`depthReadOnly$LAYOUT`, `depthReadOnly$OFFSET`, fieldValue)
    }

    private val `stencilReadOnly$LAYOUT` = `$LAYOUT`.select(groupElement("stencilReadOnly")) as ValueLayout.OfInt

    /**
     * Layout for field:
     * {@snippet lang=c :
     * * WGPUBool stencilReadOnly
     * * }
     */
    fun `stencilReadOnly$layout`(): ValueLayout.OfInt {
        return `stencilReadOnly$LAYOUT`
    }

    private const val `stencilReadOnly$OFFSET`: Long = 44

    /**
     * Offset for field:
     * {@snippet lang=c :
     * * WGPUBool stencilReadOnly
     * * }
     */
    fun `stencilReadOnly$offset`(): Long {
        return `stencilReadOnly$OFFSET`
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * * WGPUBool stencilReadOnly
     * * }
     */
    fun stencilReadOnly(struct: MemorySegment): Int {
        return struct.get(`stencilReadOnly$LAYOUT`, `stencilReadOnly$OFFSET`)
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * * WGPUBool stencilReadOnly
     * * }
     */
    fun stencilReadOnly(struct: MemorySegment, fieldValue: Int) {
        struct.set(`stencilReadOnly$LAYOUT`, `stencilReadOnly$OFFSET`, fieldValue)
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


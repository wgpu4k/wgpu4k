// Generated by jextract

package io.ygdrasil.wgpu.internal.jvm.panama;

import java.lang.foreign.*;
import java.util.function.Consumer;

import static java.lang.foreign.MemoryLayout.PathElement.groupElement;
import static java.lang.foreign.ValueLayout.OfInt;
import static java.lang.foreign.ValueLayout.OfLong;

/**
 * {@snippet lang=c :
 * struct WGPUBufferDescriptor {
 *     const WGPUChainedStruct *nextInChain;
 *     const char *label;
 *     WGPUBufferUsageFlags usage;
 *     uint64_t size;
 *     WGPUBool mappedAtCreation;
 * }
 * }
 */
public class WGPUBufferDescriptor {

    WGPUBufferDescriptor() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        wgpu_h.C_POINTER.withName("nextInChain"),
        wgpu_h.C_POINTER.withName("label"),
        wgpu_h.C_INT.withName("usage"),
        MemoryLayout.paddingLayout(4),
        wgpu_h.C_LONG_LONG.withName("size"),
        wgpu_h.C_INT.withName("mappedAtCreation"),
        MemoryLayout.paddingLayout(4)
    ).withName("WGPUBufferDescriptor");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final AddressLayout nextInChain$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("nextInChain"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const WGPUChainedStruct *nextInChain
     * }
     */
    public static final AddressLayout nextInChain$layout() {
        return nextInChain$LAYOUT;
    }

    private static final long nextInChain$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const WGPUChainedStruct *nextInChain
     * }
     */
    public static final long nextInChain$offset() {
        return nextInChain$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const WGPUChainedStruct *nextInChain
     * }
     */
    public static MemorySegment nextInChain(MemorySegment struct) {
        return struct.get(nextInChain$LAYOUT, nextInChain$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const WGPUChainedStruct *nextInChain
     * }
     */
    public static void nextInChain(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(nextInChain$LAYOUT, nextInChain$OFFSET, fieldValue);
    }

    private static final AddressLayout label$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("label"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const char *label
     * }
     */
    public static final AddressLayout label$layout() {
        return label$LAYOUT;
    }

    private static final long label$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const char *label
     * }
     */
    public static final long label$offset() {
        return label$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const char *label
     * }
     */
    public static MemorySegment label(MemorySegment struct) {
        return struct.get(label$LAYOUT, label$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const char *label
     * }
     */
    public static void label(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(label$LAYOUT, label$OFFSET, fieldValue);
    }

    private static final OfInt usage$LAYOUT = (OfInt)$LAYOUT.select(groupElement("usage"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WGPUBufferUsageFlags usage
     * }
     */
    public static final OfInt usage$layout() {
        return usage$LAYOUT;
    }

    private static final long usage$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WGPUBufferUsageFlags usage
     * }
     */
    public static final long usage$offset() {
        return usage$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WGPUBufferUsageFlags usage
     * }
     */
    public static int usage(MemorySegment struct) {
        return struct.get(usage$LAYOUT, usage$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WGPUBufferUsageFlags usage
     * }
     */
    public static void usage(MemorySegment struct, int fieldValue) {
        struct.set(usage$LAYOUT, usage$OFFSET, fieldValue);
    }

    private static final OfLong size$LAYOUT = (OfLong)$LAYOUT.select(groupElement("size"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint64_t size
     * }
     */
    public static final OfLong size$layout() {
        return size$LAYOUT;
    }

    private static final long size$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint64_t size
     * }
     */
    public static final long size$offset() {
        return size$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint64_t size
     * }
     */
    public static long size(MemorySegment struct) {
        return struct.get(size$LAYOUT, size$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint64_t size
     * }
     */
    public static void size(MemorySegment struct, long fieldValue) {
        struct.set(size$LAYOUT, size$OFFSET, fieldValue);
    }

    private static final OfInt mappedAtCreation$LAYOUT = (OfInt)$LAYOUT.select(groupElement("mappedAtCreation"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WGPUBool mappedAtCreation
     * }
     */
    public static final OfInt mappedAtCreation$layout() {
        return mappedAtCreation$LAYOUT;
    }

    private static final long mappedAtCreation$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WGPUBool mappedAtCreation
     * }
     */
    public static final long mappedAtCreation$offset() {
        return mappedAtCreation$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WGPUBool mappedAtCreation
     * }
     */
    public static int mappedAtCreation(MemorySegment struct) {
        return struct.get(mappedAtCreation$LAYOUT, mappedAtCreation$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WGPUBool mappedAtCreation
     * }
     */
    public static void mappedAtCreation(MemorySegment struct, int fieldValue) {
        struct.set(mappedAtCreation$LAYOUT, mappedAtCreation$OFFSET, fieldValue);
    }

    /**
     * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
     * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
     */
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(layout().byteSize() * index);
    }

    /**
     * The size (in bytes) of this struct
     */
    public static long sizeof() { return layout().byteSize(); }

    /**
     * Allocate a segment of size {@code layout().byteSize()} using {@code allocator}
     */
    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate(layout());
    }

    /**
     * Allocate an array of size {@code elementCount} using {@code allocator}.
     * The returned segment has size {@code elementCount * layout().byteSize()}.
     */
    public static MemorySegment allocateArray(long elementCount, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout()));
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction) (if any).
     * The returned segment has size {@code layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, Arena arena, Consumer<MemorySegment> cleanup) {
        return reinterpret(addr, 1, arena, cleanup);
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction) (if any).
     * The returned segment has size {@code elementCount * layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, long elementCount, Arena arena, Consumer<MemorySegment> cleanup) {
        return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup);
    }
}


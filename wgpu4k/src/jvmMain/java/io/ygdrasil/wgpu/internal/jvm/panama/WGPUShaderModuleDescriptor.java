// Generated by jextract

package io.ygdrasil.wgpu.internal.jvm.panama;

import java.lang.invoke.*;
import java.lang.foreign.*;
import java.nio.ByteOrder;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

/**
 * {@snippet lang=c :
 * struct WGPUShaderModuleDescriptor {
 *     const WGPUChainedStruct *nextInChain;
 *     const char *label;
 *     size_t hintCount;
 *     const WGPUShaderModuleCompilationHint *hints;
 * }
 * }
 */
public class WGPUShaderModuleDescriptor {

    WGPUShaderModuleDescriptor() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        wgpu_h.C_POINTER.withName("nextInChain"),
        wgpu_h.C_POINTER.withName("label"),
        wgpu_h.C_LONG.withName("hintCount"),
        wgpu_h.C_POINTER.withName("hints")
    ).withName("WGPUShaderModuleDescriptor");

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

    private static final OfLong hintCount$LAYOUT = (OfLong)$LAYOUT.select(groupElement("hintCount"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * size_t hintCount
     * }
     */
    public static final OfLong hintCount$layout() {
        return hintCount$LAYOUT;
    }

    private static final long hintCount$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * size_t hintCount
     * }
     */
    public static final long hintCount$offset() {
        return hintCount$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * size_t hintCount
     * }
     */
    public static long hintCount(MemorySegment struct) {
        return struct.get(hintCount$LAYOUT, hintCount$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * size_t hintCount
     * }
     */
    public static void hintCount(MemorySegment struct, long fieldValue) {
        struct.set(hintCount$LAYOUT, hintCount$OFFSET, fieldValue);
    }

    private static final AddressLayout hints$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("hints"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const WGPUShaderModuleCompilationHint *hints
     * }
     */
    public static final AddressLayout hints$layout() {
        return hints$LAYOUT;
    }

    private static final long hints$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const WGPUShaderModuleCompilationHint *hints
     * }
     */
    public static final long hints$offset() {
        return hints$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const WGPUShaderModuleCompilationHint *hints
     * }
     */
    public static MemorySegment hints(MemorySegment struct) {
        return struct.get(hints$LAYOUT, hints$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const WGPUShaderModuleCompilationHint *hints
     * }
     */
    public static void hints(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(hints$LAYOUT, hints$OFFSET, fieldValue);
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


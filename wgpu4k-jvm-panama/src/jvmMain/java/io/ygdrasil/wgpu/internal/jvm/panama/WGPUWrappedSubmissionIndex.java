// Generated by jextract

package io.ygdrasil.wgpu.internal.jvm.panama;

import java.lang.foreign.*;
import java.util.function.Consumer;

import static java.lang.foreign.MemoryLayout.PathElement.groupElement;
import static java.lang.foreign.ValueLayout.OfLong;

/**
 * {@snippet lang=c :
 * struct WGPUWrappedSubmissionIndex {
 *     WGPUQueue queue;
 *     WGPUSubmissionIndex submissionIndex;
 * }
 * }
 */
public class WGPUWrappedSubmissionIndex {

    WGPUWrappedSubmissionIndex() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        wgpu_h.C_POINTER.withName("queue"),
        wgpu_h.C_LONG_LONG.withName("submissionIndex")
    ).withName("WGPUWrappedSubmissionIndex");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final AddressLayout queue$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("queue"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WGPUQueue queue
     * }
     */
    public static final AddressLayout queue$layout() {
        return queue$LAYOUT;
    }

    private static final long queue$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WGPUQueue queue
     * }
     */
    public static final long queue$offset() {
        return queue$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WGPUQueue queue
     * }
     */
    public static MemorySegment queue(MemorySegment struct) {
        return struct.get(queue$LAYOUT, queue$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WGPUQueue queue
     * }
     */
    public static void queue(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(queue$LAYOUT, queue$OFFSET, fieldValue);
    }

    private static final OfLong submissionIndex$LAYOUT = (OfLong)$LAYOUT.select(groupElement("submissionIndex"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WGPUSubmissionIndex submissionIndex
     * }
     */
    public static final OfLong submissionIndex$layout() {
        return submissionIndex$LAYOUT;
    }

    private static final long submissionIndex$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WGPUSubmissionIndex submissionIndex
     * }
     */
    public static final long submissionIndex$offset() {
        return submissionIndex$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WGPUSubmissionIndex submissionIndex
     * }
     */
    public static long submissionIndex(MemorySegment struct) {
        return struct.get(submissionIndex$LAYOUT, submissionIndex$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WGPUSubmissionIndex submissionIndex
     * }
     */
    public static void submissionIndex(MemorySegment struct, long fieldValue) {
        struct.set(submissionIndex$LAYOUT, submissionIndex$OFFSET, fieldValue);
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

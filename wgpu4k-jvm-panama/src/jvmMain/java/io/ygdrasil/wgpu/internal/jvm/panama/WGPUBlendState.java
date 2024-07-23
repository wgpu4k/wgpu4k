// Generated by jextract

package io.ygdrasil.wgpu.internal.jvm.panama;

import java.lang.foreign.*;
import java.util.function.Consumer;

import static java.lang.foreign.MemoryLayout.PathElement.groupElement;

/**
 * {@snippet lang=c :
 * struct WGPUBlendState {
 *     WGPUBlendComponent color;
 *     WGPUBlendComponent alpha;
 * }
 * }
 */
public class WGPUBlendState {

    WGPUBlendState() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        WGPUBlendComponent.layout().withName("color"),
        WGPUBlendComponent.layout().withName("alpha")
    ).withName("WGPUBlendState");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final GroupLayout color$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("color"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WGPUBlendComponent color
     * }
     */
    public static final GroupLayout color$layout() {
        return color$LAYOUT;
    }

    private static final long color$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WGPUBlendComponent color
     * }
     */
    public static final long color$offset() {
        return color$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WGPUBlendComponent color
     * }
     */
    public static MemorySegment color(MemorySegment struct) {
        return struct.asSlice(color$OFFSET, color$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WGPUBlendComponent color
     * }
     */
    public static void color(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, color$OFFSET, color$LAYOUT.byteSize());
    }

    private static final GroupLayout alpha$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("alpha"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WGPUBlendComponent alpha
     * }
     */
    public static final GroupLayout alpha$layout() {
        return alpha$LAYOUT;
    }

    private static final long alpha$OFFSET = 12;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WGPUBlendComponent alpha
     * }
     */
    public static final long alpha$offset() {
        return alpha$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WGPUBlendComponent alpha
     * }
     */
    public static MemorySegment alpha(MemorySegment struct) {
        return struct.asSlice(alpha$OFFSET, alpha$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WGPUBlendComponent alpha
     * }
     */
    public static void alpha(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, alpha$OFFSET, alpha$LAYOUT.byteSize());
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

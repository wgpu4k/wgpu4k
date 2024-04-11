// Generated by jextract

package io.ygdrasil.wgpu.internal.jvm.panama;

import java.lang.foreign.*;
import java.util.function.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

/**
 * {@snippet lang=c :
 * struct WGPUSurfaceDescriptorFromXlibWindow {
 *     WGPUChainedStruct chain;
 *     void *display;
 *     uint64_t window;
 * }
 * }
 */
public class WGPUSurfaceDescriptorFromXlibWindow {

    WGPUSurfaceDescriptorFromXlibWindow() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        WGPUChainedStruct.layout().withName("chain"),
        webgpu_h.C_POINTER.withName("display"),
        webgpu_h.C_LONG_LONG.withName("window")
    ).withName("WGPUSurfaceDescriptorFromXlibWindow");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final GroupLayout chain$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("chain"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * WGPUChainedStruct chain
     * }
     */
    public static final GroupLayout chain$layout() {
        return chain$LAYOUT;
    }

    private static final long chain$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * WGPUChainedStruct chain
     * }
     */
    public static final long chain$offset() {
        return chain$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * WGPUChainedStruct chain
     * }
     */
    public static MemorySegment chain(MemorySegment struct) {
        return struct.asSlice(chain$OFFSET, chain$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * WGPUChainedStruct chain
     * }
     */
    public static void chain(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, chain$OFFSET, chain$LAYOUT.byteSize());
    }

    private static final AddressLayout display$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("display"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void *display
     * }
     */
    public static final AddressLayout display$layout() {
        return display$LAYOUT;
    }

    private static final long display$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void *display
     * }
     */
    public static final long display$offset() {
        return display$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void *display
     * }
     */
    public static MemorySegment display(MemorySegment struct) {
        return struct.get(display$LAYOUT, display$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void *display
     * }
     */
    public static void display(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(display$LAYOUT, display$OFFSET, fieldValue);
    }

    private static final OfLong window$LAYOUT = (OfLong)$LAYOUT.select(groupElement("window"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint64_t window
     * }
     */
    public static final OfLong window$layout() {
        return window$LAYOUT;
    }

    private static final long window$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint64_t window
     * }
     */
    public static final long window$offset() {
        return window$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint64_t window
     * }
     */
    public static long window(MemorySegment struct) {
        return struct.get(window$LAYOUT, window$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint64_t window
     * }
     */
    public static void window(MemorySegment struct, long fieldValue) {
        struct.set(window$LAYOUT, window$OFFSET, fieldValue);
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


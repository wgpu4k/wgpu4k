// Generated by jextract

package io.ygdrasil.wgpu.internal.jvm.panama;

import java.lang.foreign.*;
import java.util.function.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

/**
 * {@snippet lang=c :
 * struct WGPUColor {
 *     double r;
 *     double g;
 *     double b;
 *     double a;
 * }
 * }
 */
public class WGPUColor {

    WGPUColor() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        webgpu_h.C_DOUBLE.withName("r"),
        webgpu_h.C_DOUBLE.withName("g"),
        webgpu_h.C_DOUBLE.withName("b"),
        webgpu_h.C_DOUBLE.withName("a")
    ).withName("WGPUColor");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfDouble r$LAYOUT = (OfDouble)$LAYOUT.select(groupElement("r"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * double r
     * }
     */
    public static final OfDouble r$layout() {
        return r$LAYOUT;
    }

    private static final long r$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * double r
     * }
     */
    public static final long r$offset() {
        return r$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * double r
     * }
     */
    public static double r(MemorySegment struct) {
        return struct.get(r$LAYOUT, r$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * double r
     * }
     */
    public static void r(MemorySegment struct, double fieldValue) {
        struct.set(r$LAYOUT, r$OFFSET, fieldValue);
    }

    private static final OfDouble g$LAYOUT = (OfDouble)$LAYOUT.select(groupElement("g"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * double g
     * }
     */
    public static final OfDouble g$layout() {
        return g$LAYOUT;
    }

    private static final long g$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * double g
     * }
     */
    public static final long g$offset() {
        return g$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * double g
     * }
     */
    public static double g(MemorySegment struct) {
        return struct.get(g$LAYOUT, g$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * double g
     * }
     */
    public static void g(MemorySegment struct, double fieldValue) {
        struct.set(g$LAYOUT, g$OFFSET, fieldValue);
    }

    private static final OfDouble b$LAYOUT = (OfDouble)$LAYOUT.select(groupElement("b"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * double b
     * }
     */
    public static final OfDouble b$layout() {
        return b$LAYOUT;
    }

    private static final long b$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * double b
     * }
     */
    public static final long b$offset() {
        return b$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * double b
     * }
     */
    public static double b(MemorySegment struct) {
        return struct.get(b$LAYOUT, b$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * double b
     * }
     */
    public static void b(MemorySegment struct, double fieldValue) {
        struct.set(b$LAYOUT, b$OFFSET, fieldValue);
    }

    private static final OfDouble a$LAYOUT = (OfDouble)$LAYOUT.select(groupElement("a"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * double a
     * }
     */
    public static final OfDouble a$layout() {
        return a$LAYOUT;
    }

    private static final long a$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * double a
     * }
     */
    public static final long a$offset() {
        return a$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * double a
     * }
     */
    public static double a(MemorySegment struct) {
        return struct.get(a$LAYOUT, a$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * double a
     * }
     */
    public static void a(MemorySegment struct, double fieldValue) {
        struct.set(a$LAYOUT, a$OFFSET, fieldValue);
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


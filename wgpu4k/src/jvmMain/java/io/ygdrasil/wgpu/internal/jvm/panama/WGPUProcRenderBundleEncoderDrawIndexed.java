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
 * typedef void (*WGPUProcRenderBundleEncoderDrawIndexed)(WGPURenderBundleEncoder, uint32_t, uint32_t, uint32_t, int32_t, uint32_t)
 * }
 */
public class WGPUProcRenderBundleEncoderDrawIndexed {

    WGPUProcRenderBundleEncoderDrawIndexed() {
        // Should not be called directly
    }

    /**
     * The function pointer signature, expressed as a functional interface
     */
    public interface Function {
        void apply(MemorySegment renderBundleEncoder, int indexCount, int instanceCount, int firstIndex, int baseVertex, int firstInstance);
    }

    private static final FunctionDescriptor $DESC = FunctionDescriptor.ofVoid(
        wgpu_h.C_POINTER,
        wgpu_h.C_INT,
        wgpu_h.C_INT,
        wgpu_h.C_INT,
        wgpu_h.C_INT,
        wgpu_h.C_INT
    );

    /**
     * The descriptor of this function pointer
     */
    public static FunctionDescriptor descriptor() {
        return $DESC;
    }

    private static final MethodHandle UP$MH = wgpu_h.upcallHandle(WGPUProcRenderBundleEncoderDrawIndexed.Function.class, "apply", $DESC);

    /**
     * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
     * The lifetime of the returned segment is managed by {@code arena}
     */
    public static MemorySegment allocate(WGPUProcRenderBundleEncoderDrawIndexed.Function fi, Arena arena) {
        return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
    }

    private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

    /**
     * Invoke the upcall stub {@code funcPtr}, with given parameters
     */
    public static void invoke(MemorySegment funcPtr,MemorySegment renderBundleEncoder, int indexCount, int instanceCount, int firstIndex, int baseVertex, int firstInstance) {
        try {
             DOWN$MH.invokeExact(funcPtr, renderBundleEncoder, indexCount, instanceCount, firstIndex, baseVertex, firstInstance);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
}


// Generated by jextract

package io.ygdrasil.wgpu.internal.jvm.panama;

import java.lang.invoke.*;
import java.lang.foreign.*;

/**
 * {@snippet lang=c :
 * typedef void (*WGPUProcRenderBundleEncoderDrawIndirect)(WGPURenderBundleEncoder, WGPUBuffer, uint64_t)
 * }
 */
public class WGPUProcRenderBundleEncoderDrawIndirect {

    WGPUProcRenderBundleEncoderDrawIndirect() {
        // Should not be called directly
    }

    /**
     * The function pointer signature, expressed as a functional interface
     */
    public interface Function {
        void apply(MemorySegment renderBundleEncoder, MemorySegment indirectBuffer, long indirectOffset);
    }

    private static final FunctionDescriptor $DESC = FunctionDescriptor.ofVoid(
        webgpu_h.C_POINTER,
        webgpu_h.C_POINTER,
        webgpu_h.C_LONG_LONG
    );

    /**
     * The descriptor of this function pointer
     */
    public static FunctionDescriptor descriptor() {
        return $DESC;
    }

    private static final MethodHandle UP$MH = webgpu_h.upcallHandle(Function.class, "apply", $DESC);

    /**
     * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
     * The lifetime of the returned segment is managed by {@code arena}
     */
    public static MemorySegment allocate(Function fi, Arena arena) {
        return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
    }

    private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

    /**
     * Invoke the upcall stub {@code funcPtr}, with given parameters
     */
    public static void invoke(MemorySegment funcPtr,MemorySegment renderBundleEncoder, MemorySegment indirectBuffer, long indirectOffset) {
        try {
             DOWN$MH.invokeExact(funcPtr, renderBundleEncoder, indirectBuffer, indirectOffset);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
}


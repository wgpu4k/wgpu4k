// Generated by jextract

package io.ygdrasil.wgpu.internal.jvm.panama;

import java.lang.invoke.*;
import java.lang.foreign.*;

/**
 * {@snippet lang=c :
 * typedef WGPUTexture (*WGPUProcDeviceCreateTexture)(WGPUDevice, const WGPUTextureDescriptor *)
 * }
 */
public class WGPUProcDeviceCreateTexture {

    WGPUProcDeviceCreateTexture() {
        // Should not be called directly
    }

    /**
     * The function pointer signature, expressed as a functional interface
     */
    public interface Function {
        MemorySegment apply(MemorySegment device, MemorySegment descriptor);
    }

    private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
        webgpu_h.C_POINTER,
        webgpu_h.C_POINTER,
        webgpu_h.C_POINTER
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
    public static MemorySegment invoke(MemorySegment funcPtr,MemorySegment device, MemorySegment descriptor) {
        try {
            return (MemorySegment) DOWN$MH.invokeExact(funcPtr, device, descriptor);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
}


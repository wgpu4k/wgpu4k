import io.ygdrasil.wgpu.LogLevel
import io.ygdrasil.wgpu.internal.jvm.panama.WGPULogCallback
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment


val callback = WGPULogCallback.allocate({ level, message, data ->
    println("LOG {$level} ${message.getString(0)}")
}, Arena.global())

actual fun initLog() {
    wgpu_h.wgpuSetLogLevel(LogLevel.trace.value)
    wgpu_h.wgpuSetLogCallback(callback, MemorySegment.NULL)
}

actual fun getSceneParameter(): SceneParameter {
    val scene = System.getenv("scene")
    val frame = System.getenv("frame")
    return SceneParameter(scene, frame.toInt())
}
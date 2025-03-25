package io.ygdrasil.webgpu

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int16Array
import org.khronos.webgl.Int8Array

@Deprecated(message = "use getMappedRange instead")
actual fun GPUBuffer.mapFrom(buffer: ShortArray, offset: GPUSize64) {
    val handler = (this as Buffer).handler
    handler.getMappedRange(offset.asJsNumber(), (buffer.size * Short.SIZE_BYTES).asJsNumber())
        .let(::Int16Array)
        .set(buffer.toTypedArray(), 0)
}

@Deprecated(message = "use getMappedRange instead")
actual fun GPUBuffer.mapFrom(buffer: FloatArray, offset: GPUSize64) {
    val handler = (this as Buffer).handler
    handler.getMappedRange(offset.asJsNumber(), (buffer.size * Float.SIZE_BYTES).asJsNumber())
        .let(::Float32Array)
        .set(buffer.toTypedArray(), 0)
}

@Deprecated(message = "use getMappedRange instead")
actual fun GPUBuffer.mapFrom(buffer: ByteArray, offset: GPUSize64) {
    val handler = (this as Buffer).handler
    handler.getMappedRange(offset.asJsNumber(), buffer.size.asJsNumber())
        .let(::Int8Array)
        .set(buffer.toTypedArray(), 0)
}

@Deprecated(message = "use getMappedRange instead")
actual fun GPUBuffer.mapInto(buffer: ByteArray, offset: GPUSize64) {
    val handler = (this as Buffer).handler
    Int8Array(handler.getMappedRange(offset.asJsNumber(), buffer.size.asJsNumber()))
        .unsafeCast<ByteArray>()
        .copyInto(buffer)
}

@Deprecated(message = "use getMappedRange instead")
actual fun GPUBuffer.mapInto(buffer: IntArray, offset: GPUSize64) {
    val handler = (this as Buffer).handler
    Int8Array(handler.getMappedRange(offset.asJsNumber(), (buffer.size * Int.SIZE_BYTES).asJsNumber()))
        .unsafeCast<IntArray>()
        .copyInto(buffer)
}
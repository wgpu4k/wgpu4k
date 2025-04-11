package io.ygdrasil.webgpu

import java.lang.reflect.Field
import java.nio.ByteBuffer
import java.nio.FloatBuffer


actual inline fun arrayBufferOf(input: FloatArray, action: (ArrayBuffer) -> Unit) {

    val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(input.size * 4)
    val floatBuffer: FloatBuffer = byteBuffer.asFloatBuffer()
    floatBuffer.put(input)
    floatBuffer.flip()


    TODO("Not yet implemented")
}

actual fun FloatArray.writeInto(target: ArrayBuffer) {
    TODO("Not yet implemented")
}

private fun getDirectBufferAddressReflection(buffer: ByteBuffer): Long {
    val addressField: Field = buffer.javaClass.getDeclaredField("address")
    addressField.setAccessible(true)
    return addressField.get(buffer) as Long
}

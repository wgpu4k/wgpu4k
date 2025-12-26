package io.ygdrasil.webgpu

fun FloatArray.writeInto(target: ArrayBuffer) = forEachIndexed { index, value ->
    target.setFloat(index * Float.SIZE_BYTES, value)
}

expect fun DoubleArray.writeInto(target: ArrayBuffer)

expect fun ByteArray.writeInto(target: ArrayBuffer)

expect fun UByteArray.writeInto(target: ArrayBuffer)

expect fun ShortArray.writeInto(target: ArrayBuffer)

expect fun UShortArray.writeInto(target: ArrayBuffer)

expect fun IntArray.writeInto(target: ArrayBuffer)

expect fun UIntArray.writeInto(target: ArrayBuffer)

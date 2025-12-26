package io.ygdrasil.webgpu

fun FloatArray.writeInto(target: ArrayBuffer) = forEachIndexed { index, value ->
    target.setFloat(index * Float.SIZE_BYTES, value)
}

fun DoubleArray.writeInto(target: ArrayBuffer) = forEachIndexed { index, value ->
    target.setDouble(index * Double.SIZE_BYTES, value)
}

fun ByteArray.writeInto(target: ArrayBuffer) = forEachIndexed { index, value ->
    target.setByte(index * Byte.SIZE_BYTES, value)
}

fun UByteArray.writeInto(target: ArrayBuffer) = forEachIndexed { index, value ->
    target.setUByte(index * UByte.SIZE_BYTES, value)
}

fun ShortArray.writeInto(target: ArrayBuffer) = forEachIndexed { index, value ->
    target.setShort(index * Short.SIZE_BYTES, value)
}

fun UShortArray.writeInto(target: ArrayBuffer) = forEachIndexed { index, value ->
    target.setUShort(index * UShort.SIZE_BYTES, value)
}

fun IntArray.writeInto(target: ArrayBuffer) = forEachIndexed { index, value ->
    target.setInt(index * Int.SIZE_BYTES, value)
}

fun UIntArray.writeInto(target: ArrayBuffer) = forEachIndexed { index, value ->
    target.setUInt(index * UInt.SIZE_BYTES, value)
}

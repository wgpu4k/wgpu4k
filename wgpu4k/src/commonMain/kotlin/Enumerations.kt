package io.ygdrasil.webgpu

interface EnumerationWithValue {
    val value: ULong

    infix fun or(other: ULong): ULong = value or other
    infix fun or(other: EnumerationWithValue): ULong = value or other.value
}

internal fun Set<EnumerationWithValue>.toFlagInt(): Int = when (size) {
    0 -> 0uL
    1 -> first().value
    else -> fold(0uL) { acc, enumerationWithValue -> acc or enumerationWithValue.value }
}.toInt()

internal fun Set<EnumerationWithValue>.toFlagULong(): ULong = when (size) {
    0 -> 0uL
    1 -> first().value
    else -> fold(0uL) { acc, enumerationWithValue -> acc or enumerationWithValue.value }
}

enum class SurfaceTextureStatus(
    val value: Int,
) {
    success(0),
    timeout(1),
    outdated(2),
    lost(3),
    outOfMemory(4),
    deviceLost(5);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: SurfaceTextureStatus): Int = value or other.value

    companion object {
        fun of(value: Int): SurfaceTextureStatus? = entries.find {
            it.value == value
        }

        fun of(value: UInt): SurfaceTextureStatus? = entries.find {
            it.value == value.toInt()
        }
    }
}



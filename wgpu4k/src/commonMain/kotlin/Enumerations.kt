package io.ygdrasil.webgpu

interface EnumerationWithValue {
    val value: Int
    val uValue: UInt

    infix fun or(other: Int): Int = value or other
    infix fun or(other: EnumerationWithValue): Int = value or other.value
}

internal fun Set<EnumerationWithValue>.toFlagInt(): Int = when (size) {
    0 -> 0
    1 -> first().value
    else -> fold(0) { acc, enumerationWithValue -> acc or enumerationWithValue.value }
}

internal fun Set<EnumerationWithValue>.toFlagUInt(): UInt = when (size) {
    0 -> 0u
    1 -> first().value.toUInt()
    else -> fold(0u) { acc, enumerationWithValue -> acc or enumerationWithValue.value.toUInt() }
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



infix fun Int.or(other: TextureUsage): Int = this or other.value


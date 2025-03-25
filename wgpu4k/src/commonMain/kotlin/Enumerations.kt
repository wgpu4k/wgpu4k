package io.ygdrasil.webgpu

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



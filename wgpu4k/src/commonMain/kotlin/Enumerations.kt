package io.ygdrasil.webgpu

interface EnumerationWithValue {
    val value: Int
    val uValue: UInt
        get() = value.toUInt()

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

internal fun Set<EnumerationWithValue>.toFlagULong(): ULong = when (size) {
    0 -> 0u
    1 -> first().value.toULong()
    else -> fold(0u) { acc, enumerationWithValue -> acc or enumerationWithValue.value.toULong() }
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

enum class BufferUsage(
    override val value: Int,
) : EnumerationWithValue {
    mapread(1),
    mapwrite(2),
    copysrc(4),
    copydst(8),
    index(16),
    vertex(32),
    uniform(64),
    storage(128),
    indirect(256),
    queryresolve(512);

    companion object {
        fun of(value: Int): BufferUsage? = entries.find {
            it.value == value
        }
    }
}

enum class ColorWriteMask(
    override val value: Int,
) : EnumerationWithValue {
    none(0),
    red(1),
    green(2),
    blue(4),
    alpha(8),
    all(15);

    companion object {
        fun of(value: Int): ColorWriteMask? = entries.find {
            it.value == value
        }
    }
}

enum class MapMode(
    override val value: Int,
) : EnumerationWithValue {
    read(1),
    write(2);

    companion object {
        fun of(value: Int): MapMode? = entries.find {
            it.value == value
        }
    }
}

enum class ShaderStage(
    override val value: Int,
) : EnumerationWithValue {
    vertex(1),
    fragment(2),
    compute(4);

    companion object {
        fun of(value: Int): ShaderStage? = entries.find {
            it.value == value
        }
    }
}

enum class TextureUsage(
    override val value: Int,
) : EnumerationWithValue {
    none(0),
    copySrc(1),
    copyDst(2),
    textureBinding(4),
    storageBinding(8),
    renderAttachment(16);

    companion object {
        fun of(value: Int): TextureUsage? = entries.find {
            it.value == value
        }
    }
}

infix fun Int.or(other: TextureUsage): Int = this or other.value


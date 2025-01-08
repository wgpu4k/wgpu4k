package io.ygdrasil.webgpu.examples.helper.glb

enum class GLTFComponentType(val value: Int) {
    BYTE(5120),
    UNSIGNED_BYTE(5121),
    SHORT(5122),
    UNSIGNED_SHORT(5123),
    INT(5124),
    UNSIGNED_INT(5125),
    FLOAT(5126),
    DOUBLE(5130);

    companion object {
        fun of(id: Int): GLTFComponentType {
            return entries.firstOrNull { it.value == id }
                ?: error("Unknown id: $id")
        }
    }
}

enum class GLTFTextureFilter(val value: Int) {
    NEAREST(9728),
    LINEAR(9729),
    NEAREST_MIPMAP_NEAREST(9984),
    LINEAR_MIPMAP_NEAREST(9985),
    NEAREST_MIPMAP_LINEAR(9986),
    LINEAR_MIPMAP_LINEAR(9987),

    REPEAT(10497),
    MIRRORED_REPEAT(33648),
    CLAMP_TO_EDGE(33071);

    companion object {
        fun of(id: Int): GLTFTextureFilter {
            return values().firstOrNull { it.value == id }
                ?: error("Unknown id: $id")
        }
    }
}
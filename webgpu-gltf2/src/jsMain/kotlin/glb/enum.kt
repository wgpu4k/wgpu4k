package glb

enum class GLTFComponentType(val id: Int) {
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
            return entries.firstOrNull { it.id == id }
                ?: error("Unknown id: $id")
        }
    }
}
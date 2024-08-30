package java.lang.foreign

typealias AddressLayout = ValueLayout

abstract class ValueLayout(val size: Long, val name: String? = null) {
    companion object {
        val JAVA_LONG: ValueLayout = OfLong(Long.SIZE_BYTES.toLong())
        val JAVA_DOUBLE: ValueLayout = OfDouble(Double.SIZE_BYTES.toLong())
        val JAVA_FLOAT: ValueLayout = OfFloat(Float.SIZE_BYTES.toLong())
        val JAVA_INT: ValueLayout = OfInt(Int.SIZE_BYTES.toLong())
        val JAVA_SHORT: ValueLayout = OfShort(Short.SIZE_BYTES.toLong())
        val JAVA_BYTE: ValueLayout = OfByte(Byte.SIZE_BYTES.toLong())
        val JAVA_BOOLEAN: ValueLayout = OfBoolean(1)
        val ADDRESS: ValueLayout = JAVA_LONG
    }

    abstract fun withName(name: String) : ValueLayout


    class OfDouble(size: Long, name: String? = null) : ValueLayout(size, name) {
        override fun withName(name: String) = OfDouble(size, name)
    }
    class OfFloat(size: Long, name: String? = null) : ValueLayout(size, name) {
        override fun withName(name: String) = OfFloat(size, name)
    }
    class OfInt(size: Long, name: String? = null) : ValueLayout(size, name) {
        override fun withName(name: String) = OfInt(size, name)
    }
    class OfLong(size: Long, name: String? = null) : ValueLayout(size, name) {
        override fun withName(name: String) = OfLong(size, name)
    }
    class OfShort(size: Long, name: String? = null) : ValueLayout(size, name) {
        override fun withName(name: String) = OfShort(size, name)
    }
    class OfByte(size: Long, name: String? = null) : ValueLayout(size, name) {
        override fun withName(name: String) = OfByte(size, name)
    }
    class OfBoolean(size: Long, name: String? = null) : ValueLayout(size, name) {
        override fun withName(name: String) = OfBoolean(size, name)
    }
}


class PaddingLayout(size: Long, name: String? = null) : ValueLayout(size, name) {
    override fun withName(name: String) = PaddingLayout(size, name)
}
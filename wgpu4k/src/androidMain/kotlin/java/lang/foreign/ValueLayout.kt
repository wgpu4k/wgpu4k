package java.lang.foreign

typealias AddressLayout = ValueLayout

abstract class ValueLayout(val size: Long, val name: String? = null) {
    companion object {
        val JAVA_LONG: OfLong = OfLong(Long.SIZE_BYTES.toLong())
        val JAVA_DOUBLE: OfDouble = OfDouble(Double.SIZE_BYTES.toLong())
        val JAVA_FLOAT: OfFloat = OfFloat(Float.SIZE_BYTES.toLong())
        val JAVA_INT: OfInt = OfInt(Int.SIZE_BYTES.toLong())
        val JAVA_SHORT: OfShort = OfShort(Short.SIZE_BYTES.toLong())
        val JAVA_BYTE: OfByte = OfByte(Byte.SIZE_BYTES.toLong())
        val JAVA_BOOLEAN: OfBoolean = OfBoolean(1)
        val ADDRESS: OfLong = JAVA_LONG
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
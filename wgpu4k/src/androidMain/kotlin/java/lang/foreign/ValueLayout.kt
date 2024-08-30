package java.lang.foreign

class AddressLayout

class ValueLayout(val size: Long, val name: String? = null) {
    companion object {
        val JAVA_LONG: ValueLayout = ValueLayout(Long.SIZE_BYTES.toLong())
        val JAVA_DOUBLE: ValueLayout = ValueLayout(Double.SIZE_BYTES.toLong())
        val JAVA_FLOAT: ValueLayout = ValueLayout(Float.SIZE_BYTES.toLong())
        val JAVA_INT: ValueLayout = ValueLayout(Int.SIZE_BYTES.toLong())
        val JAVA_SHORT: ValueLayout = ValueLayout(Short.SIZE_BYTES.toLong())
        val JAVA_BYTE: ValueLayout = ValueLayout(Byte.SIZE_BYTES.toLong())
        val JAVA_BOOLEAN: ValueLayout = ValueLayout(1)
        val ADDRESS: ValueLayout = JAVA_LONG
    }

    fun withName(name: String) = ValueLayout(size, name)


    class OfDouble
    class OfFloat
    class OfInt
    class OfLong
    class OfShort
    class OfByte
    class OfBoolean
}
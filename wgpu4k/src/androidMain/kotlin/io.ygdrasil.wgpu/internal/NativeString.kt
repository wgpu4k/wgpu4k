package io.ygdrasil.wgpu.internal

import com.sun.jna.Memory
import com.sun.jna.Native
import java.nio.charset.Charset
import java.nio.charset.IllegalCharsetNameException
import java.nio.charset.UnsupportedCharsetException

internal class NativeString @JvmOverloads constructor(
    string: String,
    encoding: String = Native.getDefaultStringEncoding()
) :
    CharSequence, Comparable<Any?> {
    var pointer: StringMemory? = null
    private val encoding: String

    inner class StringMemory(size: Long) : Memory(size) {
        override fun toString(): String {
            return this@NativeString.toString()
        }
    }


    /** Create a native string (NUL-terminated array of `char`),
     * using the requested encoding.
     */
    /** Create a native string (NUL-terminated array of `char`).
     *
     *
     * Uses the encoding returned by [Native.getDefaultStringEncoding].
     */
    init {
        if (string == null) {
            throw NullPointerException("String must not be null")
        }
        // Allocate the memory to hold the string.  Note, we have to
        // make this 1 element longer in order to accommodate the terminating
        // NUL (which is generated in Pointer.setString()).
        this.encoding = encoding
        if (WIDE_STRING == this.encoding) {
            val len = (string.length + 1) * Native.WCHAR_SIZE
            pointer = StringMemory(len.toLong())
            pointer!!.setWideString(0, string)
        } else {
            val data = getBytes(string, encoding)
            pointer = StringMemory((data.size + 1).toLong()).apply {
                write(0, data, 0, data.size)
                setByte(data.size.toLong(), 0.toByte())
            }
        }
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other is CharSequence) {
            return compareTo(other) == 0
        }
        return false
    }

    override fun toString(): String {
        val wide = WIDE_STRING == encoding
        return (if (wide) pointer!!.getWideString(0) else pointer!!.getString(0, encoding))
    }

    override fun get(index: Int): Char {
        return toString()[index]
    }

    override val length: Int = toString().length

    override fun subSequence(start: Int, end: Int): CharSequence {
        return toString().subSequence(start, end)
    }

    override fun compareTo(other: Any?): Int {
        if (other == null) return 1

        return toString().compareTo(other.toString())
    }

    companion object {
        const val WIDE_STRING: String = "--WIDE-STRING--"
    }
}

fun getBytes(s: String, encoding: String?): ByteArray {
    return getBytes(s, getCharset(encoding))
}
fun getBytes(s: String, charset: Charset?): ByteArray {
    return s.toByteArray(charset!!)
}

private fun getCharset(encoding: String?): Charset? {
    var charset: Charset? = null
    if (encoding != null) {
        try {
            charset = Charset.forName(encoding)
        } catch (e: IllegalCharsetNameException) {
            //TODO
        } catch (e: UnsupportedCharsetException) {
            //TODO
        }
    }
    if (charset == null) {
        charset = Native.DEFAULT_CHARSET
    }
    return charset
}
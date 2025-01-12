package io.ygdrasil.webgpu.internal.web

actual class JsNumber: Number() {
    /**
     * Returns the value of this number as a [Byte], which may involve rounding or truncation.
     */
    override fun toByte(): Byte {
        TODO("Not yet implemented")
    }

    /**
     * Returns the value of this number as a [Double], which may involve rounding.
     */
    override fun toDouble(): Double {
        TODO("Not yet implemented")
    }

    /**
     * Returns the value of this number as a [Float], which may involve rounding.
     */
    override fun toFloat(): Float {
        TODO("Not yet implemented")
    }

    /**
     * Returns the value of this number as an [Int], which may involve rounding or truncation.
     */
    override fun toInt(): Int {
        TODO("Not yet implemented")
    }

    /**
     * Returns the value of this number as a [Long], which may involve rounding or truncation.
     */
    override fun toLong(): Long {
        TODO("Not yet implemented")
    }

    /**
     * Returns the value of this number as a [Short], which may involve rounding or truncation.
     */
    override fun toShort(): Short {
        TODO("Not yet implemented")
    }
}

actual typealias JsString = String

actual external interface JsObject
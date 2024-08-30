package io.ygdrasil.wgpu.internal.jna

import java.lang.foreign.AddressLayout
import java.lang.foreign.ValueLayout

object wgpu_h {


    @JvmField
    val C_BOOL: ValueLayout = ValueLayout.JAVA_BOOLEAN
    @JvmField
    val C_CHAR: ValueLayout = ValueLayout.JAVA_BYTE
    @JvmField
    val C_SHORT: ValueLayout = ValueLayout.JAVA_SHORT
    @JvmField
    val C_INT: ValueLayout = ValueLayout.JAVA_INT
    @JvmField
    val C_LONG_LONG: ValueLayout = ValueLayout.JAVA_LONG
    @JvmField
    val C_FLOAT: ValueLayout = ValueLayout.JAVA_FLOAT
    @JvmField
    val C_DOUBLE: ValueLayout = ValueLayout.JAVA_DOUBLE
    @JvmField
    val C_POINTER: ValueLayout = ValueLayout.ADDRESS
    @JvmField
    val C_LONG: ValueLayout = ValueLayout.JAVA_LONG
}
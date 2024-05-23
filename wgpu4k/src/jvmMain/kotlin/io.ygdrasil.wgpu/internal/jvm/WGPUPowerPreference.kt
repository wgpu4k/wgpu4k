package io.ygdrasil.wgpu.internal.jvm

enum class WGPUPowerPreference(
    val `value`: Int,
) {
    WGPUPowerPreference_Undefined(0),
    WGPUPowerPreference_LowPower(1),
    WGPUPowerPreference_HighPerformance(2),
    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: WGPUPowerPreference): Int = value or other.value

    companion object {
        fun of(`value`: Int): WGPUPowerPreference? = entries.find {
            it.value == value
        }
    }
}

package generator.enumeration

import builder.templateBuilder
import convertToKotlinClassName
import domain.YamlModel

fun List<YamlModel.Enum>.toNativeEnumerations() = templateBuilder {
    forEach { enumeration ->
        val name = enumeration.name.convertToKotlinClassName()
        appendBlock("actual enum class $name(val value: UInt)") {
            enumeration.values
                .filter { it.name != "undefined" }
                .forEach { value ->
                    val originalValueName = value.name.convertToKotlinClassName()
                    val valueName = originalValueName.fixNameStartingWithNumeric()
                    appendLine("$valueName(webgpu.WGPU${name}_$originalValueName),")
                }
        }
    }
}

private fun String.fixNameStartingWithNumeric(): String {
    return if (first().isDigit()) {
        when (first()) {
            '1' -> "One${substring(1)}"
            '2' -> "Two${substring(1)}"
            '3' -> "Three${substring(1)}"
            '4' -> "Four${substring(1)}"
            '5' -> "Five${substring(1)}"
            '6' -> "Six${substring(1)}"
            '7' -> "Seven${substring(1)}"
            '8' -> "Eight${substring(1)}"
            '9' -> "Nine${substring(1)}"
            '0' -> "Zero${substring(1)}"
            else -> error("Invalid name starting with numeric: $this")
        }
    } else this
}

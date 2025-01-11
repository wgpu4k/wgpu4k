package generator.enumeration

import builder.templateBuilder
import convertToKotlinClassName
import domain.YamlModel

fun List<YamlModel.Enum>.toWebEnumerations() = templateBuilder {
    forEach { enumeration ->
        val name = enumeration.name.convertToKotlinClassName()
        appendBlock("actual enum class $name(val value: String)") {
            enumeration.values
                .filter { it.name != "undefined" }
                .forEach { value ->
                    val name = value.name.convertToKotlinClassName()
                        .fixNameStartingWithNumeric()
                    val javascriptValueProvider = getValueProviderFrom(enumeration.name)
                    appendLine("$name(\"${javascriptValueProvider(value.name)}\"),")
                }
            appendLine(";")
            newLine()

            appendBlock("companion object") {
                appendBlock("fun of(value: String): $name?") {
                    appendLine("return entries.find { it.value == value }")
                }
            }
        }
        newLine()
    }
}

private fun getValueProviderFrom(name: String): (String) -> String {
    when (name) {
        "texture_format" -> return { textureFormatValueConverter(it) }
        else -> return { it.replace("_", "-").lowercase() }
    }
}

// @see https://gpuweb.github.io/gpuweb/#enumdef-gputextureformat
private fun textureFormatValueConverter(value: String): String {
    println("Converting $value")
    var values = value.lowercase().split("_")

    if (values.size == 1) { return values.first() }

    val suffix = values.last().takeIf { it.isTextureSuffix() }
        ?.let {
            values = values.dropLast(1)
            "-$it"
        } ?: ""

    val prefix = values.first()
        .countPrefix()
        .takeIf { it > 0 }
        ?.let { prefixes ->
            println("Found $prefixes prefix(es) ${values.subList(0, prefixes)}")
            // get prefixes
            values.subList(0, prefixes)
                // Join them
                .joinToString("-")
                // add prefix separator
                .let { "$it-"}
                // Drop prefix from the list
                .also { values = values.drop(prefixes) }
        } ?: ""

    return prefix + values.joinToString("") + suffix
}

private fun String.countPrefix() : Int = when (this) {
    "etc2", "eac" -> 1
    "bc1", "bc2", "bc3", "bc4", "bc5", "bc6h", "bc7", "astc" -> 2
    else -> 0
}

private fun String.isTextureSuffix(): Boolean = this in listOf("srgb", "stencil8")

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

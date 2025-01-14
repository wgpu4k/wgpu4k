package generator.bitflag

import builder.templateBuilder
import convertToKotlinClassName
import domain.YamlModel


private fun indexToFlagValue(base: Int): Int = if (base == 0) 0 else 1 shl (base - 1)

fun List<YamlModel.Bitflag>.toBitflagEnumerations() = templateBuilder {
    forEach { bitflag ->
        val name = bitflag.name.convertToKotlinClassName()
        appendBlock("enum class $name(override val value: Int, override val uValue: UInt): EnumerationWithValue") {
            bitflag.entries
                .forEachIndexed { index, entry ->
                    // Calculate first if that a combination
                    val value = entry.value_combination?.sumOf { subPart -> indexToFlagValue(bitflag.entries.indexOfFirst { it.name == subPart }) }
                        ?: indexToFlagValue(index)

                    val name = entry.name.convertToKotlinClassName()
                    appendLine("$name($value, ${value}u),")
                }
        }
    }
}

internal fun String.convertToEnumValueName() = split("_")
    .map { component -> component.replaceFirstChar { it.uppercase() } }
    .joinToString("")

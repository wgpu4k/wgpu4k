package generator.enumeration

import builder.templateBuilder
import convertToKotlinClassName
import domain.YamlModel

fun List<YamlModel.Enum>.toCommonEnumerations() = templateBuilder {
    forEach { enumeration ->
        val name = enumeration.name.convertToKotlinClassName()
        appendBlock("enum class $name") {
            enumeration.values.forEach { value ->
                appendLine("$value,")
            }
        }
    }
}
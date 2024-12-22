val disclamer = "// This file has been generated DO NOT EDIT !!!"

internal fun String.convertToKotlinVariableName() = split("_")
    .mapIndexed { index, component ->
        when {
            index == 0 -> component.replaceFirstChar { it.lowercase() }
            else -> component.replaceFirstChar { it.uppercase() }
        }
    }
    .joinToString("")

internal fun String.convertToKotlinCallbackStructureName() = "${this}_callback_info".convertToKotlinClassName()
internal fun String.convertToKotlinCallbackName() = "${this}_callback".convertToKotlinClassName()

internal fun String.convertToKotlinClassName() = split("_")
    .map { component -> component.replaceFirstChar { it.uppercase() } }
    .joinToString("")
    .let { "WGPU$it" }

internal fun String.convertToEnumValueName() = split("_")
    .map { component -> component.replaceFirstChar { it.uppercase() } }
    .joinToString("")

internal fun String.convertToKotlinFunctionName() = split("_")
    .map { component -> component.replaceFirstChar { it.uppercase() } }
    .joinToString("")
    .let { "wgpu$it" }

internal fun String.toFunctionKotlinType() = when {
    startsWith("struct.")
            || startsWith("array<")
            || startsWith("object.")
            || equals("string") -> "Long"

    startsWith("c_void") -> "Unit"
    startsWith("enum.")
            || equals("bool") -> "UInt"

    equals("float32") -> "Float"
    equals("float64") -> "Double"
    equals("int32") -> "Int"
    equals("uint32") -> "UInt"
    equals("int64")
            || equals("usize") -> "Long"

    equals("uint64")
            || startsWith("bitflag.") -> "ULong"

    else -> error("unknown type $this")
}


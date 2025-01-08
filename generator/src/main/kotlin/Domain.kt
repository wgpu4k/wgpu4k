val disclamer = "// This file has been generated DO NOT EDIT !!!"

internal fun String.convertToKotlinClassName() = split("_")
    .map { component -> component.replaceFirstChar { it.uppercase() } }
    .joinToString("")


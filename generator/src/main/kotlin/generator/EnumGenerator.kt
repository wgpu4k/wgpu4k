package generator

import Paths
import disclamer
import domain.YamlModel
import generator.enumeration.toCommonEnumerations
import generator.enumeration.toNativeEnumerations
import generator.enumeration.toWebEnumerations
import java.io.File

val enumerationCommonMainFile = Paths.commonMain
    .resolve("webgpu.enumerations.kt")

val enumerationWebMainFile = Paths.commonWebMain
    .resolve("webgpu.enumerations.web.kt")

val enumerationNativeMainFile = Paths.commonNativeMain
    .resolve("webgpu.enumerations.native.kt")

private val header = """
    $disclamer
    package io.ygdrasil.webgpu
    
    
""".trimIndent()

internal fun File.generateCommonEnumerations(enumerations: List<YamlModel.Enum>) {

    writeText(header)

    enumerations.toCommonEnumerations()
        .let(::appendText)

}

internal fun File.generateNativeEnumerations(enumerations: List<YamlModel.Enum>) {

    writeText(header)

    enumerations.toNativeEnumerations()
        .let(::appendText)

}

internal fun File.generateWebEnumerations(enumerations: List<YamlModel.Enum>) {

    writeText(header)

    enumerations.toWebEnumerations()
        .let(::appendText)

}
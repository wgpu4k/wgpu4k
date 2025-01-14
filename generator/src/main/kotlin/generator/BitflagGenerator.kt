package generator

import Paths
import disclamer
import domain.YamlModel
import generator.bitflag.toBitflagEnumerations
import java.io.File

val bitflagCommonMainFile = Paths.commonMain
    .resolve("bitflag.enumerations.kt")


private val header = """
    $disclamer
    package io.ygdrasil.webgpu
    
    
""".trimIndent()

internal fun File.generateCommonBitflag(enumerations: List<YamlModel.Bitflag>) {

    writeText(header)

    enumerations.toBitflagEnumerations()
        .let(::appendText)

}
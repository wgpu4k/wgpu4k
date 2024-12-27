package generator

import Paths
import disclamer
import domain.YamlModel
import generator.enumeration.toCommonEnumerations
import java.io.File

val enumerationCommonMainFile = Paths.commonMain
    .resolve("test.enumerations.kt")

private val header = """
    $disclamer
    package webgpu
    
    
""".trimIndent()

internal fun File.generateCommonEnumerations(enumerations: List<YamlModel.Enum>) {

    writeText(header)

    enumerations.toCommonEnumerations()
        .let(::appendText)

}
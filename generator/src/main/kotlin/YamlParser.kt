import com.charleskorn.kaml.Yaml
import domain.YamlModel
import generator.bitflagCommonMainFile
import generator.enumerationCommonMainFile
import generator.enumerationNativeMainFile
import generator.enumerationWebMainFile
import generator.generateCommonBitflag
import generator.generateCommonEnumerations
import generator.generateNativeEnumerations
import generator.generateWebEnumerations


fun main() {

    val webgpuModel = loadWebGPUYaml()
    val wgpuModel = loadExtraYaml()

    enumerationCommonMainFile.generateCommonEnumerations(webgpuModel.enums)
    enumerationWebMainFile.generateWebEnumerations(webgpuModel.enums)
    enumerationNativeMainFile.generateNativeEnumerations(webgpuModel.enums)

    bitflagCommonMainFile.generateCommonBitflag(webgpuModel.bitflags)
}

fun loadExtraYaml() = readFileFromClasspath("extra.yml")
    .let { text -> parser.decodeFromString(YamlModel.serializer(), text) }

fun loadWebGPUYaml() = readFileFromClasspath("webgpu.yml")
    .let { text -> parser.decodeFromString(YamlModel.serializer(), text) }

val parser = Yaml(
    configuration = Yaml.default.configuration.copy(strictMode = false)
)

fun readFileFromClasspath(fileName: String): String {
    val classLoader = Thread.currentThread().contextClassLoader
    return classLoader.getResourceAsStream(fileName)?.use { inputStream ->
        inputStream.bufferedReader().use { it.readText() }
    } ?: error("File not found: $fileName")
}


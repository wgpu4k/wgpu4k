import com.charleskorn.kaml.Yaml
import domain.YamlModel
import generator.enumerationCommonMainFile
import generator.generateCommonEnumerations
import java.io.File


object Paths {
    private val base = File(".")
    private val sourceBase = base
        .resolve("wgpu4k")
        .resolve("src")

    val commonMain = sourceBase
        .resolve("commonMain")
        .resolve("kotlin")

    val commonNativeMain = sourceBase
        .resolve("commonNativeMain")
        .resolve("kotlin")

    val commonWebMain = sourceBase
        .resolve("commonWebMain")
        .resolve("kotlin")
}
val basePath = File(".")
val sourceBasePath = basePath
    .resolve("wgpu4k")
    .resolve("src")
val commonMainBasePath = sourceBasePath
    .resolve("commonMain")
    .resolve("kotlin")

fun main() {

    println("Base path: ${basePath.absolutePath}")

    val webgpuModel = loadWebGPUYaml()
        .merge(loadExtraYaml())

    enumerationCommonMainFile.generateCommonEnumerations(webgpuModel.enums)
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


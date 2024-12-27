import com.charleskorn.kaml.Yaml
import domain.YamlModel
import java.io.File


object Paths {
    private val base = File(".")
    private val sourceBase = basePath
        .resolve("wgpu4k")
        .resolve("src")

    val commonMain = sourceBasePath
        .resolve("commonMain")
        .resolve("kotlin")

    val commonNativeMain = sourceBasePath
        .resolve("commonNativeMain")
        .resolve("kotlin")

    val commonWebMain = sourceBasePath
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

val androidMainBasePath = sourceBasePath
    .resolve("commonNativeMain")
    .resolve("kotlin")


val jvmMainBasePath = sourceBasePath
    .resolve("jvmMain")
    .resolve("kotlin")
val nativeMainBasePath = sourceBasePath
    .resolve("nativeMain")
    .resolve("kotlin")

fun main() {

    println("Base path: ${basePath.absolutePath}")

    val webgpuModel = loadWebGPUYaml()
        .merge(loadExtraYaml())


//    typesCommonMainFile.generateTypesCommonMain(webgpuCModel.pointers)
//
//
//    commonMainBasePath.apply {
//        generateCommonCallback(webgpuCModel.callbacks)
//    }
//
//    jvmMainBasePath.apply {
//        generateJvmCallback(webgpuCModel.callbacks)
//    }
//
//    nativeMainBasePath.apply {
//        generateNativeFunctions(webgpuCModel.functions)
//        generateNativeCallback(webgpuCModel.callbacks)
//    }
//
//    jvmNativeFunctionsMainFile.generateJvmNativeFunctions(webgpuCModel.functions)
//
//    functionsCommonMainFile.generateCommonFunctions(webgpuCModel.functions)
//    functionsJvmMainFile.generateJvmFunctions(webgpuCModel.functions)
//
//    structuresCommonMainFile.generateCommonStructures(webgpuCModel.structures)
//
//
//
//    structuresJvmMainFile.generateJvmStructures(webgpuCModel.structures)
//    structuresNativeMainFile.generateNativeStructures(webgpuCModel.structures)
//
//    enumerationCommonMainFile.generateCommonEnumerations(webgpuCModel.enumerations)
//
//    androidMainBasePath.apply {
//        generateAndroidCallback(webgpuCModel.callbacks)
//        generateAndroidStructures(webgpuCModel.structures)
//        generateAndroidNativeFunctions(webgpuCModel.functions)
//        generateAndroidFunctions(webgpuCModel.functions)
//    }
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


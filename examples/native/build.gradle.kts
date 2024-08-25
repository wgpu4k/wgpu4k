import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

val commonResourcesFile = getCommonProject()
    .projectDir
    .resolve("src")
    .resolve("commonMain")
    .resolve("resources")

assert(commonResourcesFile.isDirectory) { "$commonResourcesFile is not a directory" }
assert(commonResourcesFile.isNotEmpty) { "$commonResourcesFile is empty" }

val resourcesDirectory = project.file("src").resolve("nativeMain").resolve("resources")

val buildNativeResourcesDirectory = project.file("build").resolve("native")

kotlin {

    val hostOs = System.getProperty("os.name")
    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val nativeTarget = when {
        hostOs == "Linux" && isArm64 -> linuxArm64("native")
        hostOs == "Linux" && !isArm64 -> linuxX64("native")
        hostOs == "Mac OS X" && isArm64 -> macosArm64("native")
        hostOs == "Mac OS X" && !isArm64 -> macosX64("native")
        hostOs.startsWith("Windows") -> mingwX64("native").apply {
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            compilerOptions {
                freeCompilerArgs.add("-Xllvm-variant=${getCustomLLVMPath()}")
            }
        }
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }


    with(nativeTarget) {

        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(projects.examples.common)
            }
        }

        val nativeMain by getting {
            resources.setSrcDirs(
                resources.srcDirs + setOf(
                    commonResourcesFile
                )
            )
        }

    }
}

tasks.named<Exec>("runDebugExecutableNative").configure {
    args(commonResourcesFile.absolutePath)
}

fun getCommonProject() = projects.examples.common.identityPath.path
    ?.let(::project) ?: error("Could not find project path")

val File.isNotEmpty: Boolean
    get() = this.listFiles()?.isNotEmpty() ?: false
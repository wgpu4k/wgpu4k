import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

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

kotlin {
    js {
        binaries.executable()
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        binaries.executable()
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.wgpu4kScenes)
            }
        }

        val jsMain by getting {
            resources.setSrcDirs(
                resources.srcDirs + setOf(
                    commonResourcesFile
                )
            )
        }

        val wasmJsMain by getting {
            resources.setSrcDirs(
                resources.srcDirs + setOf(
                    commonResourcesFile
                )
            )
        }

    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        allWarningsAsErrors = true
    }
}

fun getCommonProject() = projects.wgpu4kScenes.identityPath.path
    ?.let(::project) ?: error("Could not find project path")

val File.isNotEmpty: Boolean
    get() = this.listFiles()?.isNotEmpty() ?: false
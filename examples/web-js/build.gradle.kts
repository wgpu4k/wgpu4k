@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

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

    wasmJs {
        binaries.executable()
        browser()
        compilerOptions {
            // Enable only when need tp debug, this required special activation on browser
            // Use chrome://flags/#enable-experimental-webassembly-features on chrome
            //freeCompilerArgs.add("-Xwasm-use-new-exception-proposal")
        }
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
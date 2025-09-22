@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile

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
            freeCompilerArgs.add("-Xwasm-use-new-exception-proposal")
        }
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        webMain {
            dependencies {
                implementation(projects.wgpu4kScenes)
            }
        }

        jsMain {
            resources.setSrcDirs(
                resources.srcDirs + setOf(
                    commonResourcesFile
                )
            )
        }

        wasmJsMain {
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

fun getCommonProject() = projects
    .wgpu4kScenes
    .path
    .let(::project)

val File.isNotEmpty: Boolean
    get() = this.listFiles()?.isNotEmpty() ?: false


tasks.withType<KotlinJsCompile>().configureEach {
    compilerOptions {
        target = "es2015"
    }
}
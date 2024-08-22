import org.jetbrains.kotlin.com.google.common.io.Files
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id("publish")
}

val buildNativeResourcesDirectory = project.file("build").resolve("native")
val headersDirectory = project.file("src").resolve("headers")
val resourcesDirectory = project.file("src").resolve("jvmMain").resolve("resources")

kotlin {

    val nativeTargets = listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosArm64(),
        macosX64(),
        linuxArm64(),
        linuxX64(),
        mingwX64(),
    )

    nativeTargets.forEach { target ->
        val main by target.compilations.getting {
            cinterops.create("glfw") {
                includeDirs(headersDirectory)
                header(buildNativeResourcesDirectory.resolve("glfw3.h"))
            }
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        allWarningsAsErrors = true
    }
}

configureDownloadTasks {
    baseUrl = "https://github.com/glfw/glfw/releases/download/3.3.10/"

    download("glfw-3.3.10.bin.MACOS.zip") {
        extract("**/include/GLFW/glfw3.h", buildNativeResourcesDirectory.resolve("glfw3.h")).doLast {
            Files.move(
                buildNativeResourcesDirectory.resolve("glfw-3.3.10.bin.MACOS").resolve("include").resolve("GLFW")
                    .resolve("glfw3.h"), buildNativeResourcesDirectory.resolve("glfw3.h")
            )
            buildNativeResourcesDirectory.resolve("glfw-3.3.10.bin.MACOS").deleteRecursively()
        }
        extract(
            "**/lib-universal/libglfw3.a",
            buildNativeResourcesDirectory.resolve("darwin").resolve("libglfw3.a")
        ).doLast {
            Files.move(
                buildNativeResourcesDirectory.resolve("darwin").resolve("glfw-3.3.10.bin.MACOS")
                    .resolve("lib-universal")
                    .resolve("libglfw3.a"), buildNativeResourcesDirectory.resolve("darwin").resolve("libglfw3.a")
            )
            buildNativeResourcesDirectory.resolve("darwin").resolve("glfw-3.3.10.bin.MACOS").deleteRecursively()
        }
    }

    download("glfw-3.3.10.bin.WIN64.zip") {
        extract(
            "**/lib-mingw-w64/libglfw3.a",
            buildNativeResourcesDirectory.resolve("windows").resolve("libglfw3.a")
        ).doLast {
            Files.move(
                buildNativeResourcesDirectory.resolve("windows").resolve("glfw-3.3.10.bin.WIN64")
                    .resolve("lib-mingw-w64")
                    .resolve("libglfw3.a"), buildNativeResourcesDirectory.resolve("windows").resolve("libglfw3.a")
            )
            buildNativeResourcesDirectory.resolve("windows").resolve("glfw-3.3.10.bin.WIN64").deleteRecursively()
        }
    }
}

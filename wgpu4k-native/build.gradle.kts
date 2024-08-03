import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id("publish")
}

val buildNativeResourcesDirectory = project.file("build").resolve("native")
val resourcesDirectory = project.file("src").resolve("jvmMain").resolve("resources")

kotlin {

    val nativeTargets = listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosArm64(),
        macosX64(),
        androidNativeX64(),
        androidNativeArm64(),
        tvosArm64(),
        tvosX64(),
        linuxArm64(),
        linuxX64(),
        mingwX64(),
    )

    nativeTargets.forEach { target ->
        val main by target.compilations.getting {
            cinterops.create("webgpu") {
                header(buildNativeResourcesDirectory.resolve("wgpu.h"))
            }
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        allWarningsAsErrors = true
    }
}

configureDownloadTasks {
    baseUrl = "https://github.com/ygdrasil-io/wgpu-native/releases/download/${libs.versions.wgpu.get()}/"

    download("wgpu-macos-aarch64-release.zip") {
        extract("webgpu.h", buildNativeResourcesDirectory.resolve("webgpu.h"))
        extract("wgpu.h", buildNativeResourcesDirectory.resolve("wgpu.h"))
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("darwin-aarch64").resolve("libWGPU.a"))
    }

    download("wgpu-macos-x86_64-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("darwin-x64").resolve("libWGPU.a"))
    }

    download("wgpu-windows-x86_64-gnu-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("windows-x64").resolve("wgpu.a"))
    }

    download("wgpu-linux-x86_64-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("linux-x64").resolve("libWGPU.a"))
    }

    download("wgpu-linux-aarch64-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("linux-aarch64").resolve("libWGPU.a"))
    }
}

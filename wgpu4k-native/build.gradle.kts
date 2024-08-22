import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id("publish")
}

val buildNativeResourcesDirectory = project.file("build").resolve("native")

kotlin {

    val androidNativeTargets = listOf(
        androidNativeArm64(),
        androidNativeX64()
    )

    val nativeTargets = listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosArm64(),
        macosX64(),
        linuxArm64(),
        linuxX64(),
        configureMingwX64(),
    ).filterNotNull() + androidNativeTargets


    nativeTargets.forEach { target ->
        val main by target.compilations.getting {
            cinterops.create("webgpu") {
                header(buildNativeResourcesDirectory.resolve("wgpu.h"))
            }
        }
    }

    androidNativeTargets.forEach { target ->
        target.binaries {
            sharedLib {
                baseName = "wgpu4kv2"
            }
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        allWarningsAsErrors = true
    }
}

configureDownloadTasks {
    baseUrl = "${project.properties["wgpu.base.url"]}${libs.versions.wgpu.get()}/"

    /*** Macos ***/
    download("wgpu-macos-aarch64-release.zip") {
        extract("webgpu.h", buildNativeResourcesDirectory.resolve("webgpu.h"))
        extract("wgpu.h", buildNativeResourcesDirectory.resolve("wgpu.h"))
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("darwin-aarch64").resolve("libWGPU.a"))
    }
    download("wgpu-macos-x86_64-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("darwin-x64").resolve("libWGPU.a"))
    }

    /*** Windows ***/
    download("wgpu-windows-x86_64-gnu-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("windows-x64").resolve("wgpu.a"))
    }

    /*** Linux ***/
    download("wgpu-linux-x86_64-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("linux-x64").resolve("libWGPU.a"))
    }
    download("wgpu-linux-aarch64-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("linux-aarch64").resolve("libWGPU.a"))
    }

    /*** Android ***/
    download("wgpu-android-x86_64-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("android-x64").resolve("libWGPU.a"))
    }
    download("wgpu-android-aarch64-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("android-aarch64").resolve("libWGPU.a"))
    }

    /*** iOS ***/
    download("wgpu-iOS-x86_64-simulator-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("ios-simulator-x64").resolve("libWGPU.a"))
    }
    download("wgpu-iOS-aarch64-simulator-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("ios-simulator-aarch64").resolve("libWGPU.a"))
    }
    download("wgpu-iOS-aarch64-release.zip") {
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("ios-aarch64").resolve("libWGPU.a"))
    }
}

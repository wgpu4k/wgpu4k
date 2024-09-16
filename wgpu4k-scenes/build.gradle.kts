import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    kotlin("plugin.serialization") version "2.0.20"
    if (isAndroidConfigured) id("android")
    id("publish")
}

kotlin {

    js {
        binaries.executable()
        browser()
    }

    jvm()

    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        binaries.executable()
        browser()
    }


    macosArm64()
    macosX64()
    linuxArm64()
    linuxX64()
    configureMingwX64(project)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    if (isAndroidConfigured) androidTarget{
        publishLibraryVariants("release", "debug")
    }

    sourceSets {

        all {
            languageSettings.optIn("kotlin.ExperimentalStdlibApi")
            languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
            languageSettings.optIn("kotlin.js.ExperimentalJsExport")
        }

        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.serialization.json)
                api(projects.wgpu4kToolkit)
                api(libs.coroutines)
                api(libs.bundles.korlibs)
            }
        }

    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        // TODO fiw warning and uncomment
        //allWarningsAsErrors = true
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

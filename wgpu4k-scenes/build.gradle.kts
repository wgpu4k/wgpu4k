
plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    kotlin("plugin.serialization") version "2.1.21"
    id("android")
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

    androidTarget{
        publishLibraryVariants("release", "debug")
    }

    applyDefaultHierarchyTemplate()

    sourceSets {

        all {
            languageSettings.optIn("kotlin.ExperimentalStdlibApi")
            languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
            languageSettings.optIn("kotlin.js.ExperimentalJsExport")
        }

        commonMain {
            dependencies {
                implementation(libs.kotlinx.serialization.json)
                api(projects.wgpu4kToolkit)
                api(libs.coroutines)
                api(libs.bundles.korlibs)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.wgpu4k.native)
            }
        }

        webMain {

            dependencies {
                implementation (kotlinWrappers.web)
                implementation (kotlinWrappers.browser)
                implementation(libs.kotlinx.browser)
            }
        }

        val commonNativeMain by creating {
            dependsOn(commonMain.get())
        }

        nativeMain.get().dependsOn(commonNativeMain)
        jvmMain.get().dependsOn(commonNativeMain)
        androidMain.get().dependsOn(commonNativeMain)
    }

    compilerOptions {
        allWarningsAsErrors = true
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

import com.android.build.api.dsl.androidLibrary
import org.gradle.api.tasks.testing.Test
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    kotlin("plugin.serialization") version "2.2.20"
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

    if (Platform.os == Os.MacOs) {
        iosX64()
        iosArm64()
        iosSimulatorArm64()
        macosArm64()
        macosX64()
    }
    linuxArm64()
    linuxX64()
    mingwX64()

    androidLibrary{
        namespace = "io.ygdrasil.wgpu4k"
        compileSdk = 36
        minSdk = 28

        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
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
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
                api(projects.wgpu4kToolkit)
                api(libs.coroutines)
                api(libs.bundles.korlibs)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.bundles.kotest)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.wgpu4k.native)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.kotest.runner.junit5)
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
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
}

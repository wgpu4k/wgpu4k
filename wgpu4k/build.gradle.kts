@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotest)
    alias(libs.plugins.ksp)
    publish
}

kotlin {

    js {
        browser()
        nodejs()
    }

    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_24
        }
    }

    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_24
        }

        android {
            namespace = "io.ygdrasil.wgpu4k"
            compileSdk = 36

            defaultConfig {
                minSdk = 28
            }

        }
        publishLibraryVariants("release", "debug")
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
    androidNativeArm64()
    androidNativeX64()

    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
        compilerOptions {
            optIn.add("kotlin.js.ExperimentalWasmJsInterop")
        }
    }

    applyDefaultHierarchyTemplate()

    sourceSets {

        all {
            languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
        }

        commonMain {
            dependencies {
                api(libs.webgpu.types)
                implementation(libs.coroutines)
                api(libs.kotlin.logging)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.logback)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.slf4j.simple)
            }
        }


        val commonNativeMain by creating {
            dependsOn(commonMain.get())
            dependencies { api(libs.wgpu4k.native) }
        }

        nativeMain.get().dependsOn(commonNativeMain)
        jvmMain.get().dependsOn(commonNativeMain)
        androidMain.get().dependsOn(commonNativeMain)


        webMain {
            dependencies {
                api(libs.webgpu.web)
                api(kotlinWrappers.browser)
                api(kotlinWrappers.web)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.bundles.kotest)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.kotest.runner.junit5)
                implementation(libs.kotlin.reflect)
            }
        }

    }

    compilerOptions {
        // Generated kotest code uses experimental features that create warnings.
        allWarningsAsErrors = true
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}

tasks.withType<Test>().configureEach {
    filter {
        failOnNoDiscoveredTests = false
    }
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
        )
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

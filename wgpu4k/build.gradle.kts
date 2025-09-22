@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    alias(libs.plugins.kotest)
    id("com.google.devtools.ksp") version "2.2.20-2.0.3"
    publish
    if (isAndroidConfigured) android
}

kotlin {

    js {
        browser()
        nodejs()
    }

    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_22
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosArm64()
    macosX64()
    linuxArm64()
    linuxX64()
    mingwX64()

    if (isAndroidConfigured) androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_22
        }

        publishLibraryVariants("release", "debug")
    }


    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
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

        if (isAndroidConfigured) androidMain {
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
        // Workaround for https://github.com/kotest/kotest/issues/4521 (fixed but not released)
        //allWarningsAsErrors = true
        tasks.withType<KotlinCompilationTask<*>>().configureEach {
            compilerOptions {
                allWarningsAsErrors = !name.contains("test", ignoreCase = true)
            }
        }
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
    filter {
        isFailOnNoMatchingTests = false
    }
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


if (Platform.os == Os.MacOs) {
    tasks.findByName("linkDebugTestMingwX64")?.apply { enabled = false }
    tasks.findByName("mingwX64Test")?.apply { enabled = false }
}

tasks.findByName("checkKotlinGradlePluginConfigurationErrors")?.enabled = isAndroidConfigured
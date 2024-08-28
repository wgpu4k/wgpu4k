import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    alias(libs.plugins.kotest)
    id("publish")
    if (isAndroidConfigured) id("android")
    if (isAndroidConfigured) id("android-copy-jni")
}

val resourcesDirectory = project.file("src").resolve("jvmMain").resolve("resources")

kotlin {

    js {
        browser()
        nodejs()
    }

    jvm {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }


    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosArm64()
    macosX64()
    linuxArm64()
    linuxX64()
    configureMingwX64()

    if (isAndroidConfigured) androidTarget()


    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
    }

    sourceSets {

        all {
            languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
        }

        jvmMain {
            sourceSets {
                languageSettings.optIn("kotlin.js.ExperimentalJsExport")
            }

            dependencies {
                api(libs.wgpu4k.panama)
            }
        }

        commonMain {
            dependencies {
                implementation(libs.coroutines)
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
                implementation("org.jetbrains.kotlin:kotlin-reflect")
            }
        }

        nativeMain {
            sourceSets {
                //languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
            }

            dependencies {
                implementation(libs.wgpu4k.native)
            }
        }

        androidMain {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.0")
            }
        }

    }
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        allWarningsAsErrors = true
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
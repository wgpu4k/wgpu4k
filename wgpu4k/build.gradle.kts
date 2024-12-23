import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    alias(libs.plugins.kotest)
    id("publish")
    if (isAndroidConfigured) id("android")
}

val resourcesDirectory = project.file("src")
    .resolve("jvmMain").resolve("resources")

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
    //configureMingwX64(project)

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
                implementation(libs.coroutines)
            }
        }

        val commonNativeMain by creating {
            dependsOn(commonMain.get())

            dependencies { implementation(libs.wgpu4k.native) }
        }

        jvmMain { dependsOn(commonNativeMain) }
        nativeMain { dependsOn(commonNativeMain) }
        androidMain {
            dependsOn(commonNativeMain)
            dependencies { implementation(libs.android.native.helper) }
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
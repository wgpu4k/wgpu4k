import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    alias(libs.plugins.kotest)
    id("publish")
    if (isAndroidConfigured) id("android")
}

val buildNativeResourcesDirectory = project.file("build").resolve("native")
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
    tvosArm64()
    tvosX64()
    linuxArm64()
    linuxX64()
    configureMingwX64()
    //androidNativeX64()
    //androidNativeArm64()

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

        val kotlinWrappersVersion = "1.0.0-pre.790"

        jsMain {
            dependencies {
                implementation(project.dependencies.platform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-js")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-web")
            }
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
                implementation(kotlin("stdlib-common"))
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
                implementation(projects.wgpu4kNative)
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

configureDownloadTasks {
    baseUrl = "${project.properties["wgpu.base.url"]}${libs.versions.wgpu.get()}/"

    download("wgpu-macos-aarch64-release.zip") {
        extract("libwgpu_native.dylib", resourcesDirectory.resolve("darwin-aarch64").resolve("libWGPU.dylib"))
    }

    download("wgpu-macos-x86_64-release.zip") {
        extract("libwgpu_native.dylib", resourcesDirectory.resolve("darwin-x86-64").resolve("libWGPU.dylib"))
    }

    download("wgpu-windows-x86_64-msvc-release.zip") {
        extract("wgpu_native.dll", resourcesDirectory.resolve("win32-x86-64").resolve("WGPU.dll"))
    }

    download("wgpu-linux-x86_64-release.zip") {
        extract("libwgpu_native.so", resourcesDirectory.resolve("linux-x86-64").resolve("libWGPU.so"))
    }

    download("wgpu-linux-aarch64-release.zip") {
        extract("libwgpu_native.so", resourcesDirectory.resolve("linux-aarch64").resolve("libWGPU.so"))
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

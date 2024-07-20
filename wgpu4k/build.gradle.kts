import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id("com.android.library")
    alias(libs.plugins.kotest)
    id("publish")
}

val buildNativeResourcesDirectory = project.file("build").resolve("native")
val resourcesDirectory = project.file("src").resolve("jvmMain").resolve("resources")

kotlin {

    js {
        browser()
        nodejs()
    }
    jvm()

    val unimplementedTarget = listOf<KotlinNativeTarget>(
        /*androidNativeX64(),
        androidNativeArm64(),
        androidTarget(),
        iosX64(),
        iosArm64(),
        tvosArm64(),
        tvosX64(),
        linuxArm64(),
        linuxX64(),
        mingwX64(),*/
    )
    val nativeTarget = setOf(
        macosArm64(),
        macosX64(),
    )


    nativeTarget.forEach { target ->
        val main by target.compilations.getting {

            cinterops.create("webgpu") {
                header(buildNativeResourcesDirectory.resolve("wgpu.h"))
            }
        }
    }

	@OptIn(ExperimentalWasmDsl::class)
	wasmJs {
		browser()
		nodejs()
	}

	sourceSets {

        all {
            languageSettings.optIn("kotlin.ExperimentalStdlibApi")
            languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
            languageSettings.optIn("kotlin.js.ExperimentalJsExport")
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }

        val kotlinWrappersVersion = "1.0.0-pre.780"

        val jsMain by getting {
            dependencies {
                implementation(project.dependencies.platform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-js")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-web")
            }
        }

        val jvmMain by getting {
            dependencies {
                api(projects.wgpu4kJvmPanama)
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(libs.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.kotest)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.kotest.runner.junit5)
            }
        }

        nativeTarget.forEach { target ->
            println("will configure ${target.name}")
            getByName("${target.name}Main").apply {
                dependencies {
                    implementation(
                        files(
                            project.file("build")
                                .resolve("classes")
                                .resolve(target.name)
                                .resolve("main")
                                .resolve("cinterop")
                                .resolve("wgpu4k-cinterop-webgpu.klib")
                        )
                    )
                }
            }

        }


        /*
		val unmappedMain by creating {
			dependsOn(commonMain)
		}

        val nativeMain by creating {
            dependsOn(commonMain)
        }


        unimplementedTarget.forEach { target ->
            getByName("${target.name}Main")
                .dependsOn(unmappedMain)
        }*/

    }
    compilerOptions {
        allWarningsAsErrors = true
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

android {
    namespace = "io.ygdrasil.wgpu4k"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}


configureDownloadTasks {
    baseUrl = "https://github.com/gfx-rs/wgpu-native/releases/download/${libs.versions.wgpu.get()}/"

    download("wgpu-macos-aarch64-release.zip") {
        extract("libwgpu_native.dylib", resourcesDirectory.resolve("darwin-aarch64").resolve("libWGPU.dylib"))
        extract("webgpu.h", buildNativeResourcesDirectory.resolve("webgpu.h"))
        extract("wgpu.h", buildNativeResourcesDirectory.resolve("wgpu.h"))
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("darwin-aarch64").resolve("libWGPU.a"))
    }

    download("wgpu-macos-x86_64-release.zip") {
        extract("libwgpu_native.dylib", resourcesDirectory.resolve("darwin-x86-64").resolve("libWGPU.dylib"))
        extract("libwgpu_native.a", buildNativeResourcesDirectory.resolve("darwin-x64").resolve("libWGPU.a"))
    }

    download("wgpu-windows-x86_64-release.zip") {
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

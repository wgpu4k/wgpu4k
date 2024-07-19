import de.undercouch.gradle.tasks.download.Download
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id("com.android.library")
    alias(libs.plugins.kotest)
    id("publish")
}

kotlin {

    js {
        browser()
        nodejs()
    }
    jvm()

    val unimplementedTarget = listOf(
        androidNativeX64(),
        androidNativeArm64(),
        androidTarget(),
        iosX64(),
        iosArm64(),
        tvosArm64(),
        tvosX64(),
        linuxArm64(),
        linuxX64(),
        macosArm64(),
        macosX64(),
        mingwX64(),
    )

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

		val unmappedMain by creating {
			dependsOn(commonMain)
		}

        unimplementedTarget.forEach { target ->
            getByName("${target.name}Main")
                .dependsOn(unmappedMain)
        }
		/*val macosX64Main by getting { dependsOn(unmappedMain) }
		val macosArm64Main by getting { dependsOn(unmappedMain) }
		val linuxArm64Main by getting { dependsOn(unmappedMain) }
		val linuxX64Main by getting { dependsOn(unmappedMain) }
		val iosX64Main by getting { dependsOn(unmappedMain) }
		val iosArm64Main by getting { dependsOn(unmappedMain) }
		val androidNativeX64Main by getting { dependsOn(unmappedMain) }
		val androidNativeArm64Main by getting { dependsOn(unmappedMain) }
        val androidTarget by getting { dependsOn(unmappedMain) }*/

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

val resourcesDirectory = project.file("src").resolve("jvmMain").resolve("resources")
val zipBuildDirectory = project.file("build").resolve("zip")
val baseUrl = "https://github.com/gfx-rs/wgpu-native/releases/download/${libs.versions.wgpu.get()}/"
val fileToDownload = listOf(
    NativeLibrary(
        "wgpu-macos-aarch64-release.zip",
        resourcesDirectory.resolve("darwin-aarch64").resolve("libWGPU.dylib"),
        "libwgpu_native.dylib"
    ),
    NativeLibrary(
        "wgpu-macos-x86_64-release.zip",
        resourcesDirectory.resolve("darwin-x86-64").resolve("libWGPU.dylib"),
        "libwgpu_native.dylib"
    ),
    NativeLibrary(
        "wgpu-windows-x86_64-release.zip",
        resourcesDirectory.resolve("win32-x86-64").resolve("WGPU.dll"),
        "wgpu_native.dll"
    ),
    NativeLibrary(
        "wgpu-linux-x86_64-release.zip",
        resourcesDirectory.resolve("linux-x86-64").resolve("libWGPU.so"),
        "libwgpu_native.so"
    ),
    NativeLibrary(
        "wgpu-linux-aarch64-release.zip",
        resourcesDirectory.resolve("linux-aarch64").resolve("libWGPU.so"),
        "libwgpu_native.so"
    ),
).forEach { (fileName, target, zipFilename) ->
    val zipFile = zipBuildDirectory.resolve(fileName)
    val downloadTask = downloadInto(fileName, zipFile)
    val unzipTask = unzipTask(zipFile, target, zipFilename, downloadTask)

    tasks.withType<ProcessResources>() {
        dependsOn(unzipTask)
    }
}

fun downloadInto(fileName: String, target: File): Task {
    val url = "$baseUrl$fileName"
    val taskName = "downloadFile-$fileName"
    return tasks.register<Download>(taskName) {
        onlyIf { !target.exists() }
        src(url)
        dest(target)
    }.get()
}

fun unzipTask(
    zipFile: File,
    target: File,
    zipFilename: String,
    downloadTask: Task,
) = tasks.register<Copy>("unzip-${zipFile.name}") {
    onlyIf { !target.exists() }
    from(zipTree(zipFile))
    include(zipFilename)
    into(target.parent)
    rename { fileName ->
        fileName.replace(zipFilename, target.name)
    }
    dependsOn(downloadTask)
}.get()

data class NativeLibrary(val remoteFile: String, val targetFile: File, val zipFileName: String)

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

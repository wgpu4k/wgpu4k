import de.undercouch.gradle.tasks.download.Download

plugins {
    alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.kotest)
	alias(libs.plugins.download)
}

kotlin {

	js {
		binaries.executable()
		browser()
		nodejs()
	}
	jvm()

    sourceSets {

		all {
			languageSettings.optIn("kotlin.ExperimentalStdlibApi")
			languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
			languageSettings.optIn("kotlin.js.ExperimentalJsExport")
		}

		val kotlinWrappersVersion = "1.0.0-pre.721"

		val jsMain by getting {
			dependencies {
				implementation(project.dependencies.platform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
				implementation("org.jetbrains.kotlin-wrappers:kotlin-js")
				implementation("org.jetbrains.kotlin-wrappers:kotlin-web")
			}
		}

		val jvmMain by getting {
			dependencies {
				api(libs.jna)
				implementation(libs.shapeshift)
			}
		}

        val commonMain by getting {
            dependencies {
				implementation(kotlin("stdlib-common"))
				implementation(libs.coroutines)
				implementation(kotlin("reflect"))
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
    }

	compilerOptions {
		allWarningsAsErrors = true
		freeCompilerArgs.add("-Xexpect-actual-classes")
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
	downloadTask: Task
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
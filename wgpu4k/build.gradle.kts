import org.jetbrains.kotlin.de.undercouch.gradle.tasks.download.Download


plugins {
    alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.kotest)
}

kotlin {
	js {
		binaries.executable()
		browser()
		nodejs()
	}
	jvm()

    sourceSets {
		val jvmMain by getting {
			dependencies {
				api(libs.jna)
				implementation("dev.krud:shapeshift:0.8.0")
			}
		}

        val commonMain by getting {
            dependencies {
				implementation(kotlin("stdlib-common"))
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
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
				implementation("org.opentest4j:opentest4j:1.3.0")
			}

		}
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
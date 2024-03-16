import io.ygdrasil.ParsingMethod
import klang.domain.*
import org.jetbrains.kotlin.de.undercouch.gradle.tasks.download.Download
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly
import java.net.URL

buildscript {
	dependencies {
		classpath("io.ygdrasil:klang:0.0.0") {
			isChanging = true
		}
		classpath("io.ygdrasil:klang-gradle-plugin:0.0.0") {
			isChanging = true
		}
	}
}

plugins {
    alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.kotest)
	alias(libs.plugins.klang)
}

kotlin {
	js {
		binaries.executable()
		browser()
		nodejs()
		//generateTypeScriptDefinitions()
	}
	jvm()

    /*androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()*/

    sourceSets {
		val jvmMain by getting {
			dependencies {
				implementation(kotlin("stdlib-common"))
				api(libs.jna)
				api("$group:sdl2-4k:$version")
				api("$group:sdl2-binaries:$version")
				implementation("dev.krud:shapeshift:0.8.0")
			}
		}

        val commonMain by getting {
            dependencies {
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

/*android {
    namespace = "org.jetbrains.kotlinx.multiplatform.library.template"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}*/


val headerUrl =
	URL("https://github.com/gfx-rs/wgpu-native/releases/download/${libs.versions.wgpu.get()}/wgpu-macos-x86_64-release.zip")

klang {

	parsingMethod = ParsingMethod.Libclang

	download(headerUrl)
		.let(::unpack)
		.let {
			parse(fileToParse = "wgpu.h", at = it) {
				// Hardfixes until Callback are fixed
				(findTypeAliasByName("WGPURequestDeviceCallback") ?: error("WGPURequestAdapterCallback should exist"))
					.let { callback ->
						(((callback.typeRef as? ResolvedTypeRef)?.type as? FunctionPointerType)
							?: error("should be resolved"))
							.let { function ->
								val arguments = function.arguments.toMutableList()
								arguments[0] = typeOf("int").unchecked()
								arguments[2] = typeOf("char *").unchecked()
								arguments[3] = typeOf("void *").unchecked()
								function.arguments = arguments.toList()
							}
					}
				(findTypeAliasByName("WGPURequestAdapterCallback") ?: error("WGPURequestAdapterCallback should exist"))
					.let { callback ->
						(((callback.typeRef as? ResolvedTypeRef)?.type as? FunctionPointerType)
							?: error("should be resolved"))
							.let { function ->
								val arguments = function.arguments.toMutableList()
								arguments[0] = typeOf("int").unchecked()
								arguments[2] = typeOf("char *").unchecked()
								arguments[3] = typeOf("void *").unchecked()
								function.arguments = arguments.toList()
							}
					}
				declarations.filterIsInstance<NativeEnumeration>()
					.forEach { enumeration ->
						enumeration.values = enumeration.values.map { (name, value) ->
							name.removePrefix("${enumeration.name}_").toLowerCaseAsciiOnly() to value
						}
					}
			}
		}

	generateBinding("io.ygdrasil.wgpu.internal.jvm", "WGPU")
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
		resourcesDirectory.resolve("win32").resolve("libWGPU.dll"),
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
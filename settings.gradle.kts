rootProject.name = "wgpu4k-root"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
		mavenLocal()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	}
}

dependencyResolutionManagement {
	repositories {
		google()
		mavenLocal()
		mavenCentral()
	}
}

val hostOs = System.getProperty("os.name")

include("wgpu4k-glfw-native")
include("wgpu4k-native")
include("wgpu4k")
include("wgpu4k-toolkit")
include("examples:common")
include("wgpu4k-e2e")
//include("examples:macos")
include("examples:compose")
include("examples:web-js")
include("examples:glfw")
include("examples:headless")
if (hostOs == "Mac OS X") include("examples:iOS")
if (isAndroidConfigured()) include("examples:android")
// right now only running on OSX
if (hostOs == "Mac OS X" || (hostOs.startsWith("Windows") && getCustomLLVMPath() != null)) include("examples:native")
include("webgpu-samples-ts")

fun getCustomLLVMPath(): String? = System.getenv("LIBCLANG_PATH")?.takeIf { it.isNotEmpty() }
fun isAndroidConfigured(): Boolean = System.getenv("ANDROID_HOME") != null
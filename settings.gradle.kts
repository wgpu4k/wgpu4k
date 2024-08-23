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

if (isAndroidConfigured()) include("wgpu4k-android-jni")
include("wgpu4k")
include("wgpu4k-toolkit")
include("examples:common")
include("wgpu4k-e2e")
include("examples:compose")
include("examples:web-js")
include("examples:glfw")
include("examples:headless")
if (hostOs == "Mac OS X") include("examples:iOS")
if (isAndroidConfigured()) include("examples:android")
// right now only running on OSX
if ((hostOs.startsWith("Windows") && getCustomLLVMPath() != null) || !hostOs.startsWith("Windows")) include("examples:native")
include("webgpu-samples-ts")

fun getCustomLLVMPath(): String? = System.getenv("LIBCLANG_PATH")?.takeIf { it.isNotEmpty() }
fun isAndroidConfigured(): Boolean = System.getenv("ANDROID_HOME") != null
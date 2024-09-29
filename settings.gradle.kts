rootProject.name = "wgpu4k-root"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
		maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
	}
}

dependencyResolutionManagement {
	repositories {
		google()
		mavenLocal()
		mavenCentral()
		// Snapshot central repository
		maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
		//wgpu4k snapshot & preview repository
		//maven("https://gitlab.com/api/v4/projects/25805863/packages/maven")

	}
}

val hostOs = System.getProperty("os.name")

include("wgpu4k")
include("wgpu4k-toolkit")
include("wgpu4k-scenes")
include("wgpu4k-e2e")
if (isInCI().not()) {
	include("examples:glfw")
	include("examples:web-js")
	include("examples:glfw")
	if (hostOs == "Mac OS X") include("examples:iOS")
	if (isAndroidConfigured()) include("examples:android")
	// right now only running on OSX and may be linux x64
	include("examples:native")
}


fun isAndroidConfigured(): Boolean = System.getenv("ANDROID_HOME") != null
fun isInCI(): Boolean = System.getenv("CI") != null
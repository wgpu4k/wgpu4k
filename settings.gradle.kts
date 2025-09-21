rootProject.name = "wgpu4k-root"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
	includeBuild("generator")
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
		maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
	}
}
plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
}

dependencyResolutionManagement {
	repositories {
		mavenLocal()
		//wgpu4k snapshot repository
		maven("https://gitlab.com/api/v4/projects/25805863/packages/maven")
		google()
		mavenCentral()

	}

    versionCatalogs {
        create("kotlinWrappers") {
            val wrappersVersion = "2025.9.8"
            from("org.jetbrains.kotlin-wrappers:kotlin-wrappers-catalog:$wrappersVersion")
        }
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
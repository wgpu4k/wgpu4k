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

include("wgpu4k-jvm-panama")
include("wgpu4k")
include("wgpu4k-toolkit")
include("librococoa")
include("examples:common")
include("wgpu4k-e2e")
//include("examples:macos")
include("examples:compose")
include("examples:web-js")
include("examples:glfw")
include("examples:headless")
include("examples:native")
include("webgpu-samples-ts")

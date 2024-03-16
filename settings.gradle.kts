rootProject.name = "wgpu"

pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
		mavenLocal()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	}
}

include("wgpu4k")
include("librococoa")
//include("examples")
include("examples:common")
//include("examples:compose")
include("examples:web-js")
//include("examples:SDL2")
include("examples:glfw")
include("webgpu-samples-ts")

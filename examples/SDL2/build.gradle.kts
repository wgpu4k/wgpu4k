
plugins {
	kotlin("jvm")
	application
}

dependencies {
	implementation(project(":examples:common"))
}

application {
	mainClass.set("io.ygdrasil.wgpu.examples.MainKt")
	applicationDefaultJvmArgs += "-XstartOnFirstThread"
}

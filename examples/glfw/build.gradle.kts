plugins {
	kotlin("jvm")
	application
}

val lwjglNatives = Pair(
	System.getProperty("os.name")!!,
	System.getProperty("os.arch")!!
).let { (name, arch) ->
	when {
		arrayOf("Linux", "SunOS", "Unit").any { name.startsWith(it) } ->
			if (arrayOf("arm", "aarch64").any { arch.startsWith(it) })
				"natives-linux${if (arch.contains("64") || arch.startsWith("armv8")) "-arm64" else "-arm32"}"
			else if (arch.startsWith("ppc"))
				"natives-linux-ppc64le"
			else if (arch.startsWith("riscv"))
				"natives-linux-riscv64"
			else
				"natives-linux"

		arrayOf("Mac OS X", "Darwin").any { name.startsWith(it) } ->
			"natives-macos${if (arch.startsWith("aarch64")) "-arm64" else ""}"

		arrayOf("Windows").any { name.startsWith(it) } ->
			"natives-windows"

		else ->
			throw Error("Unrecognized or unsupported platform. Please set \"lwjglNatives\" manually")
	}
}

val lwjglVersion = "3.3.3"
dependencies {

	implementation(project(":examples:common"))

	implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))
	implementation("org.lwjgl", "lwjgl")
	implementation("org.lwjgl", "lwjgl-glfw")
	runtimeOnly("org.lwjgl", "lwjgl", classifier = lwjglNatives)
	runtimeOnly("org.lwjgl", "lwjgl-glfw", classifier = lwjglNatives)
}



application {
	mainClass.set("io.ygdrasil.wgpu.examples.GlfwMainKt")
	applicationDefaultJvmArgs += "-XstartOnFirstThread"
	applicationDefaultJvmArgs += "--add-opens=java.base/java.lang=ALL-UNNAMED"
}

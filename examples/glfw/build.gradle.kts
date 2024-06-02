plugins {
	kotlin("jvm")
	application
}

val windowsLib = getLibraryProject()
	.projectDir
	.resolve("src")
	.resolve("jvmMain")
	.resolve("resources")
	.resolve("win32-x86-64")

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

	implementation(projects.examples.common)
	implementation(libs.jnaPlatform)
	api(libs.jna)

	implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))
	implementation("org.lwjgl", "lwjgl")
	implementation("org.lwjgl", "lwjgl-glfw")
	runtimeOnly("org.lwjgl", "lwjgl", classifier = lwjglNatives)
	runtimeOnly("org.lwjgl", "lwjgl-glfw", classifier = lwjglNatives)
}


val isOnMac = arrayOf("Mac OS X", "Darwin").any { System.getProperty("os.name").startsWith(it) }
val isOnWindows = arrayOf("Windows").any { System.getProperty("os.name").startsWith(it) }

application {
	mainClass.set("io.ygdrasil.wgpu.examples.GlfwMainKt")
	if (isOnMac) {
		applicationDefaultJvmArgs += "-XstartOnFirstThread"
	}

	println(getLibraryProject().toString())

	applicationDefaultJvmArgs += "--add-opens=java.base/java.lang=ALL-UNNAMED"
	//applicationDefaultJvmArgs += "-Djextract.trace.downcalls=true"
}

kotlin {
	compilerOptions {
		allWarningsAsErrors = true
	}
}

fun getLibraryProject() = projects.wgpu4k.identityPath.path
	?.let(::project) ?: error("Could not find project path")
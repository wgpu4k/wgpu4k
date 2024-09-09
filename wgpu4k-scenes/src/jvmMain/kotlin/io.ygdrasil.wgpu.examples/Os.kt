package io.ygdrasil.wgpu.examples

enum class Os {
	Linux,
	Window,
	MacOs
}

object Platform {
	val os: Os
		get() = System.getProperty("os.name").let { name ->
			when {
				arrayOf("Linux", "SunOS", "Unit").any { name.startsWith(it) } -> Os.Linux
				arrayOf("Mac OS X", "Darwin").any { name.startsWith(it) } -> Os.MacOs
				arrayOf("Windows").any { name.startsWith(it) } -> Os.Window
				else -> error("Unrecognized or unsupported operating system.")
			}
		}

}

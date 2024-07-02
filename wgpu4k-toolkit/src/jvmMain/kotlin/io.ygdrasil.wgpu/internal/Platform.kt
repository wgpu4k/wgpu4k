package io.ygdrasil.wgpu.internal

internal enum class Os {
    Linux,
    Window,
    MacOs
}

internal object Platform {
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

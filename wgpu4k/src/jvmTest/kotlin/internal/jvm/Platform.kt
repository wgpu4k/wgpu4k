package io.ygdrasil.webgpu.internal.jvm

internal enum class Os {
    Linux,
    Windows,
    MacOs
}

internal enum class Architecture {
    X86_64,
    AARCH64
}

internal object Platform {
    val os: Os
        get() = System.getProperty("os.name").let { name ->
            when {
                arrayOf("Linux", "SunOS", "Unit").any { name.startsWith(it) } -> Os.Linux
                arrayOf("Mac OS X", "Darwin").any { name.startsWith(it) } -> Os.MacOs
                arrayOf("Windows").any { name.startsWith(it) } -> Os.Windows
                else -> error("Unrecognized or unsupported operating system.")
            }
        }

    val architecture: Architecture
        get() = System.getProperty("os.arch").let { architecture ->
            when (architecture) {
                "aarch64" -> Architecture.AARCH64
                "x86_64", "amd64" -> Architecture.X86_64
                else -> error("Unrecognized or unsupported architecture $architecture.")
            }
        }

}

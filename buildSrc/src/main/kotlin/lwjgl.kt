val lwjglNatives = Pair(
    Platform.os,
    System.getProperty("os.arch")!!
).let { (name, arch) ->
    when {
         name == Os.Linux  ->
            if (arrayOf("arm", "aarch64").any { arch.startsWith(it) })
                "natives-linux${if (arch.contains("64") || arch.startsWith("armv8")) "-arm64" else "-arm32"}"
            else if (arch.startsWith("ppc"))
                "natives-linux-ppc64le"
            else if (arch.startsWith("riscv"))
                "natives-linux-riscv64"
            else
                "natives-linux"

        name == Os.MacOs ->
            "natives-macos${if (arch.startsWith("aarch64")) "-arm64" else ""}"

        name == Os.Windows ->
            "natives-windows"

        else ->
            throw Error("Unrecognized or unsupported platform. Please set \"lwjglNatives\" manually")
    }
}

val lwjglVersion = "3.3.3"
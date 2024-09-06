plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

val commonResourcesFile = getCommonProject()
    .projectDir
    .resolve("src")
    .resolve("commonMain")
    .resolve("resources")

assert(commonResourcesFile.isDirectory) { "$commonResourcesFile is not a directory" }
assert(commonResourcesFile.isNotEmpty) { "$commonResourcesFile is empty" }

val resourcesDirectory = project.file("src").resolve("nativeMain").resolve("resources")

val buildNativeResourcesDirectory = project.file("build").resolve("native")

kotlin {

    val hostOs = System.getProperty("os.name")
    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val nativeTarget = when {
        // No toolchain on this architecture
        hostOs == "Linux" && isArm64 -> null.also { println("Linux native Arm64 not yet supported") }
        hostOs == "Linux" && !isArm64 -> linuxX64("native")
        hostOs == "Mac OS X" && isArm64 -> macosArm64("native")
        hostOs == "Mac OS X" && !isArm64 -> macosX64("native")
        hostOs.startsWith("Windows") -> configureMingwX64(project, "native")
        else -> null // Not supported
    }

    if (nativeTarget != null) {
        with(nativeTarget) {

            binaries {
                executable {
                    entryPoint = "main"
                }
            }
        }
    }

    sourceSets {

        commonMain {
            dependencies {
                implementation(projects.wgpu4kScenes)
            }
        }

        if (nativeTarget != null) {
            nativeMain {
                resources.setSrcDirs(
                    resources.srcDirs + setOf(
                        commonResourcesFile
                    )
                )
            }
        }

    }

    if (nativeTarget != null) {
        tasks.named<Exec>("runDebugExecutableNative").configure {
            args(commonResourcesFile.absolutePath)
        }
    }
}

fun getCommonProject() = projects.wgpu4kScenes.identityPath.path
    ?.let(::project) ?: error("Could not find project path")

val File.isNotEmpty: Boolean
    get() = this.listFiles()?.isNotEmpty() ?: false
import de.undercouch.gradle.tasks.download.Download
import org.jetbrains.kotlin.com.google.common.io.Files

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

val resourcesDirectory = project.file("src").resolve("jvmMain").resolve("resources")

val buildNativeResourcesDirectory = project.file("build").resolve("native")

kotlin {

    val hostOs = System.getProperty("os.name")
    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" && isArm64 -> macosArm64("native")
        hostOs == "Mac OS X" && !isArm64 -> macosX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }


    with(nativeTarget) {

        val main by compilations.getting {

            cinterops.create("glfw") {
                header(buildNativeResourcesDirectory.resolve("glfw3.h"))
            }
        }

        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(projects.wgpu4k)
            }
        }

        val nativeMain by getting {
            resources.setSrcDirs(
                resources.srcDirs + setOf(
                    commonResourcesFile
                )
            )
        }

    }
}





configureDownloadTasks {
    baseUrl = "https://github.com/glfw/glfw/releases/download/3.3.10/"

    download("glfw-3.3.10.bin.MACOS.zip") {
        extract("**/include/GLFW/glfw3.h", buildNativeResourcesDirectory.resolve("glfw3.h")).doLast {
            Files.move(buildNativeResourcesDirectory.resolve("glfw-3.3.10.bin.MACOS").resolve("include").resolve("GLFW").resolve("glfw3.h"), buildNativeResourcesDirectory.resolve("glfw3.h"))
            buildNativeResourcesDirectory.resolve("glfw-3.3.10.bin.MACOS").deleteRecursively()
        }
        extract("**/lib-universal/libglfw3.a", buildNativeResourcesDirectory.resolve("darwin").resolve("libglfw3.a")).doLast {
            Files.move(
                buildNativeResourcesDirectory.resolve("darwin").resolve("glfw-3.3.10.bin.MACOS").resolve("lib-universal")
                    .resolve("libglfw3.a"), buildNativeResourcesDirectory.resolve("darwin").resolve("libglfw3.a")
            )
            buildNativeResourcesDirectory.resolve("darwin").resolve("glfw-3.3.10.bin.MACOS").deleteRecursively()
        }
    }
}


tasks.create<Download>("downloadFile") {
    src ("https://github.com/glfw/glfw/releases/download/3.3.10/glfw-3.3.10.bin.MACOS.zip")
    dest(layout.buildDirectory)
}

fun getCommonProject() = projects.examples.common.identityPath.path
    ?.let(::project) ?: error("Could not find project path")

val File.isNotEmpty: Boolean
    get() = this.listFiles()?.isNotEmpty() ?: false
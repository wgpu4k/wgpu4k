import de.undercouch.gradle.tasks.download.Download
import org.jetbrains.kotlin.com.google.common.io.Files
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id("publish")
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

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(22))
	}
}

kotlin {

    js {
        binaries.executable()
        browser()
        nodejs()
    }
    jvm()

    wasmJs {
        binaries.executable()
        browser()
    }

    val nativeTargets = listOf<KotlinNativeTarget>(
        //macosArm64(),
        macosX64(),
    )

    nativeTargets.forEach { target ->
        val main by target.compilations.getting {

            defaultSourceSet {

                languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")

                kotlin.srcDir(
                    //"src/${target.name}Main/kotlin",
                    "src/desktopMain/kotlin"
                )
            }

            cinterops.create("glfw") {
                header(buildNativeResourcesDirectory.resolve("glfw3.h"))
            }
        }
    }

    sourceSets {

        all {
            languageSettings.optIn("kotlin.ExperimentalStdlibApi")
            languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
            languageSettings.optIn("kotlin.js.ExperimentalJsExport")
        }

        val commonMain by getting {
            dependencies {
                api(projects.wgpu4k)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.kotest)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(projects.librococoa)
                api(libs.jnaPlatform)
                api(libs.jna)

                api("org.lwjgl:lwjgl:$lwjglVersion")
                api("org.lwjgl:lwjgl-glfw:$lwjglVersion")
                listOf("natives-windows", "natives-macos", "natives-macos-arm64", "natives-linux", "natives-linux-arm64").forEach { dependencyType ->
                    runtimeOnly("org.lwjgl:lwjgl:$lwjglVersion:$dependencyType")
                    runtimeOnly("org.lwjgl:lwjgl-glfw:$lwjglVersion:$dependencyType")
                }
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.kotest.runner.junit5)
            }

        }
    }
    compilerOptions {
        allWarningsAsErrors = true
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

configureDownloadTasks {
    baseUrl = "https://github.com/glfw/glfw/releases/download/3.3.10/"

    download("glfw-3.3.10.bin.MACOS.zip") {
        extract("**/include/GLFW/glfw3.h", buildNativeResourcesDirectory.resolve("glfw3.h")).doLast {
            Files.move(
                buildNativeResourcesDirectory.resolve("glfw-3.3.10.bin.MACOS").resolve("include").resolve("GLFW")
                    .resolve("glfw3.h"), buildNativeResourcesDirectory.resolve("glfw3.h")
            )
            buildNativeResourcesDirectory.resolve("glfw-3.3.10.bin.MACOS").deleteRecursively()
        }
        extract(
            "**/lib-universal/libglfw3.a",
            buildNativeResourcesDirectory.resolve("darwin").resolve("libglfw3.a")
        ).doLast {
            Files.move(
                buildNativeResourcesDirectory.resolve("darwin").resolve("glfw-3.3.10.bin.MACOS")
                    .resolve("lib-universal")
                    .resolve("libglfw3.a"), buildNativeResourcesDirectory.resolve("darwin").resolve("libglfw3.a")
            )
            buildNativeResourcesDirectory.resolve("darwin").resolve("glfw-3.3.10.bin.MACOS").deleteRecursively()
        }
    }
}


tasks.create<Download>("downloadFile") {
    src("https://github.com/glfw/glfw/releases/download/3.3.10/glfw-3.3.10.bin.MACOS.zip")
    dest(layout.buildDirectory)
}


fun getCommonProject() = projects.examples.common.identityPath.path
    ?.let(::project) ?: error("Could not find project path")

val File.isNotEmpty: Boolean
    get() = this.listFiles()?.isNotEmpty() ?: false
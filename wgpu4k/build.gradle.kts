import de.undercouch.gradle.tasks.download.Download
import io.github.krakowski.jextract.JextractTask
import org.jreleaser.model.Active


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotest)
    id("io.github.krakowski.jextract") version "0.5.0" apply false
    alias(libs.plugins.download)
    `maven-publish`
    id("org.jreleaser") version "1.13.1"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

// You need to use a JDK version with jextract from here
// https://jdk.java.net/jextract/
val jextract = tasks.withType<JextractTask> {
    header("${project.projectDir}/../headers/wgpu.h") {

        // The package under which all source files will be generated
        targetPackage = "io.ygdrasil.wgpu.internal.jvm.panama"

        outputDir = project.objects.directoryProperty()
            .convention(project.layout.projectDirectory.dir("src/jvmMain"))
    }
}



kotlin {

    js {
        binaries.executable()
        browser()
        nodejs()
    }
    jvm {
        withJava()
    }

    sourceSets {

        all {
            languageSettings.optIn("kotlin.ExperimentalStdlibApi")
            languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
            languageSettings.optIn("kotlin.js.ExperimentalJsExport")
        }

        val kotlinWrappersVersion = "1.0.0-pre.721"

        val jsMain by getting {
            dependencies {
                implementation(project.dependencies.platform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-js")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-web")
            }
        }

        val jvmMain by getting {
            dependencies {
                kotlin.srcDirs("src/jvmMain/kotlin", "src/jvmMain/java")
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(libs.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.kotest)
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

val resourcesDirectory = project.file("src").resolve("jvmMain").resolve("resources")
val zipBuildDirectory = project.file("build").resolve("zip")
val baseUrl = "https://github.com/gfx-rs/wgpu-native/releases/download/${libs.versions.wgpu.get()}/"
val fileToDownload = listOf(
    NativeLibrary(
        "wgpu-macos-aarch64-release.zip",
        resourcesDirectory.resolve("darwin-aarch64").resolve("libWGPU.dylib"),
        "libwgpu_native.dylib"
    ),
    NativeLibrary(
        "wgpu-macos-x86_64-release.zip",
        resourcesDirectory.resolve("darwin-x86-64").resolve("libWGPU.dylib"),
        "libwgpu_native.dylib"
    ),
    NativeLibrary(
        "wgpu-windows-x86_64-release.zip",
        resourcesDirectory.resolve("win32-x86-64").resolve("WGPU.dll"),
        "wgpu_native.dll"
    ),
    NativeLibrary(
        "wgpu-linux-x86_64-release.zip",
        resourcesDirectory.resolve("linux-x86-64").resolve("libWGPU.so"),
        "libwgpu_native.so"
    ),
    NativeLibrary(
        "wgpu-linux-aarch64-release.zip",
        resourcesDirectory.resolve("linux-aarch64").resolve("libWGPU.so"),
        "libwgpu_native.so"
    ),
).forEach { (fileName, target, zipFilename) ->
    val zipFile = zipBuildDirectory.resolve(fileName)
    val downloadTask = downloadInto(fileName, zipFile)
    val unzipTask = unzipTask(zipFile, target, zipFilename, downloadTask)

    tasks.withType<ProcessResources>() {
        dependsOn(unzipTask)
    }
}

fun downloadInto(fileName: String, target: File): Task {
    val url = "$baseUrl$fileName"
    val taskName = "downloadFile-$fileName"
    return tasks.register<Download>(taskName) {
        onlyIf { !target.exists() }
        src(url)
        dest(target)
    }.get()
}

fun unzipTask(
    zipFile: File,
    target: File,
    zipFilename: String,
    downloadTask: Task,
) = tasks.register<Copy>("unzip-${zipFile.name}") {
    onlyIf { !target.exists() }
    from(zipTree(zipFile))
    include(zipFilename)
    into(target.parent)
    rename { fileName ->
        fileName.replace(zipFilename, target.name)
    }
    dependsOn(downloadTask)
}.get()

data class NativeLibrary(val remoteFile: String, val targetFile: File, val zipFileName: String)

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
    filter {
        isFailOnNoMatchingTests = false
    }
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
        )
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

jreleaser {
    gitRootSearch = true

    project {
        description = "Webgpu binding to kotlin multiplatform"
        copyright = "MIT"
    }

    signing {
        active.set(Active.ALWAYS)
        armored = true
        artifacts = true
    }
    deploy {
        active.set(Active.ALWAYS)
        maven {
            active.set(Active.ALWAYS)
            mavenCentral {
                active.set(Active.ALWAYS)
                this.create("sonatype") {
                    active.set(Active.ALWAYS)
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository("build/staging-deploy")
                }
            }
        }
    }

    release {
        github {
            skipRelease = true
            skipTag = true
            overwrite = false
            token = "none"
        }
    }
}

publishing {
    repositories {
        maven {
            if ((version as String).contains("SNAPSHOT")) {
                name = "GitLab"
                url = uri("https://gitlab.com/api/v4/projects/25805863/packages/maven")
                credentials(HttpHeaderCredentials::class) {
                    name = "Authorization"
                    value = "Bearer ${System.getenv("GITLAB_TOKEN")}"
                }
                authentication {
                    create<HttpHeaderAuthentication>("header")
                }
            } else {
                url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
            }
        }
    }
}

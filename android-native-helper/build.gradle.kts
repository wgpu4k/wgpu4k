import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id("com.android.library")
}

val libraryName = "native-helper"

kotlin {

    val targets = listOf(
        androidNativeArm64(),
        androidNativeX64()
    )

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    targets.forEach { target ->
        target.binaries {
            sharedLib {
                baseName = libraryName
            }
        }
    }

    sourceSets {
        nativeMain {
            dependencies {
                api(libs.wgpu4k.native)
            }
        }
    }
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        allWarningsAsErrors = true
    }

}

android {
    namespace = "io.ygdrasil.nativeHelper"
    compileSdk = 35

    defaultConfig {
        minSdk = 28
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDirs(jniBasePath().absolutePath)
        }
    }

}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.create("copyJniLibraries") {
    dependsOn("androidNativeArm64Binaries")
    dependsOn("androidNativeX64Binaries")
    doFirst {
        copyJniLibraries()
    }
}

tasks.findByName("assemble")?.dependsOn("copyJniLibraries")

fun copyJniLibraries() {
    val libraryFullName = "libnative_helper.so"
    filesToCopy().forEach { (source, target) ->
        target.mkdirs()
        target.resolve(libraryFullName)
            .also { fileTarget ->
                source.resolve(libraryFullName).copyTo(fileTarget, overwrite = true)
            }
    }
}


fun nativeBasePath() = project.projectDir.resolve("build").resolve("bin")
fun jniBasePath() = nativeBasePath().resolve("libs")

fun filesToCopy() = listOf(
    nativeBasePath().resolve("androidNativeArm64").resolve("releaseShared")
                to jniBasePath().resolve("arm64-v8a"),
        nativeBasePath().resolve("androidNativeX64").resolve("releaseShared")
                to jniBasePath().resolve("x86_64")
)




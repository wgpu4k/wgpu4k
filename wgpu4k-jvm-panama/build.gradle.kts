import io.github.krakowski.jextract.JextractTask

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id("io.github.krakowski.jextract") version "0.5.0" apply false
}

kotlin {

    jvm {
        withJava()
    }

	sourceSets {
        val jvmMain by getting {
            dependencies {
                kotlin.srcDirs("src/jvmMain/kotlin", "src/jvmMain/java")
            }
        }

    }
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

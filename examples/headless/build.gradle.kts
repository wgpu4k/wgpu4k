import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

val commonResourcesFile = getCommonProject()
    .projectDir
    .resolve("src")
    .resolve("commonMain")
    .resolve("resources")

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(22))
	}
}

kotlin {

    js {
        binaries.executable()
        browser()
    }
    jvm {
        // On to make "JavaExec" work, else we got SourceSet with name 'main' not found, see https://youtrack.jetbrains.com/issue/KT-42683
        withJava()
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        mainRun {
            mainClass = "MainKt"
            if (Platform.os == Os.MacOs) {
                listOf(
                    "-XstartOnFirstThread",
                    "--add-opens=java.base/java.lang=ALL-UNNAMED",
                    "--enable-native-access=ALL-UNNAMED"
                )
            } else {
                listOf(
                    "--add-opens=java.base/java.lang=ALL-UNNAMED",
                    "--enable-native-access=ALL-UNNAMED"
                )
            }.let { setArgs(it) }
        }
    }

    sourceSets {

        commonMain {
            dependencies {
				implementation(projects.wgpu4kScenes)
            }
        }

        jsMain {
            resources.setSrcDirs(
                resources.srcDirs + setOf(
                    commonResourcesFile
                )
            )
        }

    }
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        allWarningsAsErrors = true
    }
}

tasks.register<JavaExec>("runApp") {
    mainClass = "MainKt"
    if (Platform.os == Os.MacOs) {
        jvmArgs(
            "-XstartOnFirstThread",
            "--add-opens=java.base/java.lang=ALL-UNNAMED",
            "--enable-native-access=ALL-UNNAMED"
        )
    } else {
        jvmArgs(
            "--add-opens=java.base/java.lang=ALL-UNNAMED",
            "--enable-native-access=ALL-UNNAMED"
        )
    }

    println("sourceSets ${sourceSets["main"]::class}")
    println("sourceSets ${sourceSets.asMap}")
    println("runtimeClasspath ${sourceSets["main"].runtimeClasspath.asIterable()}")
    classpath = sourceSets["main"].runtimeClasspath.asIterable() as FileCollection
}

fun getHeadlessProject() = projects.examples.headless.identityPath.path
    ?.let(::project) ?: error("Could not find project path")


fun getCommonProject() = projects.wgpu4kScenes.identityPath.path
    ?.let(::project) ?: error("Could not find project path")
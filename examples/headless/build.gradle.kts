import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlinMultiplatform)
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
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
				implementation(projects.examples.common)
            }
        }

        val jsMain by getting {
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
    classpath = sourceSets["main"].runtimeClasspath
}

fun getHeadlessProject() = projects.examples.headless.identityPath.path
    ?.let(::project) ?: error("Could not find project path")


fun getCommonProject() = projects.examples.common.identityPath.path
    ?.let(::project) ?: error("Could not find project path")
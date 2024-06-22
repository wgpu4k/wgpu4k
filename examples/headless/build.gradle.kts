import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

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

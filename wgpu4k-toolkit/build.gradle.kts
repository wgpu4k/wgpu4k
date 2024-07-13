
plugins {
    alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.kotest)
	alias(libs.plugins.download)
    `maven-publish`
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
        nodejs()
    }
    jvm()

    wasmJs {
        binaries.executable()
        browser()
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

publishing {
    repositories {
        maven {
            if (isSnapshot()) {
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

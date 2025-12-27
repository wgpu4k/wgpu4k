import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    publish
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

kotlin {

    js {
        binaries.executable()
        browser()
        nodejs()
    }
    jvm()
    androidLibrary {
        namespace = "io.ygdrasil.wgpu4k"
        compileSdk = 36
        minSdk = 28

        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        binaries.executable()
        browser()
    }

    if (Platform.os == Os.MacOs) {
        iosX64()
        iosArm64()
        iosSimulatorArm64()
        macosArm64()
        macosX64()
    }
    linuxArm64()
    linuxX64()
    mingwX64()
    androidNativeArm64()
    androidNativeX64()

    applyDefaultHierarchyTemplate()

    sourceSets {

        all {
            languageSettings.optIn("kotlin.ExperimentalStdlibApi")
            languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
            languageSettings.optIn("kotlin.js.ExperimentalJsExport")
        }

        webMain {
            dependencies {
                implementation(kotlinWrappers.browser)
            }
        }

        commonMain {
            dependencies {
                api(projects.wgpu4k)
                api(libs.webgpu.descriptors)
            }
        }

        val desktopNativeMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                api(libs.glfw.native)
            }
        }


        macosMain.get().dependsOn(desktopNativeMain)
        linuxMain.get().dependsOn(desktopNativeMain)
        mingwMain.get().dependsOn(desktopNativeMain)


        commonTest {
            dependencies {
                implementation(libs.bundles.kotest)
            }
        }

        jvmMain {
            dependencies {
                api(libs.rococoa)
                api(libs.jnaPlatform)
                api(libs.jna)
                implementation(libs.wgpu4k.native)
                val lwjglVersion = "3.3.3"
                api("org.lwjgl:lwjgl:$lwjglVersion")
                api("org.lwjgl:lwjgl-glfw:$lwjglVersion")
                listOf(
                    "natives-windows",
                    "natives-macos",
                    "natives-macos-arm64",
                    "natives-linux",
                    "natives-linux-arm64"
                ).forEach { dependencyType ->
                    runtimeOnly("org.lwjgl:lwjgl:$lwjglVersion:$dependencyType")
                    runtimeOnly("org.lwjgl:lwjgl-glfw:$lwjglVersion:$dependencyType")
                }
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.kotest.runner.junit5)
            }

        }


        androidMain {
            dependencies {
                implementation(libs.android.native.helper)
            }
        }
    }

    compilerOptions {
        //allWarningsAsErrors = true
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

val File.isNotEmpty: Boolean
    get() = this.listFiles()?.isNotEmpty() ?: false
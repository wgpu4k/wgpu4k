import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinHierarchyTemplate
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    if (isAndroidConfigured) android
    publish
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
    if (isAndroidConfigured) androidTarget {
        publishLibraryVariants("release", "debug")
    }

    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        binaries.executable()
        browser()
    }

    macosArm64()
    macosX64()
    linuxArm64()
    linuxX64()
    configureMingwX64(project)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyHierarchyTemplate(
        KotlinHierarchyTemplate {
            /* natural hierarchy is only applied to default 'main'/'test' compilations (by default) */
            withSourceSetTree(KotlinSourceSetTree.main, KotlinSourceSetTree.test)

            common {
                /* All compilations shall be added to the common group by default */
                withCompilations { true }

                group("ios") {
                    withIos()
                }

                group("desktopNative") {

                    group("macos") {
                        withMacos()
                    }

                    group("linux") {
                        withLinux()
                    }

                    group("mingw") {
                        withMingw()
                    }

                }

                group("commonWeb") {
                    withJs()
                    withWasmJs()
                }
            }
        }
    )

    sourceSets {

        all {
            languageSettings.optIn("kotlin.ExperimentalStdlibApi")
            languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
            languageSettings.optIn("kotlin.js.ExperimentalJsExport")
        }

        commonMain {
            dependencies {
                api(projects.wgpu4k)
                api(libs.webgpu.descriptors)
            }
        }

        val desktopNativeMain by getting {
            dependencies {
                api(libs.glfw.native)
            }
        }


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

        if (isAndroidConfigured) {
            androidMain {
                dependencies {
                    implementation(libs.android.native.helper)
                }
            }
        }
    }

    compilerOptions {
        allWarningsAsErrors = true
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}


fun getCommonProject() = projects.wgpu4kScenes.identityPath.path
    ?.let(::project) ?: error("Could not find project path")

val File.isNotEmpty: Boolean
    get() = this.listFiles()?.isNotEmpty() ?: false
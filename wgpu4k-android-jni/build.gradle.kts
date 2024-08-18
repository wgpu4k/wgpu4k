import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

kotlin {

    val targets = listOf(
        androidNativeArm64(),
        androidNativeX64()
    )

    targets.forEach { target ->
        target.binaries {
            sharedLib {
                baseName = "wgpu4kv2"
                export(projects.wgpu4kNative)
            }
        }
    }

    sourceSets {

        all {
            languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
        }

        nativeMain {
            dependencies {
                api(projects.wgpu4kNative)
            }
        }
    }
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        allWarningsAsErrors = true
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

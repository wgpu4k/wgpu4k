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
                baseName = "libwgpu4kv2"
            }
        }
    }

    sourceSets {

        all {
            languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
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

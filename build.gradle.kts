plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
}

allprojects {
	group = "io.ygdrasil"
	version = System.getenv("VERSION")?.takeIf { it.isNotBlank() } ?: "0.1.1-SNAPSHOT"

    /*rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
        rootProject.the<YarnRootExtension>().yarnLockMismatchReport =
            YarnLockMismatchReport.WARNING
        rootProject.the<YarnRootExtension>().reportNewYarnLock = false
        rootProject.the<YarnRootExtension>().yarnLockAutoReplace = false
    }*/
}

configurations.all {
    // Never cache changing modules (snapshots)
    resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)

    // Also disable caching for dynamic versions
    resolutionStrategy.cacheDynamicVersionsFor(0, TimeUnit.SECONDS)
}
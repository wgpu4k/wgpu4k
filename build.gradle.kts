
plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
}

allprojects {
	group = "io.ygdrasil"
	version = System.getenv("VERSION")?.takeIf { it.isNotBlank() } ?: "0.1.1-SNAPSHOT"
}

configurations.all {
    // Never cache changing modules (snapshots)
    resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)

    // Also disable caching for dynamic versions
    resolutionStrategy.cacheDynamicVersionsFor(0, TimeUnit.SECONDS)
}
allprojects {
	group = "io.ygdrasil"
	version = (findProperty("releaseVersion") as? String)
		?.takeIf { it.isNotBlank() }
		?: "0.2.0-SNAPSHOT"
}

configurations.all {
    // Never cache changing modules (snapshots)
    resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)

    // Also disable caching for dynamic versions
    resolutionStrategy.cacheDynamicVersionsFor(0, TimeUnit.SECONDS)
}

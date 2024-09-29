
allprojects {
	group = "io.ygdrasil"
	version = System.getenv("VERSION")?.takeIf { it.isNotBlank() } ?: "0.0.0-SNAPSHOT"
}



// TODO: remove later
configurations.all {
	resolutionStrategy {
		cacheChangingModulesFor(0, "seconds")
		cacheDynamicVersionsFor(0, "minutes")
	}
}
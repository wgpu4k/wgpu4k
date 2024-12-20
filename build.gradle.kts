
allprojects {
	group = "io.ygdrasil"
	version = System.getenv("VERSION")?.takeIf { it.isNotBlank() } ?: "0.0.0-SNAPSHOT"
}



// TODO: remove later
configurations.all {
	resolutionStrategy {
		cacheChangingModulesFor(1, "seconds")
		cacheDynamicVersionsFor(1, "seconds")
	}
}

allprojects {
	group = "io.ygdrasil"
	version = System.getenv("VERSION")?.takeIf { it.isNotBlank() } ?: "0.1.0-SNAPSHOT"
}

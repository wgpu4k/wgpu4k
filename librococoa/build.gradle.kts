import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths

plugins {
	kotlin("jvm")
}

repositories {
	maven {
		url = uri("http://repo.maven.cyberduck.io.s3.amazonaws.com/releases")
		isAllowInsecureProtocol = true
	}
}

dependencies {
	api("org.rococoa:rococoa-core:0.9.1")
	implementation(libs.jna)
	implementation(libs.jnaPlatform)
	implementation("org.apache.logging.log4j:log4j-api:2.20.0")
	implementation("org.apache.logging.log4j:log4j-core:2.20.0")
	implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.20.0")
}


abstract class DownloadTask : DefaultTask() {
	@TaskAction
	fun run() {
		val remoteLibrary =
			URI("http://repo.maven.cyberduck.io.s3.amazonaws.com/releases/org/rococoa/librococoa/0.9.1/librococoa-0.9.1.dylib")
		val url = remoteLibrary.toURL()

		val path = project.projectDir
			.resolve("src").resolve("main").resolve("resources").resolve("darwin")
			.also { it.mkdirs() }
			.resolve("librococoa.dylib")

		if (path.exists().not()) {
			url.openStream().use { input ->
				Files.copy(input, Paths.get(path.absolutePath))
			}
		}
	}
}


val copyFileFromDependencyTask = tasks.register<DownloadTask>("copyFileFromUrl")


tasks.named("processResources") {
	dependsOn(copyFileFromDependencyTask)
}

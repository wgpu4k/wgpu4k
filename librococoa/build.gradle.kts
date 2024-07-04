import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths

plugins {
	kotlin("jvm")
	`maven-publish`
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

kotlin {
	compilerOptions {
		//allWarningsAsErrors = true
	}
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


publishing {
	publications {
		create<MavenPublication>("library") {
			from(components["java"])
		}
	}

	repositories {
		maven {
			if ((version as String).contains("SNAPSHOT")) {
				name = "GitLab"
				url = uri("https://gitlab.com/api/v4/projects/25805863/packages/maven")
				credentials(HttpHeaderCredentials::class) {
					name = "Authorization"
					value = "Bearer ${System.getenv("GITLAB_TOKEN")}"
				}
				authentication {
					create<HttpHeaderAuthentication>("header")
				}
			} else {
				url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
			}
		}
	}
}

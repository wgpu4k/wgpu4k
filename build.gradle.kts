
plugins {
	alias(libs.plugins.kotlinMultiplatform).apply(false)
}


allprojects {

    repositories {
		mavenLocal()
        mavenCentral()
		// Use by rococoa
		maven {
			url = uri("http://repo.maven.cyberduck.io.s3.amazonaws.com/releases")
			isAllowInsecureProtocol = true
		}
    }

	group = "io.ygdrasil"
	version = "1.0.0-SNAPSHOT"
}



import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.compose)
}

repositories {
	mavenCentral()
	maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	google()
	maven {
		url = uri("http://repo.maven.cyberduck.io.s3.amazonaws.com/releases")
		isAllowInsecureProtocol = true
	}
}

kotlin {
	jvm()

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(project(":examples:common"))
			}
		}

		val jvmMain by getting {
			dependencies {
				implementation(compose.desktop.currentOs)
				implementation(project(":librococoa"))

			}
		}
	}
}

compose.desktop {
	application {
		mainClass = "MainKt"

		jvmArgs += "--add-opens=java.base/java.lang=ALL-UNNAMED"

		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			packageName = "compose"
			packageVersion = "1.0.0"
		}
	}
}

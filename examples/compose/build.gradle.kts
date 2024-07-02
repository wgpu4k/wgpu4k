import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
	kotlin("jvm")
	alias(libs.plugins.compose.compiler)
	alias(libs.plugins.compose)
}

repositories {
	mavenCentral()
	maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	google()
}

dependencies {
	implementation(compose.desktop.currentOs)
	implementation(projects.examples.common)
}

compose.desktop {
	application {
        mainClass = "MainKt"

		jvmArgs += "--add-opens=java.base/java.lang=ALL-UNNAMED"

		if (Platform.os == Os.MacOs) {
			jvmArgs.add("-XstartOnFirstThread")
		}

		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			packageName = "wgpu-X-compose"
			packageVersion = "1.0.0"
		}

		buildTypes.release.proguard {
			isEnabled = false
		}
	}
}
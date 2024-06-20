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

val isOnMac = arrayOf("Mac OS X", "Darwin").any { System.getProperty("os.name").startsWith(it) }

dependencies {
	implementation(compose.desktop.currentOs)
	implementation(projects.examples.common)
}

compose.desktop {
	application {
        mainClass = "io.ygdrasil.wgpu.examples.GlfwMainKt"

		jvmArgs += "--add-opens=java.base/java.lang=ALL-UNNAMED"

		if (isOnMac) {
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
plugins {
	alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
	js {
		binaries.executable()
		browser()
		generateTypeScriptDefinitions()
	}
	jvm()


	sourceSets {
		val commonMain by getting {
			dependencies {
				api(project(":wgpu4k"))
				api(libs.coroutines)
				api("com.soywiz.korge:korge-foundation:5.4.0")
			}
		}

		val jvmMain by getting {
			dependencies {
				api(project(":librococoa"))
			}
		}
	}
}
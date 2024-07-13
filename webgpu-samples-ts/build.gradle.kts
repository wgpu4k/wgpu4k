plugins {
	id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

kotlin {
	js {
		binaries.executable()
		browser()
		nodejs()
		generateTypeScriptDefinitions()
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(projects.examples.webJs)
			}
		}

	}
}


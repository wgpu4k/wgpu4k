rootProject.name = "wgpu4k-buildsrc"

dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			from(files("../gradle/libs.versions.toml"))
		}
	}
}
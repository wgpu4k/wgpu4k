plugins {
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.dokka")
}

val libraryDescription = "Webgpu binding to kotlin multiplatform"

val isPublishing = project.findProperty("signingInMemoryKey")?.toString()?.isNotBlank() == true ||
    project.findProperty("signing.keyId")?.toString()?.isNotBlank() == true

mavenPublishing {
    if (isPublishing) {
        publishToMavenCentral()
        signAllPublications()
    }

    coordinates(group.toString(), project.name, version.toString())

    pom {
        name.set(project.name)
        description.set(libraryDescription)
        url.set("https://github.com/wgpu4k/wgpu4k")
        inceptionYear.set("2025")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/license/MIT")
            }
        }

        developers {
            developer {
                id.set("amommers")
                name.set("Alexandre Mommers")
            }
        }

        scm {
            connection.set("scm:git:https://github.com/wgpu4k/wgpu4k.git")
            developerConnection.set("scm:git:https://github.com/wgpu4k/wgpu4k.git")
            url.set("https://github.com/wgpu4k/wgpu4k")
        }
    }
}

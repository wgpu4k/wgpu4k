import org.jreleaser.model.Active

plugins {
    `maven-publish`
    id("org.jreleaser")
}


val libraryDescription = "Webgpu binding to kotlin multiplatform"

jreleaser {
    gitRootSearch = true

    project {
        description = libraryDescription
        copyright = "MIT"
    }

    signing {
        active.set(Active.ALWAYS)
        armored = true
        artifacts = true
    }
    deploy {
        active.set(Active.ALWAYS)
        maven {
            active.set(Active.ALWAYS)
            mavenCentral {
                active.set(Active.ALWAYS)
                this.create("sonatype") {
                    active.set(Active.ALWAYS)
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository("build/staging-deploy")
                }
            }
        }
    }

    release {
        github {
            skipRelease = true
            skipTag = true
            overwrite = false
            token = "none"
        }
    }
}

publishing {
    publications {
        withType<MavenPublication> {
            pom {
                name.set(project.name)
                description.set(libraryDescription)
                url.set("https://github.com/wgpu4k/wgpu4k")
                inceptionYear.set("2024")
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
    }

    repositories {
        maven {
            if (isSnapshot()) {
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

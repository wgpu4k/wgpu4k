import publish.PublishingType
import publish.centralPortalPublish

plugins {
    `maven-publish`
    id("org.jreleaser")
    signing
    id("org.jetbrains.dokka")
}


val libraryDescription = "Webgpu binding to kotlin multiplatform"

val signingKey = System.getenv("JRELEASER_GPG_SECRET_KEY")
val signingPassword = System.getenv("JRELEASER_GPG_PASSPHRASE")

if (!isSnapshot()) {
    signing {
        useInMemoryPgpKeys(signingKey, signingPassword)
        sign(publishing.publications)
    }
}

project.centralPortalPublish {
    username = System.getenv("JRELEASER_MAVENCENTRAL_USERNAME")
    password = System.getenv("JRELEASER_MAVENCENTRAL_PASSWORD")
    publishingType = PublishingType.USER_MANAGED
    url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    from(tasks.findByName("dokkaHtml"))
}

publishing {
    publications {
        withType<MavenPublication> {
            artifact(javadocJar)
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
    }


    repositories {
        maven {
            if (isSnapshot()) {
                logger.info("publishing is configure as snapshot")
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
                name = "Local"
                logger.info("publishing is configure as release")
                url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
                logger.info("publishing path is ${url.path}")
            }
        }
    }
}

if (!isSnapshot()) {
    val signingTasks = tasks.withType<Sign>()
    tasks.withType<AbstractPublishToMaven>().configureEach {
        dependsOn(signingTasks)
    }
}
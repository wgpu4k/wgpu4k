plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

val commonResourcesFile = getCommonProject()
    .projectDir
    .resolve("src")
    .resolve("commonMain")
    .resolve("resources")

//assert(commonResourcesFile.isDirectory) { "$commonResourcesFile is not a directory" }
//assert(commonResourcesFile.isNotEmpty) { "$commonResourcesFile is empty" }

val resourcesDirectory = project.file("src").resolve("jvmMain").resolve("resources")

val buildNativeResourcesDirectory = project.file("build").resolve("native")

kotlin {

    iosX64 {
        binaries {
            executable {
                // Binary configuration.
            }
        }
    }
    iosArm64 {
        binaries {
            executable {
                // Binary configuration.
            }
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(projects.wgpu4k)
            }
        }

        /*val iosMain by getting {
            resources.setSrcDirs(
                resources.srcDirs + setOf(
                    commonResourcesFile
                )
            )
        }*/

    }
}

fun getCommonProject() = projects.examples.common.identityPath.path
    ?.let(::project) ?: error("Could not find project path")
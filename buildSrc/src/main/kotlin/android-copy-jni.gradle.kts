gradle.projectsLoaded {
    val targetProject = rootProject.allprojects.find { it.name == "wgpu4k-android-jni" }
    fun get4kAndroidJniProject() = targetProject ?: error("Could not find project path")

    val jniLibsPath = project.file("src")
        .resolve("androidMain")
        .resolve("libs")

    val jniBuildPath = get4kAndroidJniProject()
        .projectDir
        .resolve("build")
        .resolve("bin")

    val libraryName = "libwgpu4kv2.so"

    val fileToCopy = listOf(
        jniBuildPath.resolve("androidNativeArm64").resolve("releaseShared")
                to jniLibsPath.resolve("arm64-v8a"),
        jniBuildPath.resolve("androidNativeX64").resolve("releaseShared")
                to jniLibsPath.resolve("x86_64"),
    )

    fun copyJniLibraries() {
        fileToCopy.forEach { (source, target) ->
            target.mkdirs()
            target.resolve(libraryName)
                .also { fileTarget ->
                    source.resolve(libraryName).copyTo(fileTarget, overwrite = true)
                }
        }
    }

    tasks.findByName("build")?.apply {
        dependsOn(":wgpu4k-android-jni:build")
        doFirst {
            copyJniLibraries()
        }
    }

    tasks.create("copyJniLibraries") {
        dependsOn("build")
        doFirst {
            copyJniLibraries()
        }
    }
}
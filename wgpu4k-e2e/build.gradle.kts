plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

val commonResourcesFile = getCommonProject()
    .projectDir
    .resolve("src")
    .resolve("commonMain")
    .resolve("resources")

kotlin {

    jvm {
        withJava()
    }

    js {
        binaries.executable()
        browser()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.wgpu4kScenes)
            }
        }

        jsMain {
            resources.setSrcDirs(
                resources.srcDirs + setOf(
                    commonResourcesFile
                )
            )
        }
    }
}

val jvmTasks = scenes.flatMap { (sceneName, frames) ->
    frames.map { frame ->
        tasks.register<JavaExec>("e2eJvmTest-$sceneName-$frame") {
            group = "e2eTest"
            // TODO: find why the app is crashing sometimes
            isIgnoreExitValue = true
            mainClass = "MainKt"
            jvmArgs(
                if (Platform.os == Os.MacOs) {
                    listOf(
                        "-XstartOnFirstThread",
                        "--add-opens=java.base/java.lang=ALL-UNNAMED",
                        "--enable-native-access=ALL-UNNAMED"
                    )
                } else {
                    listOf(
                        "--add-opens=java.base/java.lang=ALL-UNNAMED",
                        "--enable-native-access=ALL-UNNAMED"
                    )
                } + listOf(
                    "-Dscene=$sceneName",
                    "-Dframe=$frame",
                    "-DscreenshotPath=${project.projectDir}",
                )
            )
            classpath = sourceSets["main"].runtimeClasspath
        }
    }
}

val jvmTest = tasks.create("e2eJvmTest") {
    group = "e2eTest"
    jvmTasks.forEach { tasks -> dependsOn(tasks) }
}

val e2eBrowserTest = tasks.create("e2eBrowserTest") {
    group = "e2eTest"
    doLast {
        val server = endToEndWebserver(project.projectDir, logger)
        browser(project.projectDir, logger)
        server.stop()

    }
}


val e2eCompareImages = tasks.create("e2eCompareImages") {
    group = "e2eTest"
    doLast {
        val result = compareImages(project.projectDir, logger)
            .filter { !it.similar }
        if (result.isNotEmpty()) error("Not similar tests found: ${result.joinToString()}")
    }
}


tasks.create("e2eTest") {
    group = "e2eTest"
    if(isInCI().not()) dependsOn(e2eBrowserTest)
    dependsOn(jvmTest)
    finalizedBy(e2eCompareImages)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

fun getCommonProject() = projects.wgpu4kScenes.identityPath.path
    ?.let(::project) ?: error("Could not find project path")

fun isInCI(): Boolean = System.getenv("CI") != null
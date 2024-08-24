plugins {
    kotlin("jvm")
}

dependencies {
    implementation(projects.examples.headless)
}

val jvmTasks = scenes.flatMap { (sceneName, frames) ->
    frames.map { frame ->
        tasks.register<JavaExec>("e2eJvmTest-$sceneName-$frame") {
            group = "e2eTest"
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
        val server = endToEndWebserver(getHeadlessProject().projectDir)
        browser(project.projectDir, logger)
        server.stop()

    }
}


tasks.create("e2eTest") {
    group = "e2eTest"
    dependsOn(e2eBrowserTest)
    dependsOn(jvmTest)

    doLast {
        logger.info("Starting e2e test...")
        val result = compareImages(project   .projectDir, logger)
            .filter { !it.similar }
        if (result.isNotEmpty()) error("Not similar tests found: ${result.joinToString()}")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}


fun getHeadlessProject() = projects.examples.headless.identityPath.path
    ?.let(::project) ?: error("Could not find project path")
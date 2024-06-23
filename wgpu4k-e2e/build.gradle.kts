plugins {
    kotlin("jvm")
}

dependencies {
    implementation(projects.examples.headless)
}

tasks.register<JavaExec>("runJvmE2e") {
    mainClass = "MainKt"
    if (Platform.os == Os.MacOs) {
        jvmArgs(
            "-XstartOnFirstThread",
            "--add-opens=java.base/java.lang=ALL-UNNAMED",
            "--enable-native-access=ALL-UNNAMED"
        )
    } else {
        jvmArgs(
            "--add-opens=java.base/java.lang=ALL-UNNAMED",
            "--enable-native-access=ALL-UNNAMED"
        )
    }
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.create("runE2eTest") {
    doLast {
        val server = endToEndWebserver(getHeadlessProject().projectDir.absolutePath)
        browser(project.projectDir)
        server.stop()

    }
}

fun getHeadlessProject() = projects.examples.headless.identityPath.path
    ?.let(::project) ?: error("Could not find project path")
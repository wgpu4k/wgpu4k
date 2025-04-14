import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

val commonResourcesFile = getCommonProject()
    .projectDir
    .resolve("src")
    .resolve("commonMain")
    .resolve("resources")

val jsPagePath = project.projectDir.resolve("build")
    .resolve("dist")
    .resolve("js")
    .resolve("productionExecutable")

val wasmPagePath = project.projectDir.resolve("build")
    .resolve("dist")
    .resolve("wasmJs")
    .resolve("productionExecutable")

kotlin {

    jvm {
    }

    js {
        binaries.executable()
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        binaries.executable()
        browser()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.wgpu4kScenes)
            }
        }

        wasmJsMain {
            resources.setSrcDirs(
                resources.srcDirs + setOf(
                    commonResourcesFile
                )
            )
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
            classpath = sourceSets["jvmMain"].runtimeClasspath
        }
    }
}

val jvmTest = tasks.register("e2eJvmTest") {
    group = "e2eTest"
    jvmTasks.forEach { tasks -> dependsOn(tasks) }
}

val e2eJsBrowserTest = tasks.register("e2eJsBrowserTest") {
    group = "e2eTest"
    doLast {
        val prefixPath = "js"
        project.projectDir.resolve("$prefixPath-chromium").mkdir()
        val server = endToEndWebserver(jsPagePath, logger)
        browser(project.projectDir, logger, prefixPath)
        server.stop()

    }
}

val e2eWasmBrowserTest = tasks.register("e2eWasmBrowserTest") {
    group = "e2eTest"
    doLast {
        val prefixPath = "wasm"
        project.projectDir.resolve("$prefixPath-chromium").mkdir()
        val server = endToEndWebserver(wasmPagePath, logger)
        browser(project.projectDir, logger, prefixPath)
        server.stop()

    }
}

val e2eCompareImages = tasks.register("e2eCompareImages") {
    group = "e2eTest"
    doLast {
        project.projectDir.resolve("jvm").mkdir()
        val result = compareImages(project.projectDir, logger)
            .filter { !it.similar }
        if (result.isNotEmpty()) error("Not similar tests found: ${result.joinToString()}")
    }
}

tasks.register("e2eTest") {
    group = "e2eTest"
    if(isInCI().not()) dependsOn(e2eJsBrowserTest)
    // uncomment when wasm is stable
    // if(isInCI().not()) dependsOn(e2eWasmBrowserTest)
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
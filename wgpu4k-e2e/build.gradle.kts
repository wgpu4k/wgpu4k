import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

val scenes = listOf(
    "HelloTriangleScene" to listOf(0),
    "HelloTriangleMSAAScene" to listOf(0),
    "HelloTriangleRotatingScene" to listOf(0, 10, 50, 100),
    "RotatingCubeScene" to listOf(0, 10, 50, 100),
    "TwoCubesScene" to listOf(0, 10, 50, 100),
    "CubemapScene" to listOf(0, 10, 50, 100),
    "InstancedCubeScene" to listOf(0, 10, 50, 100),
    "TexturedCubeScene" to listOf(0, 10, 50, 100),
)

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

    jvm()

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

        jvmMain {
            dependencies {
                implementation(libs.ktor.server.netty.jvm)
                implementation(libs.playwright)
                implementation(libs.ktor.server.core.jvm)
            }
        }
    }
}

val jvmTasks = scenes.flatMap { (sceneName, frames) ->
    frames.map { frame ->
        tasks.register<JavaExec>("e2eJvmTest-$sceneName-$frame") {
            group = "e2eTest"
            // TODO: find why the app is crashing sometimes
            isIgnoreExitValue = true
            mainClass = "JvmMainKt"
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

val e2eJsBrowserTest = tasks.register<JavaExec>("e2eJsBrowserTest") {
    val projectDir = project.projectDir
    group = "e2eTest"
    mainClass = "JsMainKt"
    jvmArgs(
        listOf(
            "-DprojectDir=${projectDir.absolutePath}",
            "-DjsPagePath=${jsPagePath.absolutePath}",
            "-DprefixPath=js"
        )
    )
    classpath = sourceSets["jvmMain"].runtimeClasspath
}


val e2eWasmBrowserTest = tasks.register<JavaExec>("e2eWasmBrowserTest") {
    val projectDir = project.projectDir
    group = "e2eTest"
    mainClass = "JsMainKt"
    jvmArgs(
        listOf(
            "-DprojectDir=${projectDir.absolutePath}",
            "-DjsPagePath=${jsPagePath.absolutePath}",
            "-DprefixPath=wasm"
        )
    )
    classpath = sourceSets["jvmMain"].runtimeClasspath
}

val e2eCompareImages = tasks.register("e2eCompareImages") {
    val projectDir = project.projectDir
    projectDir.resolve("jvm").mkdir()
    group = "e2eTest"
    doLast {
        val result = compareImages(projectDir, logger)
            .filter { !it.similar }
        if (result.isNotEmpty()) error("Not similar tests found: ${result.joinToString()}")
    }
}

tasks.register("e2eTest") {
    group = "e2eTest"
    if(isInCI().not()) dependsOn(e2eJsBrowserTest)
    // uncomment when wasm is stable
    // if(isInCI().not())
    dependsOn(e2eWasmBrowserTest)
    dependsOn(jvmTest)
    finalizedBy(e2eCompareImages)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

fun getCommonProject() = projects
    .wgpu4kScenes
    .path
    .let(::project)

fun isInCI(): Boolean = System.getenv("CI") != null
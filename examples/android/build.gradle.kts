    plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.compose.compiler)
}

val commonResourcesFile = getCommonProject()
    .projectDir
    .resolve("src")
    .resolve("commonMain")
    .resolve("resources")

assert(commonResourcesFile.isDirectory) { "$commonResourcesFile is not a directory" }
assert(commonResourcesFile.isNotEmpty) { "$commonResourcesFile is empty" }

android {
    compileSdk = 36

    defaultConfig {
        applicationId = "io.ygdrasil.webgpu"

        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    sourceSets {
        getByName("main") {
            assets.srcDirs(commonResourcesFile.absolutePath)
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_22
        targetCompatibility = JavaVersion.VERSION_22
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,INDEX.LIST}"
        }
    }
    namespace = "io.ygdrasil.webgpu"


    lint {
        disable += "MutableCollectionMutableState"
    }
}

dependencies {
    implementation(projects.wgpu4kScenes)
    implementation("androidx.activity:activity-compose:1.10.1")
}

fun getCommonProject() = projects
    .wgpu4kScenes
    .path
    .let(::project)

val File.isNotEmpty: Boolean
    get() = this.listFiles()?.isNotEmpty() ?: false
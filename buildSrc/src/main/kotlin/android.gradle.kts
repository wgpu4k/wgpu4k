plugins {
    id("com.android.library")
}

val jniLibsPath = project.file("src")
    .resolve("androidMain")
    .resolve("libs")
    .absolutePath

android {
    namespace = "io.ygdrasil.wgpu4k"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
    }


    sourceSets {
        getByName("main") {
            jniLibs.srcDirs(jniLibsPath)
        }
    }

}

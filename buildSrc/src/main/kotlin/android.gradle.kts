plugins {
    id("com.android.library")
}

android {
    namespace = "io.ygdrasil.wgpu4k"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("libs")
        }

    }
}

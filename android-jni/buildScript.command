#!/bin/sh
JNI_LIBS=./android/app/src/main/jniLibs
cargo ndk --bindgen  --platform 26 --target aarch64-linux-android  build --release
cargo ndk --bindgen  --platform 26 --target armv7-linux-androideabi  build --release
cargo ndk --bindgen  --platform 26 --target i686-linux-android   build --release
rm -rf $JNI_LIBS
mkdir $JNI_LIBS
mkdir $JNI_LIBS/arm64-v8a
mkdir $JNI_LIBS/armeabi-v7a
mkdir $JNI_LIBS/x86
cp target/aarch64-linux-android/release/librust.so $JNI_LIBS/arm64-v8a/librust.so
cp target/armv7-linux-androideabi/release/librust.so $JNI_LIBS/armeabi-v7a/librust.so
cp target/i686-linux-android/release/librust.so $JNI_LIBS/x86/librust.so

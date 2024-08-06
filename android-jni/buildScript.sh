#!/bin/sh
JNI_LIBS=./android/app/src/main/jniLibs
cargo ndk --bindgen  --platform 26 --target aarch64-linux-android  build --release
cargo ndk --bindgen  --platform 26 --target x86_64-linux-android  build --release
#cargo ndk --bindgen  --platform 26 --target armv7-linux-androideabi  build --release
#cargo ndk --bindgen  --platform 26 --target i686-linux-android   build --release
rm -rf $JNI_LIBS
mkdir $JNI_LIBS
mkdir $JNI_LIBS/arm64-v8a
#mkdir $JNI_LIBS/armeabi-v7a
#mkdir $JNI_LIBS/x86
mkdir $JNI_LIBS/x86_64
cp target/aarch64-linux-android/release/libwgpu4k.so $JNI_LIBS/arm64-v8a/libwgpu4k.so
cp target/x86_64-linux-android/release/libwgpu4k.so $JNI_LIBS/x86_64/libwgpu4k.so
#cp target/armv7-linux-androideabi/release/libwgpu4k.so $JNI_LIBS/armeabi-v7a/libwgpu4k.so
#cp target/i686-linux-android/release/libwgpu4k.so $JNI_LIBS/x86/libwgpu4k.so

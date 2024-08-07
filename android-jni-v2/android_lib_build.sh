#!/usr/bin/env bash

# Stop subsequent execution when encountering any errors
set -e

RELEASE_MODE=${1}
LIB_FOLDER="debug"

#cargo clean

echo "build to Android target"
cd wgpu-in-app
if [ "${RELEASE_MODE}" = "--release" ]; then
    LIB_FOLDER="release"
    cargo so b --lib --target aarch64-linux-android ${RELEASE_MODE}
else
    RUST_BACKTRACE=full RUST_LOG=wgpu_hal=debug cargo so b --lib --target aarch64-linux-android
fi

echo "copy .so files to jniLibs folder"
cd ../
ARM64="Android/app/libs/arm64-v8a"
ARMv7a="Android/app/libs/armeabi-v7a"

if [ ! -d "$ARM64" ]; then
    mkdir -p "$ARM64"
fi
if [ ! -d "$ARMv7a" ]; then
    mkdir -p "$ARMv7a"
fi

cp target/aarch64-linux-android/${LIB_FOLDER}/libwgpu_in_app.so "${ARM64}/libwgpu_in_app.so"
# cp target/armv7-linux-androideabi/debug/libwgpu_in_app.so "${ARMv7a}/libwgpu_in_app.so"

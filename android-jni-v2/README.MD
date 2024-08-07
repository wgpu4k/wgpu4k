## **Android**

### Set up Android environment

Assuming your computer already has Android Studio installed, go to `Android Studio` > `Tools` > `SDK Manager` > `Android SDK` > `SDK Tools`. Check the following options for installation and click OK.

- [x] Android SDK Build-Tools
- [x] Android SDK Command-line Tools
- [x] NDK(Side by side)

Then, set two following environment variables:

```sh
export ANDROID_SDK_ROOT=$HOME/Library/Android/sdk
# Replace the NDK version number with the version you installed
export NDK_HOME=$ANDROID_SDK_ROOT/ndk/23.1.7779620
```

### Install cargo so subcommand

```sh
# from crates.io
cargo install cargo-so
# frome source code
cargo install --path cargo-so
```

### Build

```sh
# Add build targets
# Since simulator and virtual devices only support GLES, `x86_64-linux-android` and `i686-linux-android` targets are not necessary
rustup target add aarch64-linux-android armv7-linux-androideabi

# Build
sh ./android_lib_build.sh --release
```
# This is a Docker Linux toolchain used for testing on macOS

for example to run e2e test use :
cd /app; xvfb-run ./gradlew :wgpu4k-e2e:e2eJvmTest
cd /app; wayland-run ./gradlew :wgpu4k-e2e:e2eJvmTest


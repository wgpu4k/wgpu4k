# WGPU4K: A WebGPU Binding for Kotlin Multi-platform
[![Tests](https://github.com/wgpu4k/wgpu4k/actions/workflows/test.yml/badge.svg?branch=main)](https://github.com/wgpu4k/wgpu4k/actions/workflows/test.yml)
![Static Badge](https://img.shields.io/badge/Status-Alpha-red?style=plastic)
![Static Badge](https://img.shields.io/badge/Latest%20version-preview_2-red?style=plastic)
[![Static Badge](https://img.shields.io/badge/Licence-MIT-blue?style=plastic)](https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FMIT_License)
[![Static Badge](https://img.shields.io/badge/Discord-wgpu4k-purple?style=plastic)](https://discord.gg/qy9KQAP9Kc)

This project focuses on creating a binding for WebGPU which can support Kotlin on multiple platforms.

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [How to run the demo projects from library sources](#how-to-run-the-demo-projects-from-library-sources)
3. [Getting started](#getting-started)
4. [Compatibility](#compatibility)
5. [Backend](#backend)
6. [Project Phases](#project-phases)
   1. [Proof of Concept](#proof-of-concept)
   2. [API Implementation](#api-implementation)
   3. [API Refinement](#api-refinement)
   4. [Production](#production)

## Prerequisites

- Gradle 8.8
- JDK 22+
- A recent version of Chrome or Firefox Nightly for web browser execution. Check compatibility [here][chart].

## How to run the demo projects from library sources

```
git clone https://github.com/wgpu4k/wgpu4k.git
cd wgpu4k
```
- On desktop JVM: `./gradlew examples:glfw:run`
- On web js: `./gradlew examples:web-js:jsBrowserRun`
- On web wasm: `./gradlew examples:web-js:wasmJsBrowserRun`
- On desktop native (Mac only and still experimental) `./gradlew examples:native:runDebugExecutableNative`
- On android (experimental) `./gradlew wgpu4k:build` to build the JNI library, then you can run the subproject `android` with android studio ![android-studio-capture.png](android-studio-capture.png)

Use `page down` and `page up` on your keyboard to switch scene.

## Getting started

Documentation will be added later, to start you can check out the minimalist project [here][hello-cube] or see scenes implemented to end-to-end tests [here][scenes].

## Compatibility

| Target       | Windows | Linux | MacOs | iOS | Android |
|--------------|---------|-------|-------|-----|---------|
| JVM x64      | ✅       | 🆗    | 🆗    | ❌   | ❌       |
| JVM arm64    | 🔍️     | 🆗    | ✅     | ❌   | ❌       |
| JS           | ✅       | 🆗    | ✅     | ❓   | ❓️      |
| wasm         | 🆗️     | 🆗️   | 🆗️   | ❓️  | ❓️      |
| native x64   | 🛠️     | 🔍️   | 🛠️   | 🛠️ | 🛠️️    |
| native arm64 | 🔍️     | 🔍️   | 🛠️   | 🛠️ | 🛠️️️   |


✅ = First class citizen

🆗 = Best effort

❓ = Untested, could work

🛠️ = Work in progress

🔍 = Unsupported yet, open to contributions

❌ = Not applicable 

## Backend

On JVM we are using the firefox backend, see the [GitHub page][link] to get more information.

## Project Phases

### Proof of Concept

The current one, the aim of this phase is to test the technology on as many platforms as possible using elementary examples.

### API Implementation

The entire API is implemented in this phase.

### API Refinement

In this phase, the API is fine-tuned to be more idiomatic to Kotlin.

### Production

This is the final phase when the project is ready for production.

<!-- Reference Links -->

[chart]: https://caniuse.com/webgpu
[link]: https://github.com/gfx-rs/wgpu
[hello-cube]: https://github.com/wgpu4k/hello-cube
[scenes]: https://github.com/wgpu4k/wgpu4k/tree/main/examples/common/src/commonMain/kotlin/io.ygdrasil.wgpu.examples/scenes/basic

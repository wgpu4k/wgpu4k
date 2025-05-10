# WGPU4K: A WebGPU Binding for Kotlin Multi-platform

[![Tests][test-badge]][test-url]
![Status][status-badge]
![Latest Version][version-badge]
[![License][license-badge]][license-url]
[![Discord][discord-badge]][discord-url]

This project focuses on creating a binding for WebGPU across Web, Desktop, and Mobile.  
For a low-level library that supports Mobile and Desktop, check out [wgpu4k-native][native-library].

---

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [How to Execute Examples](#how-to-execute-examples)
3. [Getting Started](#getting-started)
4. [Compatibility](#compatibility)
5. [Backend](#backend)
6. [Project Phases](#project-phases)

---

## Prerequisites

- **Gradle**: 8.11+
- **JDK**: 22+
- **Web Browsers**: A recent version of Chrome or Firefox Nightly (check [compatibility here][chart]).

---

## How to Execute Examples

```bash
git clone https://github.com/wgpu4k/wgpu4k.git
cd wgpu4k
```

- **Desktop JVM**: `./gradlew examples:glfw:run`
- **Web JS**: `./gradlew examples:web-js:jsBrowserProductionRun`
- **Web Wasm**: `./gradlew examples:web-js:wasmJsBrowserProductionRun`
- **Mac Native (Experimental)**: `./gradlew examples:native:runDebugExecutableNative`
- **Android (Experimental)**:  Execute the `android` subproject via Android Studio.
- **iOS (Experimental)**:
    - Build XCFramework: `./gradlew examples:iOS:assembleWgpuAppXCFramework`.
    - Run the `iosApp` subproject with XCode (compatible with a simulator or real device).

> **Tip**: Use `Page Down` and `Page Up` on your keyboard to switch scenes while running examples.

---

## Getting Started

Documentation is on its way!  
For now:

- Check out the minimalist project: [Hello Cube][hello-cube]
- Explore implemented scenes: [Examples/Scenes][scenes].

---

## Compatibility

| Target       | Windows | Linux | MacOS | iOS | Android |
|--------------|---------|-------|-------|-----|---------|
| JVM x64      | ✅       | 🆗    | 🆗    | ❌   | 🛠️     |
| JVM arm64    | 🔍️     | 🆗    | ✅     | ❌   | 🛠️     |
| JS           | ✅       | 🆗    | ✅     | ❓   | ❓️      |
| wasm         | 🆗️     | 🆗️   | 🆗️   | ❓️  | ❓️      |
| native x64   | 🛠️     | 🔍️   | 🛠️   | 🛠️ | ❌️️     |
| native arm64 | 🔍️     | 🔍️   | 🛠️   | 🛠️ | ❌️️     |

### Key:

- ✅ = First class citizen
- 🆗 = Best effort
- ❓ = Untested, could work
- 🛠️ = Work in progress
- 🔍 = Not supported yet (open to contributions)
- ❌ = Not applicable

---

## Backend

The native backend uses [Firefox WebGPU][backend-info]. For more details, visit the GitHub page.

---

## Project Phases

### Completed

- **Proof of Concept** (Done): Successfully tested WebGPU across multiple platforms using basic examples.

### In Progress

1. **API Implementation**: Developing the entire API.
2. **API Refinement**: Making the API more idiomatic to Kotlin.

### Upcoming

- **Production**: Preparing the project for production use.

---

<!-- Constants for Badges -->

[test-badge]: https://github.com/wgpu4k/wgpu4k/actions/workflows/test.yml/badge.svg?branch=main

[test-url]: https://github.com/wgpu4k/wgpu4k/actions/workflows/test.yml

[status-badge]: https://img.shields.io/badge/Status-Beta-orange?style=plastic

[version-badge]: https://img.shields.io/badge/Latest%20version-0.0.3-orange?style=plastic

[license-badge]: https://img.shields.io/badge/Licence-MIT-blue?style=plastic

[license-url]: https://en.wikipedia.org/wiki/MIT_License

[discord-badge]: https://img.shields.io/badge/Discord-wgpu4k-purple?style=plastic

[discord-url]: https://discord.gg/qy9KQAP9Kc

<!-- Reference Links -->

[chart]: https://caniuse.com/webgpu

[hello-cube]: https://github.com/wgpu4k/hello-cube

[scenes]: https://github.com/wgpu4k/wgpu4k/tree/main/wgpu4k-scenes/src/commonMain/kotlin/scenes/basic

[native-library]: https://github.com/wgpu4k/wgpu4k-native

[backend-info]: https://github.com/gfx-rs/wgpu
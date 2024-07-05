# WGPU4K: A WebGPU Binding for Kotlin Multi-platform
[![Tests](https://github.com/wgpu4k/wgpu4k/actions/workflows/test.yml/badge.svg?branch=main)](https://github.com/wgpu4k/wgpu4k/actions/workflows/test.yml)
![Static Badge](https://img.shields.io/badge/Status-Alpha-red?style=plastic)
![Static Badge](https://img.shields.io/badge/Latest%20version-preview_1-red?style=plastic)
[![Static Badge](https://img.shields.io/badge/Licence-MIT-blue?style=plastic)](https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FMIT_License)

This project focuses on creating a binding for WebGPU which can support Kotlin on multiple platforms.

## Table of Contents

1. [How to Run the demo projects](#how-to-run-the-project)
2. [Prerequisites](#prerequisites)
3. [Backend](#backend)
4. [Project Phases](#project-phases)
   1. [Proof of Concept](#proof-of-concept)
   2. [API Implementation](#api-implementation)
   3. [API Refinement](#api-refinement)
   4. [Production](#production)

## How to Run the demo projects

- On desktop: `./gradlew examples:glfw:run`
- On web: `./gradlew examples:web-js:jsBrowserRun`

Use `page down` and `page up` on your keyboard to switch scene.

## Prerequisites

- Gradle 8.8 or lower version with gradle wrapper
- JDK 22+
- A recent version of Chrome or Firefox Nightly for web browser execution. Check compatibility [here][chart].

## Getting started

Documentation will be added later, to start you can check out the minimalist project [here][hello-cube] or see scenes implemented to end-to-end tests [here][scenes].

## Backend

[GitHub Link][link]

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
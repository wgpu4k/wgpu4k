# WGPU4K: A WebGPU Binding for Kotlin Multi-platform

This project focuses on creating a binding for WebGPU which can support Kotlin on multiple platforms.

## Table of Contents

1. [How to Run the Project](#how-to-run-the-project)
2. [Prerequisites](#prerequisites)
3. [Backend](#backend)
4. [Project Phases](#project-phases)
   1. [Proof of Concept](#proof-of-concept)
   2. [API Implementation](#api-implementation)
   3. [API Refinement](#api-refinement)
   4. [Production](#production)

## How to Run the Project

1. On desktop: `gradle wrapper && ./gradlew examples:glfw:run`
2. On web: `gradle wrapper && ./gradlew examples:web-js:jsBrowserRun`

## Prerequisites

- Gradle 8.8(RC1+) or lower version with gradle wrapper
- JDK 22+
- A recent version of Chrome or Firefox Nightly for web browser execution. Check compatibility [here][chart].

## Backend

[GitHub Link][link]

## Project Phases

### Proof of Concept

The aim of this phase is to test the technology on as many platforms as possible using elementary examples.

### API Implementation

The entire API is implemented in this phase.

### API Refinement

In this phase, the API is fine-tuned to be more idiomatic to Kotlin.

### Production

This is the final phase when the project is ready for production.

<!-- Reference Links -->

[chart]: https://caniuse.com/webgpu

[link]: https://github.com/gfx-rs/wgpu
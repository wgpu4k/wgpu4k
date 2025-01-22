# Migration to `wgpu4k` from Preview to Beta

This README provides a guide to update your project with the changes introduced in the `wgpu4k` library upgrade
from the latest preview to the first beta 0.0.3 

---

## 1. Updated Dependencies in `libs.versions.toml`

Replace the old versions with the following updated dependency versions:

If you are TOML
```toml
[versions]
wgpu4k = "0.0.3"

[libraries]
# If you dont use the toolkit
wgpu4k = { module = "io.ygdrasil:wgpu4k", version.ref = "wgpu4k" }
# If you use the toolkit
wgpu4k-toolkit = { module = "io.ygdrasil:wgpu4k-toolkit", version.ref = "wgpu4k" }
```

---
If you are putting this directly on your build.gradle.kts

```kotlin

kotlin {
    commonMai {
        depndencies {
            // If you dont use the toolkit
            implementation("io.ygdrasil:wgpu4k:0.0.3")
            // If you use the toolkit
            implementation("io.ygdrasil:wgpu4k-toolkit:0.0.3")
        }
        
    }
}
```

---

## 3. Namespace Updates

- Update all `import` statements from `io.ygdrasil.wgpu` to `io.ygdrasil.webgpu`.

---

## 5. Code Changes

Adjustments are needed in the Kotlin code to align with the latest library API changes:

### 5.1 Replace Enum Constants

Constants from WebGPU specs are now in PascalCase

- Replace enum values like `BufferUsage.vertex` with `BufferUsage.Vertex`.
- Example:

```kotlin
val buffer = device.createBuffer(
    BufferDescriptor(
        usage = setOf(BufferUsage.Vertex),
        ...
    )
)
```

### 5.2 Adjust Unsigned Types

To reflect more the WebGPU specs, a lot a sigend type has been updated to unsigned

- Use `u` suffix for unsigned types where required (like `UInt` or `ULong`).
- Example:

```kotlin
val size: ULong = 4uL * 10uL
val value: UInt = 4u * 10u
```

### 5.3 Logging Updates

- Replace logging implementation with `KotlinLogging` where applicable.
- Example:

```kotlin
val logger = KotlinLogging.logger {}

logger.info { "something" }
```

---

## 6. Platform-Specific Adjustments

### 6.1 JVM

- Replace `WGPU.loadLibrary` with `LibraryLoader.load`.

---

Follow these steps to migrate your project to `wgpu4k` version `0.0.3` successfully. If any issues arise,
double-check the updated versions and namespace imports for consistency across your codebase, if the issue persist, please open a [ticket](https://github.com/wgpu4k/wgpu4k/issues).
# Migration to `wgpu4k` from Preview to Beta

This README provides instructions to update your project based on the changes introduced in the `wgpu4k` library upgrade
from the latest preview to the first beta (`0.0.3`).

---

### 1. Updated Dependency Versions

Replace the old versions with the following updated dependency versions:

If you are using `libs.versions.toml`:

```toml
[versions]
wgpu4k = "0.0.3"

[libraries]
# Without toolkit
wgpu4k = { module = "io.ygdrasil:wgpu4k", version.ref = "wgpu4k" }
# With toolkit
wgpu4k-toolkit = { module = "io.ygdrasil:wgpu4k-toolkit", version.ref = "wgpu4k" }
```

If you are directly using `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation("io.ygdrasil:wgpu4k:0.0.3") // Without toolkit
                implementation("io.ygdrasil:wgpu4k-toolkit:0.0.3") // With toolkit
            }
        }
    }
}
```

---

### 2. Namespace Updates

Update all `import` statements from `io.ygdrasil.wgpu` to `io.ygdrasil.webgpu`.

---

### 3. Code Adjustments

#### 3.1 Update Enum Constants

Enum values now use **PascalCase**. Adjust occurrences in your code accordingly:

```kotlin
val buffer = device.createBuffer(
    BufferDescriptor(
        usage = setOf(BufferUsage.Vertex), // Old: BufferUsage.vertex
        ...
    )
)
```

---

#### 3.2 Use Unsigned Types

Signed types have been replaced by unsigned equivalents. Use the `u` suffix where applicable:

```kotlin
val size: ULong = 4uL * 10uL
val value: UInt = 4u * 10u
```

---

#### 3.3 Logging Improvements

Replace any existing logging implementation with `KotlinLogging`:

```kotlin
val logger = KotlinLogging.logger {}
logger.info { "something" }
```

---

### 4. Platform-Specific Adjustments (JVM)

For JVM targets, replace:

```kotlin
WGPU.loadLibrary() // Old API
```

with:

```kotlin
LibraryLoader.load() // Updated API
```

---

### Final Note

Follow these steps to migrate your project to `wgpu4k` version `0.0.3` successfully. If you encounter issues, verify the
dependency versions and namespace imports across your codebase. Still stuck? Open
a [ticket](https://github.com/wgpu4k/wgpu4k/issues).
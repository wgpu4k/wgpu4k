import org.jetbrains.kotlin.gradle.dsl.KotlinTargetContainerWithPresetFunctions
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithHostTests

fun getCustomLLVMPath(): String? = System.getenv("LIBCLANG_PATH")?.takeIf { it.isNotEmpty() }

/**
 * dirty fix until 2.1.0 version
 * https://youtrack.jetbrains.com/issue/KT-49279/Kotlin-Native-update-LLVM-from-11.1.0-to-16.0.0
 */
fun KotlinTargetContainerWithPresetFunctions.configureMingwX64(): KotlinNativeTargetWithHostTests? {
    return getCustomLLVMPath()?.let { path ->
        mingwX64().apply {
            compilerOptions {
                freeCompilerArgs.add("-Xllvm-variant=$path")
            }
        }
    }
}
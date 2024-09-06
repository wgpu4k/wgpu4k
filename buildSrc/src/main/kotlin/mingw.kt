import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinTargetContainerWithPresetFunctions
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithHostTests

fun getCustomLLVMPath(): String? = System.getenv("LIBCLANG_PATH")?.takeIf { it.isNotEmpty() }

/**
 * dirty fix until 2.1.0 version
 * https://youtrack.jetbrains.com/issue/KT-49279/Kotlin-Native-update-LLVM-from-11.1.0-to-16.0.0
 */
@OptIn(ExperimentalKotlinGradlePluginApi::class)
fun KotlinTargetContainerWithPresetFunctions.configureMingwX64(project: Project, targetName: String = "mingwX64"): KotlinNativeTargetWithHostTests? {
    if ((project.properties["mingw.enabled"] ?: "").equals("true").not()) return null
    val llvmPath = getCustomLLVMPath() ?: error("if mingw.enabled == true, you should set LIBCLANG_PATH env")
    return llvmPath.let { path ->
        mingwX64(targetName).apply {
            compilerOptions {
                freeCompilerArgs.add("-Xllvm-variant=$path")
            }
        }
    }
}
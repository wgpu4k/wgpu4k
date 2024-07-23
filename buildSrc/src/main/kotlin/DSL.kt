import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.withType
import org.gradle.language.jvm.tasks.ProcessResources
import org.jetbrains.kotlin.gradle.tasks.CInteropProcess
import java.io.File


fun Project.configureDownloadTasks(action: DSL.() -> Unit) {
    action(DSL(this))
}

class DSL internal constructor(val project: Project) {

    var baseUrl: String? = null
    var zipBuildDirectory = project.file("build").resolve("zip")
    val fetchTask = project.tasks.register("fetch-native-dependencies") {
        group = "wgpu4k"
    }

    fun download(fileName: String, action: DownloadContext.() -> Unit) {
        val baseUrl = baseUrl ?: error("baseUrl must be set")

        val zipFile = zipBuildDirectory.resolve(fileName)
        val downloadTask = project.downloadInto(baseUrl, fileName, zipFile)
        action(DownloadContext(project, downloadTask, zipFile, fetchTask.get()))
    }

    class DownloadContext internal constructor(
        private val project: Project,
        private val downloadTask: Task,
        private val zipFile: File,
        private val fetchTask: Task
    ) {

        fun extract(fileName: String, target: File): Copy {
            val unzipTask = project.unzipTask(zipFile, target, fileName, downloadTask)

            project.tasks.withType<ProcessResources> {
                dependsOn(unzipTask)
            }

            // On IntelliJ we need to execute the download when the gradle project is loaded to compile cInterop without error
            if (System.getProperty("idea.active") == "true") {
                project.tasks.withType<CInteropProcess> {
                    dependsOn(unzipTask)
                }
            }

            fetchTask.dependsOn(unzipTask)

            return unzipTask
        }

    }
}
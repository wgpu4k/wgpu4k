import de.undercouch.gradle.tasks.download.Download
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.register
import java.io.File

fun Project.unzipTask(
    zipFile: File,
    target: File,
    antFilename: String,
    downloadTask: Task
): Copy {

    val zipFilename = antFilename.substringAfterLast("/")
        .substringAfterLast("\\")

    return tasks.register<Copy>("unzip-$zipFilename-from-${zipFile.name}") {
        onlyIf { !target.exists() }
        from(zipTree(zipFile))
        include(antFilename)
        into(target.parent)
        rename { fileName ->
            fileName.replace(zipFilename, target.name)
        }
        includeEmptyDirs = false
        dependsOn(downloadTask)
    }.get()
}


fun Project.downloadInto(baseUrl: String, fileName: String, target: File): Task {
    val url = "$baseUrl$fileName"
    val taskName = "downloadFile-$fileName"
    target.parentFile.mkdirs()
    return tasks.register<Download>(taskName) {
        onlyIf { !target.exists() }
        src(url)
        dest(target)
    }.get()
}
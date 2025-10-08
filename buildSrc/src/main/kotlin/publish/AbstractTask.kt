package publish

import okhttp3.Credentials.basic
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import java.net.URI
import kotlin.io.path.Path

abstract class AbstractTask : DefaultTask() {

    @get:Input
    var url: URI? = null
    @get:Input
    var username: String? = null
    @get:Input
    var password: String? = null
    @get:Input
    var publishingType: PublishingType? = null

    @get:Internal
    val client by lazy { OkHttpClient() }

    @get:Internal
    val request by lazy {
        val username = username
        val password = password
        if (username == null || password == null) {
            throw SecurityException("No username or password set.")
        }
        Request.Builder().addHeader(
            "Authorization",
            basic(
                username,
                password,
            ).replace("Basic", "Bearer"),
        )
    }

    @get:Internal
    val lastDeploymentsId by lazy {
        Path(project.layout.projectDirectory.asFile.canonicalPath, ".lastDeploymentsId")
    }

    fun publishMsg() {
        logger.lifecycle(
            "Due to the artifact's publishingType being {}" +
                "Final confirmation is required on the sonatype's central portal:{}" +
                "https://central.sonatype.com/publishing/deployments",
            PublishingType.USER_MANAGED.name +
                System.lineSeparator() +
                System.lineSeparator(),
            System.lineSeparator(),
        )
    }

    fun buildUrl() =
        HttpUrl
            .Builder()
            .scheme("https")
            .host("central.sonatype.com")
}

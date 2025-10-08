package publish

import org.gradle.api.Project
import java.net.URI

/***
 * The endpoint has two optional query parameters.
 *
 * The first, name, allows for providing a human-readable name for the deployment.
 * The second, publishingType, can have one of the following values:
 *
 * AUTOMATIC: (default) a deployment will go through validation and, if it passes,
 *   automatically proceed to publish to Maven Central
 *
 * USER_MANAGED: a deployment will go through validation and require the user to
 *   manually publish it via the Portal UI
 */
enum class PublishingType {
    /***
     * AUTOMATIC: Server validation will automatically publish
     */
    AUTOMATIC,

    /**
     * USER_MANAGED: Even if the server is validated,
     * you still need to log in to the central portal to confirm the release
     * [Maven Central: Publishing](https://central.sonatype.com/publishing/deployments)
     */
    USER_MANAGED,
}

data class CentralPortalPublishConfiguration(
    var url: URI? = null,
    var username: String? = null,
    var password: String? = null,
    var publishingType: PublishingType? = null
)

fun Project.centralPortalPublish(configure: CentralPortalPublishConfiguration.() -> Unit) {
    val configuration = CentralPortalPublishConfiguration()
        .also { it.configure() }

    val defaultPublishTask = tasks.findByName("publish")
    if (defaultPublishTask != null) {
        val publishToCentralPortalTask = tasks
            .register("publishToCentralPortal", BasePublishingTask::class.java) {
                url = configuration.url
                username = configuration.username
                password = configuration.password
                publishingType = configuration.publishingType
            }
        publishToCentralPortalTask.configure {
            dependsOn(defaultPublishTask)
        }
    } else {
        logger.error("missing default publish task!")
    }
}


println("headless ${getHeadlessProject()}")

tasks.create("rune2eServer") {
    doLast {
        val server = endToEndWebserver()
        browser()
        server.stop()
    }
}

fun getHeadlessProject() = projects.examples.headless.identityPath.path
    ?.let(::project) ?: error("Could not find project path")
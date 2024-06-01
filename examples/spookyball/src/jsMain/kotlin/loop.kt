import kotlin.js.Date

var lastTime: Double = -1.0

@OptIn(ExperimentalJsExport::class)
@JsExport
fun iterate(worldData: WorldData) {
    val current = Date.now()
    val delta = when (lastTime) {
        -1.0 -> 0.0
        else -> current - lastTime
    }.also { lastTime = Date.now() }


    ImpactDamageSystem.iterate(worldData, delta)


}

@OptIn(ExperimentalJsExport::class)
@JsExport
abstract class System(private val world: dynamic, private val worldData: dynamic) {
    val kotlin = true
    var enabled = true
    var stage = 0
    var id = 0
    var executesWhenPaused = true
    var fixedStep = 0
    var fixedStepDeltaRemainder = 0

    fun query(vararg componentsTypes: dynamic): dynamic {
        return worldData.getQuery(componentsTypes)
    }

    val singleton: dynamic
        get() = world.singleton

    abstract fun execute(delta: Double, time: Double, vararg args: dynamic)
}
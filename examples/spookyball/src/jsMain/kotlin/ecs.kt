external interface Entity {
    val worldData: WorldData
    val destroyed: Boolean
    val enabled: Boolean

    fun get(vararg componentsTypes: dynamic): dynamic
    fun add(entity: dynamic)
    fun remove(entity: dynamic)
    fun has(componentType: dynamic): Boolean
}

external interface WorldData {
    val entities: dynamic
    val components: dynamic
    val queries: MutableMap<dynamic, dynamic>
    val systems: MutableMap<dynamic, dynamic>
    val orderedSystems: MutableMap<dynamic, dynamic>

    fun getQuery(componentTypes: dynamic)
    fun getEntities(): MutableMap<dynamic, Entity>

}

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

@OptIn(ExperimentalJsExport::class)
@JsExport
external interface Query {
    val worldData: WorldData
    val queryName: String
    val includedTypes: MutableList<String>
    var excludedTypes: MutableList<String>


    var include: MutableList<String>
    var exclude: MutableList<String>


    fun not(vararg componentTypes: String): Query

    fun includeDisabled(): Query
    fun forEach(callback: (Entity, Array<Entity>) -> Boolean)

    fun getCount(): Int

    // TODO: Replace with actual Kotlin code
    fun getComponentName(type: String): String
}
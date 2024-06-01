
@OptIn(ExperimentalJsExport::class)
@JsExport
val ImpactDamageClass = ImpactDamage::class.simpleName

@OptIn(ExperimentalJsExport::class)
@JsExport
class ImpactDamage(var damage: Int = 1)

@OptIn(ExperimentalJsExport::class)
@JsExport
class Collisions {
    val entities = mutableSetOf<Entity>()

    fun insert(entity: Entity) {
        entities.add(entity)
    }
}

val FLASH_DURATION = 0.1

@OptIn(ExperimentalJsExport::class)
@JsExport
class Damaged {
    var duration = FLASH_DURATION
    var amount = 0
}


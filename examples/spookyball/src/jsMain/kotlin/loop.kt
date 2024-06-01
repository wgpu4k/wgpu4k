import kotlin.js.Date
import kotlin.math.PI
import kotlin.math.sin

var lastTime: Double = -1.0

@OptIn(ExperimentalJsExport::class)
@JsExport
fun iterate(worldData: WorldData) {
    val current = Date.now()
    val delta = when (lastTime) {
        -1.0 -> 0.0
        else -> current - lastTime
    }.also { lastTime = Date.now() }



    worldData.entities.forEach { entry ->
        val entity: Entity = entry
        val damage: ImpactDamage? = entity.get(ImpactDamage::class.js)
        val collisions: Collisions? = entity.get(Collisions::class.js)
        if (damage != undefined && collisions != undefined) {
            for (colliderEntity in collisions.entities) {
                val colliderHealth = colliderEntity.get(Health::class.js) as Health?
                if (colliderHealth != undefined) {
                    colliderHealth.health -= damage.damage

                    var damaged = colliderEntity.get(Damaged::class.js) as Damaged?
                    if (damaged == undefined) {
                        damaged = Damaged()
                        colliderEntity.add(damaged)
                    }
                    damaged.duration = FLASH_DURATION
                    damaged.amount += damage.damage
                }
            }
        }
    }

    worldData.entities.forEach { entry ->
        val entity: Entity = entry
        val damaged: Damaged? = entity.get(Damaged::class.js)
        if (damaged != undefined) {
            if (damaged.duration <= 0) {
                entity.remove(InstanceColor::class.js)
                entity.remove(Damaged::class.js)

            }

            var flash: InstanceColor? = entity.get(InstanceColor::class.js) as InstanceColor?
            if (flash == undefined) {
                flash = InstanceColor(null)
                entity.add(flash)
            }

            val t = (1.0 - (damaged.duration / FLASH_DURATION)) * 0.75 + 0.25

            flash.colorField[0] = (sin(t * PI) * 0.6).toFloat()
            flash.colorField[1] = (sin(t * PI) * 0.6).toFloat()
            flash.colorField[2] = (sin(t * PI) * 0.4).toFloat()

            damaged.duration -= delta

        }
    }
}
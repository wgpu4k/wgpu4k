import kotlin.math.PI
import kotlin.math.sin

external interface Entity {
    fun get(vararg componentsTypes: dynamic): dynamic
    fun add(entity: dynamic)
    fun remove(entity: dynamic)
}

external interface Query {
    val entities: Map<Int, Entity>?

}


@OptIn(ExperimentalJsExport::class)
@JsExport
class ImpactDamage(var damage: Int = 1)

class Collisions {
    val entities = HashSet<Entity>()
}

val FLASH_DURATION = 0.1

class Damaged {
    var duration = FLASH_DURATION
    var amount = 0
}

@OptIn(ExperimentalJsExport::class)
@JsExport
class ImpactDamageSystem(world: dynamic, worldData: dynamic) : System(world, worldData) {

    private val impactDamageQuery: Query = this.query(ImpactDamage::class.js, Collisions::class.js)
    private val damagedQuery: Query = this.query(Damaged::class.js)

    override fun execute(delta: Double, time: Double, vararg args: dynamic) {
        // Accumulate damage from all colliders that inflict impact damage
        for (entity in (impactDamageQuery.entities?.values ?: listOf())) {
            val damage = entity.get(ImpactDamage::class) as ImpactDamage
            val collisions = entity.get(Collisions::class) as Collisions
            for (colliderEntity in collisions.entities) {
                val colliderHealth = colliderEntity.get(Health::class) as Health?
                if (colliderHealth != null) {
                    colliderHealth.health -= damage.damage

                    var damaged = colliderEntity.get(Damaged::class) as Damaged?
                    if (damaged == null) {
                        damaged = Damaged()
                        colliderEntity.add(damaged)
                    }
                    damaged.duration = FLASH_DURATION
                    damaged.amount += damage.damage
                }
            }

        }

        this.damagedQuery.entities?.values?.forEach { entity ->
            val damaged = entity.get(Damaged::class) as Damaged
            if (damaged.duration <= 0) {
                entity.remove(InstanceColor::class)
                entity.remove(Damaged::class)
                return@forEach
            }

            var flash: InstanceColor? = entity.get(InstanceColor::class) as InstanceColor?
            if (flash == null) {
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
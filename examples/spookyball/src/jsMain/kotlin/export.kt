import korlibs.math.geom.Vector3D
import kotlin.math.sqrt

@OptIn(ExperimentalJsExport::class)
@JsExport
class MyVector3(x: Double, y: Double, z: Double) {

    private val vector = Vector3D(x, y, z)

    val x = vector.x
    val y = vector.y
    val z = vector.z


    fun normalize(): MyVector3 {
        var x = vector.x
        var y = vector.y
        var z = vector.z
        var len = x * x + y * y + z * z

        if (len > 0) {
            //TODO: evaluate use of glm_invsqrt here?
            len = 1 / sqrt(len)
        }

        x = vector.x * len
        y = vector.y * len
        z = vector.z * len
        return MyVector3(x, y, z)
    }

    fun scale(b: Double): MyVector3 {
        val x = this.x * b
        val y = this.x * b
        val z = this.z * b
        return MyVector3(x, y, z)
    }
}
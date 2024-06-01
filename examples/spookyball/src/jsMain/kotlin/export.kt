import korlibs.math.geom.Vector3D
import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import org.khronos.webgl.set
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

    fun toJS32Array(): Float32Array {
        return Float32Array(arrayOf(x.toFloat(), y.toFloat(), z.toFloat()))
    }

    fun into(input: Float32Array) {
        input.set(toJS32Array())
    }
}

@OptIn(ExperimentalJsExport::class)
@JsExport
fun vec3TransformMat4(out: Float32Array, a: Float32Array, m: Float32Array) {
    val x = a.get(0)
    val y = a.get(1)
    val z = a.get(2)
    var w = m.get(3) * x + m.get(7) * y + m.get(11) * z + m.get(15)
    w = w.takeIf { it != 0f } ?: 1.0f
    out.set(
        arrayOf(
            (m.get(0) * x + m.get(4) * y + m.get(8) * z + m.get(12)) / w,
            (m.get(1) * x + m.get(5) * y + m.get(9) * z + m.get(13)) / w,
            (m.get(2) * x + m.get(6) * y + m.get(10) * z + m.get(14)) / w
        )
    )
}

@OptIn(ExperimentalJsExport::class)
@JsExport
fun mat4Copy(out: Float32Array, a: Float32Array): Float32Array {
    out[0] = a[0]
    out[1] = a[1]
    out[2] = a[2]
    out[3] = a[3]
    out[4] = a[4]
    out[5] = a[5]
    out[6] = a[6]
    out[7] = a[7]
    out[8] = a[8]
    out[9] = a[9]
    out[10] = a[10]
    out[11] = a[11]
    out[12] = a[12]
    out[13] = a[13]
    out[14] = a[14]
    out[15] = a[15]
    return out
}
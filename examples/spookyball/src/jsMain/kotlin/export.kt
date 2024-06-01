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
fun mat4FromRotationTranslationScale(out: Float32Array, q: Float32Array, v: Float32Array, s: Float32Array): Float32Array {
    // Quaternion math
    val x = q[0]
    val y = q[1]
    val z = q[2]
    val w = q[3]
    val x2 = x + x
    val y2 = y + y
    val z2 = z + z
    val xx = x * x2
    val xy = x * y2
    val xz = x * z2
    val yy = y * y2
    val yz = y * z2
    val zz = z * z2
    val wx = w * x2
    val wy = w * y2
    val wz = w * z2
    val sx = s[0]
    val sy = s[1]
    val sz = s[2]
    out[0] = (1 - (yy + zz)) * sx
    out[1] = (xy + wz) * sx
    out[2] = (xz - wy) * sx
    out[3] = 0.0f
    out[4] = (xy - wz) * sy
    out[5] = (1 - (xx + zz)) * sy
    out[6] = (yz + wx) * sy
    out[7] = 0.0f
    out[8] = (xz + wy) * sz
    out[9] = (yz - wx) * sz
    out[10] = (1 - (xx + yy)) * sz
    out[11] = 0.0f
    out[12] = v[0]
    out[13] = v[1]
    out[14] = v[2]
    out[15] = 1.0f
    return out
}

@OptIn(ExperimentalJsExport::class)
@JsExport
fun mat4Multiply(out: Float32Array, a: Float32Array, b: Float32Array): Float32Array {
    // Cache only the current line of the second matrix
    out[0] = b[0] * a[0] + b[1] * a[4] + b[2] * a[8] + b[3] * a[12]
    out[1] = b[0] * a[1] + b[1] * a[5] + b[2] * a[9] + b[3] * a[13]
    out[2] = b[0] * a[2] + b[1] * a[6] + b[2] * a[10] + b[3] * a[14]
    out[3] = b[0] * a[3] + b[1] * a[7] + b[2] * a[11] + b[3] * a[15]

    out[4] = b[4] * a[0] + b[5] * a[4] + b[6] * a[8] + b[7] * a[12]
    out[5] = b[4] * a[1] + b[5] * a[5] + b[6] * a[9] + b[7] * a[13]
    out[6] = b[4] * a[2] + b[5] * a[6] + b[6] * a[10] + b[7] * a[14]
    out[7] = b[4] * a[3] + b[5] * a[7] + b[6] * a[11] + b[7] * a[15]

    out[8] = b[8] * a[0] + b[9] * a[4] + b[10] * a[8] + b[11] * a[12]
    out[9] = b[8] * a[1] + b[9] * a[5] + b[10] * a[9] + b[11] * a[13]
    out[10] = b[8] * a[2] + b[9] * a[6] + b[10] * a[10] + b[11] * a[14]
    out[11] = b[8] * a[3] + b[9] * a[7] + b[10] * a[11] + b[11] * a[15]

    out[12] = b[12] * a[0] + b[13] * a[4] + b[14] * a[8] + b[15] * a[12]
    out[13] = b[12] * a[1] + b[13] * a[5] + b[14] * a[9] + b[15] * a[13]
    out[14] = b[12] * a[2] + b[13] * a[6] + b[14] * a[10] + b[15] * a[14]
    out[15] = b[12] * a[3] + b[13] * a[7] + b[14] * a[11] + b[15] * a[15]

    return out
}
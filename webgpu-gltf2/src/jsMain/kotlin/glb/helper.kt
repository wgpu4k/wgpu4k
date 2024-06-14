@file:OptIn(ExperimentalJsExport::class)

package glb

import korlibs.math.geom.Matrix4

@JsExport
fun gltfTypeNumComponents(type: String): Int {
    return when (type) {
        "SCALAR" -> 1
        "VEC2" -> 2
        "VEC3" -> 3
        "VEC4" -> 4
        else -> error("Unhandled glTF Type $type")
    }
}

fun gltfTypeSize(componentType: GLTFComponentType, type: String): Int {
    val typeSize = when (componentType) {
        GLTFComponentType.BYTE -> 1
        GLTFComponentType.UNSIGNED_BYTE -> 1
        GLTFComponentType.SHORT -> 2
        GLTFComponentType.UNSIGNED_SHORT -> 2
        GLTFComponentType.INT -> 4
        GLTFComponentType.UNSIGNED_INT -> 4
        GLTFComponentType.FLOAT -> 4
        GLTFComponentType.DOUBLE -> 4
    }
    return gltfTypeNumComponents(type) * typeSize
}

@JsExport
fun alignTo(value: Int, align: Int): Int {
    return ((value + align - 1) / align) * align
}

@JsExport
fun readNodeTransform(node: dynamic): DoubleArray {

    if (node.matrix != null) {
        val m = node.matrix
        // Both glTF and gl matrix are column major
        return doubleArrayOf(
            m[0],
            m[1],
            m[2],
            m[3],
            m[4],
            m[5],
            m[6],
            m[7],
            m[8],
            m[9],
            m[10],
            m[11],
            m[12],
            m[13],
            m[14],
            m[15]
        )
    } else {
        val scale = if (node.scale != null) node.scale as Array<Double> else arrayOf(1.0, 1.0, 1.0)
        val rotation = if (node.rotation != null) node.rotation as Array<Double> else arrayOf(0.0, 0.0, 0.0, 1.0)
        val translation = if (node.translation != null) node.translation as Array<Double> else arrayOf(0.0, 0.0, 0.0)

        val matrix = create()

        return fromRotationTranslationScale(matrix, rotation, translation, scale)
    }
}

fun create(): DoubleArray {
    val out = DoubleArray(16)

    out.fill(0.0)

    out[0] = 1.0
    out[5] = 1.0
    out[10] = 1.0
    out[15] = 1.0
    return out
}


fun fromRotationTranslationScale(out: DoubleArray, q: Array<Double>, v: Array<Double>, s: Array<Double>): DoubleArray {
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
    out[3] = 0.0
    out[4] = (xy - wz) * sy
    out[5] = (1 - (xx + zz)) * sy
    out[6] = (yz + wx) * sy
    out[7] = 0.0
    out[8] = (xz + wy) * sz
    out[9] = (yz - wx) * sz
    out[10] = (1 - (xx + yy)) * sz
    out[11] = 0.0
    out[12] = v[0]
    out[13] = v[1]
    out[14] = v[2]
    out[15] = 1.0

    return out
}


@JsExport
fun multiply(out: DoubleArray, a: DoubleArray, b: DoubleArray): DoubleArray {
    val a00 = a[0]
    val a01 = a[1]
    val a02 = a[2]
    val a03 = a[3]
    val a10 = a[4]
    val a11 = a[5]
    val a12 = a[6]
    val a13 = a[7]
    val a20 = a[8]
    val a21 = a[9]
    val a22 = a[10]
    val a23 = a[11]
    val a30 = a[12]
    val a31 = a[13]
    val a32 = a[14]
    val a33 = a[15]

    var b0 = b[0]
    var b1 = b[1]
    var b2 = b[2]
    var b3 = b[3]
    out[0] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
    out[1] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
    out[2] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
    out[3] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
    b0 = b[4]
    b1 = b[5]
    b2 = b[6]
    b3 = b[7]
    out[4] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
    out[5] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
    out[6] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
    out[7] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
    b0 = b[8]
    b1 = b[9]
    b2 = b[10]
    b3 = b[11]
    out[8] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
    out[9] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
    out[10] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
    out[11] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
    b0 = b[12]
    b1 = b[13]
    b2 = b[14]
    b3 = b[15]
    out[12] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
    out[13] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
    out[14] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
    out[15] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
    return out
}
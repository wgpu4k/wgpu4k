
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.math.PI


fun getProjectionMatrix(width: Int, height: Int): Matrix4 {
    val aspect = width / height.toDouble()
    val fox = Angle.fromRadians((2 * PI) / 5)
    return Matrix4.perspective(fox, aspect, 1.0, 100.0)
}

fun getTransformationMatrix(angle: Double, projectionMatrix: Matrix4): FloatArray {
    var viewMatrix = Matrix4.IDENTITY
    viewMatrix = viewMatrix.translated(0, 0, -4)

    viewMatrix = viewMatrix.rotated(
        Angle.fromRadians(Angle.fromRadians(angle).sine),
        Angle.fromRadians(Angle.fromRadians(angle).cosine),
        Angle.fromRadians(0)
    )

    return (projectionMatrix * viewMatrix).copyToColumns()
}
import org.khronos.webgl.Float32Array

@OptIn(ExperimentalJsExport::class)
@JsExport
val DEFAULT_POSITION = MyVector3(0.0, 0.0, 0.0)

@OptIn(ExperimentalJsExport::class)
@JsExport
val DEFAULT_SCALE = MyVector3(1.0, 1.0, 1.0)

@OptIn(ExperimentalJsExport::class)
@JsExport
class TransformKt(
    var position: Float32Array,
    var orientation: Float32Array,
    var scale: Float32Array,
    var localMatrix: Float32Array,
    var worldMatrix: Float32Array,
) {

}
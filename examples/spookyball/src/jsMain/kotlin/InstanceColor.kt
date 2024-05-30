@OptIn(ExperimentalJsExport::class)
@JsExport
enum class InstanceColorMode(val value: Int) {
    Add(0),
    Multiply(1)
}

@OptIn(ExperimentalJsExport::class)
@JsExport
class InstanceColor(color: FloatArray? = null, mode: InstanceColorMode = InstanceColorMode.Add) {
    val buffer: FloatArray = FloatArray(4)
    val colorField: FloatArray = FloatArray(3)
    val color: FloatArray = FloatArray(3)

    init {
        if (color != null) {
            colorField[0] = color[0]
            colorField[1] = color[1]
            colorField[2] = color[2]
        }
        buffer[3] = mode.value.toFloat()
    }
}
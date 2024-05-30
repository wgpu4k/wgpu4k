@OptIn(ExperimentalJsExport::class)
@JsExport
class GameState {
    var level = 0
    var levelStarting = true
    var lives = 3
}

@OptIn(ExperimentalJsExport::class)
@JsExport
class Paddle {
    var x = 0
    var launch = false
}
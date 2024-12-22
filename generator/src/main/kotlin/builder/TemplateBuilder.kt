package builder


class Builder(
    internal val indent: Int = 0
) {
    val textBuilder = StringBuilder()

    fun appendLine(text: String, run: (Builder.() -> Unit)? = null) {
        textBuilder.append("\t".repeat(indent))
        textBuilder.append(text)
        newLine()

        run?.let { run ->
            Builder(indent + 1).apply(run).toString()
                .also {textBuilder.append(it)}
        }
    }

    fun appendBlock(text: String, args: String, run: Builder.() -> Unit) {
        textBuilder.append("\t".repeat(indent))
        textBuilder.append(text)
        textBuilder.append(" { $args ->")
        newLine()
        Builder(indent + 1).apply(run).toString()
            .also {textBuilder.append(it)}

        textBuilder.append("\t".repeat(indent))
        textBuilder.append("}")
        newLine()
    }

    fun appendBlock(text: String, run: Builder.() -> Unit) {
        textBuilder.append("\t".repeat(indent))
        textBuilder.append(text)
        textBuilder.append(" {")
        newLine()
        Builder(indent + 1).apply(run).toString()
            .also {textBuilder.append(it)}

        textBuilder.append("\t".repeat(indent))
        textBuilder.append("}")
        newLine()
    }

    fun newLine() {
        textBuilder.append("\n")
    }

    override fun toString(): String {
        return textBuilder.toString()
    }
}

fun templateBuilder(run: Builder.() -> Unit) = Builder()
    .apply(run).toString()
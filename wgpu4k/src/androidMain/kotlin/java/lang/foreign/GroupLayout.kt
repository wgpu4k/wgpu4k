package java.lang.foreign

class GroupLayout(internal val layouts: List<ValueLayout>, name: String? = null) : ValueLayout(layouts.fold(0L, { acc, layout -> acc + layout.size }), name) {

    override fun withName(name: String) = GroupLayout(layouts, name)
    fun select(name: String): ValueLayout = layouts.first { it.name == name }
    fun byteSize(): Long = size

}
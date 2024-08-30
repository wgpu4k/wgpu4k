package java.lang.foreign

class GroupLayout(internal val layouts: List<ValueLayout>) {

    val size: Long = layouts.fold(0L, { acc, layout -> acc + layout.size })

    fun withName(WGPUColor: String) = this
    fun select(r: Any?): Any? = null
    fun byteSize(): Long = size

}
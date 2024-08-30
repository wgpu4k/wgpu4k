package java.lang.foreign

class MemoryLayout {
    fun withName(WGPUColor: String) = this

    companion object {
        @JvmStatic
        fun structLayout(vararg layouts: ValueLayout) =  GroupLayout(layouts.toList())

        @JvmStatic
        fun sequenceLayout(elementCount: Long, layout: GroupLayout): GroupLayout {
            val layouts: MutableList<ValueLayout> = mutableListOf()
            repeat(elementCount.toInt()) { layouts += layout.layouts}
            return GroupLayout(layouts)
        }
    }

    object PathElement {

        @JvmStatic
        fun groupElement(none: Any?): Any? = null

    }

}
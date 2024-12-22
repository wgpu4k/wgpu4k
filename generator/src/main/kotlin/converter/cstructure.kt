package converter

import domain.CLibraryModel

internal fun List<CLibraryModel.Structure>.calculateSizeAndPadding(): List<CLibraryModel.Structure> {
    val stack = toMutableList()
    val processed = mutableListOf<CLibraryModel.Structure>()
    while (stack.isNotEmpty()) {
        stack.removeFirst()
            .updateSizeAndPadding(stack, processed)
    }
    return processed
}

private fun CLibraryModel.Structure.updateSizeAndPadding(
    stack: MutableList<CLibraryModel.Structure>,
    processed: MutableList<CLibraryModel.Structure>
) {
    var structureAlignment = 1
    var offset = 0
    val members = members.map { member ->
        val (size, alignment) = if (member.type is CLibraryModel.Reference.StructureField) {
            stack.firstOrNull { it.name == member.type.name }
                ?.also { stack.remove(it) }
                ?.updateSizeAndPadding(stack, processed)

            (processed.firstOrNull { it.name == member.type.name } ?: error("${member.type.name} should have been processed"))
                .let { it.size to it.alignment }
        } else {
            member.size to member.alignment
        }
        if (size == null) error("size should have been calculated to $member")
        if (alignment == null) error("alignment should have been calculated to $member")

        structureAlignment = maxOf(structureAlignment, alignment)
        val padding = offset % alignment
        offset += size + padding

        CLibraryModel.StructureField(member.name, member.type, member.option, alignment, size, padding)
    }

    val padding = offset % structureAlignment
    if (padding > 0) println("padding on $name: $padding structureAlignment: $structureAlignment size before padding: $offset")
    offset += padding

    processed.add(CLibraryModel.Structure(name, members, offset, structureAlignment, padding))
}

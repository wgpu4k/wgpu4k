@OptIn(ExperimentalJsExport::class)
@JsExport
open class TextureAtlasRect(private var node: TextureAtlasNode?) {
    val x
        get() = node?.x
    val y
        get() = node?.y
    val width
        get() = node?.width
    val height
        get() = node?.height

    fun release() {
        node?.onRelease()
        node = null
    }
}

@OptIn(ExperimentalJsExport::class)
@JsExport
open class TextureAtlasNode(
    var x: Int,
    var y: Int,
    var width: Int,
    var height: Int,
    private val parent: TextureAtlasNode? = null
) {
    var allocated = false
    var children: Array<TextureAtlasNode>? = null
    private var rect: TextureAtlasRect? = null
    val fullyAllocated: Boolean
        get() = children?.let { it[0].fullyAllocated && it[1].fullyAllocated } ?: rect != null
    val hasAllocation: Boolean
        get() = (children?.let { it[0].hasAllocation || it[1].hasAllocation }) ?: (rect != null)

    fun allocate(): TextureAtlasRect? {
        if (rect == null) {
            if (children != null) {
                throw Exception("Split nodes cannot be allocated.")
            }
            rect = TextureAtlasRect(this)
        }
        return rect
    }

    fun split(width: Int, height: Int) {
        if (children != null) {
            throw Exception("Node is already split.")
        }
        if ((this.width - width) > (this.height - height)) {
            // Horizontal split
            children = arrayOf(
                TextureAtlasNode(x, y, width, this.height, this),
                TextureAtlasNode(x + width, y, this.width - width, this.height, this)
            )
        } else {
            // Vertical split
            children = arrayOf(
                TextureAtlasNode(x, y, this.width, height, this),
                TextureAtlasNode(x, y + height, this.width, this.height - height, this)
            )
        }
    }

    fun onRelease() {
        rect = null
        children = null
        // When a node is released, check to see if its parent node can also be released, which
        // collapses a split node into a single bigger node.
        if (parent?.hasAllocation == false) {
            parent.onRelease()
        }
    }
}

@OptIn(ExperimentalJsExport::class)
@JsExport
class TextureAtlasAllocator(width: Int, height: Int) {
    private var root: TextureAtlasNode = TextureAtlasNode(0, 0, width, height ?: width)

    private fun findNodeToAllocate(node: TextureAtlasNode, width: Int, height: Int): TextureAtlasNode? {
        // Node is too small for the required size.
        if (node.width < width || node.height < height) {
            return null
        }
        // Already used
        if (node.fullyAllocated) {
            return null
        }
        // Check children nodes
        node.children?.let { children ->
            findNodeToAllocate(children[0], width, height)?.let { return it }
            findNodeToAllocate(children[1], width, height)?.let { return it }
        }
        // Perfect fit. Allocate without splitting
        if(node.width == width && node.height == height) {
            return node
        }
        node.split(width, height)
        return findNodeToAllocate(node.children!![0], width, height)
    }

    fun allocate(width: Int, height: Int): TextureAtlasRect? {
        return findNodeToAllocate(root, width, height ?: width)?.allocate()
    }
}
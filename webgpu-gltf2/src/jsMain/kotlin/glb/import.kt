@file:OptIn(ExperimentalJsExport::class)

package glb

import io.ygdrasil.wgpu.internal.js.GPUDevice

@JsExport
fun uploadGLBModelKt(
    glbJsonData: dynamic,
    meshes: dynamic,
    device: GPUDevice,
    materials: Array<GLTFMaterial>,
    defaultMaterial: GLTFMaterial,
    bufferViews: Array<GLTFBufferView>
): GLBModel {
    println("uploadGLBModelKt")

    // Upload the different views used by meshes
    bufferViews.forEach { bufferView ->
        if (bufferView.needsUpload) {
            bufferView.upload(device)
        }
    }

    defaultMaterial.upload(device)
    materials.forEach { material -> material.upload(device) }

    val nodes = mutableListOf<GLTFNode>()
    val gltfNodes = makeGLTFSingleLevel(glbJsonData["nodes"])
    for (i in 0 until gltfNodes.length) {
        val n = gltfNodes[i]
        if (n["mesh"] != null) {
            val nodeName = n["name"] as String
            val mesh = meshes[n["mesh"]]
            val node = GLTFNode(nodeName, mesh, readNodeTransform(n))
            node.upload(device)
            nodes.add(node)
        }
    }
    return GLBModel(nodes.toTypedArray())
}

@JsExport
fun flattenGLTFChildren(nodes: dynamic, node: dynamic, parent_transform: DoubleArray) {
    var tfm = readNodeTransform(node)
    tfm = multiply(tfm, parent_transform, tfm)
    node["matrix"] = tfm
    node["scale"] = undefined
    node["rotation"] = undefined
    node["translation"] = undefined
    node["children"]?.let { children ->
        for (i in 0 until children.size) {
            flattenGLTFChildren(nodes, nodes[children[i]], tfm)
        }
    }
     node["children"] = undefined
}

@JsExport
    fun makeGLTFSingleLevel(nodes: dynamic): dynamic {
    val rootTfm = create()
    for (i in 0 until nodes.length) {
        flattenGLTFChildren(nodes, nodes[i], rootTfm)
    }
    return nodes
}

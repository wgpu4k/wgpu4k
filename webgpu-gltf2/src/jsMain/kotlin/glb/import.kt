package glb

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.helper.GLTFRenderMode
import io.ygdrasil.wgpu.examples.helper.readGLB
import io.ygdrasil.wgpu.examples.toBitmapHolder
import korlibs.image.format.readBitmap
import korlibs.io.file.VfsFile
import korlibs.io.file.std.asMemoryVfsFile
import korlibs.io.lang.TextDecoder
import korlibs.io.util.toInt8Array
import org.khronos.webgl.Uint32Array
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.get


suspend fun uploadGLBModel(
    device: Device,
    rawFile: VfsFile,
): GLBModel {
    println("uploadGLBModel2")

    val rawJSBuffer = rawFile.readBytes().toInt8Array().buffer
    val header = Uint32Array(rawJSBuffer, 0, 5)
    if (header.get(0) != 0x46546C67) {
        error("This does not appear to be a glb file?")
    }
    val glbJsonData: dynamic =
        JSON.parse(TextDecoder("utf-8").decode(Uint8Array(rawJSBuffer, 20, header.get(3))))

    val binaryHeader = Uint32Array(rawJSBuffer, 20 + header.get(3), 2)

    if (28 + header.get(3) + binaryHeader.get(0) != rawJSBuffer.byteLength) {
        console.log("TODO: Multiple binary chunks in file")
    }

    val gltf2 = rawFile
        .readGLB()

    val bufferViews = gltf2.bufferViews.mapIndexed { index, bufferView ->
        GLTFBufferView(bufferView, gltf2.buffers[bufferView.buffer])
    }

    val images = gltf2.images.map { image ->

        val bufferView = gltf2.bufferViews[image.bufferView]
        val imageView = GLTFBufferView(
            bufferView,
            gltf2.buffers[bufferView.buffer]
        )

        val bitmap = imageView.buffer
            .asMemoryVfsFile()
            .readBitmap()
            .toBMP32()

        // TODO: For glTF we need to look at where an image is used to know
        // if it should be srgb or not. We basically need to pass through
        // the material list and find if the texture which uses this image
        // is used by a metallic/roughness param
        val gpuImg = device.createTexture(
            TextureDescriptor(
                size = Size3D(width = bitmap.width, height = bitmap.height, depthOrArrayLayers = 1),
                format = TextureFormat.rgba8unormsrgb,
                usage = setOf(TextureUsage.texturebinding, TextureUsage.copydst, TextureUsage.renderattachment)
            )
        )

        val src = ImageCopyExternalImage(source = bitmap.toBitmapHolder())
        val dst = ImageCopyTextureTagged(texture = gpuImg)
        device.queue.copyExternalImageToTexture(
            src,
            dst,
            bitmap.width to bitmap.height
        )

        gpuImg
    }

    val defaultSampler = GLTFSampler(device)
    val samplers = gltf2.samplers.mapIndexed { index, sampler ->
        GLTFSampler(device, sampler)
    }

    val textures = gltf2.textures.mapIndexed { index, texture ->
        val sampler = if (texture.sampler != null) samplers[texture.sampler!!] else defaultSampler
        GLTFTexture(sampler, images[texture.source])
    }

    val defaultMaterial = GLTFMaterial(mapOf<Any, Any>())
    val materials = gltf2.materials.mapIndexed { index, material ->
        GLTFMaterial(glbJsonData.materials[index], textures)
    }

    val meshes = gltf2.meshes.map { mesh ->
        val primitives = mesh.primitives
            .filter { primitive ->
                // Filter only supported mode
                (GLTFRenderMode.of(primitive.mode) ?: error("topology not found")) in listOf(
                    GLTFRenderMode.TRIANGLES,
                    GLTFRenderMode.TRIANGLE_STRIP
                )
            }
            .map { primitive ->
            val topology = GLTFRenderMode.of(primitive.mode) ?: error("topology not found")

            val indices: GLTFAccessor? = if (primitive.indices != null) {
                val accessor = gltf2.accessors[primitive.indices!!]
                val viewID = accessor.bufferView
                bufferViews[viewID].needsUpload = true
                bufferViews[viewID].addUsage(BufferUsage.index)
                GLTFAccessor(bufferViews[viewID], accessor)
            } else null

            var positions: GLTFAccessor? = null
            var normals: GLTFAccessor? = null
            val texcoords = mutableListOf<GLTFAccessor>()
            primitive.attributes.forEach { (attribute, index) ->
                val accessor = gltf2.accessors[index]
                val viewID = accessor.bufferView
                bufferViews[viewID].needsUpload = true
                bufferViews[viewID].addUsage(BufferUsage.vertex)
                when (attribute.str) {
                    "POSITION" -> positions = GLTFAccessor(bufferViews[viewID], accessor)
                    "NORMAL" -> normals = GLTFAccessor(bufferViews[viewID], accessor)
                    else -> {
                        if (attribute.str.startsWith("TEXCOORD")) {
                            texcoords.add(GLTFAccessor(bufferViews[viewID], accessor))
                        }
                    }
                }
            }

            val material = if (primitive.material != null) {
                materials[primitive.material!!]
            } else {
                defaultMaterial
            }
            GLTFPrimitive(indices, positions!!, normals, texcoords, material, topology)
        }
        GLTFMesh(mesh.name ?: "", primitives)
    }

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

fun makeGLTFSingleLevel(nodes: dynamic): dynamic {
    val rootTfm = create()
    for (i in 0 until nodes.length) {
        flattenGLTFChildren(nodes, nodes[i], rootTfm)
    }
    return nodes
}

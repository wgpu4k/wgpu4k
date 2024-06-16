@file:OptIn(ExperimentalJsExport::class, DelicateCoroutinesApi::class)

package glb

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.helper.GLTFRenderMode
import io.ygdrasil.wgpu.examples.helper.readGLB
import io.ygdrasil.wgpu.examples.toBitmapHolder
import korlibs.image.format.readBitmap
import korlibs.io.file.std.asMemoryVfsFile
import korlibs.io.lang.TextDecoder
import korlibs.io.util.toByteArray
import kotlinx.coroutines.DelicateCoroutinesApi
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint32Array
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.get


suspend fun uploadGLBModel(
    device: Device,
    rawJSBuffer: ArrayBuffer
): GLBModel {
        println("uploadGLBModelKt")

        val header = Uint32Array(rawJSBuffer, 0, 5)
        if (header.get(0) != 0x46546C67) {
            error("This does not appear to be a glb file?")
        }
        val glbJsonData: dynamic =
            JSON.parse(TextDecoder("utf-8").decode(Uint8Array(rawJSBuffer, 20, header.get(3))))

        val binaryHeader = Uint32Array(rawJSBuffer, 20 + header.get(3), 2)
        val glbBuffer = GLTFBuffer(rawJSBuffer, binaryHeader.get(0), 28 + header.get(3))

        if (28 + header.get(3) + binaryHeader.get(0) != rawJSBuffer.byteLength) {
            console.log("TODO: Multiple binary chunks in file")
        }

        val byteBuffer = rawJSBuffer.toByteArray()
        val gltf2 = byteBuffer.asMemoryVfsFile()
            .readGLB()

        val bufferViews = mutableListOf<GLTFBufferView>()
        for (i in 0 until glbJsonData.bufferViews.length) {
            bufferViews.add(GLTFBufferView(glbBuffer, glbJsonData.bufferViews[i]))
        }


    val images = mutableListOf<Texture>()
        if (glbJsonData["images"] != undefined) {
            for (i in 0 until glbJsonData["images"].length as Int) {
                val imgJson = glbJsonData["images"][i]
                val imageView = GLTFBufferView(glbBuffer, glbJsonData["bufferViews"][imgJson["bufferView"]])

                val image = imageView.buffer.asMemoryVfsFile().readBitmap()
                    .toBMP32()

                // TODO: For glTF we need to look at where an image is used to know
                // if it should be srgb or not. We basically need to pass through
                // the material list and find if the texture which uses this image
                // is used by a metallic/roughness param
                val gpuImg = device.createTexture(
                    TextureDescriptor(
                        size = Size3D(width = image.width, height = image.height, depthOrArrayLayers = 1),
                        format = TextureFormat.rgba8unormsrgb,
                        usage = setOf(TextureUsage.texturebinding, TextureUsage.copydst, TextureUsage.renderattachment)
                    )
                )

                val src = ImageCopyExternalImage(source = image.toBitmapHolder())
                val dst = ImageCopyTextureTagged(texture = gpuImg)
                device.queue.copyExternalImageToTexture(
                    src,
                    dst,
                    image.width to image.height
                )

                images.add(gpuImg)
            }
        }

    val defaultSampler = GLTFSampler(mapOf<Any, Any>(), device)
        val samplers = mutableListOf<GLTFSampler>()
        if (glbJsonData["samplers"] != undefined) {
            for (i in 0 until glbJsonData["samplers"].length as Int) {
                samplers.add(GLTFSampler(glbJsonData["samplers"][i], device))
            }
        }

        val textures = mutableListOf<GLTFTexture>()
        if (glbJsonData.textures != undefined) {
            for (i in 0 until glbJsonData.textures.length as Int) {
                val tex = glbJsonData.textures[i]
                val sampler = if (tex.sampler != undefined) samplers[tex.sampler] else defaultSampler
                textures.add(GLTFTexture(sampler, images[tex.source]))
            }
        }

        val defaultMaterial = GLTFMaterial(mapOf<Any, Any>())
        val materials = mutableListOf<GLTFMaterial>()
        for (i in 0 until glbJsonData.materials.length as Int) {
            materials.add(GLTFMaterial(glbJsonData.materials[i], textures.toTypedArray()))
        }

        val meshes = mutableListOf<GLTFMesh>()
        for (i in 0 until glbJsonData.meshes.length as Int) {
            val mesh = glbJsonData.meshes[i]

            val primitives = mutableListOf<GLTFPrimitive>()
            for (j in 0 until mesh.primitives.length as Int) {
                val prim = mesh.primitives[j]
                var topology = prim["mode"]
                // Default is triangles if mode specified
                if (topology == undefined) {
                    topology = GLTFRenderMode.TRIANGLES.value
                }
                if (topology != GLTFRenderMode.TRIANGLES.value && topology != GLTFRenderMode.TRIANGLE_STRIP.value) {
                    console.warn("Ignoring primitive with unsupported mode ${prim["mode"]}")
                    continue
                }

                var indices: GLTFAccessor? = null
                if (glbJsonData["accessors"][prim["indices"]] != undefined) {
                    val accessor = glbJsonData["accessors"][prim["indices"]]
                    val viewID = accessor["bufferView"]
                    bufferViews[viewID].needsUpload = true
                    bufferViews[viewID].addUsage(BufferUsage.index)
                    indices = GLTFAccessor(bufferViews[viewID], accessor)
                }

                var positions: GLTFAccessor? = null
                var normals: GLTFAccessor? = null
                val texcoords = mutableListOf<GLTFAccessor>()
                for (attr in js("Object.keys(prim['attributes'])") as Array<String>) {
                    val accessor = glbJsonData["accessors"][prim.attributes[attr]]
                    val viewID = accessor["bufferView"]
                    bufferViews[viewID].needsUpload = true
                    bufferViews[viewID].addUsage(BufferUsage.vertex)
                    when (attr) {
                        "POSITION" -> positions = GLTFAccessor(bufferViews[viewID], accessor)
                        "NORMAL" -> normals = GLTFAccessor(bufferViews[viewID], accessor)
                        else -> {
                            if (attr.startsWith("TEXCOORD")) {
                                texcoords.add(GLTFAccessor(bufferViews[viewID], accessor))
                            }
                        }
                    }
                }

                val material = if (prim["material"] != undefined) {
                    materials[prim["material"]]
                } else {
                    defaultMaterial
                }
                val gltfPrim =
                    GLTFPrimitive(indices, positions!!, normals, texcoords.toTypedArray(), material, topology)
                primitives.add(gltfPrim)
            }
            meshes.add(GLTFMesh(mesh["name"], primitives.toTypedArray()))
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

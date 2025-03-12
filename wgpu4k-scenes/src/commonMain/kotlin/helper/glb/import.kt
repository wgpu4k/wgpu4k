package io.ygdrasil.webgpu.examples.helper.glb

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.BufferUsage
import io.ygdrasil.webgpu.GPUDevice
import io.ygdrasil.webgpu.GPUTextureFormat
import io.ygdrasil.webgpu.ImageCopyExternalImage
import io.ygdrasil.webgpu.ImageCopyTextureTagged
import io.ygdrasil.webgpu.Size3D
import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.TextureUsage
import io.ygdrasil.webgpu.examples.toBitmapHolder
import korlibs.image.format.readBitmap
import korlibs.io.file.std.asMemoryVfsFile
import korlibs.math.geom.Matrix4

private val logger = KotlinLogging.logger {}

suspend fun uploadGLBModel(
    device: GPUDevice,
    gltf2: GLTF2,
    textureFormat: GPUTextureFormat,
): GLBModel {
    logger.debug { "uploadGLBModel" }

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

        val gpuImg = device.createTexture(
            TextureDescriptor(
                size = Size3D(width = bitmap.width.toUInt(), height = bitmap.height.toUInt(), depthOrArrayLayers = 1u),
                format = textureFormat,
                usage = setOf(TextureUsage.TextureBinding, TextureUsage.CopyDst, TextureUsage.RenderAttachment)
            )
        )

        val src = ImageCopyExternalImage(source = bitmap.toBitmapHolder(textureFormat))
        val dst = ImageCopyTextureTagged(texture = gpuImg)
        device.queue.copyExternalImageToTexture(
            src,
            dst,
            bitmap.width.toUInt() to bitmap.height.toUInt()
        )

        gpuImg
    }

    val defaultSampler = GLTFSampler(device)
    val samplers = gltf2.samplers.mapIndexed { index, sampler ->
        GLTFSampler(device, sampler)
    }

    val textures = gltf2.textures.mapIndexed { index, texture ->
        val sampler = if (texture.sampler != null) samplers[texture.sampler] else defaultSampler
        GLTFTexture(sampler, images[texture.source])
    }

    val defaultMaterial = GLTFMaterial()
    val materials = gltf2.materials.mapIndexed { index, material ->
        GLTFMaterial(material, textures)
    }

    logger.debug { "uploadGLBModel: meshes" }
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
                    val accessor = gltf2.accessors[primitive.indices]
                    val viewID = accessor.bufferView
                    bufferViews[viewID].needsUpload = true
                    bufferViews[viewID].addUsage(BufferUsage.Index)
                    GLTFAccessor(bufferViews[viewID], accessor)
                } else null

                var positions: GLTFAccessor? = null
                var normals: GLTFAccessor? = null
                val texcoords = mutableListOf<GLTFAccessor>()
                primitive.attributes.forEach { (attribute, index) ->
                    val accessor = gltf2.accessors[index]
                    val viewID = accessor.bufferView
                    bufferViews[viewID].needsUpload = true
                    bufferViews[viewID].addUsage(BufferUsage.Vertex)
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
                    materials[primitive.material]
                } else {
                    defaultMaterial
                }
                GLTFPrimitive(indices, positions!!, normals, texcoords, material, topology)
            }
        GLTFMesh(mesh.name ?: "", primitives)
    }

    logger.debug { "uploadGLBModel: upload ${bufferViews.size} buffer views" }
    // Upload the different views used by meshes
    bufferViews.forEach { bufferView ->
        if (bufferView.needsUpload) {
            bufferView.upload(device)
        }
    }

    logger.debug { "uploadGLBModel: upload default material" }
    defaultMaterial.upload(device)
    logger.debug { "uploadGLBModel: upload ${materials.size} materials" }
    materials.forEach { material -> material.upload(device) }


    val nodes = makeGLTFSingleLevel(gltf2, meshes)
    nodes.forEach { it.upload(device) }
    return GLBModel(nodes)
}

fun makeGLTFSingleLevel(gltF2: GLTF2, meshes: List<GLTFMesh>): List<GLTFNode> {
    logger.debug { "makeGLTFSingleLevel" }
    val rootTfm = Matrix4.IDENTITY.copyToColumns()
    return gltF2.scenes[gltF2.scene]
        .nodes
        .map { gltF2.nodes[it] }
        .flatMap { node ->
            loadNodes(gltF2.nodes, node, rootTfm, meshes)
        }
}

fun loadNodes(
    nodes: List<GLTF2.Node>,
    node: GLTF2.Node,
    parent_transform: FloatArray,
    meshes: List<GLTFMesh>,
    createdNodes: MutableList<GLTFNode> = mutableListOf(),
): List<GLTFNode> {
    logger.debug { "loadNodes: ${node.name}" }
    var tfm = readNodeTransform(node)
    tfm = multiply(tfm, parent_transform, tfm)
    val mesh = meshes[node.mesh!!]
    val createdNode = GLTFNode(node.name ?: "", mesh, tfm)
    createdNodes.add(createdNode)

    node.children.map { index -> nodes[index] }
        .forEach { node -> loadNodes(nodes, node, parent_transform, meshes, createdNodes) }

    return createdNodes
}

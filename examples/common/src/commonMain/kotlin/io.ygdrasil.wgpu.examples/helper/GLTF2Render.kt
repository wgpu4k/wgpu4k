package io.ygdrasil.wgpu.examples.helper

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.BufferBindingLayout
import io.ygdrasil.wgpu.RenderPipelineDescriptor.VertexState
import io.ygdrasil.wgpu.RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute
import io.ygdrasil.wgpu.examples.AutoClosableContext
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeVertexArray
import korlibs.memory.getF32Array
import korlibs.memory.getS16Array
import korlibs.memory.getS8Array
import kotlin.math.floor
import kotlin.math.max


class GLTF2RenderContext(
    val gltf2: GLTF2,
    val device: Device,
    val autoClosableContext: AutoClosableContext,
) {

    val skinBindGroupLayout: BindGroupLayout = createSharedBindGroupLayout(device)
    val buffers: Map<GLTF2.BufferView, Buffer>

    init {

        val bufferUsages = gltf2.bufferViews
            .associateWith { mutableSetOf<BufferUsage>() }

        //Mark each accessor with its intended usage within the vertexShader.
        //Often necessary due to infrequencey with which the BufferView target field is populated.
        gltf2.meshes.forEach { mesh ->
            mesh.primitives.forEach { primitive ->
                primitive.indices?.let {
                    val accessor = gltf2.accessors[primitive.indices]
                    val bufferView = gltf2.bufferViews[accessor.bufferView]
                    bufferUsages[bufferView]?.add(BufferUsage.index)
                }

                primitive.attributes.values.forEach { attribute ->
                    val attributeAccessor = gltf2.accessors[attribute]
                    val attributeBufferView = gltf2.bufferViews[attributeAccessor.bufferView]
                    bufferUsages[attributeBufferView]?.add(BufferUsage.vertex)
                }
            }
        }

        gltf2.skins.forEach { skin ->
            skin.inverseBindMatrices?.let {
                val accessor = gltf2.accessors[skin.inverseBindMatrices]
                val bufferView = gltf2.bufferViews[accessor.bufferView]
                bufferUsages[bufferView]?.apply {
                    add(BufferUsage.uniform)
                    add(BufferUsage.copydst)
                }
            }
        }

        // Create GLTFBufferView objects for all the buffer views in the glTF file
        buffers = gltf2.bufferViews
            .filter { bufferView -> bufferUsages[bufferView]?.isNotEmpty() ?: false }
            .associateWith { bufferView ->
                println("create buffer ${bufferView} with usage ${bufferUsages[bufferView]}")
                val usages = bufferUsages[bufferView] ?: error("buffer usage not found")
                val byteBuffer = gltf2.buffers[bufferView.buffer]
                    .buffer
                    .getS8Array(bufferView.byteOffset, bufferView.byteLength)

                if (usages.first() == BufferUsage.index) {
                    println(
                        gltf2.buffers[bufferView.buffer]
                            .buffer
                            .getS16Array(bufferView.byteOffset, bufferView.byteLength / Short.SIZE_BYTES)
                            .map { it.toString() }
                    )
                } else {
                    val vertex =
                        gltf2.buffers[bufferView.buffer]
                            .buffer
                            .getF32Array(bufferView.byteOffset, bufferView.byteLength / Float.SIZE_BYTES)
                            .map { it.toString() }
                    println(
                        "${vertex.size} " + cubeVertexArray.size
                    )
                }

                device.createBuffer(
                    BufferDescriptor(
                        size = alignTo(bufferView.byteLength, 4).toLong(),
                        usage = usages,
                        mappedAtCreation = true
                    )
                ).also { buffer ->
                    buffer.mapFrom(byteBuffer)
                    buffer.unmap()
                    println(buffer.usage)
                }
            }


        bufferUsages.forEach { println(it) }
    }

    internal fun GLTF2.Mesh.buildRenderPipeline(
        vertexShader: String,
        fragmentShader: String,
        textureFormat: TextureFormat,
        format: TextureFormat,
        bgLayouts: List<BindGroupLayout>
    ): List<RenderPipeline> {
        // We take a pretty simple approach to start. Just loop through all the primitives and
        // build their respective render pipelines
        return primitives.mapIndexed { index, primitive ->
            primitive.buildRenderPipeline(
                vertexShader,
                fragmentShader,
                textureFormat,
                format,
                bgLayouts,
                "PrimitivePipeline$index"
            )
        }
    }


    private fun GLTF2.Primitive.buildRenderPipeline(
        vertexShader: String,
        fragmentShader: String,
        colorFormat: TextureFormat,
        depthFormat: TextureFormat,
        bgLayouts: List<BindGroupLayout>,
        label: String
    ): RenderPipeline {
        if (GLTFRenderMode.of(mode) != GLTFRenderMode.TRIANGLES) error("only triangle mode is supported")

        // For now, just check if the attributeMap contains a given attribute using map.has(), and add it if it does
        // POSITION, NORMAL, TEXCOORD_0, JOINTS_0, WEIGHTS_0 for order
        // Vertex attribute state and shader stage
        val vertexInputShaderStringBuilder = StringBuilder("struct VertexInput {\n")
        val vertexBuffers = this.attributes.map { (attribute, index) ->
            val accessor = gltf2.accessors.get(index)
            val attrString = attribute.str.lowercase().replace("_0", "")
            vertexInputShaderStringBuilder.append(
                "\t@location($index) $attrString: ${accessor.convertToWGSLFormat()},\n"
            )
            val bufferView = gltf2.bufferViews.get(accessor.bufferView)
            val format = accessor.convertToVertexType()
            VertexState.VertexBufferLayout(
                arrayStride = max(format.sizeInByte, bufferView.byteStride).toLong(),
                attributes = arrayOf(
                    VertexAttribute(
                        format = format,
                        offset = accessor.byteOffset.toLong(),
                        shaderLocation = index
                    )
                )
            )
        }
        vertexInputShaderStringBuilder.append("}")
        val vertexInputShaderString = vertexInputShaderStringBuilder.toString()


        val vertexState = VertexState(
            module = device.createShaderModule(
                ShaderModuleDescriptor(code = vertexInputShaderString + vertexShader)
            ),
            buffers = vertexBuffers.toTypedArray()
        )

        val fragmentState = RenderPipelineDescriptor.FragmentState(
            module = device.createShaderModule(
                ShaderModuleDescriptor(vertexInputShaderString + fragmentShader)
            ),
            targets = arrayOf(
                RenderPipelineDescriptor.FragmentState.ColorTargetState(format = colorFormat)
            )
        )


        val pipelineLayout = device.createPipelineLayout(
            PipelineLayoutDescriptor(
                bindGroupLayouts = bgLayouts.toTypedArray(),
                label = "$label.pipelineLayout"
            )
        )

        val renderPipelineDescriptor = RenderPipelineDescriptor(
            layout = pipelineLayout,
            label = "$label.pipeline",
            vertex = vertexState,
            fragment = fragmentState,
            depthStencil = RenderPipelineDescriptor.DepthStencilState(
                format = depthFormat,
                depthWriteEnabled = true,
                depthCompare = CompareFunction.less
            )
        )

        return device.createRenderPipeline(renderPipelineDescriptor)
    }

}

fun GLTF2.Node.renderDrawables(
    renderPipeline: RenderPipeline,
    gltf2: GLTF2,
    passEncoder: RenderPassEncoder,
    bindGroups: List<BindGroup>
) {

    if (mesh != null) {
        val mesh = gltf2.meshes[mesh]
        mesh.render(renderPipeline, gltf2, passEncoder, bindGroups)
    }

    // Render any of its children
    children.forEach { child -> gltf2.nodes[child].renderDrawables(renderPipeline, gltf2, passEncoder, bindGroups) }

}

private fun GLTF2.Mesh.render(
    renderPipeline: RenderPipeline,
    gltf2: GLTF2,
    passEncoder: RenderPassEncoder,
    bindGroups: List<BindGroup>
) {
    // We take a pretty simple approach to start. Just loop through all the primitives and
    // call their individual draw methods
    primitives.forEach {
        it.render(renderPipeline, gltf2, passEncoder, bindGroups)
    }
}

private fun GLTF2.Primitive.render(
    renderPipeline: RenderPipeline,
    gltf2: GLTF2,
    renderPassEncoder: RenderPassEncoder,
    bindGroups: List<BindGroup>
) {
    renderPassEncoder.setPipeline(renderPipeline);
    bindGroups.forEachIndexed { idx, bg ->
        renderPassEncoder.setBindGroup(idx, bg)
    }

    //if skin do something with bone bind group
    /*attributes.forEach { (attr, idx) ->
        renderPassEncoder.setVertexBuffer(
            idx,
            attributeMap[attr]?.view?.gpuBuffer,
            attributeMap[attr]?.byteOffset,
            attributeMap[attr]?.byteLength
        )
    }

    if (attributeMap["INDICES"] != null) {
        renderPassEncoder.setIndexBuffer(
            attributeMap["INDICES"]?.view?.gpuBuffer,
            attributeMap["INDICES"]?.vertexType,
            attributeMap["INDICES"]?.byteOffset,
            attributeMap["INDICES"]?.byteLength
        )
        renderPassEncoder.drawIndexed(attributeMap["INDICES"]?.count ?: 0)
    } else {
        renderPassEncoder.draw(attributeMap["POSITION"]?.count ?: 0)
    }*/
    TODO()
}

private fun createSharedBindGroupLayout(device: Device) = device.createBindGroupLayout(
    BindGroupLayoutDescriptor(
        label = "StaticGLTFSkin.bindGroupLayout",
        entries = arrayOf(
            // Holds the initial joint matrices buffer
            BindGroupLayoutDescriptor.Entry(
                binding = 0,
                visibility = setOf(ShaderStage.vertex),
                bindingType = BufferBindingLayout(
                    type = BufferBindingType.readonlystorage
                )
            ),
            // Holds the inverse bind matrices buffer
            BindGroupLayoutDescriptor.Entry(
                binding = 1,
                visibility = setOf(ShaderStage.vertex),
                bindingType = BufferBindingLayout(
                    type = BufferBindingType.readonlystorage
                )
            )
        )
    )
)


internal fun GLTF2.Accessor.convertToVertexType(): VertexFormat = when (type) {
    GLTF2.AccessorType.VEC2 -> when (componentType) {
        5126 -> VertexFormat.float32x2
        else -> TODO("convertToVertexType $componentType")
    }

    GLTF2.AccessorType.VEC3 -> when (componentType) {
        5126 -> VertexFormat.float32x3
        else -> TODO("convertToVertexType $componentType")
    }

    GLTF2.AccessorType.VEC4 -> when (componentType) {
        5126 -> VertexFormat.float32x4
        5121 -> VertexFormat.uint8x4
        else -> TODO("convertToVertexType $componentType")
    }

    else -> TODO("convertToVertexType $type")
}

internal fun GLTF2.Accessor.convertToWGSLFormat(): String {
    return "${type.convertToWGSLFormat()}${componentType.convertToWGSLFormat()}"
}

/**
 * https://registry.khronos.org/glTF/specs/2.0/glTF-2.0.html#accessor-data-types
 */
private fun Int.convertToWGSLFormat(): String = when (this) {
    5126 -> "f"
    5121 -> "u"
    else -> TODO("$this")
}

private fun GLTF2.AccessorType.convertToWGSLFormat(): String = when (this) {
    GLTF2.AccessorType.SCALAR -> TODO()
    GLTF2.AccessorType.VEC2 -> "vec2"
    GLTF2.AccessorType.VEC3 -> "vec3"
    GLTF2.AccessorType.VEC4 -> "vec4"
    GLTF2.AccessorType.MAT2 -> TODO()
    GLTF2.AccessorType.MAT3 -> TODO()
    GLTF2.AccessorType.MAT4 -> TODO()
}

fun alignTo(value: Int, align: Int): Int {
    return floor(((value + align - 1) / align).toDouble()).toInt() * align
}

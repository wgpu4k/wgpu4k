package io.ygdrasil.wgpu.examples.helper

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.BufferBindingLayout


fun createSharedBindGroupLayout(device: Device) = device.createBindGroupLayout(
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

internal fun GLTF2.Mesh.buildRenderPipeline(
    scene: GLTF2,
    device: Device,
    vertexShader: String,
    fragmentShader: String,
    textureFormat: TextureFormat,
    format: TextureFormat,
    bgLayouts: List<BindGroupLayout>
) {
    // We take a pretty simple approach to start. Just loop through all the primitives and
    // build their respective render pipelines
    this.primitives.forEachIndexed { index, primitive ->
        primitive.buildRenderPipeline(
            scene,
            device,
            vertexShader,
            fragmentShader,
            textureFormat,
            format,
            bgLayouts,
            "PrimitivePipeline$index"
        )
    }
}

internal fun GLTF2.Primitive.buildRenderPipeline(
    scene: GLTF2,
    device: Device,
    vertexShader: String,
    fragmentShader: String,
    textureFormat: TextureFormat,
    format: TextureFormat,
    bgLayouts: List<BindGroupLayout>,
    label: String
) {
    // For now, just check if the attributeMap contains a given attribute using map.has(), and add it if it does
    // POSITION, NORMAL, TEXCOORD_0, JOINTS_0, WEIGHTS_0 for order
    // Vertex attribute state and shader stage
    val vertexInputShaderStringBuilder = StringBuilder("struct VertexInput {\n")
    val vertexBuffers = this.attributes.map { (attribute, index) ->
        val accessor = scene.accessors.get(index)
        val attrString = attribute.str.lowercase().replace("_0", "")
        vertexInputShaderStringBuilder.append(
            "\t@location($index) $attrString: ${scene.accessors.get(index).convertToWGSLFormat()},\n"
        )

        // TODO
        /*RenderPipelineDescriptor.VertexState.VertexBufferLayout(
            arrayStride = accessor.byteStride,
            attributes = arrayOf(
                VertexAttribute(
                    format = accessor.vertexType,
                    offset = accessor.byteOffset.toLong(),
                    shaderLocation = index
                )
            )
        )*/
    }
    vertexInputShaderStringBuilder.append("}")
    val vertexInputShaderString = vertexInputShaderStringBuilder.toString()

    /*
        const vertexState: GPUVertexState = {
            // Shader stage info
            module: device.createShaderModule({
                code: VertexInputShaderString + vertexShader,
            }),
            buffers: vertexBuffers,
        };

        const fragmentState: GPUFragmentState = {
            // Shader info
            module: device.createShaderModule({
                code: VertexInputShaderString + fragmentShader,
            }),
            // Output render target info
            targets: [{format: colorFormat}],
        };

        // Our loader only supports triangle lists and strips, so by default we set
        // the primitive topology to triangle list, and check if it's instead a triangle strip
        const primitive: GPUPrimitiveState = {topology: 'triangle-list'};
        if (this.topology == GLTFRenderMode.TRIANGLE_STRIP) {
            primitive.topology = 'triangle-strip';
            primitive.stripIndexFormat = this.attributeMap['INDICES'].vertexType;
        }

        const layout: GPUPipelineLayout = device.createPipelineLayout({
            bindGroupLayouts: bgLayouts,
            label: `${label}.pipelineLayout`,
        });

        const rpDescript: GPURenderPipelineDescriptor = {
            layout: layout,
            label: `${label}.pipeline`,
            vertex: vertexState,
            fragment: fragmentState,
            primitive: primitive,
            depthStencil: {
                format: depthFormat,
                depthWriteEnabled: true,
                depthCompare: 'less',
            },
        };

        this.renderPipeline = device.createRenderPipeline(rpDescript);
     */
    TODO("Not yet implemented")
}

private fun GLTF2.Accessor.convertToWGSLFormat(): String {
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


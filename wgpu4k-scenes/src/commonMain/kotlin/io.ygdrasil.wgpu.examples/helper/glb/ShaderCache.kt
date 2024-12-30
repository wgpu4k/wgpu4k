package io.ygdrasil.webgpu.examples.helper.glb

import io.ygdrasil.webgpu.Device
import io.ygdrasil.webgpu.ShaderModule
import io.ygdrasil.webgpu.ShaderModuleDescriptor

class ShaderCache(private val device: Device) {
    private val shaderCache: MutableMap<String, ShaderModule> = mutableMapOf()

    fun getShader(hasNormals: Boolean, hasUVs: Boolean, hasColorTexture: Boolean): ShaderModule {
        var shaderID = "io.ygdrasil.wgpu.examples/helper/glb"
        if (hasNormals) {
            shaderID += "n"
        }
        if (hasUVs) {
            shaderID += "uv"
        }
        if (hasColorTexture) {
            shaderID += "colortex"
        }
        if (!shaderCache.containsKey(shaderID)) {
            val shaderSource = generateGLTFShader(hasNormals, hasUVs, hasColorTexture)
            println("new shader added with id $shaderID and code $shaderSource")
            shaderCache[shaderID] = device.createShaderModule(ShaderModuleDescriptor(code = shaderSource))
        }
        return shaderCache[shaderID]!!
    }
}

fun generateGLTFShader(hasNormals: Boolean, hasUVs: Boolean, hasColorTexture: Boolean): String {
    var typeDefs =
        """
        alias float2 = vec2<f32>;
        alias float3 = vec3<f32>;
        alias float4 = vec4<f32>;
        """

    var vertexInputStruct =
        """
    struct VertexInput {
        @location(0) position: float3,
        """

    var vertexOutputStruct =
        """
        struct VertexOutput {
            @builtin(position) position: float4,
        """

    if (hasNormals) {
        vertexInputStruct +=
            """
                @location(1) normal: float3,
                """
        vertexOutputStruct +=
            """
                @location(1) normal: float3,
            """
    }
    if (hasUVs) {
        vertexInputStruct +=
            """
                @location(2) uv: float2,
            """
        vertexOutputStruct +=
            """
                @location(2) uv: float2,
            """
    }
    vertexInputStruct += "};"
    vertexOutputStruct += "};"

    var vertexUniformParams =
        """
            struct Mat4Uniform {
                    m: mat4x4<f32>,
            };

            @group(0) @binding(0)
            var<uniform> view_proj: Mat4Uniform;
            @group(1) @binding(0)
            var<uniform> node_transform: Mat4Uniform;
            """

    var vertexStage = vertexInputStruct + vertexOutputStruct + vertexUniformParams +
            """
            @vertex
            fn vertex_main(vin: VertexInput) -> VertexOutput {
            var vout: VertexOutput;
            vout.position = view_proj.m * node_transform.m * float4(vin.position, 1.0);
            """
    if (hasNormals) {
        vertexStage +=
            """
                vout.normal = vin.normal;
                """
    }
    if (hasUVs) {
        vertexStage +=
            """
                vout.uv = vin.uv;
                """
    }
    vertexStage +=
        """
            return vout;
        }"""

    var fragmentParams =
        """
            struct MaterialParams {
                base_color_factor: float4,
                emissive_factor: float4,
                metallic_factor: f32,
                roughness_factor: f32,
            };

            @group(2) @binding(0)
            var<uniform> material: MaterialParams;
            """

    if (hasColorTexture) {
        fragmentParams +=
            """
                @group(2) @binding(1)
                var base_color_sampler: sampler;
                @group(2) @binding(2)
                var base_color_texture: texture_2d<f32>;
                """
    }

    var fragmentStage = fragmentParams +
            """
            fn linear_to_srgb(x: f32) -> f32 {
            if (x <= 0.0031308) {
                return 12.92 * x;
            }
            return 1.055 * pow(x, 1.0 / 2.4) - 0.055;
        }

        @fragment
        fn fragment_main(fin: VertexOutput) -> @location(0) float4 {
            var color = float4(material.base_color_factor.xyz, 1.0);
            """

    if (hasUVs && hasColorTexture) {
        fragmentStage +=
            """
                var texture_color = textureSample(base_color_texture, base_color_sampler, fin.uv);
                if (texture_color.a < 0.001) {
                    discard;
                }
                color = float4(material.base_color_factor.xyz * texture_color.xyz, 1.0);
                """
    }

    fragmentStage +=
        """
            color.x = linear_to_srgb(color.x);
            color.y = linear_to_srgb(color.y);
            color.z = linear_to_srgb(color.z);
            color.w = 1.0;
            return color;
        }
            """

    return typeDefs + vertexStage + fragmentStage
}

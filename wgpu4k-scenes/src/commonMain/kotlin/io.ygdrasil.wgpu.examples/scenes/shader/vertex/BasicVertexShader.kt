package io.ygdrasil.wgpu.examples.scenes.shader.vertex

// language=wgsl
const val basicVertexShader = """
struct Uniforms {
  modelViewProjectionMatrix : mat4x4<f32>,
}
@binding(0) @group(0) var<uniform> uniforms : Uniforms;

struct VertexOutput {
  @builtin(position) Position : vec4<f32>,
  @location(0) fragUV : vec2<f32>,
  @location(1) fragPosition: vec4<f32>,
}

@vertex
fn main(
  @location(0) position : vec4<f32>,
  @location(1) uv : vec2<f32>
) -> VertexOutput {
  var output : VertexOutput;
  output.Position = uniforms.modelViewProjectionMatrix * position;
  output.fragUV = uv;
  output.fragPosition = 0.5 * (position + vec4(1.0, 1.0, 1.0, 1.0));
  return output;
}

"""

const val basicVertexShader2 = """
struct Uniforms {
  modelViewProjectionMatrix : mat4x4<f32>,
}
@binding(0) @group(0) var<uniform> uniforms : Uniforms;

@vertex
fn main(
	@location(0) normal: vec3f,
	@location(1) position2: vec3f,
) -> @builtin(position) vec4f {
    var position : vec4f = vec4f(position2.x, position2.y, position2.z, 0f);
    return uniforms.modelViewProjectionMatrix * position;
}

"""
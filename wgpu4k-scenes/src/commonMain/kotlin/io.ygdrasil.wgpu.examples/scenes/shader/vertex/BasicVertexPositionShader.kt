package io.ygdrasil.webgpu.examples.scenes.shader.vertex

// language=wgsl
const val basicVertexPositionShader = """
struct Uniforms {
  modelViewProjectionMatrix : mat4x4<f32>,
}
@binding(0) @group(0) var<uniform> uniforms : Uniforms;

@vertex
fn main(
  @builtin(vertex_index) VertexIndex : u32
) -> @builtin(position) vec4f  {
  var pos = array<vec2f, 3>(
    vec2(0.0, 0.5),
    vec2(-0.5, -0.5),
    vec2(0.5, -0.5)
  );

  return uniforms.modelViewProjectionMatrix * vec4f(pos[VertexIndex], 0.0, 1.0);
}

"""
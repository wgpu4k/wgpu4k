package io.ygdrasil.webgpu.examples.scenes.shader.fragment

// language=wgsl
const val vertexPositionColorShader = """
@fragment
fn main(
  @location(0) fragUV: vec2<f32>,
  @location(1) fragPosition: vec4<f32>
) -> @location(0) vec4<f32> {
  return fragPosition;
}
"""
package io.ygdrasil.wgpu.examples.scenes.shader

const val fragmentVertexPositionColorShader = """
@fragment
fn main(
  @location(0) fragUV: vec2<f32>,
  @location(1) fragPosition: vec4<f32>
) -> @location(0) vec4<f32> {
  return fragPosition;
}
"""
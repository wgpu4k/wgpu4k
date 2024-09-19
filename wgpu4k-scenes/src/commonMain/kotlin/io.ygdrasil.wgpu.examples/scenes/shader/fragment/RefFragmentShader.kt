package io.ygdrasil.wgpu.examples.scenes.shader.fragment

// language=wgsl
const val redFragmentShader = """
@fragment
fn main() -> @location(0) vec4f {
  return vec4(1.0, 0.0, 0.0, 1.0);
}
"""
package io.ygdrasil.wgpu.examples.helper.material

import korlibs.image.bitmap.Bitmap
import korlibs.image.color.Colors
import korlibs.image.color.RGBA

data class PBRMaterial3D(
    val emission: Light = LightColor(Colors.BLACK),
    val ambient: Light = LightColor(Colors.BLACK),
    val diffuse: Light = LightColor(Colors.BLACK),
	//val specular: Light = LightColor(RGBA.float(.5f, .5f, .5f, 1f)),
    val specular: Light = LightColor(Colors.BLACK),
    val shininess: Float = .5f,
    val indexOfRefraction: Float = 1f,
    val occlusionTexture: Bitmap? = null,
    val doubleSided: Boolean = false,
) {
    companion object {
        val DEFAULT = PBRMaterial3D(diffuse = LightColor(Colors.WHITE))
    }

    val hasTexture: Boolean = emission.hasTexture || ambient.hasTexture || diffuse.hasTexture || specular.hasTexture

    open class Light(val kind: String, val hasTexture: Boolean)

	data class LightColor(val color: RGBA) : Light("color", hasTexture = false)
    data class LightTexture(val bitmap: Bitmap?) : Light("texture", hasTexture = true)

    val kind: String = "${emission.kind}_${ambient.kind}_${diffuse.kind}_${specular.kind}"
}

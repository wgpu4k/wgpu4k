package io.ygdrasil.webgpu.examples

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.ImageBitmapHolder
import io.ygdrasil.webgpu.TextureFormat
import io.ygdrasil.webgpu.examples.helper.glb.GLTF2
import io.ygdrasil.webgpu.examples.helper.glb.readGLB
import korlibs.image.bitmap.Bitmap32
import korlibs.image.color.BGRA
import korlibs.image.color.ColorFormat
import korlibs.image.color.RGBA
import korlibs.image.format.readBitmap
import korlibs.io.file.Vfs
import korlibs.io.file.std.resourcesVfs
import korlibs.io.file.std.rootLocalVfs

private val logger = KotlinLogging.logger {}

expect var customVfs: Vfs

interface AssetManager {

    val Di3d: ImageBitmapHolder

    val cubemapPosx: ImageBitmapHolder
    val cubemapNegx: ImageBitmapHolder
    val cubemapPosy: ImageBitmapHolder
    val cubemapNegy: ImageBitmapHolder
    val cubemapPosz: ImageBitmapHolder
    val cubemapNegz: ImageBitmapHolder

    val webgpu4kotlin: ImageBitmapHolder

    val boxMesh: GLTF2
}

suspend fun bitmapFrom(textureFormat: TextureFormat, path: String): ImageBitmapHolder = (resourcesVfs[path]
    .takeIfExists() ?: rootLocalVfs[path].takeIfExists() ?: customVfs[path])
    .readBitmap()
    .toBMP32()
    .toBitmapHolder(textureFormat)

suspend fun glt2From(path: String): GLTF2 = (resourcesVfs[path]
    .takeIfExists() ?: rootLocalVfs[path].takeIfExists() ?: customVfs[path])
    .readGLB()

fun Bitmap32.toBitmapHolder(textureFormat: TextureFormat): ImageBitmapHolder {
    val format = when  {
        textureFormat.name.lowercase().contains("rgba") -> RGBA
        textureFormat.name.lowercase().contains("bgra") -> BGRA
        else -> error("dont know how to convert this format $textureFormat")
    }
    logger.debug { "will convert loaded image to format ${format::class.simpleName}" }
    return toBitmapHolder(format)
}

internal expect fun Bitmap32.toBitmapHolder(textureFormat: ColorFormat): ImageBitmapHolder

suspend fun genericAssetManager(textureFormat: TextureFormat, resourceBasePath: String = "") = GenericAssetManager(
    bitmapFrom(textureFormat, "${resourceBasePath}assets/img/Di-3d.png"),
    bitmapFrom(textureFormat, "${resourceBasePath}assets/img/cubemap/posx.png"),
    bitmapFrom(textureFormat, "${resourceBasePath}assets/img/cubemap/negx.png"),
    bitmapFrom(textureFormat, "${resourceBasePath}assets/img/cubemap/posy.png"),
    bitmapFrom(textureFormat, "${resourceBasePath}assets/img/cubemap/negy.png"),
    bitmapFrom(textureFormat, "${resourceBasePath}assets/img/cubemap/posz.png"),
    bitmapFrom(textureFormat, "${resourceBasePath}assets/img/cubemap/negz.png"),
    bitmapFrom(textureFormat, "${resourceBasePath}assets/img/webgpu4kotlin.png"),
    glt2From("${resourceBasePath}assets/gltf/DamagedHelmet.glb"),
)

class GenericAssetManager(
    override val Di3d: ImageBitmapHolder,

    override val cubemapPosx: ImageBitmapHolder,
    override val cubemapNegx: ImageBitmapHolder,
    override val cubemapPosy: ImageBitmapHolder,
    override val cubemapNegy: ImageBitmapHolder,
    override val cubemapPosz: ImageBitmapHolder,
    override val cubemapNegz: ImageBitmapHolder,

    override val webgpu4kotlin: ImageBitmapHolder,

    override val boxMesh: GLTF2,
) : AssetManager
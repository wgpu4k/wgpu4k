package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.ImageBitmapHolder
import io.ygdrasil.wgpu.examples.helper.glb.GLTF2
import io.ygdrasil.wgpu.examples.helper.glb.readGLB
import korlibs.image.bitmap.Bitmap32
import korlibs.image.format.readBitmap
import korlibs.io.file.std.resourcesVfs
import korlibs.io.file.std.rootLocalVfs

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

suspend fun bitmapFrom(path: String): ImageBitmapHolder = (resourcesVfs[path]
    .takeIfExists() ?: rootLocalVfs[path])
    .readBitmap()
    .toBMP32()
    .toBitmapHolder()

suspend fun glt2From(path: String): GLTF2 = (resourcesVfs[path]
    .takeIfExists() ?: rootLocalVfs[path])
    .readGLB()

expect fun Bitmap32.toBitmapHolder(): ImageBitmapHolder

suspend fun genericAssetManager(resourceBasePath: String = "") = GenericAssetManager(
    bitmapFrom("${resourceBasePath}assets/img/Di-3d.png"),
    bitmapFrom("${resourceBasePath}assets/img/cubemap/posx.png"),
    bitmapFrom("${resourceBasePath}assets/img/cubemap/negx.png"),
    bitmapFrom("${resourceBasePath}assets/img/cubemap/posy.png"),
    bitmapFrom("${resourceBasePath}assets/img/cubemap/negy.png"),
    bitmapFrom("${resourceBasePath}assets/img/cubemap/posz.png"),
    bitmapFrom("${resourceBasePath}assets/img/cubemap/negz.png"),
    bitmapFrom("${resourceBasePath}assets/img/webgpu4kotlin.png"),
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

package io.ygdrasil.webgpu.examples

import io.ygdrasil.webgpu.ImageBitmapHolder
import io.ygdrasil.webgpu.asArrayBuffer
import io.ygdrasil.webgpu.createJsObject
import js.buffer.ArrayBuffer
import js.typedarrays.Int8Array
import js.typedarrays.toByteArray
import korlibs.image.bitmap.Bitmap32
import korlibs.image.color.ColorFormat
import korlibs.io.file.VfsFile
import korlibs.io.file.std.asMemoryVfsFile
import web.http.GET
import web.http.RequestCache
import web.http.RequestInit
import web.http.RequestMethod
import web.http.arrayBuffer
import web.http.default
import web.http.fetch

/**
 * Loads a file from the given URL and returns it as a ByteArray
 * @param url The URL to load the file from
 * @return ByteArray containing the file data
 * @throws Exception if the request fails or returns an error status
 */
suspend fun loadFileAsByteArray(
    url: String
): ByteArray {
    // Configure request options
    val options = createJsObject<RequestInit>()
    options.method = RequestMethod.GET
    options.cache = RequestCache.default

    try {
        // Fetch the file
        val response = fetch(url, options)

        if (!response.ok) {
            throw Exception("HTTP ${response.status}: ${response.statusText}")
        }

        // Get as ArrayBuffer and convert to ByteArray
        val arrayBuffer = response.arrayBuffer()
        return arrayBufferToByteArray(arrayBuffer)

    } catch (e: Exception) {
        throw Exception("Failed to load file from $url: ${e.message}")
    }
}

/**
 * Converts an ArrayBuffer to a ByteArray
 */
private fun arrayBufferToByteArray(arrayBuffer: ArrayBuffer): ByteArray {
    return Int8Array(arrayBuffer)
        .toByteArray()
}

actual suspend fun String.asVsfFile(): VfsFile = loadFileAsByteArray(this)
    .asMemoryVfsFile()

internal actual fun Bitmap32.toBitmapHolder(textureFormat: ColorFormat): ImageBitmapHolder {
    return ImageBitmapHolder(width.toUInt(), height.toUInt(), extractBytes(textureFormat).asArrayBuffer())
}
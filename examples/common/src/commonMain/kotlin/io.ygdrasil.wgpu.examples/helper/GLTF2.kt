@file:OptIn(ExperimentalSerializationApi::class)

package io.ygdrasil.wgpu.examples.helper

import io.ygdrasil.wgpu.examples.helper.material.PBRMaterial3D
import korlibs.datastructure.*
import korlibs.encoding.*
import korlibs.image.bitmap.*
import korlibs.image.color.*
import korlibs.image.format.*
import korlibs.io.file.*
import korlibs.io.file.std.*
import korlibs.io.lang.*
import korlibs.io.net.*
import korlibs.io.stream.*
import korlibs.logger.*
import korlibs.math.*
import korlibs.math.geom.*
import korlibs.math.interpolation.*
import korlibs.memory.*
import korlibs.time.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import kotlin.js.JsExport
import kotlin.jvm.JvmInline

suspend fun VfsFile.readGLB(options: GLTF2.ReadOptions = GLTF2.ReadOptions.DEFAULT): GLTF2 = GLTF2.readGLB(this, options = options)
suspend fun VfsFile.readGLTF2(options: GLTF2.ReadOptions = GLTF2.ReadOptions.DEFAULT): GLTF2 {
    if (this.extensionLC == "glb") return readGLB(options)
    return GLTF2.readGLTF(this.readString(), null, this, options)
}

// https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/Specification.adoc
// https://github.com/KhronosGroup/glTF-Sample-Models/tree/master/2.0
// https://github.com/javagl/glTF-Tutorials/blob/master/gltfTutorial/gltfTutorial_019_SimpleSkin.md
// https://github.com/syoyo/tinygltf
@Serializable
data class GLTF2(
    override var name: String? = null,
    val asset: Asset = Asset(),
    val extensionsUsed: List<String> = emptyList(),
    val extensionsRequired: List<String> = emptyList(),
    val scene: Int = -1,
    val images: List<Image> = emptyList(),
    val textures: List<Texture> = emptyList(),
    val scenes: List<Scene> = emptyList(),
    val nodes: List<Node> = emptyList(),
    val meshes: List<Mesh> = emptyList(),
    val skins: List<Skin> = emptyList(),
    val animations: List<Animation> = emptyList(),
    val buffers: List<Buffer> = emptyList(),
    val bufferViews: List<BufferView> = emptyList(),
    val accessors: List<Accessor> = emptyList(),
    val materials: List<Material> = emptyList(),
    val samplers: List<Sampler> = emptyList(),
    val cameras: List<Camera> = emptyList(),
    override val extensions: JsonElement? = null,
    override val extras: JsonElement? = null,
) : GLTFProperty(), GLTF2Holder {
    override val gltf: GLTF2 get() = this

    val materials3D: List<PBRMaterial3D> by lazy {
        materials.map { gmat ->
            val gMetallicRoughness = gmat.pbrMetallicRoughness
            val baseColorFactor = gMetallicRoughness.baseColorFactor ?: FloatArray(0)
            PBRMaterial3D(
                diffuse = gMetallicRoughness.baseColorTexture
                    ?.let { PBRMaterial3D.LightTexture(it.getTexture(this)) }
                //?.let { null }
                    ?: PBRMaterial3D.LightColor(
                        RGBA.float(
                        baseColorFactor.getOrElse(0) { 1f },
                        baseColorFactor.getOrElse(1) { 1f },
                        baseColorFactor.getOrElse(2) { 1f },
                        baseColorFactor.getOrElse(3) { 1f }
                    )),
                occlusionTexture = gmat.occlusionTexture?.let { it.getTexture(gltf) },
                doubleSided = gmat.doubleSided,
            )
        }
    }

    private suspend fun ensureLoad(file: VfsFile?, bin: ByteArray?) {
        ensureLoadBuffers(file, bin)
        ensureLoadImages(file)
    }

    private suspend fun ensureLoadBuffers(file: VfsFile?, bin: ByteArray?) {
        for (buffer in buffers) {
            if (buffer.optBuffer == null) {
                val vfile = buffer.uri?.let { resolveUri(file, it) }?.readBytes()
                val (bytes, time) = measureTimeWithResult {
                    vfile
                        ?: bin
                        ?: error("Couldn't load buffer : $buffer")
                }
                println("Loaded $vfile in ... $time")
                buffer.optBuffer = Buffer(bytes)
            }
        }
    }

    private suspend fun ensureLoadImages(file: VfsFile?) {
        for (image in images) {
            if (image.bitmap == null) {

                val vfile = image.uri?.let { resolveUri(file, it) }

                val (buffer, time) = measureTimeWithResult {
                    (vfile ?: (if (image.bufferView >= 0) bufferViews[image.bufferView].slice(this).asInt8().getArray().asMemoryVfsFile() else null))

                }
                val (bitmap, timeBitmap) = measureTimeWithResult {
                    buffer?.let { nativeImageFormatProvider.decode(it) } ?: Bitmaps.transparent.bmp
                    //buffer?.readBitmap() ?: Bitmaps.transparent.bmp
                }
                println("$vfile read in $time, decoded in $timeBitmap...")
                image.bitmap = bitmap
            }
        }
    }

    data class ReadOptions(
        val ignoreUnknownKeys: Boolean = false,
    ) {
        companion object {
            val DEFAULT = ReadOptions()
        }
    }

    fun resolveUri(file: VfsFile?, uri: String): VfsFile? {
        if (uri.startsWith("data:")) {
            val dataPart = uri.substringBefore(',', "")
            val content = uri.substringAfter(',')
            return content.fromBase64().asMemoryVfsFile("buffer.bin")
        }
        return file?.parent?.get(URL.decodeComponent(uri))
    }

    @Serializable
    data class Asset(
        override var name: String? = null,
        /** The glTF version in the form of `<major>.<minor>` that this asset targets. */
        val version: String = "2.0",
        /** Tool that generated this glTF model.  Useful for debugging. */
        val generator: String? = null,
        /** A copyright message suitable for display to credit the content creator. */
        val copyright: String? = null,
        /** The minimum glTF version in the form of `<major>.<minor>` that this asset targets. This property **MUST NOT** be greater than the asset version. */
        val minVersion: String? = null,
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty()
    @Serializable
    data class Scene(
        override var name: String? = null,
        /** The indices of each root node */
        val nodes: IntArray = intArrayOf(),
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty()
    @Serializable
    data class Node(
        override var name: String? = null,
        /** The index of the camera referenced by this node. */
        val camera: Int = -1,
        /** The index of the skin referenced by this node. When a skin is referenced by a node within a scene, all joints used by the skin **MUST** belong to the same scene. When defined, `mesh` **MUST** also be defined. */
        val skin: Int = -1,
        /** The index of the mesh in this node. */
        val mesh: Int? = null,
        /** The indices of this node's children. */
        val children: IntArray = intArrayOf(),
        /** The node's non-uniform scale, given as the scaling factors along the x, y, and z axes. */
        val scale: FloatArray? = null,
        /** The node's translation along the x, y, and z axes. */
        val translation: FloatArray? = null,
        /** The node's unit quaternion rotation in the order (x, y, z, w), where w is the scalar. */
        val rotation: FloatArray? = null,
        /** A floating-point 4x4 transformation matrix stored in column-major order. */
        val matrix: FloatArray? = null,
        /** The weights of the instantiated morph target. The number of array elements **MUST** match the number of morph targets of the referenced mesh. When defined, `mesh` **MUST** also be defined. */
        val weights: IntArray? = null,
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty() {
        val mmatrix by lazy {
            var out: Matrix4 = Matrix4.IDENTITY
            // column-major order
            if (matrix != null) out = Matrix4.fromColumns(matrix)
            if (translation != null) out *= Matrix4.translation(translation[0], translation[1], translation[2])
            if (rotation != null) out *= Quaternion(rotation[0], rotation[1], rotation[2], rotation[3]).toMatrix()
            if (scale != null) out *= Matrix4.scale(scale[0], scale[1], scale[2])
            out
        }
    }
    @Serializable
    data class Mesh(
        override var name: String? = null,
        /** An array of primitives, each defining geometry to be rendered. */
        val primitives: List<Primitive> = emptyList(),
        /** Array of weights to be applied to the morph targets. The number of array elements **MUST** match the number of morph targets. */
        val weights: FloatArray? = null,
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty() {
        fun getBounds(gltf: GLTF2): AABB3D = primitives.map { it.getBounds(gltf) }.combineBounds()

        @Transient
        val weightsVector: Vector4 = if (weights != null) Vector4.func { weights.getOrElse(it) { 0f } } else Vector4.ZERO
    }
    @JvmInline
    @Serializable
    value class PrimitiveAttribute(val str: String) {
        companion object {
            val POSITION = PrimitiveAttribute("POSITION")
            val NORMAL = PrimitiveAttribute("NORMAL")
            val TANGENT = PrimitiveAttribute("TANGENT")
            val TEXCOORD_0 = PrimitiveAttribute("TEXCOORD_0")
            val TEXCOORD_1 = PrimitiveAttribute("TEXCOORD_1")
            val JOINTS_0 = PrimitiveAttribute("JOINTS_0")
            val WEIGHTS_0 = PrimitiveAttribute("WEIGHTS_0")

            fun TEXCOORD(n: Int): PrimitiveAttribute = PrimitiveAttribute("TEXCOORD_$n")
            fun COLOR(n: Int): PrimitiveAttribute = PrimitiveAttribute("COLOR_$n")
        }

        val index: Int get() {
            val lc = str.last()
            return if (lc in '0'..'9') lc - '0' else 0
        }

        val isPosition: Boolean get() = str == "POSITION"
        val isNormal: Boolean get() = str == "NORMAL"
        val isTangent: Boolean get() = str == "TANGENT"
        val isColor0: Boolean get() = str == "COLOR_0"
        val isTexcoord: Boolean get() = str.startsWith("TEXCOORD", ignoreCase = true)
        fun isTexcoord(n: Int): Boolean = isTexcoord && index == n

        val isJoints: Boolean get() = str.startsWith("JOINTS", ignoreCase = true)
        fun isJoints(n: Int): Boolean = isJoints && index == n

        val isWeights: Boolean get() = str.startsWith("WEIGHTS", ignoreCase = true)
        fun isWeights(n: Int): Boolean = isWeights && index == n
    }
    @Serializable
    data class Primitive(
        override var name: String? = null,
        /** A plain JSON object, where each key corresponds to a mesh attribute semantic and each value is the index of the accessor containing attribute's data. */
        val attributes: Map<PrimitiveAttribute, Int> = emptyMap(),
        /** The index of the accessor that contains the vertex indices.  When this is undefined, the primitive defines non-indexed geometry.  When defined, the accessor **MUST** have `SCALAR` type and an unsigned integer component type. */
        val indices: Int? = null,
        /** The index of the material to apply to this primitive when rendering. */
        val material: Int? = null,
        /** The topology type of primitives to render. */
        val mode: Int = 4,
        /** An array of morph targets. Morph targets: one per morphing weight. Typically, 4 as max for standard. A plain JSON object specifying attributes displacements in a morph target, where each key corresponds to one of the three supported attribute semantic (`POSITION`, `NORMAL`, or `TANGENT`) and each value is the index of the accessor containing the attribute displacements' data. */
        val targets: List<Map<PrimitiveAttribute, Int>> = emptyList(),
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty() {

        fun getBounds(gltf: GLTF2): AABB3D {
            val accessorIndex = attributes[PrimitiveAttribute.POSITION] ?: return AABB3D(Vector3.ZERO, Vector3.ZERO)
            val accessor = gltf.accessors[accessorIndex]
            return AABB3D(Vector3.func { accessor.min[it] }, Vector3.func { accessor.max[it] })
        }
    }
    /**
     * <https://github.com/javagl/glTF-Tutorials/blob/master/gltfTutorial/gltfTutorial_019_SimpleSkin.md>
     */
    @Serializable
    data class Skin(
        override var name: String? = null,
        /** The index of the accessor containing the floating-point 4x4 inverse-bind matrices. */
        val inverseBindMatrices: Int? = null,
        /** Indices of skeleton nodes, used as joints in this skin. */
        val joints: IntArray = IntArray(0),
        /** he index of the node used as a skeleton root. */
        val skeleton: Int? = null,
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null
    ) : GLTFProperty()

    @Serializable
    data class Animation(
        override var name: String? = null,
        /** An array of animation channels. An animation channel combines an animation sampler with a target property being animated. Different channels of the same animation **MUST NOT** have the same targets. */
        val channels: List<Channel> = emptyList(),
        /** An array of animation samplers. An animation sampler combines timestamps with a sequence of output values and defines an interpolation algorithm. */
        val samplers: List<Sampler> = emptyList(),
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null
    ) : GLTFProperty() {
        @Serializable
        data class Channel(
            override var name: String? = null,
            val sampler: Int = -1,
            val target: Target? = null,
            override val extensions: JsonElement? = null,
            override val extras: JsonElement? = null
        ) : GLTFProperty() {
            @Serializable
            data class Target(
                override var name: String? = null,
                val node: Int = -1,
                val path: TargetPath? = null,
                override val extensions: JsonElement? = null,
                override val extras: JsonElement? = null,
            ) : GLTFProperty()

            @Serializable(with = TargetPathSerializer::class)
            enum class TargetPath(val key: String) {
                TRANSLATION("translation"),
                ROTATION("rotation"),
                SCALE("scale"),
                WEIGHTS("weights"),
                UNKNOWN("unknown"),
                ;
                companion object {
                    val BY_KEY = values().associateBy { it.key }
                }
            }

            object TargetPathSerializer : KSerializer<TargetPath> {
                override val descriptor: SerialDescriptor
                    get() = PrimitiveSerialDescriptor("TargetPath", PrimitiveKind.STRING)
                override fun serialize(encoder: Encoder, value: TargetPath) {
                    encoder.encodeString(value.key)
                }
                override fun deserialize(decoder: Decoder): TargetPath =
                    TargetPath.BY_KEY[decoder.decodeString()] ?: TargetPath.UNKNOWN
            }
        }
        @Serializable
        data class Sampler(
            override var name: String? = null,
            /** The index of an accessor containing keyframe timestamps. The accessor **MUST** be of scalar type with floating-point components. The values represent time in seconds with `time[0] >= 0.0`, and strictly increasing values, i.e., `time[n + 1] > time[n]`. */
            val input: Int = -1,
            /**
             * Interpolation algorithm.
             *
             * LINEAR: The animated values are linearly interpolated between keyframes. When targeting a rotation, spherical linear interpolation (slerp) **SHOULD** be used to interpolate quaternions. The number of output elements **MUST** equal the number of input elements.
             * STEP: The animated values remain constant to the output of the first keyframe, until the next keyframe. The number of output elements **MUST** equal the number of input elements.
             * CUBICSPLINE: The animation's interpolation is computed using a cubic spline with specified tangents. The number of output elements **MUST** equal three times the number of input elements. For each input element, the output stores three elements, an in-tangent, a spline vertex, and an out-tangent. There **MUST** be at least two keyframes when using this interpolation.
             * */
            val interpolation: String = "LINEAR", // LINEAR, STEP, CUBICSPLINE, other
            /** The index of an accessor, containing keyframe output values. */
            val output: Int = -1,
            override val extensions: JsonElement? = null,
            override val extras: JsonElement? = null,
        ) : GLTFProperty() {
            init {
                if (interpolation != "LINEAR" && interpolation != "STEP") {
                    error("Only implemented LINEAR & STEP interpolations for now, but requested '$interpolation'")
                }
            }

            fun maxTime(gltf: GLTF2): Float {
                val times = times(gltf)
                return if (times.size > 0) times[times.size - 1, 0] else 0f
            }

            @Transient
            var inputAccessor: GLTF2AccessorVector? = null
            @Transient
            var outputAccessor: GLTF2AccessorVector? = null

            fun inputAccessor(gltf: GLTF2): Accessor = gltf.accessors[input]
            fun outputAccessor(gltf: GLTF2): Accessor = gltf.accessors[output]

            fun times(gltf: GLTF2): GLTF2AccessorVector {
                if (inputAccessor == null) {
                    val accessor = inputAccessor(gltf)
                    inputAccessor = GLTF2AccessorVector(accessor, accessor.bufferSlice(gltf))
                }
                return inputAccessor!!
            }
            fun outputBuffer(gltf: GLTF2): GLTF2AccessorVector {
                if (outputAccessor == null) {
                    val accessor = outputAccessor(gltf)
                    outputAccessor = GLTF2AccessorVector(accessor, accessor.bufferSlice(gltf))
                }
                return outputAccessor!!
            }

            data class Lookup(
                var requestedTime: Float = 0f,
                var lowIndex: Int = 0, var highIndex: Int = 0,
                var lowTime: Float = 0f, var highTime: Float = 0f,
                var interpolation: String = "LINEAR",
            ) {
                val ratio: Float get() {
                    return when (interpolation) {
                        "LINEAR" -> requestedTime.convertRange(lowTime, highTime, 0f, 1f)
                        "STEP" -> 0f
                        else -> TODO("Unimplemented interpolation $interpolation")
                    }
                }
                val ratioClamped: Float get() = ratio.clamp01()
            }

            enum class LookupKind { NORMAL, QUATERNION }

            fun lookup(gltf: GLTF2, time: Float, kind: LookupKind, out: Lookup = Lookup()): Lookup {
                val times = times(gltf)
                val lowIndex = genericBinarySearchLeft(0, times.size) { times[it, 0].compareTo(time) }
                val highIndex = if (lowIndex >= times.size - 1) lowIndex else lowIndex + 1
                out.requestedTime = time
                out.lowIndex = lowIndex
                out.highIndex = highIndex
                out.lowTime = times[lowIndex, 0]
                out.highTime = times[highIndex, 0]
                out.interpolation = interpolation
                return out
            }
            fun doLookup(gltf: GLTF2, time: Float, kind: LookupKind, count: Int = 1): GLTF2AccessorVector {
                val vec = GLTF2AccessorVector(outputAccessor(gltf), count)
                doLookup(gltf, time, kind, vec, count)
                return vec
            }
            fun doLookup(gltf: GLTF2, time: Float, kind: LookupKind, out: GLTF2AccessorVector, count: Int = 1, outIndex: Int = 0) {
                val lookup = lookup(gltf, time, kind)
                val output = outputBuffer(gltf)
                for (n in 0 until count) {
                    out.setInterpolated(
                        outIndex + n,
                        output,
                        lookup.lowIndex * count + n,
                        output,
                        lookup.highIndex * count + n,
                        lookup.ratioClamped,
                        kind
                    )
                }
                //println("lookup.ratioClamped=${lookup.ratioClamped}, lookup.lowIndex=${lookup.lowIndex}, lookup.highIndex=${lookup.highIndex}, out=$out : ${out.accessor}")
            }
        }
    }
    /**
     * A buffer points to binary geometry, animation, or skins.
     *
     * https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/schema/buffer.schema.json
     */
    @Serializable
    data class Buffer(
        override var name: String? = null,
        /** The URI (or IRI) of the buffer.  Relative paths are relative to the current glTF asset.  Instead of referencing an external file, this field **MAY** contain a `data:`-URI. */
        val uri: String? = null,
        /** The length of the buffer in bytes. */
        val byteLength: Int = 0,
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty() {
        @Transient
        var optBuffer: korlibs.memory.Buffer? = null
        val buffer: korlibs.memory.Buffer get() = optBuffer ?: error("Buffer not loaded!")

        override fun toString(): String = "Buffer($name, uri=${uri?.substr(0, 100)}, byteLength=$byteLength)"
    }

    /**
     * A view into a buffer generally representing a subset of the buffer.
     *
     * <https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/schema/bufferView.schema.json>
     */
    @Serializable
    data class BufferView(
        override var name: String? = null,
        /** The index of the buffer. */
        val buffer: Int = -1,
        /** The offset into the buffer in bytes. */
        val byteOffset: Int = 0,
        /** The length of the bufferView in bytes. */
        val byteLength: Int = 0,
        /** The stride, in bytes, between vertex attributes.  When this is not defined, data is tightly packed. When two or more accessors use the same buffer view, this field **MUST** be defined. */
        val byteStride: Int = 0,
        /**
         * The hint representing the intended GPU buffer type to use with this buffer view.
         *
         * ARRAY_BUFFER: 34962
         * ELEMENT_ARRAY_BUFFER: 34963
         **/
        val target: Int = -1,
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty() {
        fun slice(gltf: GLTF2): korlibs.memory.Buffer =
            gltf.buffers[buffer].buffer.sliceWithSize(byteOffset, byteLength)
    }
    @Serializable
    enum class AccessorType(
        val ncomponent: Int
    ) {
        SCALAR(1), VEC2(2), VEC3(3), VEC4(4), MAT2(4), MAT3(9), MAT4(16);
    }
    /**
     * <https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/schema/accessor.schema.json>
     */
    @Serializable
    data class Accessor(
        override var name: String? = null,
        /** The index of the buffer view. When undefined, the accessor **MUST** be initialized with zeros; `sparse` property or extensions **MAY** override zeros with actual values. */
        val bufferView: Int = 0,
        /** The offset relative to the start of the buffer view in bytes. */
        val byteOffset: Int = 0,
        /**
         * The datatype of the accessor's components.
         * UNSIGNED_INT type **MUST NOT** be used for any accessor that is not referenced by `mesh.primitive.indices`.
         * `type` parameter of `vertexAttribPointer()`.  The corresponding typed arrays are `Int8Array`, `Uint8Array`, `Int16Array`, `Uint16Array`, `Uint32Array`, and `Float32Array`. */
        val componentType: Int = 0,
        /** Specifies whether integer data values are normalized before usage." */
        val normalized: Boolean = false,
        /** The number of elements referenced by this accessor, not to be confused with the number of bytes or number of components. */
        val count: Int = 1,
        /** Minimum value of each component in this accessor. */
        val min: FloatArray = FloatArray(0),
        /** Maximum value of each component in this accessor. */
        val max: FloatArray = FloatArray(0),
        /** Specifies if the accessor's elements are scalars, vectors, or matrices. */
        val type: AccessorType = AccessorType.SCALAR,
        ///** Sparse storage of elements that deviate from their initialization value. */
        //val sparse: Sparse,
        val sparse: JsonElement? = null,
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty() {
        init {
            if (sparse != null) TODO("Unimplemented SPARSE: $sparse")
        }
        ///**
        // * <https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/schema/accessor.sparse.schema.json>
        // */
        //@Serializable
        //data class Sparse(
        //    override var name: String? = null,
        //    /** Number of deviating accessor values stored in the sparse array. */
        //    val count: Int = -1,
        //    override val extensions: JsonElement? = null,
        //    override val extras: JsonElement? = null,
        //) : GLTFProperty() {
        //}

        var attachDebugName: String? = null

        val componentTType: VarKind get() = when (componentType) {
            /* 5120 */ 0x1400 -> VarKind.TBYTE // signed byte --- f = max(c / 127.0, -1.0)   --- c = round(f * 127.0)
            /* 5121 */ 0x1401 -> VarKind.TUNSIGNED_BYTE // unsigned byte --- f = c / 255.0  --- c = round(f * 255.0)
            /* 5122 */ 0x1402 -> VarKind.TSHORT // signed short --- f = max(c / 32767.0, -1.0) --- c = round(f * 32767.0)
            /* 5123 */ 0x1403 -> VarKind.TUNSIGNED_SHORT // unsigned short --- f = c / 65535.0 --- c = round(f * 65535.0)
            /* 5124 */ 0x1404 -> VarKind.TINT
            /* 5125 */ 0x1405 -> VarKind.TINT // TODO: TUNSIGNED_INT
            /* 5126 */ 0x1406 -> VarKind.TFLOAT
            else -> TODO("Unsupported componentType=$componentType")
        }
        val ncomponent get() = type.ncomponent
        val bytesPerEntry get() = componentTType.bytesSize * ncomponent

        fun VarType.Companion.BOOL(count: Int) =
            when (count) { 0 -> VarType.TVOID; 1 -> VarType.Bool1; 2 -> VarType.Bool2; 3 -> VarType.Bool3; 4 -> VarType.Bool4; else -> invalidOp; }
        fun VarType.Companion.MAT(count: Int) =
            when (count) { 0 -> VarType.TVOID; 1 -> VarType.Float1; 2 -> VarType.Mat2; 3 -> VarType.Mat3; 4 -> VarType.Mat4; else -> invalidOp; }

        fun VarType.Companion.gen(kind: VarKind, ncomponent: Int, type: AccessorType): VarType {
            return when (type) {
                AccessorType.MAT2 -> VarType.MAT(2)
                AccessorType.MAT3 -> VarType.MAT(3)
                AccessorType.MAT4 -> VarType.MAT(4)
                else -> when (kind) {
                    VarKind.TBOOL -> VarType.BOOL(ncomponent)
                    VarKind.TBYTE -> VarType.BYTE(ncomponent)
                    VarKind.TUNSIGNED_BYTE -> VarType.UBYTE(ncomponent)
                    VarKind.TSHORT -> VarType.SHORT(ncomponent)
                    VarKind.TUNSIGNED_SHORT -> VarType.USHORT(ncomponent)
                    VarKind.TINT -> VarType.INT(ncomponent)
                    VarKind.TFLOAT -> VarType.FLOAT(ncomponent)
                }
            }
        }

        val varType: VarType = VarType.gen(componentTType, ncomponent, type)
        fun asIndexType(): AGIndexType = when (componentTType) {
            VarKind.TBOOL, VarKind.TBYTE, VarKind.TUNSIGNED_BYTE -> AGIndexType.UBYTE
            VarKind.TSHORT, VarKind.TUNSIGNED_SHORT -> AGIndexType.USHORT
            VarKind.TINT, VarKind.TFLOAT -> AGIndexType.UINT
        }
        fun bufferView(gltf: GLTF2): BufferView = gltf.bufferViews[bufferView]
        fun bufferSlice(gltf: GLTF2): korlibs.memory.Buffer {
            val allBuffer = bufferView(gltf).slice(gltf)
            return when {
                count < 0 -> allBuffer.sliceBuffer(byteOffset)
                else -> allBuffer.sliceWithSize(byteOffset, count * bytesPerEntry)
            }
        }
        fun accessor(gltf: GLTF2): GLTF2AccessorVector = GLTF2AccessorVector(this, bufferSlice(gltf))
    }
    /**
     * The material appearance of a primitive.
     *
     * <https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/schema/material.schema.json>
     */
    @Serializable
    data class Material(
        override var name: String? = null,
        /**
         * OPAQUE: The alpha value is ignored, and the rendered output is fully opaque.
         * MASK: The rendered output is either fully opaque or fully transparent depending on the alpha value and the specified `alphaCutoff` value; the exact appearance of the edges **MAY** be subject to implementation-specific techniques such as \"`Alpha-to-Coverage`\".
         * BLEND: The alpha value is used to composite the source and destination areas. The rendered output is combined with the background using the normal painting operation (i.e. the Porter and Duff over operator).
         */
        val alphaMode: String? = null, // OPAQUE
        /** Specifies the cutoff threshold when in `MASK` alpha mode. If the alpha value is greater than or equal to this value then it is rendered as fully opaque, otherwise, it is rendered as fully transparent. A value greater than `1.0` will render the entire material as fully transparent. This value **MUST** be ignored for other alpha modes. When `alphaMode` is not defined, this value **MUST NOT** be defined. */
        val alphaCutoff: Float = 0.5f,
        /** Specifies whether the material is double sided. When this value is false, back-face culling is enabled. When this value is true, back-face culling is disabled and double-sided lighting is enabled. The back-face **MUST** have its normals reversed before the lighting equation is evaluated. */
        val doubleSided: Boolean = true,
        //val extensions: Map<String, JsonObject> = emptyMap(),
        /** The factors for the emissive color of the material. This value defines linear multipliers for the sampled texels of the emissive texture. */
        val emissiveFactor: FloatArray? = null,
        /** The emissive texture. It controls the color and intensity of the light being emitted by the material. This texture contains RGB components encoded with the sRGB transfer function. If a fourth component (A) is present, it **MUST** be ignored. When undefined, the texture **MUST** be sampled as having `1.0` in RGB components. */
        val emissiveTexture: TextureInfo? = null,
        /** A set of parameter values that are used to define the metallic-roughness material model from Physically Based Rendering (PBR) methodology. When undefined, all the default values of `pbrMetallicRoughness` **MUST** apply. */
        val pbrMetallicRoughness: PBRMetallicRoughness = PBRMetallicRoughness(),
        /** The tangent space normal texture. The texture encodes RGB components with linear transfer function. Each texel represents the XYZ components of a normal vector in tangent space. The normal vectors use the convention +X is right and +Y is up. +Z points toward the viewer. If a fourth component (A) is present, it **MUST** be ignored. When undefined, the material does not have a tangent space normal texture. */
        val normalTexture: NormalTextureInfo? = null,
        /** The occlusion texture. The occlusion values are linearly sampled from the R channel. Higher values indicate areas that receive full indirect lighting and lower values indicate no indirect lighting. If other channels are present (GBA), they **MUST** be ignored for occlusion calculations. When undefined, the material does not have an occlusion texture. */
        val occlusionTexture: OcclusionTextureInfo? = null,
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty() {
        /**
         * A set of parameter values that are used to define the metallic-roughness material model from Physically-Based Rendering (PBR) methodology.
         *
         * <https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/schema/material.pbrMetallicRoughness.schema.json>
         */
        @Serializable
        data class PBRMetallicRoughness(
            override var name: String? = null,
            /**
             * The factors for the base color of the material. This value defines linear multipliers
             *  for the sampled texels of the base color texture. */
            val baseColorFactor: FloatArray? = null,
            /** The base color texture. The first three components (RGB) **MUST** be encoded with
             * the sRGB transfer function. They specify the base color of the material.
             * If the fourth component (A) is present, it represents the linear alpha coverage of the material.
             * Otherwise, the alpha coverage is equal to `1.0`.
             * The `material.alphaMode` property specifies how alpha is interpreted.
             * The stored texels **MUST NOT** be premultiplied. When undefined,
             * the texture **MUST** be sampled as having `1.0` in all components.
             **/
            val baseColorTexture: TextureInfo? = null, // textureInfo
            /**
             * The factor for the metalness of the material. This value defines a linear multiplier for
             * the sampled metalness values of the metallic-roughness texture.
             **/
            val metallicFactor: Float = 0f,
            /**
             * The factor for the roughness of the material.
             * This value defines a linear multiplier for the sampled roughness
             * values of the metallic-roughness texture.
             **/
            val roughnessFactor: Float = 0f,
            /**
             * The metallic-roughness texture. The metalness values are sampled from the B channel.
             * The roughness values are sampled from the G channel.
             * These values **MUST** be encoded with a linear transfer function.
             * If other channels are present (R or A), they **MUST** be ignored for metallic-roughness calculations.
             * When undefined, the texture **MUST** be sampled as having `1.0` in G and B components.
             **/
            val metallicRoughnessTexture: TextureInfo? = null,
            override val extensions: JsonElement? = null,
            override val extras: JsonElement? = null,
        ) : GLTFProperty()

        /**
         * <https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/schema/textureInfo.schema.json>
         */
        @Serializable
        abstract class BaseTextureInfo() : GLTFProperty() {
            abstract val index: Int
            abstract val texCoord: Int
            fun getTexture(gltf: GLTF2): Bitmap? = gltf.textures[index].getImage(gltf).bitmap
        }

        @Serializable
        data class TextureInfo(
            override var name: String? = null,
            override val index: Int = -1,
            override val texCoord: Int = -1,
            override val extensions: JsonElement? = null,
            override val extras: JsonElement? = null,
        ) : BaseTextureInfo() {
        }

        /**
         * https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/schema/material.occlusionTextureInfo.schema.json
         */
        @Serializable
        data class OcclusionTextureInfo(
            override var name: String? = null,
            override val index: Int = -1,
            override val texCoord: Int = -1,
            /**
             * The scalar parameter applied to each normal vector of the texture. This value scales the normal vector in X and Y directions using the formula: `scaledNormal =  normalize((<sampled normal texture value> * 2.0 - 1.0) * vec3(<normal scale>, <normal scale>, 1.0))`.
             */
            val strength: Float = 1f,
            override val extensions: JsonElement? = null,
            override val extras: JsonElement? = null,
        ) : BaseTextureInfo() {

        }

        /**
         * <https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/schema/material.normalTextureInfo.schema.json>
         */
        @Serializable
        data class NormalTextureInfo(
            override var name: String? = null,
            override val index: Int = -1,
            override val texCoord: Int = -1,
            /**
             * The scalar parameter applied to each normal vector of the texture. This value scales the normal vector in X and Y directions using the formula: `scaledNormal =  normalize((<sampled normal texture value> * 2.0 - 1.0) * vec3(<normal scale>, <normal scale>, 1.0))`.
             */
            val scale: Float = 1f,
            override val extensions: JsonElement? = null,
            override val extras: JsonElement? = null,
        ) : BaseTextureInfo() {
        }
    }
    @Serializable
    data class Texture(
        override var name: String? = null,
        val sampler: Int = -1,
        val source: Int = -1,
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty() {
        fun getImage(gltf: GLTF2): Image {
            return gltf.images[source]
        }
    }

    @Serializable
    data class Image(
        override var name: String? = null,
        val uri: String? = null,
        val bufferView: Int = -1,
        val mimeType: String? = null,
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty() {
        @Transient
        var bitmap: Bitmap? = null
    }
    @Serializable
    data class Sampler(
        override var name: String? = null,
        val magFilter: Int = -1,
        val minFilter: Int = -1,
        val wrapS: Int = -1,
        val wrapT: Int = -1,
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty()
    @Serializable
    data class Camera(
        override var name: String? = null,
        val type: String,
        val perspective: Perspective? = null,
        val orthographic: Orthographic? = null,
        override val extensions: JsonElement? = null,
        override val extras: JsonElement? = null,
    ) : GLTFProperty() {
        @Serializable
        data class Orthographic(
            override var name: String? = null,
            val xmag: Float,
            val ymag: Float,
            val zfar: Float,
            val znear: Float,
            override val extensions: JsonElement? = null,
            override val extras: JsonElement? = null,
        ) : GLTFProperty()

        @Serializable
        data class Perspective(
            override var name: String? = null,
            val aspectRatio: Float = 1.5f,
            val yfov: Float = 0.660593f,
            val zfar: Float = 100f,
            val znear: Float = 0.01f,
            override val extensions: JsonElement? = null,
            override val extras: JsonElement? = null,
        ) : GLTFProperty()
    }

    companion object {

        val logger = Logger("GLTF2")

        suspend fun readGLB(file: VfsFile, options: ReadOptions = ReadOptions.DEFAULT): GLTF2 = readGLB(file.readBytes(), file, options)
        suspend fun readGLB(data: ByteArray, file: VfsFile? = null, options: ReadOptions = ReadOptions.DEFAULT): GLTF2 {
            val s = data.openSync()
            if (s.readString(4) != "glTF") error("Not a glTF binary")
            val version = s.readS32LE()
            if (version != 2) error("Not a glTF version 2.0")
            val fileSize = s.readS32LE()
            var json = "{}"
            var bin: ByteArray? = null
            while (s.position < fileSize) {
                val chunkSize = s.readS32LE()
                val chunkName = s.readStringz(4)
                val chunkData = s.readStream(chunkSize)
                when (chunkName) {
                    "JSON" -> json = chunkData.toByteArray().toString(Charsets.UTF8)
                    "BIN" -> bin = chunkData.toByteArray()
                }
                logger.trace { "CHUNK[$chunkName] = $chunkSize" }
            }

            return readGLTF(json, bin, file, options)
        }

        // @TODO: Use kotlinx-serialization
        suspend fun readGLTF(jsonString: String, bin: ByteArray? = null, file: VfsFile? = null, options: ReadOptions = ReadOptions.DEFAULT): GLTF2 {
            return try {
                Json { this.ignoreUnknownKeys = options.ignoreUnknownKeys }
                    .decodeFromString<GLTF2>(jsonString)
                    .also { it.ensureLoad(file, bin) }
            } catch (e: Throwable) {
                println("options=$options")
                println("ERROR parsing: $jsonString")
                throw e
            }
        }
    }
}


/**
 * <https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/schema/glTFProperty.schema.json>
 * <https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/schema/extension.schema.json>
 * <https://github.com/KhronosGroup/glTF/blob/main/specification/2.0/schema/extras.schema.json>
 */
@Serializable
abstract class GLTFProperty {
    abstract var name: String?
    /** JSON object with extension-specific objects. */
    abstract val extensions: JsonElement?
    /** Although `extras` **MAY** have any type, it is common for applications to store and access custom data as key/value pairs. Therefore, `extras` **SHOULD** be a JSON object rather than a primitive value for best portability. */
    abstract val extras: JsonElement?
}

@JvmInline
value class GLTF2AccessorVectorMAT4(val vec: GLTF2AccessorVector) {
    val size: Int get() = vec.size
    operator fun get(index: Int): Matrix4 = Matrix4.fromColumns(FloatArray(16) { vec[index, it] })
    operator fun set(index: Int, value: Matrix4) {
        val values = value.copyToColumns()
        for (n in 0 until 16) vec[index, n] = values[n]
    }
    fun toList(): List<Matrix4> = (0 until size).map { this[it] }

    override fun toString(): String = "${toList()}"
}

data class GLTF2AccessorVector(val accessor: GLTF2.Accessor, val buffer: Buffer) {
    constructor(accessor: GLTF2.Accessor, size: Int = 1) : this(accessor, Buffer(accessor.bytesPerEntry * size))
    val dims: Int get() = accessor.ncomponent
    val bytesPerEntry = accessor.bytesPerEntry
    val size: Int get() = buffer.sizeInBytes / bytesPerEntry
    val sizeComponents: Int get() = buffer.sizeInBytes / accessor.componentTType.bytesSize
    fun toVector3(): Vector3 = Vector3.func { if (it < sizeComponents) getLinear(it) else 0f }
    fun toVector4(): Vector4 = Vector4.func { if (it < sizeComponents) getLinear(it) else 0f }

    fun getLinear(index: Int): Float {
        try {
            val value = when (accessor.componentTType) {
                VarKind.TBYTE -> buffer.asInt8()[index].toFloat()
                VarKind.TBOOL, VarKind.TUNSIGNED_BYTE -> buffer.asInt8()[index].toFloat()
                VarKind.TSHORT -> buffer.asInt16()[index].toFloat()
                VarKind.TUNSIGNED_SHORT -> buffer.asUInt16()[index].toFloat()
                VarKind.TINT -> buffer.asInt32()[index].toFloat()
                VarKind.TFLOAT -> buffer.asFloat32()[index]
            }
            return if (accessor.normalized) {
                when (accessor.componentTType) {
                    VarKind.TBYTE -> kotlin.math.max(value / 127f, -1f)
                    VarKind.TBOOL, VarKind.TUNSIGNED_BYTE -> value / 255f
                    VarKind.TSHORT -> kotlin.math.max(value / 32767f, -1f)
                    VarKind.TUNSIGNED_SHORT -> value / 65535f
                    VarKind.TINT -> value
                    VarKind.TFLOAT -> value
                }
            } else {
                value
            }
        } catch (e: IndexOutOfBoundsException) {
            println("!! ERROR accessing $index of buffer.sizeInBytes=${buffer.sizeInBytes}, dims=$dims, bytesPerEntry=$bytesPerEntry, size=$size, accessor=$accessor")
            throw e
        }
    }

    fun setLinear(index: Int, value: Float) {
        when (accessor.componentTType) {
            VarKind.TBYTE -> buffer.asInt8()[index] = kotlin.math.round(value * 127.0).toInt().toByte()
            VarKind.TBOOL, VarKind.TUNSIGNED_BYTE -> buffer.asInt8()[index] = kotlin.math.round(value * 255.0).toInt().toByte()
            VarKind.TSHORT -> buffer.asInt16()[index] = kotlin.math.round(value * 32767f).toInt().toShort()
            VarKind.TUNSIGNED_SHORT -> buffer.asInt16()[index] = kotlin.math.round(value * 65535f).toInt().toShort()
            VarKind.TINT -> buffer.asInt32()[index] = value.toInt()
            VarKind.TFLOAT -> buffer.asFloat32()[index] = value
        }
    }

    fun getFloat4(index: Int): Vector4 {
        return Vector4(
            if (dims >= 1) this[index, 0] else 0f,
            if (dims >= 2) this[index, 1] else 0f,
            if (dims >= 3) this[index, 2] else 0f,
            if (dims >= 4) this[index, 3] else 0f,
        )
    }
    operator fun get(index: Int, dim: Int): Float = getLinear(index * dims + dim)
    operator fun set(index: Int, dim: Int, value: Float) {
        setLinear(index * dims + dim, value)
    }

    fun setInterpolated(index: Int, a: GLTF2AccessorVector, aIndex: Int, b: GLTF2AccessorVector, bIndex: Int, ratio: Float, kind: GLTF2.Animation.Sampler.LookupKind) {
        when (kind) {
            GLTF2.Animation.Sampler.LookupKind.NORMAL -> {
                for (dim in 0 until dims) {
                    this[index, dim] = ratio.toRatio().interpolate(a[aIndex, dim], b[bIndex, dim])
                }
            }
            GLTF2.Animation.Sampler.LookupKind.QUATERNION -> {
                if (dims != 4) error("Invalid dimensions $dims for quaternion")
                val q1 = Quaternion(a.getFloat4(aIndex))
                val q2 = Quaternion(b.getFloat4(bIndex))
                val qr = Quaternion.interpolated(q1, q2, ratio)
                this[index, 0] = qr.x
                this[index, 1] = qr.y
                this[index, 2] = qr.z
                this[index, 3] = qr.w
            }
        }
    }

    override fun toString(): String {
        return buildString {
            append("[")
            for (n in 0 until size) {
                if (n != 0) append(", ")
                append("[")
                for (dim in 0 until dims) {
                    if (dim != 0) append(", ")
                    append(this@GLTF2AccessorVector[n, dim])
                }
                append("]")
            }
            append("]")
        }
    }
}

interface GLTF2Holder {
    val gltf: GLTF2
    val GLTF2.Node.childrenNodes: List<GLTF2.Node> get() = this.childrenNodes(gltf) ?: emptyList()
    val GLTF2.Scene.childrenNodes: List<GLTF2.Node> get() = this.childrenNodes(gltf) ?: emptyList()
}

fun GLTF2.Node.childrenNodes(gltf: GLTF2): List<GLTF2.Node>? = this.children?.map { gltf.nodes[it] }
fun GLTF2.Scene.childrenNodes(gltf: GLTF2): List<GLTF2.Node>? = this.nodes?.map { gltf.nodes[it] }
fun GLTF2.Node.mesh(gltf: GLTF2): GLTF2.Mesh = gltf.meshes[this.mesh ?: error("cannot get mesh")]

@JsExport
enum class GLTFRenderMode(val value: Int) {
    POINTS(0),
    LINE(1),
    LINE_LOOP(2),
    LINE_STRIP(3),
    TRIANGLES(4),
    TRIANGLE_STRIP(5),
    TRIANGLE_FAN(6);

    companion object {
        fun of(value: Int): GLTFRenderMode? {
            return entries.find { it.value == value }
        }
    }
}
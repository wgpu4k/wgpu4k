package io.ygdrasil.wgpu

interface EnumerationWithValue {
    val value: Int
    val uValue: UInt
        get() = value.toUInt()

    infix fun or(other: Int): Int = value or other
    infix fun or(other: EnumerationWithValue): Int = value or other.value
}

internal fun Set<EnumerationWithValue>.toFlagInt(): Int = when (size) {
    0 -> 0
    1 -> first().value
    else -> fold(0) { acc, enumerationWithValue -> acc or enumerationWithValue.value }
}

internal fun Set<EnumerationWithValue>.toFlagUInt(): UInt = when (size) {
    0 -> 0u
    1 -> first().value.toUInt()
    else -> fold(0u) { acc, enumerationWithValue -> acc or enumerationWithValue.value.toUInt() }
}

enum class AdapterType(
    val value: Int,
) {
    discretegpu(0),
    integratedgpu(1),
    cpu(2),
    unknown(3);

    companion object {
        fun of(value: Int): AdapterType? = entries.find {
            it.value == value
        }
    }
}

enum class AddressMode(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    repeat(0, "repeat"),
    mirrorrepeat(1, "mirror-repeat"),
    clamptoedge(2, "clamp-to-edge"),
    ;

    companion object {
        fun of(value: Int): AddressMode? = entries.find {
            it.value == value
        }
    }
}

enum class BackendType(
    val value: Int,
) {
    undefined(0),
    `null`(1),
    webgpu(2),
    d3d11(3),
    d3d12(4),
    metal(5),
    vulkan(6),
    opengl(7),
    opengles(8),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: BackendType): Int = value or other.value

    companion object {
        fun of(value: Int): BackendType? = entries.find {
            it.value == value
        }
    }
}

enum class BlendFactor(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    zero(0, "zero"),
    one(1, "one"),
    src(2, "src"),
    oneminussrc(3, "one-minus-src"),
    srcalpha(4, "src-alpha"),
    oneminussrcalpha(5, "one-minus-src-alpha"),
    dst(6, "dst"),
    oneminusdst(7, "one-minus-dst"),
    dstalpha(8, "dst-alpha"),
    oneminusdstalpha(9, "one-minus-dst-alpha"),
    srcalphasaturated(10, "src-alpha-saturated"),
    constant(11, "constant"),
    oneminusconstant(12, "one-minus-constant"),
    ;

    companion object {
        fun of(value: Int): BlendFactor? = entries.find {
            it.value == value
        }
    }
}

enum class BlendOperation(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    add(0, "add"),
    subtract(1, "subtract"),
    reversesubtract(2, "reverse-subtract"),
    min(3, "min"),
    max(4, "max"),
    ;

    companion object {
        fun of(value: Int): BlendOperation? = entries.find {
            it.value == value
        }
    }
}

enum class BufferBindingType(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    uniform(1, "uniform"),
    storage(2, "storage"),
    readonlystorage(3, "read-only-storage");

    companion object {
        fun of(value: Int): BufferBindingType? = entries.find {
            it.value == value
        }
    }
}

enum class BufferMapAsyncStatus(
    val value: Int,
) {
    success(0),
    validationerror(1),
    unknown(2),
    devicelost(3),
    destroyedbeforecallback(4),
    unmappedbeforecallback(5),
    mappingalreadypending(6),
    offsetoutofrange(7),
    sizeoutofrange(8),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: BufferMapAsyncStatus): Int = value or other.value

    companion object {
        fun of(value: Int): BufferMapAsyncStatus? = entries.find {
            it.value == value
        }
    }
}

enum class BufferMapState(
    val value: Int,
) {
    unmapped(0),
    pending(1),
    mapped(2);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: BufferMapState): Int = value or other.value

    companion object {
        fun of(value: Int): BufferMapState? = entries.find {
            it.value == value
        }

        fun of(value: String): BufferMapState? = entries.find {
            it.name == value
        }
    }
}

enum class CompareFunction(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    never(1, "never"),
    less(2, "less"),
    lessequal(3, "less-equal"),
    greater(4, "greater"),
    greaterequal(5, "greater-equal"),
    equal(6, "equal"),
    notequal(7, "not-equal"),
    always(8, "always");

    companion object {
        fun of(value: Int): CompareFunction? = entries.find {
            it.value == value
        }
    }
}

enum class CompilationInfoRequestStatus(
    val value: Int,
) {
    success(0),
    error(1),
    devicelost(2),
    unknown(3);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: CompilationInfoRequestStatus): Int = value or other.value

    companion object {
        fun of(value: Int): CompilationInfoRequestStatus? = entries.find {
            it.value == value
        }
    }
}

enum class CompilationMessageType(
    val value: Int,
) {
    error(0),
    warning(1),
    info(2);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: CompilationMessageType): Int = value or other.value

    companion object {
        fun of(value: Int): CompilationMessageType? = entries.find {
            it.value == value
        }
    }
}

enum class CompositeAlphaMode(
    val value: Int,
) {
    auto(0),
    opaque(1),
    premultiplied(2),
    postmultiplied(3),
    inherit(4);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: CompositeAlphaMode): Int = value or other.value

    companion object {
        fun of(value: Int): CompositeAlphaMode? = entries.find {
            it.value == value
        }
    }
}

enum class CreatePipelineAsyncStatus(
    val value: Int,
) {
    success(0),
    validationerror(1),
    internalerror(2),
    devicelost(3),
    devicedestroyed(4),
    unknown(5);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: CreatePipelineAsyncStatus): Int = value or other.value

    companion object {
        fun of(value: Int): CreatePipelineAsyncStatus? = entries.find {
            it.value == value
        }
    }
}

enum class CullMode(
    override val value: Int,
) : EnumerationWithValue {
    none(0),
    front(1),
    back(2);

    companion object {
        fun of(value: Int): CullMode? = entries.find {
            it.value == value
        }
    }
}

enum class DeviceLostReason(
    val value: Int,
) {
    undefined(0),
    destroyed(1);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: DeviceLostReason): Int = value or other.value

    companion object {
        fun of(value: Int): DeviceLostReason? = entries.find {
            it.value == value
        }
    }
}

enum class ErrorFilter(
    val value: Int,
) {
    validation(0),
    outofmemory(1),
    `internal`(2);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: ErrorFilter): Int = value or other.value

    companion object {
        fun of(value: Int): ErrorFilter? = entries.find {
            it.value == value
        }
    }
}

enum class ErrorType(
    val value: Int,
) {
    noerror(0),
    validation(1),
    outofmemory(2),
    `internal`(3),
    unknown(4),
    devicelost(5);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: ErrorType): Int = value or other.value

    companion object {
        fun of(value: Int): ErrorType? = entries.find {
            it.value == value
        }
    }
}

enum class FeatureName(
    override val value: Int,
    private val extraName: String? = null,
) : EnumerationWithValue {
    depthClipControl(1, "depth-clip-control"),
    depth32floatStencil8(2, "depth32float-stencil8"),
    timestampQuery(3, "timestamp-query"),
    textureCompressionBc(4, "texture-compression-bc"),
    textureCompressionEtc2(5, "texture-compression-etc2"),
    textureCompressionAstc(6, "texture-compression-astc"),
    indirectFirstInstance(7, "indirect-first-instance"),
    shaderF16(8, "shader-f16"),
    rg11b10ufloatRenderable(9, "rg11b10ufloat-renderable"),
    bgra8unormStorage(10, "bgra8unorm-storage"),
    float32Filterable(11, "float32-filterable");

    val actualName
        get() = if (extraName != null) extraName else name

    companion object {
        fun of(value: Int): FeatureName? = entries.find {
            it.value == value
        }
    }
}

enum class FilterMode(
    override val value: Int,
) : EnumerationWithValue {
    nearest(0),
    linear(1);

    companion object {
        fun of(value: Int): FilterMode? = entries.find {
            it.value == value
        }
    }
}

enum class FrontFace(
    override val value: Int,
) : EnumerationWithValue {
    ccw(0),
    cw(1);

    companion object {
        fun of(value: Int): FrontFace? = entries.find {
            it.value == value
        }
    }
}

enum class IndexFormat(
    override val value: Int,
) : EnumerationWithValue {

    uint16(1),
    uint32(2);

    companion object {
        fun of(value: Int): IndexFormat? = entries.find {
            it.value == value
        }
    }
}

enum class LoadOp(
    override val value: Int,
) : EnumerationWithValue {
    clear(1),
    load(2);

    companion object {
        fun of(value: Int): LoadOp? = entries.find {
            it.value == value
        }
    }
}

enum class MipmapFilterMode(
    override val value: Int,
) : EnumerationWithValue {
    nearest(0),
    linear(1);

    companion object {
        fun of(value: Int): MipmapFilterMode? = entries.find {
            it.value == value
        }
    }
}

enum class PowerPreference(
    override val value: Int,
    val stringValue: String?,
) : EnumerationWithValue {

    lowPower(1, "low-power"),
    highPerformance(2, "low-power");

    companion object {
        fun of(value: Int): PowerPreference? = entries.find {
            it.value == value
        }
    }
}

enum class PresentMode(
    override val value: Int,
) : EnumerationWithValue {
    fifo(0),
    fiforelaxed(1),
    immediate(2),
    mailbox(3);

    companion object {
        fun of(value: Int): PresentMode? = entries.find {
            it.value == value
        }
    }
}

enum class PrimitiveTopology(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    pointlist(0, "point-list"),
    linelist(1, "line-list"),
    linestrip(2, "line-strip"),
    trianglelist(3, "triangle-list"),
    trianglestrip(4, "triangle-strip");

    companion object {
        fun of(value: Int): PrimitiveTopology? = entries.find {
            it.value == value
        }
    }
}

enum class QueryType(
    val value: Int,
) {
    occlusion(0),
    timestamp(1);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: QueryType): Int = value or other.value

    companion object {
        fun of(value: Int): QueryType? = entries.find {
            it.value == value
        }
    }
}

enum class QueueWorkDoneStatus(
    val value: Int,
) {
    success(0),
    error(1),
    unknown(2),
    devicelost(3);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: QueueWorkDoneStatus): Int = value or other.value

    companion object {
        fun of(value: Int): QueueWorkDoneStatus? = entries.find {
            it.value == value
        }
    }
}

enum class RequestAdapterStatus(
    override val value: Int,
) : EnumerationWithValue {
    success(0),
    unavailable(1),
    error(2),
    unknown(3);

    companion object {
        fun of(value: Int): RequestAdapterStatus? = entries.find {
            it.value == value
        }
    }
}

enum class RequestDeviceStatus(
    val value: Int,
) {
    success(0),
    error(1),
    unknown(2);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: RequestDeviceStatus): Int = value or other.value

    companion object {
        fun of(value: Int): RequestDeviceStatus? = entries.find {
            it.value == value
        }
    }
}

enum class SType(
    val value: Int,
) {
    invalid(0),
    surfacedescriptorfrommetallayer(1),
    surfacedescriptorfromwindowshwnd(2),
    surfacedescriptorfromxlibwindow(3),
    surfacedescriptorfromcanvashtmlselector(4),
    shadermodulespirvdescriptor(5),
    shadermodulewgsldescriptor(6),
    primitivedepthclipcontrol(7),
    surfacedescriptorfromwaylandsurface(8),
    surfacedescriptorfromandroidnativewindow(9),
    surfacedescriptorfromxcbwindow(10),
    renderpassdescriptormaxdrawcount(15),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: SType): Int = value or other.value

    companion object {
        fun of(value: Int): SType? = entries.find {
            it.value == value
        }
    }
}

enum class SamplerBindingType(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    filtering(1, "filtering"),
    nonfiltering(2, "non-filtering"),
    comparison(3, "comparison");

    companion object {
        fun of(value: Int): SamplerBindingType? = entries.find {
            it.value == value
        }
    }
}

enum class StencilOperation(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    keep(0, "keep"),
    zero(1, "zero"),
    replace(2, "replace"),
    invert(3, "invert"),
    incrementclamp(4, "increment-clamp"),
    decrementclamp(5, "decrement-clamp"),
    incrementwrap(6, "increment-wrap"),
    decrementwrap(7, "decrement-wrap");

    companion object {
        fun of(value: Int): StencilOperation? = entries.find {
            it.value == value
        }
    }
}

enum class StorageTextureAccess(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    writeonly(1, "write-only"),
    readonly(2, "read-only"),
    readwrite(3, "read-write"),
    ;

    companion object {
        fun of(value: Int): StorageTextureAccess? = entries.find {
            it.value == value
        }
    }
}

enum class StoreOp(
    override val value: Int,
) : EnumerationWithValue {
    store(1),
    discard(2);

    companion object {
        fun of(value: Int): StoreOp? = entries.find {
            it.value == value
        }
    }
}

enum class SurfaceGetCurrentTextureStatus(
    val value: Int,
) {
    success(0),
    timeout(1),
    outdated(2),
    lost(3),
    outofmemory(4),
    devicelost(5);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: SurfaceGetCurrentTextureStatus): Int = value or other.value

    companion object {
        fun of(value: Int): SurfaceGetCurrentTextureStatus? = entries.find {
            it.value == value
        }
    }
}

enum class TextureAspect(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    all(0, "all"),
    stencilonly(1, "stencil-only"),
    depthonly(2, "depth-only");

    companion object {
        fun of(value: Int): TextureAspect? = entries.find {
            it.value == value
        }
    }
}

enum class TextureDimension(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    _1d(0, "1d"),
    _2d(1, "2d"),
    _3d(2, "3d"),
    ;

    companion object {
        fun of(value: Int): TextureDimension? = entries.find {
            it.value == value
        }

        fun of(value: String): TextureDimension? = entries.find {
            it.stringValue == value
        }
    }
}


enum class TextureSampleType(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    float(1, "float"),
    unfilterablefloat(2, "unfilterable-float"),
    depth(3, "depth"),
    sint(4, "sint"),
    uint(5, "uint");

    companion object {
        fun of(value: Int): TextureSampleType? = entries.find {
            it.value == value
        }
    }
}

enum class TextureViewDimension(
    override val value: Int,
    val stringValue: String,
) : EnumerationWithValue {
    _1d(1, "1d"),
    _2d(2, "2d"),
    _2darray(3, "2d-array"),
    cube(4, "cube"),
    cubearray(5, "cube-array"),
    _3d(6, "3d");

    companion object {
        fun of(value: Int): TextureViewDimension? = entries.find {
            it.value == value
        }
    }
}


enum class VertexStepMode(
    override val value: Int,
) : EnumerationWithValue {
    vertex(0),
    instance(1),
    vertexbuffernotused(2);

    companion object {
        fun of(value: Int): VertexStepMode? = entries.find {
            it.value == value
        }
    }
}

enum class BufferUsage(
    override val value: Int,
) : EnumerationWithValue {
    mapread(1),
    mapwrite(2),
    copysrc(4),
    copydst(8),
    index(16),
    vertex(32),
    uniform(64),
    storage(128),
    indirect(256),
    queryresolve(512);

    companion object {
        fun of(value: Int): BufferUsage? = entries.find {
            it.value == value
        }
    }
}

enum class ColorWriteMask(
    override val value: Int,
) : EnumerationWithValue {
    none(0),
    red(1),
    green(2),
    blue(4),
    alpha(8),
    all(15);

    companion object {
        fun of(value: Int): ColorWriteMask? = entries.find {
            it.value == value
        }
    }
}

enum class MapMode(
    override val value: Int,
) : EnumerationWithValue {
    read(1),
    write(2);

    companion object {
        fun of(value: Int): MapMode? = entries.find {
            it.value == value
        }
    }
}

enum class ShaderStage(
    override val value: Int,
) : EnumerationWithValue {
    vertex(1),
    fragment(2),
    compute(4);

    companion object {
        fun of(value: Int): ShaderStage? = entries.find {
            it.value == value
        }
    }
}

enum class TextureUsage(
    override val value: Int,
) : EnumerationWithValue {
    none(0),
    copysrc(1),
    copydst(2),
    texturebinding(4),
    storagebinding(8),
    renderattachment(16);

    companion object {
        fun of(value: Int): TextureUsage? = entries.find {
            it.value == value
        }
    }
}

infix fun Int.or(other: TextureUsage): Int = this or other.value

enum class NativeSType(
    val value: Int,
) {
    stype_deviceextras(196_609),
    stype_requiredlimitsextras(196_610),
    stype_pipelinelayoutextras(196_611),
    stype_shadermoduleglsldescriptor(196_612),
    stype_supportedlimitsextras(196_613),
    stype_instanceextras(196_614),
    stype_bindgroupentryextras(196_615),
    stype_bindgrouplayoutentryextras(196_616),
    stype_querysetdescriptorextras(196_617),
    stype_surfaceconfigurationextras(196_618);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: NativeSType): Int = value or other.value

    companion object {
        fun of(value: Int): NativeSType? = entries.find {
            it.value == value
        }
    }
}

enum class NativeFeature(
    val value: Int,
) {
    pushconstants(196_609),
    textureadapterspecificformatfeatures(196_610),
    multidrawindirect(196_611),
    multidrawindirectcount(196_612),
    vertexwritablestorage(196_613),
    texturebindingarray(196_614),
    sampledtextureandstoragebufferarraynonuniformindexing(196_615),
    pipelinestatisticsquery(196_616);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: NativeFeature): Int = value or other.value

    companion object {
        fun of(value: Int): NativeFeature? = entries.find {
            it.value == value
        }
    }
}

enum class LogLevel(
    val value: Int,
) {
    off(0),
    error(1),
    warn(2),
    info(3),
    debug(4),
    trace(5);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: LogLevel): Int = value or other.value

    companion object {
        fun of(value: Int): LogLevel? = entries.find {
            it.value == value
        }
    }
}

enum class InstanceBackend(
    val value: Int,
) {
    all(0),
    vulkan(1),
    gl(2),
    metal(4),
    dx12(8),
    dx11(16),
    browserwebgpu(32),
    primary(45),
    secondary(18);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: InstanceBackend): Int = value or other.value

    companion object {
        fun of(value: Int): InstanceBackend? = entries.find {
            it.value == value
        }
    }
}

enum class InstanceFlag(
    val value: Int,
) {
    default(0),
    debug(1),
    validation(2),
    discardhallabels(4);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: InstanceFlag): Int = value or other.value

    companion object {
        fun of(value: Int): InstanceFlag? = entries.find {
            it.value == value
        }
    }
}

enum class Dx12Compiler(
    val value: Int,
) {
    undefined(0),
    fxc(1),
    dxc(2);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: Dx12Compiler): Int = value or other.value

    companion object {
        fun of(value: Int): Dx12Compiler? = entries.find {
            it.value == value
        }
    }
}

enum class Gles3MinorVersion(
    val value: Int,
) {
    automatic(0),
    version0(1),
    version1(2),
    version2(3);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: Gles3MinorVersion): Int = value or other.value

    companion object {
        fun of(value: Int): Gles3MinorVersion? = entries.find {
            it.value == value
        }
    }
}

enum class PipelineStatisticName(
    val value: Int,
) {
    vertexshaderinvocations(0),
    clipperinvocations(1),
    clipperprimitivesout(2),
    fragmentshaderinvocations(3),
    computeshaderinvocations(4);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: PipelineStatisticName): Int = value or other.value

    companion object {
        fun of(value: Int): PipelineStatisticName? = entries.find {
            it.value == value
        }
    }
}

enum class NativeQueryType(
    val value: Int,
) {
    pipelinestatistics(196_608);

    infix fun or(other: Int): Int = value or other

    infix fun or(other: NativeQueryType): Int = value or other.value

    companion object {
        fun of(value: Int): NativeQueryType? = entries.find {
            it.value == value
        }
    }
}

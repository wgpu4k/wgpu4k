

package io.ygdrasil.wgpu

interface EnumerationWithValue {
    val value: Int

    infix fun or(other: Int): Int = value or other
    infix fun or(other: EnumerationWithValue): Int = value or other.value
}

internal fun Set<EnumerationWithValue>.toFlagInt(): Int = when (size) {
    0 -> 0
    1 -> first().value
    else -> fold(0) { acc, enumerationWithValue -> acc or enumerationWithValue.value }
}

enum class AdapterType(
    val `value`: Int,
) {
    discretegpu(0),
    integratedgpu(1),
    cpu(2),
    unknown(3),

    ;

    companion object {
        fun of(`value`: Int): AdapterType? = entries.find {
            it.value == value
        }
    }
}

enum class AddressMode(
    override val `value`: Int,
    val stringValue: String,
) : EnumerationWithValue {
    repeat(0, "repeat"),
    mirrorrepeat(1, "mirror-repeat"),
    clamptoedge(2, "clamp-to-edge"),
    ;

    companion object {
        fun of(`value`: Int): AddressMode? = entries.find {
            it.value == value
        }
    }
}

enum class BackendType(
    val `value`: Int,
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
        fun of(`value`: Int): BackendType? = entries.find {
            it.value == value
        }
    }
}

enum class BlendFactor(
    override val `value`: Int,
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
        fun of(`value`: Int): BlendFactor? = entries.find {
            it.value == value
        }
    }
}

enum class BlendOperation(
    override val `value`: Int,
    val stringValue: String,
) : EnumerationWithValue {
    add(0, "add"),
    subtract(1, "subtract"),
    reversesubtract(2, "reverse-subtract"),
    min(3, "min"),
    max(4, "max"),
    ;

    companion object {
        fun of(`value`: Int): BlendOperation? = entries.find {
            it.value == value
        }
    }
}

enum class BufferBindingType(
    override val `value`: Int,
    val stringValue: String,
) : EnumerationWithValue {
    uniform(1, "uniform"),
    storage(2, "storage"),
    readonlystorage(3, "read-only-storage");

    companion object {
        fun of(`value`: Int): BufferBindingType? = entries.find {
            it.value == value
        }
    }
}

enum class BufferMapAsyncStatus(
    val `value`: Int,
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
        fun of(`value`: Int): BufferMapAsyncStatus? = entries.find {
            it.value == value
        }
    }
}

enum class BufferMapState(
    val `value`: Int,
) {
    unmapped(0),
    pending(1),
    mapped(2),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: BufferMapState): Int = value or other.value

    companion object {
        fun of(`value`: Int): BufferMapState? = entries.find {
            it.value == value
        }
    }
}

enum class CompareFunction(
    override val `value`: Int,
    val stringValue: String,
) : EnumerationWithValue {
    never(1, "never"),
    less(2, "less"),
    lessequal(3, "less-equal"),
    greater(4, "greater"),
    greaterequal(5, "greater-equal"),
    equal(6, "equal"),
    notequal(7, "not-equal"),
    always(8, "always"),
    ;


    companion object {
        fun of(`value`: Int): CompareFunction? = entries.find {
            it.value == value
        }
    }
}

enum class CompilationInfoRequestStatus(
    val `value`: Int,
) {
    success(0),
    error(1),
    devicelost(2),
    unknown(3),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: CompilationInfoRequestStatus): Int = value or other.value

    companion object {
        fun of(`value`: Int): CompilationInfoRequestStatus? = entries.find {
            it.value == value
        }
    }
}

enum class CompilationMessageType(
    val `value`: Int,
) {
    error(0),
    warning(1),
    info(2),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: CompilationMessageType): Int = value or other.value

    companion object {
        fun of(`value`: Int): CompilationMessageType? = entries.find {
            it.value == value
        }
    }
}

enum class CompositeAlphaMode(
    val `value`: Int,
) {
    auto(0),
    opaque(1),
    premultiplied(2),
    postmultiplied(3),
    inherit(4),
    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: CompositeAlphaMode): Int = value or other.value

    companion object {
        fun of(`value`: Int): CompositeAlphaMode? = entries.find {
            it.value == value
        }
    }
}

enum class CreatePipelineAsyncStatus(
    val `value`: Int,
) {
    success(0),
    validationerror(1),
    internalerror(2),
    devicelost(3),
    devicedestroyed(4),
    unknown(5),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: CreatePipelineAsyncStatus): Int = value or other.value

    companion object {
        fun of(`value`: Int): CreatePipelineAsyncStatus? = entries.find {
            it.value == value
        }
    }
}

enum class CullMode(
    override val `value`: Int,
) : EnumerationWithValue {
    none(0),
    front(1),
    back(2),

    ;

    companion object {
        fun of(`value`: Int): CullMode? = entries.find {
            it.value == value
        }
    }
}

enum class DeviceLostReason(
    val `value`: Int,
) {
    undefined(0),
    destroyed(1),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: DeviceLostReason): Int = value or other.value

    companion object {
        fun of(`value`: Int): DeviceLostReason? = entries.find {
            it.value == value
        }
    }
}

enum class ErrorFilter(
    val `value`: Int,
) {
    validation(0),
    outofmemory(1),
    `internal`(2),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: ErrorFilter): Int = value or other.value

    companion object {
        fun of(`value`: Int): ErrorFilter? = entries.find {
            it.value == value
        }
    }
}

enum class ErrorType(
    val `value`: Int,
) {
    noerror(0),
    validation(1),
    outofmemory(2),
    `internal`(3),
    unknown(4),
    devicelost(5),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: ErrorType): Int = value or other.value

    companion object {
        fun of(`value`: Int): ErrorType? = entries.find {
            it.value == value
        }
    }
}

enum class FeatureName(
    val `value`: Int,
) {
    undefined(0),
    depthclipcontrol(1),
    depth32floatstencil8(2),
    timestampquery(3),
    texturecompressionbc(4),
    texturecompressionetc2(5),
    texturecompressionastc(6),
    indirectfirstinstance(7),
    shaderf16(8),
    rg11b10ufloatrenderable(9),
    bgra8unormstorage(10),
    float32filterable(11),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: FeatureName): Int = value or other.value

    companion object {
        fun of(`value`: Int): FeatureName? = entries.find {
            it.value == value
        }
    }
}

enum class FilterMode(
    override val `value`: Int,
) : EnumerationWithValue {
    nearest(0),
    linear(1),
    ;

    companion object {
        fun of(`value`: Int): FilterMode? = entries.find {
            it.value == value
        }
    }
}

enum class FrontFace(
    override val `value`: Int,
) : EnumerationWithValue {
    ccw(0),
    cw(1),

    ;

    companion object {
        fun of(`value`: Int): FrontFace? = entries.find {
            it.value == value
        }
    }
}

enum class IndexFormat(
    override val `value`: Int,
) : EnumerationWithValue {
    undefined(0),
    uint16(1),
    uint32(2),

    ;

    companion object {
        fun of(`value`: Int): IndexFormat? = entries.find {
            it.value == value
        }
    }
}

enum class LoadOp(
    override val `value`: Int,
) : EnumerationWithValue {
    clear(1),
    load(2);

    companion object {
        fun of(`value`: Int): LoadOp? = entries.find {
            it.value == value
        }
    }
}

enum class MipmapFilterMode(
    override val `value`: Int,
) : EnumerationWithValue {
    nearest(0),
    linear(1),
    ;

    companion object {
        fun of(`value`: Int): MipmapFilterMode? = entries.find {
            it.value == value
        }
    }
}

enum class PowerPreference(
    override val `value`: Int,
     val stringValue: String?,
) : EnumerationWithValue {

    lowPower(1, "low-power"),
    highPerformance(2, "low-power");

    companion object {
        fun of(`value`: Int): PowerPreference? = entries.find {
            it.value == value
        }
    }
}

enum class PresentMode(
    val `value`: Int,
) {
    fifo(0),
    fiforelaxed(1),
    immediate(2),
    mailbox(3),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: PresentMode): Int = value or other.value

    companion object {
        fun of(`value`: Int): PresentMode? = entries.find {
            it.value == value
        }
    }
}

enum class PrimitiveTopology(
    override val `value`: Int,
    val stringValue: String,
) : EnumerationWithValue {
    pointlist(0, "point-list"),
    linelist(1, "line-list"),
    linestrip(2, "line-strip"),
    trianglelist(3, "triangle-list"),
    trianglestrip(4, "triangle-strip"),
    ;

    companion object {
        fun of(`value`: Int): PrimitiveTopology? = entries.find {
            it.value == value
        }
    }
}

enum class QueryType(
    val `value`: Int,
) {
    occlusion(0),
    timestamp(1),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: QueryType): Int = value or other.value

    companion object {
        fun of(`value`: Int): QueryType? = entries.find {
            it.value == value
        }
    }
}

enum class QueueWorkDoneStatus(
    val `value`: Int,
) {
    success(0),
    error(1),
    unknown(2),
    devicelost(3),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: QueueWorkDoneStatus): Int = value or other.value

    companion object {
        fun of(`value`: Int): QueueWorkDoneStatus? = entries.find {
            it.value == value
        }
    }
}

enum class RequestAdapterStatus(
    val `value`: Int,
) {
    success(0),
    unavailable(1),
    error(2),
    unknown(3),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: RequestAdapterStatus): Int = value or other.value

    companion object {
        fun of(`value`: Int): RequestAdapterStatus? = entries.find {
            it.value == value
        }
    }
}

enum class RequestDeviceStatus(
    val `value`: Int,
) {
    success(0),
    error(1),
    unknown(2),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: RequestDeviceStatus): Int = value or other.value

    companion object {
        fun of(`value`: Int): RequestDeviceStatus? = entries.find {
            it.value == value
        }
    }
}

enum class SType(
    val `value`: Int,
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
        fun of(`value`: Int): SType? = entries.find {
            it.value == value
        }
    }
}

enum class SamplerBindingType(
    override val `value`: Int,
    val `stringValue`: String,
) : EnumerationWithValue{
    filtering(1, "filtering"),
    nonfiltering(2, "non-filtering"),
    comparison(3, "comparison");

    companion object {
        fun of(`value`: Int): SamplerBindingType? = entries.find {
            it.value == value
        }
    }
}

enum class StencilOperation(
    override val `value`: Int,
    val `stringValue`: String,
) : EnumerationWithValue {
    keep(0, "keep"),
    zero(1, "zero"),
    replace(2, "replace"),
    invert(3, "invert"),
    incrementclamp(4, "increment-clamp"),
    decrementclamp(5, "decrement-clamp"),
    incrementwrap(6, "increment-wrap"),
    decrementwrap(7, "decrement-wrap")
    ;

    companion object {
        fun of(`value`: Int): StencilOperation? = entries.find {
            it.value == value
        }
    }
}

enum class StorageTextureAccess(
    override val `value`: Int,
    val `stringValue`: String,
) : EnumerationWithValue {
    writeonly(1, "write-only"),
    readonly(2, "read-only"),
    readwrite(3, "read-write"),
    ;

    companion object {
        fun of(`value`: Int): StorageTextureAccess? = entries.find {
            it.value == value
        }
    }
}

enum class StoreOp(
    override val `value`: Int,
) : EnumerationWithValue {
    store(1),
    discard(2),
    ;

    companion object {
        fun of(`value`: Int): StoreOp? = entries.find {
            it.value == value
        }
    }
}

enum class SurfaceGetCurrentTextureStatus(
    val `value`: Int,
) {
    success(0),
    timeout(1),
    outdated(2),
    lost(3),
    outofmemory(4),
    devicelost(5),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: SurfaceGetCurrentTextureStatus): Int = value or other.value

    companion object {
        fun of(`value`: Int): SurfaceGetCurrentTextureStatus? = entries.find {
            it.value == value
        }
    }
}

enum class TextureAspect(
    override val `value`: Int,
    val stringValue: String,
) : EnumerationWithValue {
    all(0, "all"),
    stencilonly(1, "stencil-only"),
    depthonly(2, "depth-only"),
    ;

    companion object {
        fun of(`value`: Int): TextureAspect? = entries.find {
            it.value == value
        }
    }
}

enum class TextureDimension(
    override val `value`: Int,
    val stringValue: String
) : EnumerationWithValue {
    `_1d`(0, "1d"),
    `_2d`(1, "2d"),
    `_3d`(2, "3d"),
    ;

    companion object {
        fun of(`value`: Int): TextureDimension? = entries.find {
            it.value == value
        }

        fun of(`value`: String): TextureDimension? = entries.find {
            it.stringValue == value
        }
    }
}

enum class TextureFormat(
    override val value: Int,
) : EnumerationWithValue {
    r8unorm(1),
    r8snorm(2),
    r8uint(3),
    r8sint(4),
    r16uint(5),
    r16sint(6),
    r16float(7),
    rg8unorm(8),
    rg8snorm(9),
    rg8uint(10),
    rg8sint(11),
    r32float(12),
    r32uint(13),
    r32sint(14),
    rg16uint(15),
    rg16sint(16),
    rg16float(17),
    rgba8unorm(18),
    rgba8unormsrgb(19),
    rgba8snorm(20),
    rgba8uint(21),
    rgba8sint(22),
    bgra8unorm(23),
    bgra8unormsrgb(24),
    rgb10a2uint(25),
    rgb10a2unorm(26),
    rg11b10ufloat(27),
    rgb9e5ufloat(28),
    rg32float(29),
    rg32uint(30),
    rg32sint(31),
    rgba16uint(32),
    rgba16sint(33),
    rgba16float(34),
    rgba32float(35),
    rgba32uint(36),
    rgba32sint(37),
    stencil8(38),
    depth16unorm(39),
    depth24plus(40),
    depth24plusstencil8(41),
    depth32float(42),
    depth32floatstencil8(43),
    bc1rgbaunorm(44),
    bc1rgbaunormsrgb(45),
    bc2rgbaunorm(46),
    bc2rgbaunormsrgb(47),
    bc3rgbaunorm(48),
    bc3rgbaunormsrgb(49),
    bc4runorm(50),
    bc4rsnorm(51),
    bc5rgunorm(52),
    bc5rgsnorm(53),
    bc6hrgbufloat(54),
    bc6hrgbfloat(55),
    bc7rgbaunorm(56),
    bc7rgbaunormsrgb(57),
    etc2rgb8unorm(58),
    etc2rgb8unormsrgb(59),
    etc2rgb8a1unorm(60),
    etc2rgb8a1unormsrgb(61),
    etc2rgba8unorm(62),
    etc2rgba8unormsrgb(63),
    eacr11unorm(64),
    eacr11snorm(65),
    eacrg11unorm(66),
    eacrg11snorm(67),
    astc4x4unorm(68),
    astc4x4unormsrgb(69),
    astc5x4unorm(70),
    astc5x4unormsrgb(71),
    astc5x5unorm(72),
    astc5x5unormsrgb(73),
    astc6x5unorm(74),
    astc6x5unormsrgb(75),
    astc6x6unorm(76),
    astc6x6unormsrgb(77),
    astc8x5unorm(78),
    astc8x5unormsrgb(79),
    astc8x6unorm(80),
    astc8x6unormsrgb(81),
    astc8x8unorm(82),
    astc8x8unormsrgb(83),
    astc10x5unorm(84),
    astc10x5unormsrgb(85),
    astc10x6unorm(86),
    astc10x6unormsrgb(87),
    astc10x8unorm(88),
    astc10x8unormsrgb(89),
    astc10x10unorm(90),
    astc10x10unormsrgb(91),
    astc12x10unorm(92),
    astc12x10unormsrgb(93),
    astc12x12unorm(94),
    astc12x12unormsrgb(95),
    ;

    companion object {
        fun of(`value`: Int): TextureFormat? = entries.find {
            it.value == value
        }

        fun of(value: String): TextureFormat? = entries.find {
            it.name == value
        }
    }
}

enum class TextureSampleType(
    override val `value`: Int,
    val stringValue: String
) : EnumerationWithValue {
    float(1, "float"),
    unfilterablefloat(2, "unfilterable-float"),
    depth(3, "depth"),
    sint(4, "sint"),
    uint(5, "uint");

    companion object {
        fun of(`value`: Int): TextureSampleType? = entries.find {
            it.value == value
        }
    }
}

enum class TextureViewDimension(
    override val `value`: Int,
    val stringValue: String,
) : EnumerationWithValue {
    `_1d`(1, "1d"),
    `_2d`(2, "2d"),
    `_2darray`(3, "2d-array"),
    cube(4, "cube"),
    cubearray(5, "cube-array"),
    `_3d`(6, "3d"),
    ;

    companion object {
        fun of(`value`: Int): TextureViewDimension? = entries.find {
            it.value == value
        }
    }
}

enum class VertexFormat(
    override val `value`: Int,
) : EnumerationWithValue {
    undefined(0),
    uint8x2(1),
    uint8x4(2),
    sint8x2(3),
    sint8x4(4),
    unorm8x2(5),
    unorm8x4(6),
    snorm8x2(7),
    snorm8x4(8),
    uint16x2(9),
    uint16x4(10),
    sint16x2(11),
    sint16x4(12),
    unorm16x2(13),
    unorm16x4(14),
    snorm16x2(15),
    snorm16x4(16),
    float16x2(17),
    float16x4(18),
    float32(19),
    float32x2(20),
    float32x3(21),
    float32x4(22),
    uint32(23),
    uint32x2(24),
    uint32x3(25),
    uint32x4(26),
    sint32(27),
    sint32x2(28),
    sint32x3(29),
    sint32x4(30),
    ;

    companion object {
        fun of(`value`: Int): VertexFormat? = entries.find {
            it.value == value
        }
    }
}

enum class VertexStepMode(
    override val `value`: Int,
) : EnumerationWithValue {
    vertex(0),
    instance(1),
    vertexbuffernotused(2),
    ;

    companion object {
        fun of(`value`: Int): VertexStepMode? = entries.find {
            it.value == value
        }
    }
}

enum class BufferUsage(
    override val `value`: Int,
) : EnumerationWithValue {
    none(0),
    mapread(1),
    mapwrite(2),
    copysrc(4),
    copydst(8),
    index(16),
    vertex(32),
    uniform(64),
    storage(128),
    indirect(256),
    queryresolve(512),

    ;

    companion object {
        fun of(`value`: Int): BufferUsage? = entries.find {
            it.value == value
        }
    }
}

enum class ColorWriteMask(
    override val `value`: Int,
) : EnumerationWithValue {
    none(0),
    red(1),
    green(2),
    blue(4),
    alpha(8),
    all(15),
    ;

    companion object {
        fun of(`value`: Int): ColorWriteMask? = entries.find {
            it.value == value
        }
    }
}

enum class MapMode(
    val `value`: Int,
) {
    none(0),
    read(1),
    write(2),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: MapMode): Int = value or other.value

    companion object {
        fun of(`value`: Int): MapMode? = entries.find {
            it.value == value
        }
    }
}

enum class ShaderStage(
    val `value`: Int,
) {
    none(0),
    vertex(1),
    fragment(2),
    compute(4),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: ShaderStage): Int = value or other.value

    companion object {
        fun of(`value`: Int): ShaderStage? = entries.find {
            it.value == value
        }
    }
}

enum class TextureUsage(
    override val `value`: Int,
) : EnumerationWithValue {
    none(0),
    copysrc(1),
    copydst(2),
    texturebinding(4),
    storagebinding(8),
    renderattachment(16),

    ;

    companion object {
        fun of(`value`: Int): TextureUsage? = entries.find {
            it.value == value
        }
    }
}

infix fun Int.or(other: TextureUsage): Int = this or other.value

enum class NativeSType(
    val `value`: Int,
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
    stype_surfaceconfigurationextras(196_618),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: NativeSType): Int = value or other.value

    companion object {
        fun of(`value`: Int): NativeSType? = entries.find {
            it.value == value
        }
    }
}

enum class NativeFeature(
    val `value`: Int,
) {
    pushconstants(196_609),
    textureadapterspecificformatfeatures(196_610),
    multidrawindirect(196_611),
    multidrawindirectcount(196_612),
    vertexwritablestorage(196_613),
    texturebindingarray(196_614),
    sampledtextureandstoragebufferarraynonuniformindexing(196_615),
    pipelinestatisticsquery(196_616),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: NativeFeature): Int = value or other.value

    companion object {
        fun of(`value`: Int): NativeFeature? = entries.find {
            it.value == value
        }
    }
}

enum class LogLevel(
    val `value`: Int,
) {
    off(0),
    error(1),
    warn(2),
    info(3),
    debug(4),
    trace(5),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: LogLevel): Int = value or other.value

    companion object {
        fun of(`value`: Int): LogLevel? = entries.find {
            it.value == value
        }
    }
}

enum class InstanceBackend(
    val `value`: Int,
) {
    all(0),
    vulkan(1),
    gl(2),
    metal(4),
    dx12(8),
    dx11(16),
    browserwebgpu(32),
    primary(45),
    secondary(18),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: InstanceBackend): Int = value or other.value

    companion object {
        fun of(`value`: Int): InstanceBackend? = entries.find {
            it.value == value
        }
    }
}

enum class InstanceFlag(
    val `value`: Int,
) {
    default(0),
    debug(1),
    validation(2),
    discardhallabels(4),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: InstanceFlag): Int = value or other.value

    companion object {
        fun of(`value`: Int): InstanceFlag? = entries.find {
            it.value == value
        }
    }
}

enum class Dx12Compiler(
    val `value`: Int,
) {
    undefined(0),
    fxc(1),
    dxc(2),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: Dx12Compiler): Int = value or other.value

    companion object {
        fun of(`value`: Int): Dx12Compiler? = entries.find {
            it.value == value
        }
    }
}

enum class Gles3MinorVersion(
    val `value`: Int,
) {
    automatic(0),
    version0(1),
    version1(2),
    version2(3),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: Gles3MinorVersion): Int = value or other.value

    companion object {
        fun of(`value`: Int): Gles3MinorVersion? = entries.find {
            it.value == value
        }
    }
}

enum class PipelineStatisticName(
    val `value`: Int,
) {
    vertexshaderinvocations(0),
    clipperinvocations(1),
    clipperprimitivesout(2),
    fragmentshaderinvocations(3),
    computeshaderinvocations(4),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: PipelineStatisticName): Int = value or other.value

    companion object {
        fun of(`value`: Int): PipelineStatisticName? = entries.find {
            it.value == value
        }
    }
}

enum class NativeQueryType(
    val `value`: Int,
) {
    pipelinestatistics(196_608),

    ;

    infix fun or(other: Int): Int = value or other

    infix fun or(other: NativeQueryType): Int = value or other.value

    companion object {
        fun of(`value`: Int): NativeQueryType? = entries.find {
            it.value == value
        }
    }
}

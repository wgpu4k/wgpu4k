import io.ygdrasil.wgpu.EnumerationWithValue

enum class TextureFormat(
    override val value: Int,
    private val extraName: String? = null
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
    rgba8unormsrgb(19, "rgba8unorm-srgb"),
    rgba8snorm(20),
    rgba8uint(21),
    rgba8sint(22),
    bgra8unorm(23),
    bgra8unormsrgb(24, "bgra8unorm-srgb"),
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
    depth24plusstencil8(41, "depth24plus-stencil8"),
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

    val actualName
        get() = if (extraName != null) extraName else name

    companion object {
        fun of(`value`: Int): TextureFormat? = entries.find {
            it.value == value
        }

        fun of(value: String): TextureFormat? = entries.find {
            it.actualName == value
        }
    }
}

/* TODO fix missing value
"depth32float-stencil8"
"bc1-rgba-unorm"
"bc1-rgba-unorm-srgb"
"bc2-rgba-unorm"
"bc2-rgba-unorm-srgb"
"bc3-rgba-unorm"
"bc3-rgba-unorm-srgb"
"bc4-r-unorm"
"bc4-r-snorm"
"bc5-rg-unorm"
"bc5-rg-snorm"
"bc6h-rgb-ufloat"
"bc6h-rgb-float"
"bc7-rgba-unorm"
"bc7-rgba-unorm-srgb"
"etc2-rgb8unorm"
"etc2-rgb8unorm-srgb"
"etc2-rgb8a1unorm"
"etc2-rgb8a1unorm-srgb"
"etc2-rgba8unorm"
"etc2-rgba8unorm-srgb"
"eac-r11unorm"
"eac-r11snorm"
"eac-rg11unorm"
"eac-rg11snorm"
"astc-4x4-unorm"
"astc-4x4-unorm-srgb"
"astc-5x4-unorm"
"astc-5x4-unorm-srgb"
"astc-5x5-unorm"
"astc-5x5-unorm-srgb"
"astc-6x5-unorm"
"astc-6x5-unorm-srgb"
"astc-6x6-unorm"
"astc-6x6-unorm-srgb"
"astc-8x5-unorm"
"astc-8x5-unorm-srgb"
"astc-8x6-unorm"
"astc-8x6-unorm-srgb"
"astc-8x8-unorm"
"astc-8x8-unorm-srgb"
"astc-10x5-unorm"
"astc-10x5-unorm-srgb"
"astc-10x6-unorm"
"astc-10x6-unorm-srgb"
"astc-10x8-unorm"
"astc-10x8-unorm-srgb"
"astc-10x10-unorm"
"astc-10x10-unorm-srgb"
"astc-12x10-unorm"
"astc-12x10-unorm-srgb"
"astc-12x12-unorm"
"astc-12x12-unorm-srgb"
 */
package darwin

import com.sun.jna.Pointer
import org.rococoa.Foundation
import org.rococoa.ObjCClass
import org.rococoa.Rococoa
import org.rococoa.cocoa.CGFloat
import java.nio.FloatBuffer

/// <i>native declaration : :35</i>
abstract class NSColor : NSObject(), NSCopying {
	interface _Class : ObjCClass {
		/**
		 * Create NSCalibratedWhiteColorSpace colors.<br></br> Original signature : `NSColor*
		 * colorWithCalibratedWhite(CGFloat, CGFloat)`<br></br>
		 * *native declaration : :39*
		 */
		fun colorWithCalibratedWhite_alpha(white: CGFloat?, alpha: CGFloat?): NSColor?

		/**
		 * Create NSCalibratedRGBColorSpace colors.<br></br> Original signature : `NSColor*
		 * colorWithCalibratedHue(CGFloat, CGFloat, CGFloat, CGFloat)`<br></br>
		 * *native declaration : :44*
		 */
		fun colorWithCalibratedHue_saturation_brightness_alpha(
			hue: CGFloat?,
			saturation: CGFloat?,
			brightness: CGFloat?,
			alpha: CGFloat?
		): NSColor?

		/**
		 * Original signature : `NSColor* colorWithCalibratedRed(CGFloat, CGFloat, CGFloat, CGFloat)`<br></br>
		 * *native declaration : :45*
		 */
		fun colorWithCalibratedRed_green_blue_alpha(
			red: CGFloat?,
			green: CGFloat?,
			blue: CGFloat?,
			alpha: CGFloat?
		): NSColor?

		/**
		 * Create colors in various device color spaces. In PostScript these colorspaces correspond directly to the
		 * device-dependent operators setgray, sethsbcolor, setrgbcolor, and setcmykcolor.<br></br> Original signature :
		 * `NSColor* colorWithDeviceWhite(CGFloat, CGFloat)`<br></br>
		 * *native declaration : :50*
		 */
		fun colorWithDeviceWhite_alpha(white: CGFloat?, alpha: CGFloat?): NSColor?

		/**
		 * Original signature : `NSColor* colorWithDeviceHue(CGFloat, CGFloat, CGFloat, CGFloat)`<br></br>
		 * *native declaration : :51*
		 */
		fun colorWithDeviceHue_saturation_brightness_alpha(
			hue: CGFloat?,
			saturation: CGFloat?,
			brightness: CGFloat?,
			alpha: CGFloat?
		): NSColor?

		/**
		 * Original signature : `NSColor* colorWithDeviceRed(CGCGFloat, CGCGFloat, CGCGFloat,
		 * CGCGFloat)`<br></br>
		 * *native declaration : :52*
		 */
		fun colorWithDeviceRed_green_blue_alpha(
			red: CGFloat?,
			green: CGFloat?,
			blue: CGFloat?,
			alpha: CGFloat?
		): NSColor?

		/**
		 * Original signature : `NSColor* colorWithDeviceCyan(CGFloat, CGFloat, CGFloat, CGFloat,
		 * CGFloat)`<br></br>
		 * *native declaration : :53*
		 */
		fun colorWithDeviceCyan_magenta_yellow_black_alpha(
			cyan: CGFloat?,
			magenta: CGFloat?,
			yellow: CGFloat?,
			black: CGFloat?,
			alpha: CGFloat?
		): NSColor?

		/**
		 * Create named colors from standard color catalogs (such as Pantone).<br></br> Original signature : `NSColor*
		 * colorWithCatalogName(NSString*, NSString*)`<br></br>
		 * *native declaration : :58*
		 */
		fun colorWithCatalogName_colorName(listName: Pointer?, colorName: Pointer?): NSColor?

		/**
		 * Create colors with arbitrary colorspace. The number of components in the provided array should match the
		 * number dictated by the specified colorspace, plus one for alpha (supply 1.0 for opaque colors); otherwise an
		 * exception will be raised.  If the colorspace is one which cannot be used with NSColors, nil is returned.<br></br>
		 * Original signature : `NSColor* colorWithColorSpace(NSColorSpace*, const CGFloat*, NSInteger)`<br></br>
		 * *native declaration : :64*
		 */
		fun colorWithColorSpace_components_count(
			space: Pointer?,
			components: Array<CGFloat?>?,
			numberOfComponents: Int
		): NSColor?

		/**
		 * Create colors with arbitrary colorspace. The number of components in the provided array should match the
		 * number dictated by the specified colorspace, plus one for alpha (supply 1.0 for opaque colors); otherwise an
		 * exception will be raised.  If the colorspace is one which cannot be used with NSColors, nil is returned.<br></br>
		 * Original signature : `NSColor* colorWithColorSpace(NSColorSpace*, const CGFloat*, NSInteger)`<br></br>
		 * *native declaration : :64*
		 */
		fun colorWithColorSpace_components_count(
			space: Pointer?,
			components: FloatBuffer?,
			numberOfComponents: Int
		): NSColor?

		/**
		 * Some convenience methods to create colors in the calibrated color spaces...<br></br> Original signature :
		 * `NSColor* blackColor()`<br></br> 0.0 white<br></br>
		 * *native declaration : :70*
		 */
		fun blackColor(): NSColor?

		/**
		 * Original signature : `NSColor* darkGrayColor()`<br></br> 0.333 white<br></br>
		 * *native declaration : :71*
		 */
		fun darkGrayColor(): NSColor

		/**
		 * Original signature : `NSColor* lightGrayColor()`<br></br> 0.667 white<br></br>
		 * *native declaration : :72*
		 */
		fun lightGrayColor(): NSColor?

		/**
		 * Original signature : `NSColor* whiteColor()`<br></br> 1.0 white<br></br>
		 * *native declaration : :73*
		 */
		fun whiteColor(): NSColor

		/**
		 * Original signature : `NSColor* grayColor()`<br></br> 0.5 white<br></br>
		 * *native declaration : :74*
		 */
		fun grayColor(): NSColor?

		/**
		 * Original signature : `NSColor* redColor()`<br></br> 1.0, 0.0, 0.0 RGB<br></br>
		 * *native declaration : :75*
		 */
		fun redColor(): NSColor

		/**
		 * Original signature : `NSColor* greenColor()`<br></br> 0.0, 1.0, 0.0 RGB<br></br>
		 * *native declaration : :76*
		 */
		fun greenColor(): NSColor?

		/**
		 * Original signature : `NSColor* blueColor()`<br></br> 0.0, 0.0, 1.0 RGB<br></br>
		 * *native declaration : :77*
		 */
		fun blueColor(): NSColor

		/**
		 * Original signature : `NSColor* cyanColor()`<br></br> 0.0, 1.0, 1.0 RGB<br></br>
		 * *native declaration : :78*
		 */
		fun cyanColor(): NSColor?

		/**
		 * Original signature : `NSColor* yellowColor()`<br></br> 1.0, 1.0, 0.0 RGB<br></br>
		 * *native declaration : :79*
		 */
		fun yellowColor(): NSColor?

		/**
		 * Original signature : `NSColor* magentaColor()`<br></br> 1.0, 0.0, 1.0 RGB<br></br>
		 * *native declaration : :80*
		 */
		fun magentaColor(): NSColor?

		/**
		 * Original signature : `NSColor* orangeColor()`<br></br> 1.0, 0.5, 0.0 RGB<br></br>
		 * *native declaration : :81*
		 */
		fun orangeColor(): NSColor?

		/**
		 * Original signature : `NSColor* purpleColor()`<br></br> 0.5, 0.0, 0.5 RGB<br></br>
		 * *native declaration : :82*
		 */
		fun purpleColor(): NSColor?

		/**
		 * Original signature : `NSColor* brownColor()`<br></br> 0.6, 0.4, 0.2 RGB<br></br>
		 * *native declaration : :83*
		 */
		fun brownColor(): NSColor?

		/**
		 * Original signature : `NSColor* clearColor()`<br></br> 0.0 white, 0.0 alpha<br></br>
		 * *native declaration : :84*
		 */
		fun clearColor(): NSColor?

		/**
		 * Original signature : `NSColor* controlShadowColor()`<br></br> Dark border for controls<br></br>
		 * *native declaration : :86*
		 */
		fun controlShadowColor(): NSColor

		/**
		 * Original signature : `NSColor* controlDarkShadowColor()`<br></br> Darker border for controls<br></br>
		 * *native declaration : :87*
		 */
		fun controlDarkShadowColor(): NSColor

		/**
		 * Original signature : `NSColor* controlColor()`<br></br> Control face and old window background
		 * color<br></br>
		 * *native declaration : :88*
		 */
		fun controlColor(): NSColor

		/**
		 * Original signature : `NSColor* controlHighlightColor()`<br></br> Light border for controls<br></br>
		 * *native declaration : :89*
		 */
		fun controlHighlightColor(): NSColor

		/**
		 * Original signature : `NSColor* controlLightHighlightColor()`<br></br> Lighter border for controls<br></br>
		 * *native declaration : :90*
		 */
		fun controlLightHighlightColor(): NSColor

		/**
		 * Original signature : `NSColor* controlTextColor()`<br></br> Text on controls<br></br>
		 * *native declaration : :91*
		 */
		fun controlTextColor(): NSColor

		/**
		 * Original signature : `NSColor* controlBackgroundColor()`<br></br> Background of large controls
		 * (browser, tableview, clipview, ...)<br></br>
		 * *native declaration : :92*
		 */
		fun controlBackgroundColor(): NSColor

		/**
		 * Original signature : `NSColor* selectedControlColor()`<br></br> Control face for selected controls<br></br>
		 * *native declaration : :93*
		 */
		fun selectedControlColor(): NSColor

		/**
		 * Original signature : `NSColor* secondarySelectedControlColor()`<br></br> Color for selected controls
		 * when control is not active (that is, not focused)<br></br>
		 * *native declaration : :94*
		 */
		fun secondarySelectedControlColor(): NSColor

		/**
		 * Original signature : `NSColor* selectedControlTextColor()`<br></br> Text on selected controls<br></br>
		 * *native declaration : :95*
		 */
		fun selectedControlTextColor(): NSColor

		/**
		 * Original signature : `NSColor* disabledControlTextColor()`<br></br> Text on disabled controls<br></br>
		 * *native declaration : :96*
		 */
		fun disabledControlTextColor(): NSColor

		/**
		 * Original signature : `NSColor* textColor()`<br></br> Document text<br></br>
		 * *native declaration : :97*
		 */
		fun textColor(): NSColor

		/**
		 * Original signature : `NSColor* textBackgroundColor()`<br></br> Document text background<br></br>
		 * *native declaration : :98*
		 */
		fun textBackgroundColor(): NSColor

		/**
		 * Original signature : `NSColor* selectedTextColor()`<br></br> Selected document text<br></br>
		 * *native declaration : :99*
		 */
		fun selectedTextColor(): NSColor

		/**
		 * Original signature : `NSColor* selectedTextBackgroundColor()`<br></br> Selected document text
		 * background<br></br>
		 * *native declaration : :100*
		 */
		fun selectedTextBackgroundColor(): NSColor

		/**
		 * Original signature : `NSColor* gridColor()`<br></br> Grids in controls<br></br>
		 * *native declaration : :101*
		 */
		fun gridColor(): NSColor

		/**
		 * Original signature : `NSColor* keyboardFocusIndicatorColor()`<br></br> Keyboard focus ring around
		 * controls<br></br>
		 * *native declaration : :102*
		 */
		fun keyboardFocusIndicatorColor(): NSColor

		/**
		 * Original signature : `NSColor* windowBackgroundColor()`<br></br> Background fill for window
		 * contents<br></br>
		 * *native declaration : :103*
		 */
		fun windowBackgroundColor(): NSColor

		/**
		 * Original signature : `NSColor* scrollBarColor()`<br></br> Scroll bar slot color<br></br>
		 * *native declaration : :105*
		 */
		fun scrollBarColor(): NSColor

		/**
		 * Original signature : `NSColor* knobColor()`<br></br> Knob face color for controls<br></br>
		 * *native declaration : :106*
		 */
		fun knobColor(): NSColor

		/**
		 * Original signature : `NSColor* selectedKnobColor()`<br></br> Knob face color for selected controls<br></br>
		 * *native declaration : :107*
		 */
		fun selectedKnobColor(): NSColor

		/**
		 * Original signature : `NSColor* windowFrameColor()`<br></br> Window frames<br></br>
		 * *native declaration : :109*
		 */
		fun windowFrameColor(): NSColor

		/**
		 * Original signature : `NSColor* windowFrameTextColor()`<br></br> Text on window frames<br></br>
		 * *native declaration : :110*
		 */
		fun windowFrameTextColor(): NSColor

		/**
		 * Original signature : `NSColor* selectedMenuItemColor()`<br></br> Highlight color for menus<br></br>
		 * *native declaration : :112*
		 */
		fun selectedMenuItemColor(): NSColor

		/**
		 * Original signature : `NSColor* selectedMenuItemTextColor()`<br></br> Highlight color for menu text<br></br>
		 * *native declaration : :113*
		 */
		fun selectedMenuItemTextColor(): NSColor

		/**
		 * Original signature : `NSColor* highlightColor()`<br></br> Highlight color for UI elements (this is
		 * abstract and defines the color all highlights tend toward)<br></br>
		 * *native declaration : :115*
		 */
		fun highlightColor(): NSColor

		/**
		 * Original signature : `NSColor* shadowColor()`<br></br> Shadow color for UI elements (this is abstract
		 * and defines the color all shadows tend toward)<br></br>
		 * *native declaration : :116*
		 */
		fun shadowColor(): NSColor

		/**
		 * Original signature : `NSColor* headerColor()`<br></br> Background color for header cells in
		 * Table/OutlineView<br></br>
		 * *native declaration : :118*
		 */
		fun headerColor(): NSColor

		/**
		 * Original signature : `NSColor* headerTextColor()`<br></br> Text color for header cells in
		 * Table/OutlineView<br></br>
		 * *native declaration : :119*
		 */
		fun headerTextColor(): NSColor

		/**
		 * Original signature : `NSColor* alternateSelectedControlColor()`<br></br> Similar to
		 * selectedControlColor; for use in lists and tables<br></br>
		 * *native declaration : :122*
		 */
		fun alternateSelectedControlColor(): NSColor

		/**
		 * Original signature : `NSColor* alternateSelectedControlTextColor()`<br></br> Similar to
		 * selectedControlTextColor; see alternateSelectedControlColor<br></br>
		 * *native declaration : :123*
		 */
		fun alternateSelectedControlTextColor(): NSColor

		/**
		 * Original signature : `NSArray* controlAlternatingRowBackgroundColors()`<br></br> Standard colors for
		 * alternating colored rows in tables and lists (for instance, light blue/white; don't assume just two
		 * colors)<br></br>
		 * *native declaration : :127*
		 */
		fun controlAlternatingRowBackgroundColors(): NSArray?
		/**
		 * *native declaration : :133*<br></br>
		 * Conversion Error : /// Original signature : `NSColor* colorForControlTint(null)`<br></br>
		 * + (NSColor*)colorForControlTint:(null)controlTint; // pass in valid tint to get rough color matching. returns default if invalid tint<br></br>
		 * (Argument controlTint cannot be converted)
		 */
		/**
		 * Original signature : `currentControlTint()`<br></br> returns current system control tint<br></br>
		 * *native declaration : :136*
		 */
		fun currentControlTint(): Pointer?

		/**
		 * Pasteboard methods<br></br> Original signature : `NSColor* colorFromPasteboard(NSPasteboard*)`<br></br>
		 * *native declaration : :243*
		 */
		fun colorFromPasteboard(pasteBoard: NSPasteboard?): NSColor?

		/**
		 * Pattern methods. Note that colorWithPatternImage: mistakenly returns a non-autoreleased color in 10.1.x and
		 * earlier. This has been fixed in (NSAppKitVersionNumber >= NSAppKitVersionNumberWithPatternColorLeakFix), for
		 * apps linked post-10.1.x.<br></br> Original signature : `NSColor* colorWithPatternImage(NSImage*)`<br></br>
		 * *native declaration : :248*
		 */
		fun colorWithPatternImage(image: NSImage?): NSColor?

		/**
		 * Global flag for determining whether an application supports alpha.  This flag is consulted when an
		 * application imports alpha (through color dragging, for instance). The value of this flag also determines
		 * whether the color panel has an opacity slider. This value is YES by default, indicating that the opacity
		 * components of imported colors will be set to 1.0. If an application wants alpha, it can either set the
		 * "NSIgnoreAlpha" default to NO or call the set method below.<br></br> This method provides a global approach to
		 * removing alpha which might not always be appropriate. Applications which need to import alpha sometimes
		 * should set this flag to NO and explicitly make colors opaque in cases where it matters to them.<br></br> Original
		 * signature : `void setIgnoresAlpha(BOOL)`<br></br>
		 * *native declaration : :260*
		 */
		fun setIgnoresAlpha(flag: Boolean)

		/**
		 * Original signature : `BOOL ignoresAlpha()`<br></br>
		 * *native declaration : :261*
		 */
		fun ignoresAlpha(): Boolean

		/**
		 * Original signature : `NSColor* colorWithCIColor(CIColor*)`<br></br>
		 * *from NSQuartzCoreAdditions native declaration : :268*
		 */
		//        NSColor colorWithCIColor(CIColor color);
		fun systemBlueColor(): NSColor?

		fun systemBrownColor(): NSColor?

		fun systemGrayColor(): NSColor

		fun systemGreenColor(): NSColor?

		fun systemOrangeColor(): NSColor?

		fun systemPinkColor(): NSColor?

		fun systemPurpleColor(): NSColor?

		fun systemRedColor(): NSColor?

		fun systemYellowColor(): NSColor?

		fun placeholderTextColor(): NSColor

		fun labelColor(): NSColor

		fun secondaryLabelColor(): NSColor

		fun tertiaryLabelColor(): NSColor

		fun quaternaryLabelColor(): NSColor
	}

	/**
	 * Original signature : `NSColor* highlightWithLevel(CGFloat)`<br></br> val = 0 => receiver, val = 1 =>
	 * highlightColor<br></br>
	 * *native declaration : :130*
	 */
	abstract fun highlightWithLevel(`val`: CGFloat?): NSColor?

	/**
	 * Original signature : `NSColor* shadowWithLevel(CGFloat)`<br></br> val = 0 => receiver, val = 1 =>
	 * shadowColor<br></br>
	 * *native declaration : :131*
	 */
	abstract fun shadowWithLevel(`val`: CGFloat?): NSColor?

	/**
	 * Set the color: Sets the fill and stroke colors in the current drawing context. If the color doesn't know about
	 * alpha, it's set to 1.0. Should be implemented by subclassers.<br></br> Original signature : `void
	 * set()`<br></br>
	 * *native declaration : :142*
	 */
	abstract fun set()

	/**
	 * Set the fill or stroke colors individually. These should be implemented by subclassers.<br></br> Original signature :
	 * `void setFill()`<br></br>
	 * *native declaration : :147*
	 */
	abstract fun setFill()

	/**
	 * Original signature : `void setStroke()`<br></br>
	 * *native declaration : :148*
	 */
	abstract fun setStroke()

	/**
	 * Get the color space of the color. Should be implemented by subclassers.<br></br> Original signature : `NSString*
	 * colorSpaceName()`<br></br>
	 * *native declaration : :153*
	 */
	abstract fun colorSpaceName(): Pointer?

	/**
	 * Convert the color to another colorspace, using a colorspace name. This may return nil if the specified conversion
	 * cannot be done. The abstract implementation of this method returns the receiver if the specified colorspace
	 * matches that of the receiver; otherwise it returns nil. Subclassers who can convert themselves to other
	 * colorspaces override this method to do something better.<br></br> The version of this method which takes a device
	 * description allows the color to specialize itself for the given device.  Device descriptions can be obtained from
	 * windows, screens, and printers with the "deviceDescription" method.<br></br> If device is nil then the current device
	 * (as obtained from the currently lockFocus'ed view's window or, if printing, the current printer) is used. The
	 * method without the device: argument passes nil for the device.<br></br> If colorSpace is nil, then the most
	 * appropriate color space is used.<br></br> Original signature : `NSColor* colorUsingColorSpaceName(NSString*)`<br></br>
	 * *native declaration : :164*
	 */
	abstract fun colorUsingColorSpaceName(colorSpace: Pointer?): NSColor?

	/**
	 * Original signature : `NSColor* colorUsingColorSpaceName(NSString*, NSDictionary*)`<br></br>
	 * *native declaration : :165*
	 */
	abstract fun colorUsingColorSpaceName_device(colorSpace: Pointer?, deviceDescription: Pointer?): NSColor?

	/**
	 * colorUsingColorSpace: will convert existing color to a new colorspace and create a new color, which will likely
	 * have different component values but look the same. It will return the same color if the colorspace is already the
	 * same as the one specified.  Will return nil if conversion is not possible.<br></br> Original signature :
	 * `NSColor* colorUsingColorSpace(NSColorSpace*)`<br></br>
	 * *native declaration : :171*
	 */
	abstract fun colorUsingColorSpace(space: Pointer?): NSColor?

	/**
	 * Blend using the NSCalibratedRGB color space. Both colors are converted into the calibrated RGB color space, and
	 * they are blended by taking fraction of color and 1 - fraction of the receiver. The result is in the calibrated
	 * RGB color space. If the colors cannot be converted into the calibrated RGB color space the blending fails and nil
	 * is returned.<br></br> Original signature : `NSColor* blendedColorWithFraction(CGFloat, NSColor*)`<br></br>
	 * *native declaration : :177*
	 */
	abstract fun blendedColorWithFraction_ofColor(fraction: CGFloat?, color: NSColor?): NSColor?

	/**
	 * Returns a color in the same color space as the receiver with the specified alpha component. The abstract
	 * implementation of this method returns the receiver if alpha is 1.0, otherwise it returns nil; subclassers who
	 * have explicit opacity components override this method to actually return a color with the specified alpha.<br></br>
	 * Original signature : `NSColor* colorWithAlphaComponent(CGFloat)`<br></br>
	 * *native declaration : :182*
	 */
	abstract fun colorWithAlphaComponent(alpha: CGFloat?): NSColor?

	/**
	 * Get the catalog and color name of standard colors from catalogs. These colors are special colors which are
	 * usually looked up on each device by their name.<br></br> Original signature : `NSString*
	 * catalogNameComponent()`<br></br>
	 * *native declaration : :189*
	 */
	abstract fun catalogNameComponent(): String?

	/**
	 * Original signature : `NSString* colorNameComponent()`<br></br>
	 * *native declaration : :190*
	 */
	abstract fun colorNameComponent(): String?

	/**
	 * Return localized versions of the above.<br></br> Original signature : `NSString*
	 * localizedCatalogNameComponent()`<br></br>
	 * *native declaration : :194*
	 */
	abstract fun localizedCatalogNameComponent(): String?

	/**
	 * Original signature : `NSString* localizedColorNameComponent()`<br></br>
	 * *native declaration : :195*
	 */
	abstract fun localizedColorNameComponent(): String?

	/**
	 * Get the red, green, or blue components of NSCalibratedRGB or NSDeviceRGB colors.<br></br> Original signature :
	 * `CGFloat redComponent()`<br></br>
	 * *native declaration : :199*
	 */
	abstract fun redComponent(): CGFloat?

	/**
	 * Original signature : `CGFloat greenComponent()`<br></br>
	 * *native declaration : :200*
	 */
	abstract fun greenComponent(): CGFloat?

	/**
	 * Original signature : `CGFloat blueComponent()`<br></br>
	 * *native declaration : :201*
	 */
	abstract fun blueComponent(): CGFloat?

	/**
	 * Original signature : `void getRed(CGFloat*, CGFloat*, CGFloat*, CGFloat*)`<br></br>
	 * *native declaration : :202*
	 */
	abstract fun getRed_green_blue_alpha(red: CGFloat?, green: CGFloat?, blue: CGFloat?, alpha: CGFloat?)

	/**
	 * Get the components of NSCalibratedRGB or NSDeviceRGB colors as hue, saturation, or brightness.<br></br> Original
	 * signature : `CGFloat hueComponent()`<br></br>
	 * *native declaration : :206*
	 */
	abstract fun hueComponent(): CGFloat?

	/**
	 * Original signature : `CGFloat saturationComponent()`<br></br>
	 * *native declaration : :207*
	 */
	abstract fun saturationComponent(): CGFloat?

	/**
	 * Original signature : `CGFloat brightnessComponent()`<br></br>
	 * *native declaration : :208*
	 */
	abstract fun brightnessComponent(): CGFloat?

	/**
	 * Original signature : `void getHue(CGFloat*, CGFloat*, CGFloat*, CGFloat*)`<br></br>
	 * *native declaration : :209*
	 */
	abstract fun getHue_saturation_brightness_alpha(
		hue: FloatBuffer?,
		saturation: FloatBuffer?,
		brightness: FloatBuffer?,
		alpha: FloatBuffer?
	)

	/**
	 * Get the white component of NSCalibratedWhite or NSDeviceWhite colors.<br></br> Original signature : `CGFloat
	 * whiteComponent()`<br></br>
	 * *native declaration : :214*
	 */
	abstract fun whiteComponent(): CGFloat?

	/**
	 * Original signature : `void getWhite(CGFloat*, CGFloat*)`<br></br>
	 * *native declaration : :215*
	 */
	abstract fun getWhite_alpha(white: FloatBuffer?, alpha: FloatBuffer?)

	/**
	 * Get the CMYK components of NSDeviceCMYK colors.<br></br> Original signature : `CGFloat
	 * cyanComponent()`<br></br>
	 * *native declaration : :220*
	 */
	abstract fun cyanComponent(): CGFloat?

	/**
	 * Original signature : `CGFloat magentaComponent()`<br></br>
	 * *native declaration : :221*
	 */
	abstract fun magentaComponent(): CGFloat?

	/**
	 * Original signature : `CGFloat yellowComponent()`<br></br>
	 * *native declaration : :222*
	 */
	abstract fun yellowComponent(): CGFloat?

	/**
	 * Original signature : `CGFloat blackComponent()`<br></br>
	 * *native declaration : :223*
	 */
	abstract fun blackComponent(): CGFloat?

	/**
	 * Original signature : `void getCyan(CGFloat*, CGFloat*, CGFloat*, CGFloat*, CGFloat*)`<br></br>
	 * *native declaration : :224*
	 */
	abstract fun getCyan_magenta_yellow_black_alpha(
		cyan: FloatBuffer?,
		magenta: FloatBuffer?,
		yellow: FloatBuffer?,
		black: FloatBuffer?,
		alpha: FloatBuffer?
	)

	/**
	 * For colors with custom colorspace; get the colorspace and individual floating point components, including alpha.
	 * Note that all these methods will work for other NSColors which have floating point components. They will raise
	 * exceptions otherwise, like other existing colorspace-specific methods.<br></br> Original signature :
	 * `NSColorSpace* colorSpace()`<br></br>
	 * *native declaration : :230*
	 */
	abstract fun colorSpace(): Pointer?

	/**
	 * Original signature : `NSInteger numberOfComponents()`<br></br>
	 * *native declaration : :231*
	 */
	abstract fun numberOfComponents(): Int

	/**
	 * Original signature : `void getComponents(CGFloat*)`<br></br>
	 * *native declaration : :232*
	 */
	abstract fun getComponents(components: FloatBuffer?)

	/**
	 * Get the alpha component. For colors which do not have alpha components, this will return 1.0 (opaque).<br></br>
	 * Original signature : `CGFloat alphaComponent()`<br></br>
	 * *native declaration : :238*
	 */
	abstract fun alphaComponent(): CGFloat?

	/**
	 * Original signature : `void writeToPasteboard(NSPasteboard*)`<br></br>
	 * *native declaration : :244*
	 */
	abstract fun writeToPasteboard(pasteBoard: NSPasteboard?)

	/**
	 * Original signature : `NSImage* patternImage()`<br></br>
	 * *native declaration : :249*
	 */
	abstract fun patternImage(): NSImage?

	/**
	 * *native declaration : :253*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Draws the color and adorns it to indicate the type of color. Used by colorwells, swatches, and other UI objects that need to display colors. Implementation in NSColor simply draws the color (with an indication of transparency if the color isn't fully opaque); subclassers can draw more stuff as they see fit.<br></br>
	 * * Original signature : `void drawSwatchInRect(null)`<br></br>
	 * * /<br></br>
	 * - (void)drawSwatchInRect:(null)rect; (Argument rect cannot be converted)
	 */
	companion object {
		private val CLASS: _Class = Rococoa.createClass("NSColor", _Class::class.java)

		fun labelColor(): NSColor {
			if (Rococoa.cast(CLASS, NSColor::class.java).respondsToSelector(Foundation.selector("labelColor"))) {
				return CLASS.labelColor()
			}
			return controlTextColor()
		}

		fun secondaryLabelColor(): NSColor {
			if (Rococoa.cast(CLASS, NSColor::class.java)
					.respondsToSelector(Foundation.selector("secondaryLabelColor"))
			) {
				return CLASS.secondaryLabelColor()
			}
			return systemGrayColor()
		}

		fun tertiaryLabelColor(): NSColor {
			if (Rococoa.cast(CLASS, NSColor::class.java)
					.respondsToSelector(Foundation.selector("tertiaryLabelColor"))
			) {
				return CLASS.tertiaryLabelColor()
			}
			return systemGrayColor()
		}

		fun quaternaryLabelColor(): NSColor {
			if (Rococoa.cast(CLASS, NSColor::class.java)
					.respondsToSelector(Foundation.selector("quaternaryLabelColor"))
			) {
				return CLASS.quaternaryLabelColor()
			}
			return systemGrayColor()
		}

		fun placeholderTextColor(): NSColor {
			if (Rococoa.cast(CLASS, NSColor::class.java)
					.respondsToSelector(Foundation.selector("placeholderTextColor"))
			) {
				return CLASS.placeholderTextColor()
			}
			return systemGrayColor()
		}

		fun whiteColor(): NSColor {
			return CLASS.whiteColor()
		}

		fun systemGrayColor(): NSColor {
			if (Rococoa.cast(CLASS, NSObject::class.java).respondsToSelector(Foundation.selector("systemGrayColor"))) {
				return CLASS.systemGrayColor()
			}
			return darkGrayColor()
		}

		fun darkGrayColor(): NSColor {
			return CLASS.darkGrayColor()
		}

		fun blueColor(): NSColor {
			return CLASS.blueColor()
		}

		fun redColor(): NSColor {
			return CLASS.redColor()
		}

		/**
		 * Original signature : `NSColor* controlShadowColor()`<br></br> Dark border for controls<br></br>
		 * *native declaration : :86*
		 */
		fun controlShadowColor(): NSColor {
			return CLASS.controlShadowColor()
		}

		/**
		 * Original signature : `NSColor* controlDarkShadowColor()`<br></br> Darker border for controls<br></br>
		 * *native declaration : :87*
		 */
		fun controlDarkShadowColor(): NSColor {
			return CLASS.controlDarkShadowColor()
		}

		/**
		 * Original signature : `NSColor* controlColor()`<br></br> Control face and old window background color<br></br>
		 * *native declaration : :88*
		 */
		fun controlColor(): NSColor {
			return CLASS.controlColor()
		}

		/**
		 * Original signature : `NSColor* controlHighlightColor()`<br></br> Light border for controls<br></br>
		 * *native declaration : :89*
		 */
		fun controlHighlightColor(): NSColor {
			return CLASS.controlHighlightColor()
		}

		/**
		 * Original signature : `NSColor* controlLightHighlightColor()`<br></br> Lighter border for controls<br></br>
		 * *native declaration : :90*
		 */
		fun controlLightHighlightColor(): NSColor {
			return CLASS.controlLightHighlightColor()
		}

		/**
		 * Original signature : `NSColor* controlTextColor()`<br></br> Text on controls<br></br>
		 * *native declaration : :91*
		 */
		fun controlTextColor(): NSColor {
			return CLASS.controlTextColor()
		}

		/**
		 * Original signature : `NSColor* controlBackgroundColor()`<br></br> Background of large controls (browser,
		 * tableview, clipview, ...)<br></br>
		 * *native declaration : :92*
		 */
		fun controlBackgroundColor(): NSColor {
			return CLASS.controlBackgroundColor()
		}

		/**
		 * Original signature : `NSColor* selectedControlColor()`<br></br> Control face for selected controls<br></br>
		 * *native declaration : :93*
		 */
		fun selectedControlColor(): NSColor {
			return CLASS.selectedControlColor()
		}

		/**
		 * Original signature : `NSColor* secondarySelectedControlColor()`<br></br> Color for selected controls when
		 * control is not active (that is, not focused)<br></br>
		 * *native declaration : :94*
		 */
		fun secondarySelectedControlColor(): NSColor {
			return CLASS.secondarySelectedControlColor()
		}

		/**
		 * Original signature : `NSColor* selectedControlTextColor()`<br></br> Text on selected controls<br></br>
		 * *native declaration : :95*
		 */
		fun selectedControlTextColor(): NSColor {
			return CLASS.selectedControlTextColor()
		}

		/**
		 * Original signature : `NSColor* disabledControlTextColor()`<br></br> Text on disabled controls<br></br>
		 * *native declaration : :96*
		 */
		fun disabledControlTextColor(): NSColor {
			return CLASS.disabledControlTextColor()
		}

		/**
		 * Original signature : `NSColor* textColor()`<br></br> Document text<br></br>
		 * *native declaration : :97*
		 */
		fun textColor(): NSColor {
			return CLASS.textColor()
		}

		/**
		 * Original signature : `NSColor* textBackgroundColor()`<br></br> Document text background<br></br>
		 * *native declaration : :98*
		 */
		fun textBackgroundColor(): NSColor {
			return CLASS.textBackgroundColor()
		}

		/**
		 * Original signature : `NSColor* selectedTextColor()`<br></br> Selected document text<br></br>
		 * *native declaration : :99*
		 */
		fun selectedTextColor(): NSColor {
			return CLASS.selectedTextColor()
		}

		/**
		 * Original signature : `NSColor* selectedTextBackgroundColor()`<br></br> Selected document text
		 * background<br></br>
		 * *native declaration : :100*
		 */
		fun selectedTextBackgroundColor(): NSColor {
			return CLASS.selectedTextBackgroundColor()
		}

		/**
		 * Original signature : `NSColor* gridColor()`<br></br> Grids in controls<br></br>
		 * *native declaration : :101*
		 */
		fun gridColor(): NSColor {
			return CLASS.gridColor()
		}

		/**
		 * Original signature : `NSColor* keyboardFocusIndicatorColor()`<br></br> Keyboard focus ring around
		 * controls<br></br>
		 * *native declaration : :102*
		 */
		fun keyboardFocusIndicatorColor(): NSColor {
			return CLASS.keyboardFocusIndicatorColor()
		}

		/**
		 * Original signature : `NSColor* windowBackgroundColor()`<br></br> Background fill for window contents<br></br>
		 * *native declaration : :103*
		 */
		fun windowBackgroundColor(): NSColor {
			return CLASS.windowBackgroundColor()
		}

		/**
		 * Original signature : `NSColor* scrollBarColor()`<br></br> Scroll bar slot color<br></br>
		 * *native declaration : :105*
		 */
		fun scrollBarColor(): NSColor {
			return CLASS.scrollBarColor()
		}

		/**
		 * Original signature : `NSColor* knobColor()`<br></br> Knob face color for controls<br></br>
		 * *native declaration : :106*
		 */
		fun knobColor(): NSColor {
			return CLASS.knobColor()
		}

		/**
		 * Original signature : `NSColor* selectedKnobColor()`<br></br> Knob face color for selected controls<br></br>
		 * *native declaration : :107*
		 */
		fun selectedKnobColor(): NSColor {
			return CLASS.selectedKnobColor()
		}

		/**
		 * Original signature : `NSColor* windowFrameColor()`<br></br> Window frames<br></br>
		 * *native declaration : :109*
		 */
		fun windowFrameColor(): NSColor {
			return CLASS.windowFrameColor()
		}

		/**
		 * Original signature : `NSColor* windowFrameTextColor()`<br></br> Text on window frames<br></br>
		 * *native declaration : :110*
		 */
		fun windowFrameTextColor(): NSColor {
			return CLASS.windowFrameTextColor()
		}

		/**
		 * Original signature : `NSColor* selectedMenuItemColor()`<br></br> Highlight color for menus<br></br>
		 * *native declaration : :112*
		 */
		fun selectedMenuItemColor(): NSColor {
			return CLASS.selectedMenuItemColor()
		}

		/**
		 * Original signature : `NSColor* selectedMenuItemTextColor()`<br></br> Highlight color for menu text<br></br>
		 * *native declaration : :113*
		 */
		fun selectedMenuItemTextColor(): NSColor {
			return CLASS.selectedMenuItemTextColor()
		}

		/**
		 * Original signature : `NSColor* highlightColor()`<br></br> Highlight color for UI elements (this is abstract
		 * and defines the color all highlights tend toward)<br></br>
		 * *native declaration : :115*
		 */
		fun highlightColor(): NSColor {
			return CLASS.highlightColor()
		}

		/**
		 * Original signature : `NSColor* shadowColor()`<br></br> Shadow color for UI elements (this is abstract and
		 * defines the color all shadows tend toward)<br></br>
		 * *native declaration : :116*
		 */
		fun shadowColor(): NSColor {
			return CLASS.shadowColor()
		}

		/**
		 * Original signature : `NSColor* headerColor()`<br></br> Background color for header cells in
		 * Table/OutlineView<br></br>
		 * *native declaration : :118*
		 */
		fun headerColor(): NSColor {
			return CLASS.headerColor()
		}

		/**
		 * Original signature : `NSColor* headerTextColor()`<br></br> Text color for header cells in
		 * Table/OutlineView<br></br>
		 * *native declaration : :119*
		 */
		fun headerTextColor(): NSColor {
			return CLASS.headerTextColor()
		}

		/**
		 * Original signature : `NSColor* alternateSelectedControlColor()`<br></br> Similar to selectedControlColor;
		 * for use in lists and tables<br></br>
		 * *native declaration : :122*
		 */
		fun alternateSelectedControlColor(): NSColor {
			return CLASS.alternateSelectedControlColor()
		}

		/**
		 * Original signature : `NSColor* alternateSelectedControlTextColor()`<br></br> Similar to
		 * selectedControlTextColor; see alternateSelectedControlColor<br></br>
		 * *native declaration : :123*
		 */
		fun alternateSelectedControlTextColor(): NSColor {
			return CLASS.alternateSelectedControlTextColor()
		}
	}
}

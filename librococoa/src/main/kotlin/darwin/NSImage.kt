package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.NSRect
import org.rococoa.cocoa.foundation.NSSize

/// <i>native declaration : :41</i>
abstract class NSImage : NSObject(), NSCopying {
	interface _Class : ObjCClass {
		/**
		 * Original signature : `id imageNamed(NSString*)`<br></br> If this finds & creates the image, only name
		 * is saved when archived<br></br>
		 * *native declaration : :73*
		 */
		open fun imageNamed(name: String?): NSImage?

		open fun imageWithSystemSymbolName_accessibilityDescription(symbolName: String?, description: String?): NSImage?

		/**
		 * These return union of all the types registered with NSImageRep.<br></br> Original signature : `NSArray*
		 * imageUnfilteredFileTypes()`<br></br>
		 * *native declaration : :138*
		 */
		open fun imageUnfilteredFileTypes(): NSArray?

		/**
		 * Original signature : `NSArray* imageUnfilteredPasteboardTypes()`<br></br>
		 * *native declaration : :139*
		 */
		open fun imageUnfilteredPasteboardTypes(): NSArray?

		/**
		 * Original signature : `NSArray* imageFileTypes()`<br></br>
		 * *native declaration : :140*
		 */
		open fun imageFileTypes(): NSArray?

		/**
		 * Original signature : `NSArray* imagePasteboardTypes()`<br></br>
		 * *native declaration : :141*
		 */
		open fun imagePasteboardTypes(): NSArray?

		/**
		 * Original signature : `NSArray* imageTypes()`<br></br>
		 * *native declaration : :144*
		 */
		open fun imageTypes(): NSArray?

		/**
		 * Original signature : `NSArray* imageUnfilteredTypes()`<br></br>
		 * *native declaration : :145*
		 */
		open fun imageUnfilteredTypes(): NSArray?

		/**
		 * Original signature : `BOOL canInitWithPasteboard(NSPasteboard*)`<br></br>
		 * *native declaration : :148*
		 */
		open fun canInitWithPasteboard(pasteBoard: NSPasteboard?): Boolean

		open fun alloc(): NSImage
	}

	/**
	 * *native declaration : :75*<br></br>
	 */
	abstract fun initWithSize(aSize: NSSize?): NSImage?

	/**
	 * Original signature : `id initWithData(NSData*)`<br></br> When archived, saves contents<br></br>
	 * *native declaration : :76*
	 */
	abstract fun initWithData(data: NSData?): NSImage?

	/**
	 * Original signature : `id initWithContentsOfFile(NSString*)`<br></br> When archived, saves contents<br></br>
	 * *native declaration : :77*
	 */
	abstract fun initWithContentsOfFile(fileName: String?): NSImage?

	/**
	 * Original signature : `id initWithContentsOfURL(NSURL*)`<br></br> When archived, saves contents<br></br>
	 * *native declaration : :78*
	 */
	abstract fun initWithContentsOfURL(url: NSURL?): NSImage?

	/**
	 * Original signature : `id initByReferencingFile(NSString*)`<br></br> When archived, saves fileName<br></br>
	 * *native declaration : :79*
	 */
	abstract fun initByReferencingFile(fileName: String?): NSImage?

	/**
	 * Original signature : `id initByReferencingURL(NSURL*)`<br></br> When archived, saves url, supports
	 * progressive loading<br></br>
	 * *native declaration : :81*
	 */
	abstract fun initByReferencingURL(url: NSURL?): NSImage?

	/**
	 * Original signature : `id initWithPasteboard(NSPasteboard*)`<br></br>
	 * *native declaration : :86*
	 */
	abstract fun initWithPasteboard(pasteboard: NSPasteboard?): NSImage?

	/**
	 * @return Copy of original image
	 */
	abstract fun copy(): NSImage?

	/**
	 * *native declaration : :88*<br></br>
	 */
	abstract fun setSize(aSize: NSSize?)

	/**
	 * *native declaration : :89*<br></br>
	 */
	abstract fun size(): NSSize?

	/**
	 * Original signature : `BOOL setName(NSString*)`<br></br>
	 * *native declaration : :90*
	 */
	abstract fun setName(string: String?): Boolean

	/**
	 * Original signature : `NSString* name()`<br></br>
	 * *native declaration : :91*
	 */
	abstract fun name(): String?

	/**
	 * Original signature : `void setScalesWhenResized(BOOL)`<br></br>
	 * *native declaration : :92*
	 */
	abstract fun setScalesWhenResized(flag: Boolean)

	/**
	 * Original signature : `BOOL scalesWhenResized()`<br></br>
	 * *native declaration : :93*
	 */
	abstract fun scalesWhenResized(): Boolean

	/**
	 * Original signature : `void setDataRetained(BOOL)`<br></br>
	 * *native declaration : :94*
	 */
	abstract fun setDataRetained(flag: Boolean)

	/**
	 * Original signature : `BOOL isDataRetained()`<br></br>
	 * *native declaration : :95*
	 */
	abstract fun isDataRetained(): Boolean

	/**
	 * Original signature : `void setCachedSeparately(BOOL)`<br></br>
	 * *native declaration : :96*
	 */
	abstract fun setCachedSeparately(flag: Boolean)

	/**
	 * Original signature : `BOOL isCachedSeparately()`<br></br>
	 * *native declaration : :97*
	 */
	abstract fun isCachedSeparately(): Boolean

	/**
	 * Original signature : `void setCacheDepthMatchesImageDepth(BOOL)`<br></br>
	 * *native declaration : :98*
	 */
	abstract fun setCacheDepthMatchesImageDepth(flag: Boolean)

	/**
	 * Original signature : `BOOL cacheDepthMatchesImageDepth()`<br></br>
	 * *native declaration : :99*
	 */
	abstract fun cacheDepthMatchesImageDepth(): Boolean

	/**
	 * Original signature : `public abstract  void setBackgroundColor(NSColor*)`<br></br>
	 * *native declaration : :100*
	 */
	abstract fun setBackgroundColor(aColor: NSColor?)

	/**
	 * Original signature : `NSColor* backgroundColor()`<br></br>
	 * *native declaration : :101*
	 */
	abstract fun backgroundColor(): NSColor?

	/**
	 * Original signature : `public abstract  void setUsesEPSOnResolutionMismatch(BOOL)`<br></br>
	 * *native declaration : :102*
	 */
	abstract fun setUsesEPSOnResolutionMismatch(flag: Boolean)

	/**
	 * Original signature : `BOOL usesEPSOnResolutionMismatch()`<br></br>
	 * *native declaration : :103*
	 */
	abstract fun usesEPSOnResolutionMismatch(): Boolean

	/**
	 * Original signature : `public abstract  void setPrefersColorMatch(BOOL)`<br></br>
	 * *native declaration : :104*
	 */
	abstract fun setPrefersColorMatch(flag: Boolean)

	/**
	 * Original signature : `BOOL prefersColorMatch()`<br></br>
	 * *native declaration : :105*
	 */
	abstract fun prefersColorMatch(): Boolean

	/**
	 * Original signature : `public abstract  void setMatchesOnMultipleResolution(BOOL)`<br></br>
	 * *native declaration : :106*
	 */
	abstract fun setMatchesOnMultipleResolution(flag: Boolean)

	/**
	 * Original signature : `BOOL matchesOnMultipleResolution()`<br></br>
	 * *native declaration : :107*
	 */
	abstract fun matchesOnMultipleResolution(): Boolean

	/**
	 * *native declaration : :115*<br></br>
	 * Conversion Error : /// Original signature : `public abstract  void drawInRect(null, null, null,
	 * CGFloat)`<br></br> - (void)drawInRect:(null)rect fromRect:(null)fromRect operation:(null)op
	 * fraction:(CGFloat)delta; (Argument rect cannot be converted)
	 */
	abstract fun drawInRect_fromRect_operation_fraction(
		rect: NSRect?,
		fromRect: NSRect?,
		operation: Int,
		delta: CGFloat?
	)

	fun drawInRect(rect: NSRect?, fromRect: NSRect?, operation: Int, delta: Double) {
		this.drawInRect_fromRect_operation_fraction(rect, fromRect, operation, CGFloat(delta))
	}

	/**
	 * Original signature : `public abstract  void recache()`<br></br>
	 * *native declaration : :117*
	 */
	abstract fun recache()

	/**
	 * Original signature : `NSData* TIFFRepresentation()`<br></br>
	 * *native declaration : :118*
	 */
	abstract fun TIFFRepresentation(): NSData?

	/**
	 * Original signature : `NSArray* representations()`<br></br>
	 * *native declaration : :121*
	 */
	abstract fun representations(): NSArray?

	/**
	 * Original signature : `public abstract  void addRepresentations(NSArray*)`<br></br>
	 * *native declaration : :122*
	 */
	abstract fun addRepresentations(imageReps: NSArray?)

	/**
	 * Original signature : `public abstract  void addRepresentation(NSImageRep*)`<br></br>
	 * *native declaration : :123*
	 */
	abstract fun addRepresentation(imageRep: com.sun.jna.Pointer?)

	/**
	 * Original signature : `public abstract  void removeRepresentation(NSImageRep*)`<br></br>
	 * *native declaration : :124*
	 */
	abstract fun removeRepresentation(imageRep: com.sun.jna.Pointer?)

	/**
	 * Original signature : `BOOL isValid()`<br></br>
	 * *native declaration : :126*
	 */
	abstract fun isValid(): Boolean

	/**
	 * Original signature : `public abstract  void lockFocus()`<br></br>
	 * *native declaration : :127*
	 */
	abstract fun lockFocus()

	/**
	 * Original signature : `public abstract  void lockFocusOnRepresentation(NSImageRep*)`<br></br>
	 * *native declaration : :128*
	 */
	abstract fun lockFocusOnRepresentation(imageRepresentation: com.sun.jna.Pointer?)

	/**
	 * Original signature : `public abstract  void unlockFocus()`<br></br>
	 * *native declaration : :129*
	 */
	abstract fun unlockFocus()

	/**
	 * Original signature : `NSImageRep* bestRepresentationForDevice(NSDictionary*)`<br></br>
	 * *native declaration : :131*
	 */
	abstract fun bestRepresentationForDevice(deviceDescription: NSDictionary?): com.sun.jna.Pointer?

	/**
	 * Original signature : `public abstract  void setDelegate(id)`<br></br>
	 * *native declaration : :133*
	 */
	abstract fun setDelegate(anObject: org.rococoa.ID?)

	/**
	 * Original signature : `id delegate()`<br></br>
	 * *native declaration : :134*
	 */
	abstract fun delegate(): org.rococoa.ID?

	/**
	 * Original signature : `public abstract  void setFlipped(BOOL)`<br></br>
	 * *native declaration : :150*
	 */
	abstract fun setFlipped(flag: Boolean)

	/**
	 * Original signature : `BOOL isFlipped()`<br></br>
	 * *native declaration : :151*
	 */
	abstract fun isFlipped(): Boolean

	/**
	 * Original signature : `public abstract  void cancelIncrementalLoad()`<br></br>
	 * *native declaration : :154*
	 */
	abstract fun cancelIncrementalLoad()

	/**
	 * Original signature : `public abstract  void setCacheMode(NSImageCacheMode)`<br></br>
	 * *native declaration : :156*
	 */
	abstract fun setCacheMode(mode: Int)

	/**
	 * Original signature : `NSImageCacheMode cacheMode()`<br></br>
	 * *native declaration : :157*
	 */
	abstract fun cacheMode(): Int

	/**
	 * The alignmentRect of an image is metadata that a client may use to help determine layout. The bottom of the rect
	 * gives the baseline of the image. The other edges give similar information in other directions.<br></br> A 20x20 image
	 * of a phone icon with a glow might specify an alignmentRect of {{2,2},{16,16}} that excludes the glow.
	 * NSButtonCell can take advantage of the alignmentRect to place the image in the same visual location as an 16x16
	 * phone icon without the glow. A 5x5 star that should render high when aligned with text might specify a rect of
	 * {{0,-7},{5,12}}.<br></br> The alignmentRect of an image has no effect on methods such as
	 * drawInRect:fromRect:operation:Fraction: or drawAtPoint:fromRect:operation:fraction:. It is the client's
	 * responsibility to take the alignmentRect into account where applicable.<br></br> The default alignmentRect of an image
	 * is {{0,0},imageSize}. The rect is adjusted when setSize: is called.<br></br> Original signature :
	 * `alignmentRect()`<br></br>
	 * *native declaration : :169*
	 */
	abstract fun alignmentRect(): NSObject?

	/**
	 * The 'template' property is metadata that allows clients to be smarter about image processing.  An image should be
	 * marked as a template if it is basic glpyh-like black and white art that is intended to be processed into derived
	 * images for use on screen.<br></br> NSButtonCell applies effects to images based on the state of the button.  For
	 * example, images are shaded darker when the button is pressed.  If a template image is set on a cell, the cell can
	 * apply more sophisticated effects.  For example, it may be processed into an image that looks engraved when drawn
	 * into a cell whose interiorBackgroundStyle is NSBackgroundStyleRaised, like on a textured button.<br></br> Original
	 * signature : `BOOL isTemplate()`<br></br>
	 * *native declaration : :176*
	 */
	abstract fun isTemplate(): Boolean

	/**
	 * Original signature : `public abstract  void setTemplate(BOOL)`<br></br>
	 * *native declaration : :177*
	 */
	abstract fun setTemplate(isTemplate: Boolean)

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSImage", _Class::class.java)

		/// <i>native declaration : :13</i>
		const val NSImageLoadStatusCompleted: Int = 0

		/// <i>native declaration : :14</i>
		const val NSImageLoadStatusCancelled: Int = 1

		/// <i>native declaration : :15</i>
		const val NSImageLoadStatusInvalidData: Int = 2

		/// <i>native declaration : :16</i>
		const val NSImageLoadStatusUnexpectedEOF: Int = 3

		/// <i>native declaration : :17</i>
		const val NSImageLoadStatusReadError: Int = 4

		/**
		 * unspecified. use image rep's default<br></br>
		 * *native declaration : :22*
		 */
		const val NSImageCacheDefault: Int = 0

		/**
		 * always generate a cache when drawing<br></br>
		 * *native declaration : :23*
		 */
		const val NSImageCacheAlways: Int = 1

		/**
		 * cache if cache size is smaller than original data<br></br>
		 * *native declaration : :24*
		 */
		const val NSImageCacheBySize: Int = 2

		/**
		 * never cache, always draw direct<br></br>
		 * *native declaration : :25*
		 */
		const val NSImageCacheNever: Int = 3

		fun imageNamed(name: String?): NSImage? {
			return CLASS.imageNamed(name)
		}

		/**
		 * Creates a symbol image with the system symbol name and accessibility description you specify.
		 *
		 *
		 * macOS 11.0+
		 *
		 * @param symbolName The name of the system symbol image.
		 * @return A symbol image based on the name you specify; otherwise nil if the method couldn’t find a suitable image.
		 */
		fun imageWithSymbol(symbolName: String?): NSImage? {
			return CLASS.imageWithSystemSymbolName_accessibilityDescription(symbolName, null)
		}

		fun imageWithData(data: NSData?): NSImage? {
			return CLASS.alloc().initWithData(data)
		}

		fun imageWithSize(size: NSSize?): NSImage? {
			return CLASS.alloc().initWithSize(size)
		}

		fun imageWithContentsOfFile(filename: String?): NSImage? {
			return CLASS.alloc().initWithContentsOfFile(filename)
		}
	}
}

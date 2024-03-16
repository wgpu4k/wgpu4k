package darwin 

import org.rococoa.ObjCClass
import org.rococoa.Rococoa
import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.NSInteger

/// <i>native declaration : :26</i>
abstract class NSSegmentedControl : NSControl() {
	interface _Class : ObjCClass {
		open fun alloc(): NSSegmentedControl
	}

	abstract override fun init(): NSSegmentedControl

	/**
	 * Original signature : `void setSegmentCount(NSInteger)`<br></br>
	 * *native declaration : :34*
	 */
	abstract fun setSegmentCount(count: Int)

	/**
	 * Original signature : `NSInteger segmentCount()`<br></br>
	 * *native declaration : :35*
	 */
	abstract fun segmentCount(): Int

	/**
	 * Original signature : `public abstract void setSelectedSegment(NSInteger)`<br></br>
	 * *native declaration : :37*
	 */
	abstract fun setSelectedSegment(selectedSegment: Int)

	/**
	 * Original signature : `NSInteger selectedSegment()`<br></br>
	 * *native declaration : :38*
	 */
	abstract fun selectedSegment(): Int

	/**
	 * Original signature : `BOOL selectSegmentWithTag(NSInteger)`<br></br>
	 * *native declaration : :41*
	 */
	abstract fun selectSegmentWithTag(tag: NSInteger?): Boolean

	/**
	 * Original signature : `public abstract void setWidth(CGFloat, NSInteger)`<br></br>
	 * *native declaration : :44*
	 */
	abstract fun setWidth_forSegment(width: CGFloat?, segment: Int)

	/**
	 * Original signature : `CGFloat widthForSegment(NSInteger)`<br></br>
	 * *native declaration : :45*
	 */
	abstract fun widthForSegment(segment: Int): CGFloat?

	/**
	 * Original signature : `public abstract void setImage(NSImage*, NSInteger)`<br></br>
	 * *native declaration : :47*
	 */
	abstract fun setImage_forSegment(image: NSImage?, segment: Int)

	/**
	 * Original signature : `NSImage* imageForSegment(NSInteger)`<br></br>
	 * *native declaration : :48*
	 */
	abstract fun imageForSegment(segment: Int): NSImage?
	/**
	 * *native declaration : :52*<br></br>
	 * Conversion Error : /// Original signature : `public abstract void setImageScaling(null, NSInteger)`<br></br>
	 * - (public abstract void)setImageScaling:(null)scaling forSegment:(NSInteger)segment; (Argument scaling cannot be converted)
	 */
	/**
	 * Original signature : `imageScalingForSegment(NSInteger)`<br></br>
	 * *native declaration : :53*
	 */
	abstract fun imageScalingForSegment(segment: Int): com.sun.jna.Pointer?

	/**
	 * Original signature : `public abstract void setLabel(NSString*, NSInteger)`<br></br>
	 * *native declaration : :57*
	 */
	abstract fun setLabel_forSegment(label: String?, segment: Int)

	/**
	 * Original signature : `NSString* labelForSegment(NSInteger)`<br></br>
	 * *native declaration : :58*
	 */
	abstract fun labelForSegment(segment: Int): String?

	/**
	 * Original signature : `public abstract void setMenu(NSMenu*, NSInteger)`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun setMenu_forSegment(menu: NSMenu?, segment: Int)

	/**
	 * Original signature : `NSMenu* menuForSegment(NSInteger)`<br></br>
	 * *native declaration : :61*
	 */
	abstract fun menuForSegment(segment: Int): NSMenu?

	/**
	 * Original signature : `public abstract void setSelected(BOOL, NSInteger)`<br></br>
	 * *native declaration : :63*
	 */
	abstract fun setSelected_forSegment(selected: Boolean, segment: Int)

	/**
	 * Original signature : `BOOL isSelectedForSegment(NSInteger)`<br></br>
	 * *native declaration : :64*
	 */
	abstract fun isSelectedForSegment(segment: Int): Boolean

	/**
	 * Original signature : `public abstract void setEnabled(BOOL, NSInteger)`<br></br>
	 * *native declaration : :66*
	 */
	abstract fun setEnabled_forSegment(enabled: Boolean, segment: Int)

	/**
	 * Original signature : `BOOL isEnabledForSegment(NSInteger)`<br></br>
	 * *native declaration : :67*
	 */
	abstract fun isEnabledForSegment(segment: Int): Boolean

	/**
	 * Original signature : `public abstract void setSegmentStyle(NSSegmentStyle)`<br></br>
	 * *native declaration : :70*
	 */
	abstract fun setSegmentStyle(segmentStyle: NSInteger?)

	/**
	 * Original signature : `NSSegmentStyle segmentStyle()`<br></br>
	 * *native declaration : :71*
	 */
	abstract fun segmentStyle(): NSInteger?

	companion object {
		private val CLASS: _Class = Rococoa.createClass("NSSegmentedControl", _Class::class.java)

		val NSSegmentStyleAutomatic: NSInteger? = NSInteger(0)
		val NSSegmentStyleRounded: NSInteger? = NSInteger(1)
		val NSSegmentStyleTexturedRounded: NSInteger? = NSInteger(2)
		val NSSegmentStyleRoundRect: NSInteger? = NSInteger(3)
		val NSSegmentStyleTexturedSquare: NSInteger? = NSInteger(4)
		val NSSegmentStyleCapsule: NSInteger? = NSInteger(5)
		val NSSegmentStyleSmallSquare: NSInteger? = NSInteger(6)
		val NSSegmentStyleSeparated: NSInteger? = NSInteger(8)

		fun segmentedControl(): NSSegmentedControl? {
			return CLASS.alloc().init()
		}
	}
}

package darwin 

import org.rococoa.cocoa.CGFloat

/// <i>native declaration : :20</i>
abstract class NSSegmentedCell : NSActionCell() {
	/**
	 * Number of segments<br></br>
	 * Original signature : `void setSegmentCount(NSInteger)`<br></br>
	 * *native declaration : :46*
	 */
	abstract fun setSegmentCount(count: Int)

	/**
	 * Original signature : `NSInteger segmentCount()`<br></br>
	 * *native declaration : :47*
	 */
	abstract fun segmentCount(): Int

	/**
	 * Which button is active. May turn off other segments depending on mode.<br></br>
	 * Original signature : `void setSelectedSegment(NSInteger)`<br></br>
	 * *native declaration : :51*
	 */
	abstract fun setSelectedSegment(selectedSegment: Int)

	/**
	 * Original signature : `NSInteger selectedSegment()`<br></br>
	 * *native declaration : :52*
	 */
	abstract fun selectedSegment(): Int

	/**
	 * Original signature : `BOOL selectSegmentWithTag(NSInteger)`<br></br>
	 * *native declaration : :55*
	 */
	abstract fun selectSegmentWithTag(tag: Int): Boolean

	/**
	 * For keyboard UI. Wraps.<br></br>
	 * Original signature : `void makeNextSegmentKey()`<br></br>
	 * *native declaration : :60*
	 */
	abstract fun makeNextSegmentKey()

	/**
	 * Original signature : `void makePreviousSegmentKey()`<br></br>
	 * *native declaration : :61*
	 */
	abstract fun makePreviousSegmentKey()

	/**
	 * Original signature : `void setTrackingMode(NSSegmentSwitchTracking)`<br></br>
	 * *native declaration : :63*
	 */
	abstract fun setTrackingMode(trackingMode: Int)

	/**
	 * Original signature : `NSSegmentSwitchTracking trackingMode()`<br></br>
	 * *native declaration : :64*
	 */
	abstract fun trackingMode(): Int

	/**
	 * Width of 0 means autosize to fit<br></br>
	 * Original signature : `void setWidth(CGFloat, NSInteger)`<br></br>
	 * *native declaration : :71*
	 */
	abstract fun setWidth_forSegment(width: CGFloat?, segment: Int)

	/**
	 * Original signature : `CGFloat widthForSegment(NSInteger)`<br></br>
	 * *native declaration : :72*
	 */
	abstract fun widthForSegment(segment: Int): CGFloat?

	/**
	 * Original signature : `void setImage(NSImage*, NSInteger)`<br></br>
	 * *native declaration : :74*
	 */
	abstract fun setImage_forSegment(image: NSImage?, segment: Int)

	/**
	 * Original signature : `NSImage* imageForSegment(NSInteger)`<br></br>
	 * *native declaration : :75*
	 */
	abstract fun imageForSegment(segment: Int): NSImage?
	/**
	 * *native declaration : :79*<br></br>
	 * Conversion Error : /// Original signature : `void setImageScaling(null, NSInteger)`<br></br>
	 * - (void)setImageScaling:(null)scaling forSegment:(NSInteger)segment; (Argument scaling cannot be converted)
	 */
	/**
	 * Original signature : `imageScalingForSegment(NSInteger)`<br></br>
	 * *native declaration : :80*
	 */
	abstract fun imageScalingForSegment(segment: Int): com.sun.jna.Pointer?

	/**
	 * Original signature : `void setLabel(NSString*, NSInteger)`<br></br>
	 * *native declaration : :84*
	 */
	abstract fun setLabel_forSegment(label: String?, segment: Int)

	/**
	 * Original signature : `NSString* labelForSegment(NSInteger)`<br></br>
	 * *native declaration : :85*
	 */
	abstract fun labelForSegment(segment: Int): String?

	/**
	 * Original signature : `void setSelected(BOOL, NSInteger)`<br></br>
	 * *native declaration : :87*
	 */
	abstract fun setSelected_forSegment(selected: Boolean, segment: Int)

	/**
	 * Original signature : `BOOL isSelectedForSegment(NSInteger)`<br></br>
	 * *native declaration : :88*
	 */
	abstract fun isSelectedForSegment(segment: Int): Boolean

	/**
	 * Original signature : `void setEnabled(BOOL, NSInteger)`<br></br>
	 * *native declaration : :90*
	 */
	abstract fun setEnabled_forSegment(enabled: Boolean, segment: Int)

	/**
	 * Original signature : `BOOL isEnabledForSegment(NSInteger)`<br></br>
	 * *native declaration : :91*
	 */
	abstract fun isEnabledForSegment(segment: Int): Boolean

	/**
	 * Original signature : `void setMenu(NSMenu*, NSInteger)`<br></br>
	 * *native declaration : :93*
	 */
	abstract fun setMenu_forSegment(menu: NSMenu?, segment: Int)

	/**
	 * Original signature : `NSMenu* menuForSegment(NSInteger)`<br></br>
	 * *native declaration : :94*
	 */
	abstract fun menuForSegment(segment: Int): NSMenu?

	/**
	 * Original signature : `void setToolTip(NSString*, NSInteger)`<br></br>
	 * *native declaration : :96*
	 */
	abstract fun setToolTip_forSegment(toolTip: String?, segment: Int)

	/**
	 * Original signature : `NSString* toolTipForSegment(NSInteger)`<br></br>
	 * *native declaration : :97*
	 */
	abstract fun toolTipForSegment(segment: Int): String?

	/**
	 * Original signature : `void setTag(NSInteger, NSInteger)`<br></br>
	 * *native declaration : :99*
	 */
	abstract fun setTag_forSegment(tag: Int, segment: Int)

	/**
	 * Original signature : `NSInteger tagForSegment(NSInteger)`<br></br>
	 * *native declaration : :100*
	 */
	abstract fun tagForSegment(segment: Int): Int
	/**
	 * *native declaration : :104*<br></br>
	 * Conversion Error : / **<br></br>
	 * * see NSSegmentedControl.h for segment style names and values<br></br>
	 * * Original signature : `void setSegmentStyle(null)`<br></br>
	 * * /<br></br>
	 * - (void)setSegmentStyle:(null)segmentStyle; (Argument segmentStyle cannot be converted)
	 */
	/**
	 * Original signature : `segmentStyle()`<br></br>
	 * *native declaration : :105*
	 */
	abstract fun segmentStyle(): Int
	/**
	 * *native declaration : :110*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Describes the surface drawn onto in -[NSCell drawSegment:inFrame:withView:]. That method draws a segment interior, not the segment bezel.  This is both an override point and a useful method to call. A segmented cell that draws a custom bezel would override this to describe that surface. A cell that has custom segment drawing might query this method to help pick an image that looks good on the cell. Calling this method gives you some independence from changes in framework art style.<br></br>
	 * Original signature : `interiorBackgroundStyleForSegment(NSInteger)`<br></br>
	 * *from NSSegmentBackgroundStyle native declaration : :119*
	 */
	abstract fun interiorBackgroundStyleForSegment(segment: Int): Int

	companion object {
		/**
		 * only one button can be selected<br></br>
		 * *native declaration : :12*
		 */
		const val NSSegmentSwitchTrackingSelectOne: Int = 0

		/**
		 * any button can be selected<br></br>
		 * *native declaration : :13*
		 */
		const val NSSegmentSwitchTrackingSelectAny: Int = 1

		/**
		 * only selected while tracking<br></br>
		 * *native declaration : :14*
		 */
		const val NSSegmentSwitchTrackingMomentary: Int = 2
	}
}

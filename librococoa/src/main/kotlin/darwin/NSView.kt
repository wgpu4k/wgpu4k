package darwin

import com.sun.jna.Pointer
import com.sun.jna.ptr.PointerByReference
import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.Rococoa
import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.NSPoint
import org.rococoa.cocoa.foundation.NSRect
import org.rococoa.cocoa.foundation.NSSize
import org.rococoa.cocoa.foundation.NSUInteger
import java.nio.FloatBuffer
import java.nio.IntBuffer

abstract class NSView : NSResponder() {
	enum class NSFocusRingType {
		NSFocusRingTypeDefault,
		NSFocusRingTypeNone,
		NSFocusRingTypeExterior
	}

	interface _Class : ObjCClass {
		fun alloc(): NSView

		/**
		 * Original signature : `NSView* focusView()`<br></br>
		 * *native declaration : :213*
		 */
		fun focusView(): NSView?

		/**
		 * Original signature : `NSMenu* defaultMenu()`<br></br>
		 * *native declaration : :311*
		 */
		fun defaultMenu(): NSMenu?

		/**
		 * Original signature : `defaultFocusRingType()`<br></br>
		 * *from NSKeyboardUI native declaration : :357*
		 */
		fun defaultFocusRingType(): NSObject?
	}

	abstract fun init(): NSView

	abstract fun initWithFrame(frameRect: NSRect?): NSView

	/**
	 * *native declaration : :115*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `NSWindow* window()`<br></br>
	 * *native declaration : :117*
	 */
	abstract fun window(): NSWindow?

	/**
	 * Original signature : `NSView* superview()`<br></br>
	 * *native declaration : :118*
	 */
	abstract fun superview(): NSView?

	/**
	 * Original signature : `NSArray* subviews()`<br></br>
	 * *native declaration : :119*
	 */
	abstract fun subviews(): NSArray?

	/**
	 * Original signature : `BOOL isDescendantOf(NSView*)`<br></br>
	 * *native declaration : :120*
	 */
	abstract fun isDescendantOf(aView: NSView?): Boolean

	/**
	 * Original signature : `NSView* ancestorSharedWithView(NSView*)`<br></br>
	 * *native declaration : :121*
	 */
	abstract fun ancestorSharedWithView(aView: NSView?): NSView?

	/**
	 * Original signature : `NSView* opaqueAncestor()`<br></br>
	 * *native declaration : :122*
	 */
	abstract fun opaqueAncestor(): NSView?

	/**
	 * Original signature : `BOOL isHidden()`<br></br>
	 * *native declaration : :125*
	 */
	/**
	 * Original signature : `void setHidden(BOOL)`<br></br>
	 * *native declaration : :124*
	 */
	abstract var isHidden: Boolean

	/**
	 * Original signature : `BOOL isHiddenOrHasHiddenAncestor()`<br></br>
	 * *native declaration : :126*
	 */
	abstract val isHiddenOrHasHiddenAncestor: Boolean

	/**
	 * Original signature : `void getRectsBeingDrawn(const NSRect**, NSInteger*)`<br></br>
	 * *native declaration : :128*
	 */
	abstract fun getRectsBeingDrawn_count(rects: PointerByReference?, count: IntBuffer?)
	/**
	 * *native declaration : :129*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `BOOL wantsDefaultClipping()`<br></br>
	 * *native declaration : :130*
	 */
	abstract fun wantsDefaultClipping(): Boolean

	/**
	 * Original signature : `void viewDidHide()`<br></br>
	 * *native declaration : :133*
	 */
	abstract fun viewDidHide()

	/**
	 * Original signature : `void viewDidUnhide()`<br></br>
	 * *native declaration : :134*
	 */
	abstract fun viewDidUnhide()

	/**
	 * Original signature : `void setSubviews(NSArray*)`<br></br>
	 * *native declaration : :136*
	 */
	abstract fun setSubviews(newSubviews: NSArray?)

	/**
	 * Original signature : `void addSubview(NSView*)`<br></br>
	 * *native declaration : :138*
	 */
	abstract fun addSubview(aView: NSView?)

	/**
	 * *native declaration : :139*<br></br>
	 * Conversion Error : /// Original signature : `void addSubview(NSView*, null, NSView*)`<br></br>
	 * - (void)addSubview:(NSView*)aView positioned:(null)place relativeTo:(NSView*)otherView; (Argument place cannot be converted)
	 */
	abstract fun addSubview_positioned_relativeTo(aView: NSView?, place: Int, otherView: NSView?)

	/**
	 * Original signature : `void viewWillMoveToWindow(NSWindow*)`<br></br>
	 * *native declaration : :141*
	 */
	abstract fun viewWillMoveToWindow(newWindow: NSWindow?)

	/**
	 * Original signature : `void viewDidMoveToWindow()`<br></br>
	 * *native declaration : :142*
	 */
	abstract fun viewDidMoveToWindow()

	/**
	 * Original signature : `void viewWillMoveToSuperview(NSView*)`<br></br>
	 * *native declaration : :143*
	 */
	abstract fun viewWillMoveToSuperview(newSuperview: NSView?)

	/**
	 * Original signature : `void viewDidMoveToSuperview()`<br></br>
	 * *native declaration : :144*
	 */
	abstract fun viewDidMoveToSuperview()

	/**
	 * Original signature : `void didAddSubview(NSView*)`<br></br>
	 * *native declaration : :145*
	 */
	abstract fun didAddSubview(subview: NSView?)

	/**
	 * Original signature : `void willRemoveSubview(NSView*)`<br></br>
	 * *native declaration : :146*
	 */
	abstract fun willRemoveSubview(subview: NSView?)

	/**
	 * Original signature : `void removeFromSuperview()`<br></br>
	 * *native declaration : :147*
	 */
	abstract fun removeFromSuperview()

	/**
	 * Original signature : `void replaceSubview(NSView*, NSView*)`<br></br>
	 * *native declaration : :148*
	 */
	abstract fun replaceSubview_with(oldView: NSView?, newView: NSView?)

	/**
	 * Original signature : `void removeFromSuperviewWithoutNeedingDisplay()`<br></br>
	 * *native declaration : :149*
	 */
	abstract fun removeFromSuperviewWithoutNeedingDisplay()

	/**
	 * Original signature : `void setPostsFrameChangedNotifications(BOOL)`<br></br>
	 * *native declaration : :151*
	 */
	abstract fun setPostsFrameChangedNotifications(flag: Boolean)

	/**
	 * Original signature : `BOOL postsFrameChangedNotifications()`<br></br>
	 * *native declaration : :152*
	 */
	abstract fun postsFrameChangedNotifications(): Boolean
	/**
	 * *native declaration : :153*<br></br>
	 * Conversion Error : /// Original signature : `void resizeSubviewsWithOldSize(null)`<br></br>
	 * - (void)resizeSubviewsWithOldSize:(null)oldSize; (Argument oldSize cannot be converted)
	 */
	/**
	 * *native declaration : :154*<br></br>
	 * Conversion Error : /// Original signature : `void resizeWithOldSuperviewSize(null)`<br></br>
	 * - (void)resizeWithOldSuperviewSize:(null)oldSize; (Argument oldSize cannot be converted)
	 */
	/**
	 * Original signature : `void setAutoresizesSubviews(BOOL)`<br></br>
	 * *native declaration : :155*
	 */
	abstract fun setAutoresizesSubviews(flag: Boolean)

	/**
	 * Original signature : `BOOL autoresizesSubviews()`<br></br>
	 * *native declaration : :156*
	 */
	abstract fun autoresizesSubviews(): Boolean

	/**
	 * Original signature : `void setAutoresizingMask(NSUInteger)`<br></br>
	 * *native declaration : :157*
	 */
	abstract fun setAutoresizingMask(mask: NSUInteger?)

	/**
	 * Original signature : `NSUInteger autoresizingMask()`<br></br>
	 * *native declaration : :158*
	 */
	abstract fun autoresizingMask(): NSUInteger?

	/**
	 * *native declaration : :160*<br></br>
	 * Conversion Error : /// Original signature : `void setFrameOrigin(null)`<br></br>
	 * - (void)setFrameOrigin:(null)newOrigin; (Argument newOrigin cannot be converted)
	 */
	abstract fun setFrameOrigin(origin: NSPoint?)

	/**
	 * *native declaration : :161*<br></br>
	 * Conversion Error : /// Original signature : `void setFrameSize(null)`<br></br>
	 * - (void)setFrameSize:(null)newSize; (Argument newSize cannot be converted)
	 */
	abstract fun setFrameSize(size: NSSize?)

	/**
	 * *native declaration : :162*<br></br>
	 * Conversion Error : NSRect
	 */
	abstract fun setFrame(frame: NSRect?)

	/**
	 * *native declaration : :163*<br></br>
	 * Conversion Error : NSRect
	 */
	abstract fun frame(): NSRect?

	/**
	 * Original signature : `void setFrameRotation(CGFloat)`<br></br>
	 * *native declaration : :164*
	 */
	abstract fun setFrameRotation(angle: CGFloat?)

	/**
	 * Original signature : `CGFloat frameRotation()`<br></br>
	 * *native declaration : :165*
	 */
	abstract fun frameRotation(): CGFloat?

	/**
	 * Original signature : `void setFrameCenterRotation(CGFloat)`<br></br>
	 * *native declaration : :167*
	 */
	abstract fun setFrameCenterRotation(angle: CGFloat?)

	/**
	 * Original signature : `CGFloat frameCenterRotation()`<br></br>
	 * *native declaration : :168*
	 */
	abstract fun frameCenterRotation(): CGFloat?
	/**
	 * *native declaration : :171*<br></br>
	 * Conversion Error : /// Original signature : `void setBoundsOrigin(null)`<br></br>
	 * - (void)setBoundsOrigin:(null)newOrigin; (Argument newOrigin cannot be converted)
	 */
	/**
	 * *native declaration : :172*<br></br>
	 * Conversion Error : /// Original signature : `void setBoundsSize(null)`<br></br>
	 * - (void)setBoundsSize:(null)newSize; (Argument newSize cannot be converted)
	 */
	/**
	 * Original signature : `void setBoundsRotation(CGFloat)`<br></br>
	 * *native declaration : :173*
	 */
	abstract fun setBoundsRotation(angle: CGFloat?)

	/**
	 * Original signature : `CGFloat boundsRotation()`<br></br>
	 * *native declaration : :174*
	 */
	abstract fun boundsRotation(): CGFloat?
	/**
	 * *native declaration : :175*<br></br>
	 * Conversion Error : /// Original signature : `void translateOriginToPoint(null)`<br></br>
	 * - (void)translateOriginToPoint:(null)translation; (Argument translation cannot be converted)
	 */
	/**
	 * *native declaration : :176*<br></br>
	 * Conversion Error : /// Original signature : `void scaleUnitSquareToSize(null)`<br></br>
	 * - (void)scaleUnitSquareToSize:(null)newUnitSize; (Argument newUnitSize cannot be converted)
	 */
	/**
	 * Original signature : `void rotateByAngle(CGFloat)`<br></br>
	 * *native declaration : :177*
	 */
	abstract fun rotateByAngle(angle: CGFloat?)
	/**
	 * *native declaration : :178*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :179*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `BOOL isFlipped()`<br></br>
	 * *native declaration : :181*
	 */
	abstract val isFlipped: Boolean

	/**
	 * Original signature : `BOOL isRotatedFromBase()`<br></br>
	 * *native declaration : :182*
	 */
	abstract val isRotatedFromBase: Boolean

	/**
	 * Original signature : `BOOL isRotatedOrScaledFromBase()`<br></br>
	 * *native declaration : :183*
	 */
	abstract val isRotatedOrScaledFromBase: Boolean

	/**
	 * Original signature : `BOOL isOpaque()`<br></br>
	 * *native declaration : :184*
	 */
	abstract val isOpaque: Boolean

	/**
	 * *native declaration : :186*<br></br>
	 * Conversion Error : /// Original signature : `convertPoint(null, NSView*)`<br></br>
	 * - (null)convertPoint:(null)aPoint fromView:(NSView*)aView; (Argument aPoint cannot be converted)
	 */
	abstract fun convertPoint_fromView(aPoint: NSPoint?, aView: NSView?): NSPoint?

	/**
	 * *native declaration : :187*<br></br>
	 * Conversion Error : /// Original signature : `convertPoint(null, NSView*)`<br></br>
	 * - (null)convertPoint:(null)aPoint toView:(NSView*)aView; (Argument aPoint cannot be converted)
	 */
	abstract fun convertPoint_toView(aPoint: NSPoint?, aView: NSView?): NSPoint?
	/**
	 * *native declaration : :188*<br></br>
	 * Conversion Error : /// Original signature : `convertSize(null, NSView*)`<br></br>
	 * - (null)convertSize:(null)aSize fromView:(NSView*)aView; (Argument aSize cannot be converted)
	 */
	/**
	 * *native declaration : :189*<br></br>
	 * Conversion Error : /// Original signature : `convertSize(null, NSView*)`<br></br>
	 * - (null)convertSize:(null)aSize toView:(NSView*)aView; (Argument aSize cannot be converted)
	 */
	/**
	 * *native declaration : :190*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :191*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :192*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :195*<br></br>
	 * Conversion Error : /// Original signature : `convertPointToBase(null)`<br></br>
	 * - (null)convertPointToBase:(null)aPoint; (Argument aPoint cannot be converted)
	 */
	abstract fun convertPointToBase(aPoint: NSPoint?): NSPoint?

	/**
	 * *native declaration : :196*<br></br>
	 * Conversion Error : /// Original signature : `convertPointFromBase(null)`<br></br>
	 * - (null)convertPointFromBase:(null)aPoint; (Argument aPoint cannot be converted)
	 */
	abstract fun convertPointFromBase(aPoint: NSPoint?): NSPoint?

	/**
	 * *native declaration : :197*<br></br>
	 * Conversion Error : /// Original signature : `convertSizeToBase(null)`<br></br>
	 * - (null)convertSizeToBase:(null)aSize; (Argument aSize cannot be converted)
	 */
	abstract fun convertSizeToBase(aSize: NSSize?): NSSize?

	/**
	 * *native declaration : :198*<br></br>
	 * Conversion Error : /// Original signature : `convertSizeFromBase(null)`<br></br>
	 * - (null)convertSizeFromBase:(null)aSize; (Argument aSize cannot be converted)
	 */
	abstract fun convertSizeFromBase(aSize: NSSize?): NSSize?
	/**
	 * *native declaration : :199*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :200*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `BOOL canDraw()`<br></br>
	 * *native declaration : :203*
	 */
	abstract fun canDraw(): Boolean

	/**
	 * Original signature : `void setNeedsDisplay(BOOL)`<br></br>
	 * *native declaration : :204*
	 */
	abstract fun setNeedsDisplay(flag: Boolean)
	/**
	 * *native declaration : :205*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `BOOL needsDisplay()`<br></br>
	 * *native declaration : :206*
	 */
	abstract fun needsDisplay(): Boolean

	/**
	 * Original signature : `void lockFocus()`<br></br>
	 * *native declaration : :207*
	 */
	abstract fun lockFocus()

	/**
	 * Original signature : `void unlockFocus()`<br></br>
	 * *native declaration : :208*
	 */
	abstract fun unlockFocus()

	/**
	 * Original signature : `BOOL lockFocusIfCanDraw()`<br></br>
	 * *native declaration : :209*
	 */
	abstract fun lockFocusIfCanDraw(): Boolean

	/**
	 * Original signature : `BOOL lockFocusIfCanDrawInContext(NSGraphicsContext*)`<br></br>
	 * *native declaration : :211*
	 */
	abstract fun lockFocusIfCanDrawInContext(context: Pointer?): Boolean
	/**
	 * *native declaration : :214*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `void display()`<br></br>
	 * *native declaration : :216*
	 */
	abstract fun display()

	/**
	 * Original signature : `void displayIfNeeded()`<br></br>
	 * *native declaration : :217*
	 */
	abstract fun displayIfNeeded()

	/**
	 * Original signature : `void displayIfNeededIgnoringOpacity()`<br></br>
	 * *native declaration : :218*
	 */
	abstract fun displayIfNeededIgnoringOpacity()
	/**
	 * *native declaration : :219*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :220*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :221*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :222*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :223*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :225*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :227*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :228*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `void viewWillDraw()`<br></br>
	 * *native declaration : :231*
	 */
	abstract fun viewWillDraw()

	/**
	 * Original signature : `NSInteger gState()`<br></br>
	 * *native declaration : :234*
	 */
	abstract fun gState(): Int

	/**
	 * Original signature : `void allocateGState()`<br></br>
	 * *native declaration : :235*
	 */
	abstract fun allocateGState()

	/**
	 * Original signature : `void releaseGState()`<br></br>
	 * *native declaration : :236*
	 */
	abstract fun releaseGState()

	/**
	 * Original signature : `void setUpGState()`<br></br>
	 * *native declaration : :237*
	 */
	abstract fun setUpGState()

	/**
	 * Original signature : `void renewGState()`<br></br>
	 * *native declaration : :238*
	 */
	abstract fun renewGState()
	/**
	 * *native declaration : :240*<br></br>
	 * Conversion Error : /// Original signature : `void scrollPoint(null)`<br></br>
	 * - (void)scrollPoint:(null)aPoint; (Argument aPoint cannot be converted)
	 */
	/**
	 * *native declaration : :241*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `BOOL autoscroll(NSEvent*)`<br></br>
	 * *native declaration : :242*
	 */
	abstract fun autoscroll(event: NSEvent?): Boolean
	/**
	 * *native declaration : :243*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :244*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :246*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :249*<br></br>
	 */
	abstract fun hitTest(point: NSPoint?): NSView?

	/**
	 * *native declaration : :250*<br></br>
	 * Conversion Error : /// Original signature : `BOOL mouse(null, NSRect)`<br></br>
	 * - (BOOL)mouse:(null)aPoint inRect:(NSRect)aRect; (Argument aPoint cannot be converted)
	 */
	/**
	 * Original signature : `id viewWithTag(NSInteger)`<br></br>
	 * *native declaration : :251*
	 */
	abstract fun viewWithTag(aTag: Int): NSView?

	/**
	 * Original signature : `NSInteger tag()`<br></br>
	 * *native declaration : :252*
	 */
	abstract fun tag(): Int

	/**
	 * Original signature : `BOOL acceptsFirstMouse(NSEvent*)`<br></br>
	 * *native declaration : :254*
	 */
	abstract fun acceptsFirstMouse(event: NSEvent?): Boolean

	/**
	 * Original signature : `BOOL shouldDelayWindowOrderingForEvent(NSEvent*)`<br></br>
	 * *native declaration : :255*
	 */
	abstract fun shouldDelayWindowOrderingForEvent(event: NSEvent?): Boolean

	/**
	 * Original signature : `BOOL needsPanelToBecomeKey()`<br></br>
	 * *native declaration : :256*
	 */
	abstract fun needsPanelToBecomeKey(): Boolean

	/**
	 * Original signature : `BOOL mouseDownCanMoveWindow()`<br></br>
	 * *native declaration : :258*
	 */
	abstract fun mouseDownCanMoveWindow(): Boolean
	/**
	 * *native declaration : :261*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *native declaration : :262*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `void discardCursorRects()`<br></br>
	 * *native declaration : :263*
	 */
	abstract fun discardCursorRects()

	/**
	 * Original signature : `void resetCursorRects()`<br></br>
	 * *native declaration : :264*
	 */
	abstract fun resetCursorRects()
	/**
	 * *native declaration : :266*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `void removeTrackingRect(NSTrackingRectTag)`<br></br>
	 * *native declaration : :267*
	 */
	abstract fun removeTrackingRect(tag: Int)

	/**
	 * Original signature : `void setWantsLayer(BOOL)`<br></br>
	 * *native declaration : :270*
	 */
	abstract fun setWantsLayer(flag: Boolean)

	/**
	 * Original signature : `BOOL wantsLayer()`<br></br>
	 * *native declaration : :271*
	 */
	abstract fun wantsLayer(): Boolean

	/**
	 * Original signature : `void setLayer(CALayer*)`<br></br>
	 * *native declaration : :273*
	 */
	abstract fun setLayer(newLayer: Pointer?)

	/**
	 * Original signature : `CALayer* layer()`<br></br>
	 * *native declaration : :274*
	 */
	abstract fun layer(): CALayer?

	abstract fun setLayerContentsRedrawPolicy(policy: Int)

	abstract fun layerContentsRedrawPolicy(): Int

	/**
	 * Original signature : `void setAlphaValue(CGFloat)`<br></br>
	 * *native declaration : :276*
	 */
	abstract fun setAlphaValue(viewAlpha: CGFloat?)

	/**
	 * Original signature : `CGFloat alphaValue()`<br></br>
	 * *native declaration : :277*
	 */
	abstract fun alphaValue(): CGFloat?

	/**
	 * Original signature : `void setBackgroundFilters(NSArray*)`<br></br>
	 * *native declaration : :279*
	 */
	abstract fun setBackgroundFilters(filters: NSArray?)

	/**
	 * Original signature : `NSArray* backgroundFilters()`<br></br>
	 * *native declaration : :280*
	 */
	abstract fun backgroundFilters(): NSArray?

	/**
	 * Original signature : `void setCompositingFilter(CIFilter*)`<br></br>
	 * *native declaration : :282*
	 */
	abstract fun setCompositingFilter(filter: Pointer?)

	/**
	 * Original signature : `CIFilter* compositingFilter()`<br></br>
	 * *native declaration : :283*
	 */
	abstract fun compositingFilter(): Pointer?

	/**
	 * Original signature : `void setContentFilters(NSArray*)`<br></br>
	 * *native declaration : :285*
	 */
	abstract fun setContentFilters(filters: Pointer?)

	/**
	 * Original signature : `NSArray* contentFilters()`<br></br>
	 * *native declaration : :286*
	 */
	abstract fun contentFilters(): Pointer?

	/**
	 * Original signature : `void setShadow(NSShadow*)`<br></br>
	 * *native declaration : :288*
	 */
	abstract fun setShadow(shadow: Pointer?)

	/**
	 * Original signature : `NSShadow* shadow()`<br></br>
	 * *native declaration : :289*
	 */
	abstract fun shadow(): Pointer?

	/**
	 * The following methods are meant to be invoked, and probably don't need to be overridden<br></br>
	 * Original signature : `void addTrackingArea(NSTrackingArea*)`<br></br>
	 * *native declaration : :293*
	 */
	abstract fun addTrackingArea(trackingArea: Pointer?)

	/**
	 * Original signature : `void removeTrackingArea(NSTrackingArea*)`<br></br>
	 * *native declaration : :294*
	 */
	abstract fun removeTrackingArea(trackingArea: Pointer?)

	/**
	 * Original signature : `NSArray* trackingAreas()`<br></br>
	 * *native declaration : :295*
	 */
	abstract fun trackingAreas(): NSArray?

	/**
	 * updateTrackingAreas should be overridden to remove out of date tracking areas and add recomputed tracking areas, and should call super.<br></br>
	 * Original signature : `void updateTrackingAreas()`<br></br>
	 * *native declaration : :299*
	 */
	abstract fun updateTrackingAreas()

	/**
	 * Original signature : `BOOL shouldDrawColor()`<br></br>
	 * *native declaration : :303*
	 */
	abstract fun shouldDrawColor(): Boolean

	/**
	 * Original signature : `void setPostsBoundsChangedNotifications(BOOL)`<br></br>
	 * *native declaration : :305*
	 */
	abstract fun setPostsBoundsChangedNotifications(flag: Boolean)

	/**
	 * Original signature : `BOOL postsBoundsChangedNotifications()`<br></br>
	 * *native declaration : :306*
	 */
	abstract fun postsBoundsChangedNotifications(): Boolean

	/**
	 * Original signature : `NSScrollView* enclosingScrollView()`<br></br>
	 * *native declaration : :308*
	 */
	abstract fun enclosingScrollView(): NSView?

	/**
	 * Original signature : `NSMenu* menuForEvent(NSEvent*)`<br></br>
	 * *native declaration : :310*
	 */
	abstract fun menuForEvent(event: NSEvent?): NSMenu?

	/**
	 * Original signature : `void setToolTip(NSString*)`<br></br>
	 * *native declaration : :313*
	 */
	abstract fun setToolTip(string: String?)

	/**
	 * Original signature : `NSString* toolTip()`<br></br>
	 * *native declaration : :314*
	 */
	abstract fun toolTip(): String?
	/**
	 * *native declaration : :315*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `void removeToolTip(NSToolTipTag)`<br></br>
	 * *native declaration : :316*
	 */
	abstract fun removeToolTip(tag: Int)

	/**
	 * Original signature : `void removeAllToolTips()`<br></br>
	 * *native declaration : :317*
	 */
	abstract fun removeAllToolTips()

	/**
	 * a view receives viewWillStartLiveResize before the frame is first changed for a live resize<br></br>
	 * Original signature : `void viewWillStartLiveResize()`<br></br>
	 * *native declaration : :321*
	 */
	abstract fun viewWillStartLiveResize()

	/**
	 * a view receives viewWillEndLiveResize after the frame is last changed for a live resize<br></br>
	 * Original signature : `void viewDidEndLiveResize()`<br></br>
	 * *native declaration : :323*
	 */
	abstract fun viewDidEndLiveResize()

	/**
	 * inLiveResize can be called from drawRect: to decide between cheap and full drawing<br></br>
	 * Original signature : `BOOL inLiveResize()`<br></br>
	 * *native declaration : :325*
	 */
	abstract fun inLiveResize(): Boolean

	/**
	 * A view that returns YES for -preservesContentDuringLiveResize is responsible for invalidating its own dirty rects during live resize<br></br>
	 * Original signature : `BOOL preservesContentDuringLiveResize()`<br></br>
	 * *native declaration : :328*
	 */
	abstract fun preservesContentDuringLiveResize(): Boolean

	/**
	 * *native declaration : :330*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * On return from -getRectsExposedDuringLiveResize, exposedRects indicates the parts of the view that are newly exposed (at most 4 rects).  *count indicates how many rects are in the exposedRects list<br></br>
	 * Original signature : `void getRectsExposedDuringLiveResize(NSRect[], NSInteger*)`<br></br>
	 * *native declaration : :332*
	 */
	abstract fun getRectsExposedDuringLiveResize_count(exposedRects: Pointer?, count: IntBuffer?)

	/**
	 * Original signature : `BOOL performMnemonic(NSString*)`<br></br>
	 * *from NSKeyboardUI native declaration : :341*
	 */
	abstract fun performMnemonic(theString: Pointer?): Boolean

	/**
	 * Original signature : `void setNextKeyView(NSView*)`<br></br>
	 * *from NSKeyboardUI native declaration : :342*
	 */
	abstract fun setNextKeyView(next: NSView?)

	/**
	 * Original signature : `NSView* nextKeyView()`<br></br>
	 * *from NSKeyboardUI native declaration : :343*
	 */
	abstract fun nextKeyView(): NSView?

	/**
	 * Original signature : `NSView* previousKeyView()`<br></br>
	 * *from NSKeyboardUI native declaration : :344*
	 */
	abstract fun previousKeyView(): NSView?

	/**
	 * Original signature : `NSView* nextValidKeyView()`<br></br>
	 * *from NSKeyboardUI native declaration : :345*
	 */
	abstract fun nextValidKeyView(): NSView?

	/**
	 * Original signature : `NSView* previousValidKeyView()`<br></br>
	 * *from NSKeyboardUI native declaration : :346*
	 */
	abstract fun previousValidKeyView(): NSView?

	/**
	 * Original signature : `BOOL canBecomeKeyView()`<br></br>
	 * *from NSKeyboardUI native declaration : :349*
	 */
	abstract fun canBecomeKeyView(): Boolean
	/**
	 * *from NSKeyboardUI native declaration : :352*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *from NSKeyboardUI native declaration : :355*<br></br>
	 * Conversion Error : /// Original signature : `void setFocusRingType(null)`<br></br>
	 */
	abstract fun setFocusRingType(focusRingType: Int)

	/**
	 * Original signature : `focusRingType()`<br></br>
	 * *from NSKeyboardUI native declaration : :356*
	 */
	abstract fun focusRingType(): Int
	/**
	 * *from NSPrinting native declaration : :364*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *from NSPrinting native declaration : :365*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *from NSPrinting native declaration : :366*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *from NSPrinting native declaration : :367*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Printing action method (Note fax: is obsolete)<br></br>
	 * Original signature : `void print(id)`<br></br>
	 * *from NSPrinting native declaration : :370*
	 */
	abstract fun print(sender: ID?)
	/**
	 * *from NSPrinting native declaration : :373*<br></br>
	 * Conversion Error : / **<br></br>
	 * * Pagination<br></br>
	 * * Original signature : `BOOL knowsPageRange(null)`<br></br>
	 * * /<br></br>
	 * - (BOOL)knowsPageRange:(null)range; (Argument range cannot be converted)
	 */
	/**
	 * Original signature : `CGFloat heightAdjustLimit()`<br></br>
	 * *from NSPrinting native declaration : :374*
	 */
	abstract fun heightAdjustLimit(): CGFloat?

	/**
	 * Original signature : `CGFloat widthAdjustLimit()`<br></br>
	 * *from NSPrinting native declaration : :375*
	 */
	abstract fun widthAdjustLimit(): CGFloat?

	/**
	 * Original signature : `void adjustPageWidthNew(CGFloat*, CGFloat, CGFloat, CGFloat)`<br></br>
	 * *from NSPrinting native declaration : :376*
	 */
	abstract fun adjustPageWidthNew_left_right_limit(
		newRight: FloatBuffer?,
		oldLeft: CGFloat?,
		oldRight: CGFloat?,
		rightLimit: CGFloat?
	)

	/**
	 * Original signature : `void adjustPageHeightNew(CGFloat*, CGFloat, CGFloat, CGFloat)`<br></br>
	 * *from NSPrinting native declaration : :377*
	 */
	abstract fun adjustPageHeightNew_top_bottom_limit(
		newBottom: FloatBuffer?,
		oldTop: CGFloat?,
		oldBottom: CGFloat?,
		bottomLimit: CGFloat?
	)
	/**
	 * *from NSPrinting native declaration : :378*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *from NSPrinting native declaration : :379*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * *from NSPrinting native declaration : :380*<br></br>
	 * Conversion Error : /// Original signature : `void drawPageBorderWithSize(null)`<br></br>
	 * - (void)drawPageBorderWithSize:(null)borderSize; (Argument borderSize cannot be converted)
	 */
	/**
	 * Original signature : `NSAttributedString* pageHeader()`<br></br>
	 * *from NSPrinting native declaration : :382*
	 */
	abstract fun pageHeader(): NSAttributedString?

	/**
	 * Original signature : `NSAttributedString* pageFooter()`<br></br>
	 * *from NSPrinting native declaration : :383*
	 */
	abstract fun pageFooter(): NSAttributedString?
	/**
	 * *from NSPrinting native declaration : :387*<br></br>
	 * Conversion Error : / **<br></br>
	 * * This method is obsolete.  It will never be invoked from within AppKit, and NSView's implementation of it does nothing. **<br></br>
	 * * Original signature : `void drawSheetBorderWithSize(null)`<br></br>
	 * * /<br></br>
	 * - (void)drawSheetBorderWithSize:(null)borderSize; (Argument borderSize cannot be converted)
	 */
	/**
	 * Returns print job title. Default implementation first tries its window's NSDocument (displayName), then window's title<br></br>
	 * Original signature : `NSString* printJobTitle()`<br></br>
	 * *from NSPrinting native declaration : :391*
	 */
	abstract fun printJobTitle(): String?

	/**
	 * Original signature : `void beginDocument()`<br></br>
	 * *from NSPrinting native declaration : :392*
	 */
	abstract fun beginDocument()

	/**
	 * Original signature : `void endDocument()`<br></br>
	 * *from NSPrinting native declaration : :393*
	 */
	abstract fun endDocument()
	/**
	 * *from NSPrinting native declaration : :395*<br></br>
	 * Conversion Error : NSRect
	 */
	/**
	 * Original signature : `void endPage()`<br></br>
	 * *from NSPrinting native declaration : :396*
	 */
	abstract fun endPage()
	/**
	 * *from NSDrag native declaration : :401*<br></br>
	 * Conversion Error : /// Original signature : `void dragImage(NSImage*, null, null, NSEvent*, NSPasteboard*, id, BOOL)`<br></br>
	 * - (void)dragImage:(NSImage*)anImage at:(null)viewLocation offset:(null)initialOffset event:(NSEvent*)event pasteboard:(NSPasteboard*)pboard source:(id)sourceObj slideBack:(BOOL)slideFlag; (Argument viewLocation cannot be converted)
	 */
	/**
	 * Original signature : `NSArray* registeredDraggedTypes()`<br></br>
	 * *from NSDrag native declaration : :404*
	 */
	abstract fun registeredDraggedTypes(): NSArray?

	/**
	 * Original signature : `void registerForDraggedTypes(NSArray*)`<br></br>
	 * *from NSDrag native declaration : :406*
	 */
	abstract fun registerForDraggedTypes(types: NSArray?)

	/**
	 * Original signature : `void unregisterDraggedTypes()`<br></br>
	 * *from NSDrag native declaration : :407*
	 */
	abstract fun unregisterDraggedTypes()

	/**
	 * *from NSDrag native declaration : :409*<br></br>
	 * Conversion Error : NSRect
	 */
	abstract fun dragFile_fromRect_slideBack_event(
		filename: String?,
		rect: NSRect?,
		slideBack: Boolean,
		event: NSEvent?
	): Boolean

	/**
	 * *from NSDrag native declaration : :411*<br></br>
	 * Conversion Error : NSRect
	 */
	abstract fun dragPromisedFilesOfTypes_fromRect_source_slideBack_event(
		typeArray: NSArray?, rect: NSRect?, sourceObject: ID?, slideBack: Boolean, event: NSEvent?
	): Boolean

	fun dragPromisedFilesOfTypes(
		typeArray: NSArray?, rect: NSRect?, sourceObject: ID?, slideBack: Boolean, event: NSEvent?
	): Boolean {
		return this.dragPromisedFilesOfTypes_fromRect_source_slideBack_event(
			typeArray,
			rect,
			sourceObject,
			slideBack,
			event
		)
	}

	/**
	 * Original signature : `BOOL enterFullScreenMode(NSScreen*, NSDictionary*)`<br></br>
	 * *from NSFullScreenMode native declaration : :417*
	 */
	abstract fun enterFullScreenMode_withOptions(screen: Pointer?, options: Pointer?): Boolean

	/**
	 * Original signature : `void exitFullScreenModeWithOptions(NSDictionary*)`<br></br>
	 * *from NSFullScreenMode native declaration : :418*
	 */
	abstract fun exitFullScreenModeWithOptions(options: NSDictionary?)

	/**
	 * Original signature : `BOOL isInFullScreenMode()`<br></br>
	 * *from NSFullScreenMode native declaration : :419*
	 */
	abstract val isInFullScreenMode: Boolean

	companion object {
		private val CLASS: _Class = Rococoa.createClass("NSView", _Class::class.java)

		const val NSViewNotSizable: Int = 0
		const val NSViewMinXMargin: Int = 1
		const val NSViewWidthSizable: Int = 2
		const val NSViewMaxXMargin: Int = 4
		const val NSViewMinYMargin: Int = 8
		const val NSViewHeightSizable: Int = 16
		const val NSViewMaxYMargin: Int = 32

		const val NSViewLayerContentsRedrawNever: Int = 0
		const val NSViewLayerContentsRedrawOnSetNeedsDisplay: Int = 1
		const val NSViewLayerContentsRedrawDuringViewResize: Int = 2
		const val NSViewLayerContentsRedrawBeforeViewResize: Int = 3
		const val NSViewLayerContentsRedrawCrossfade: Int = 4

		fun create(): NSView {
			return CLASS.alloc().init()
		}

		fun create(frameRect: NSRect?): NSView {
			return CLASS.alloc().initWithFrame(frameRect)
		}
	}
}

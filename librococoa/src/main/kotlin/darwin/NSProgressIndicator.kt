package darwin 

import org.rococoa.ID
import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSRect

/// <i>native declaration : :42</i>
abstract class NSProgressIndicator : NSView() {
	interface _Class : ObjCClass {
		open fun alloc(): NSProgressIndicator
	}

	@Override
	abstract override fun initWithFrame(frameRect: NSRect?): NSProgressIndicator

	/**
	 * Original signature : `BOOL isIndeterminate()`<br></br>
	 * *native declaration : :86*
	 */
	abstract fun isIndeterminate(): Boolean

	/**
	 * Original signature : `void setIndeterminate(BOOL)`<br></br>
	 * *native declaration : :87*
	 */
	abstract fun setIndeterminate(flag: Boolean)

	/**
	 * Original signature : `BOOL isBezeled()`<br></br>
	 * *native declaration : :89*
	 */
	abstract fun isBezeled(): Boolean

	/**
	 * Original signature : `void setBezeled(BOOL)`<br></br>
	 * *native declaration : :90*
	 */
	abstract fun setBezeled(flag: Boolean)

	/**
	 * Original signature : `controlTint()`<br></br>
	 * *native declaration : :92*
	 */
	abstract fun controlTint(): Int
	/**
	 * *native declaration : :93*<br></br>
	 * Conversion Error : /// Original signature : `void setControlTint(null)`<br></br>
	 * - (void)setControlTint:(null)tint; (Argument tint cannot be converted)
	 */
	/**
	 * Original signature : `controlSize()`<br></br>
	 * *native declaration : :95*
	 */
	abstract fun controlSize(): Int

	/**
	 * *native declaration : :96*<br></br>
	 * Conversion Error : /// Original signature : `void setControlSize(null)`<br></br>
	 * - (void)setControlSize:(null)size; (Argument size cannot be converted)
	 */
	abstract fun setControlSize(size: Int)

	/**
	 * Original signature : `double doubleValue()`<br></br>
	 * *native declaration : :100*
	 */
	abstract fun doubleValue(): Double

	/**
	 * Original signature : `void setDoubleValue(double)`<br></br>
	 * *native declaration : :101*
	 */
	abstract fun setDoubleValue(doubleValue: Double)

	/**
	 * Original signature : `void incrementBy(double)`<br></br>
	 * equivalent to [self setDoubleValue:[self doubleValue] + delta]<br></br>
	 * *native declaration : :103*
	 */
	abstract fun incrementBy(delta: Double)

	/**
	 * Original signature : `double minValue()`<br></br>
	 * *native declaration : :105*
	 */
	abstract fun minValue(): Double

	/**
	 * Original signature : `double maxValue()`<br></br>
	 * *native declaration : :106*
	 */
	abstract fun maxValue(): Double

	/**
	 * Original signature : `void setMinValue(double)`<br></br>
	 * *native declaration : :107*
	 */
	abstract fun setMinValue(newMinimum: Double)

	/**
	 * Original signature : `void setMaxValue(double)`<br></br>
	 * *native declaration : :108*
	 */
	abstract fun setMaxValue(newMaximum: Double)
	/**
	 * *native declaration : :112*<br></br>
	 * Conversion Error : NSTimeInterval
	 */
	/**
	 * *native declaration : :113*<br></br>
	 * Conversion Error : NSTimeInterval
	 */
	/**
	 * Original signature : `BOOL usesThreadedAnimation()`<br></br>
	 * returns YES if the PI uses a thread instead of a timer (default in NO)<br></br>
	 * *native declaration : :115*
	 */
	abstract fun usesThreadedAnimation(): Boolean

	/**
	 * Original signature : `void setUsesThreadedAnimation(BOOL)`<br></br>
	 * *native declaration : :116*
	 */
	abstract fun setUsesThreadedAnimation(threadedAnimation: Boolean)

	/**
	 * Original signature : `void startAnimation(id)`<br></br>
	 * *native declaration : :118*
	 */
	abstract fun startAnimation(sender: ID?)

	/**
	 * Original signature : `void stopAnimation(id)`<br></br>
	 * *native declaration : :119*
	 */
	abstract fun stopAnimation(sender: ID?)

	/**
	 * Original signature : `void animate(id)`<br></br>
	 * manual animation<br></br>
	 * *native declaration : :121*
	 */
	abstract fun animate(sender: ID?)

	/**
	 * Original signature : `void setStyle(NSProgressIndicatorStyle)`<br></br>
	 * *native declaration : :125*
	 */
	abstract fun setStyle(style: Int)

	/**
	 * Original signature : `NSProgressIndicatorStyle style()`<br></br>
	 * *native declaration : :126*
	 */
	abstract fun style(): Int

	/**
	 * For the bar style, the height will be set to the recommended height.<br></br>
	 * Original signature : `void sizeToFit()`<br></br>
	 * *native declaration : :130*
	 */
	abstract fun sizeToFit()

	/**
	 * Original signature : `BOOL isDisplayedWhenStopped()`<br></br>
	 * *native declaration : :132*
	 */
	abstract fun isDisplayedWhenStopped(): Boolean

	/**
	 * Original signature : `void setDisplayedWhenStopped(BOOL)`<br></br>
	 * *native declaration : :133*
	 */
	abstract fun setDisplayedWhenStopped(isDisplayed: Boolean)

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSProgressIndicator", _Class::class.java)

		fun progressIndicatorWithFrame(frameRect: NSRect?): NSProgressIndicator? {
			return CLASS.alloc().initWithFrame(frameRect)
		}

		/// <i>native declaration : :22</i>
		const val NSProgressIndicatorPreferredThickness: Int = 14

		/// <i>native declaration : :23</i>
		const val NSProgressIndicatorPreferredSmallThickness: Int = 10

		/// <i>native declaration : :24</i>
		const val NSProgressIndicatorPreferredLargeThickness: Int = 18

		/// <i>native declaration : :25</i>
		const val NSProgressIndicatorPreferredAquaThickness: Int = 12

		/// <i>native declaration : :32</i>
		const val NSProgressIndicatorBarStyle: Int = 0

		/// <i>native declaration : :33</i>
		const val NSProgressIndicatorSpinningStyle: Int = 1
	}
}

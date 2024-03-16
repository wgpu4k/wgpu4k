package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.foundation.NSRect

abstract class NSLevelIndicator : NSControl() {
	interface _Class : ObjCClass {
		open fun alloc(): NSLevelIndicator
	}

	abstract override fun initWithFrame(frameRect: NSRect?): NSLevelIndicator

	abstract fun minValue(): Int

	abstract fun setMinValue(minValue: Int)

	abstract fun maxValue(): Int

	abstract fun setMaxValue(maxValue: Int)

	abstract fun warningValue(): Int

	abstract fun setWarningValue(warningValue: Int)

	abstract fun criticalValue(): Int

	abstract fun setCriticalValue(criticalValue: Int)

	abstract fun tickMarkPosition(): Int

	abstract fun setTickMarkPosition(tickMarkPosition: Int)

	abstract fun numberOfTickMarks(): Int

	abstract fun setNumberOfTickMarks(numberOfTickMarks: Int)

	abstract fun levelIndicatorStyle(): Int

	abstract fun setLevelIndicatorStyle(levelIndicatorStyle: Int)

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSLevelIndicator", _Class::class.java)

		const val NSRelevancyLevelIndicatorStyle: Int = 0
		const val NSContinuousCapacityLevelIndicatorStyle: Int = 1
		const val NSDiscreteCapacityLevelIndicatorStyle: Int = 2
		const val NSRatingLevelIndicatorStyle: Int = 3

		fun levelIndicatorWithFrame(frameRect: NSRect?): NSLevelIndicator? {
			return CLASS.alloc().initWithFrame(frameRect)
		}
	}
}

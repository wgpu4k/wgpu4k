package darwin

/// <i>native declaration : :10</i>
abstract class NSStepper : NSControl() {
	/**
	 * Original signature : `double minValue()`<br></br>
	 * *native declaration : :18*
	 */
	abstract fun minValue(): Double

	/**
	 * Original signature : `void setMinValue(double)`<br></br>
	 * *native declaration : :19*
	 */
	abstract fun setMinValue(minValue: Double)

	/**
	 * Original signature : `double maxValue()`<br></br>
	 * *native declaration : :21*
	 */
	abstract fun maxValue(): Double

	/**
	 * Original signature : `void setMaxValue(double)`<br></br>
	 * *native declaration : :22*
	 */
	abstract fun setMaxValue(maxValue: Double)

	/**
	 * Original signature : `double increment()`<br></br>
	 * *native declaration : :24*
	 */
	abstract fun increment(): Double

	/**
	 * Original signature : `void setIncrement(double)`<br></br>
	 * *native declaration : :25*
	 */
	abstract fun setIncrement(increment: Double)

	/**
	 * Original signature : `BOOL valueWraps()`<br></br>
	 * *native declaration : :27*
	 */
	abstract fun valueWraps(): Boolean

	/**
	 * Original signature : `void setValueWraps(BOOL)`<br></br>
	 * *native declaration : :28*
	 */
	abstract fun setValueWraps(valueWraps: Boolean)

	/**
	 * Original signature : `BOOL autorepeat()`<br></br>
	 * *native declaration : :30*
	 */
	abstract fun autorepeat(): Boolean

	/**
	 * Original signature : `void setAutorepeat(BOOL)`<br></br>
	 * *native declaration : :31*
	 */
	abstract fun setAutorepeat(autorepeat: Boolean)
}

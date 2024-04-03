package darwin

/// <i>native declaration : :83</i>
abstract class NSPanel : NSWindow() {
	/**
	 * Original signature : `BOOL isFloatingPanel()`<br></br>
	 * *native declaration : :88*
	 */
	abstract fun isFloatingPanel(): Boolean

	/**
	 * Original signature : `void setFloatingPanel(BOOL)`<br></br>
	 * *native declaration : :89*
	 */
	abstract fun setFloatingPanel(flag: Boolean)

	/**
	 * Original signature : `BOOL becomesKeyOnlyIfNeeded()`<br></br>
	 * *native declaration : :90*
	 */
	abstract fun becomesKeyOnlyIfNeeded(): Boolean

	/**
	 * Original signature : `void setBecomesKeyOnlyIfNeeded(BOOL)`<br></br>
	 * *native declaration : :91*
	 */
	abstract fun setBecomesKeyOnlyIfNeeded(flag: Boolean)

	/**
	 * Original signature : `void setWorksWhenModal(BOOL)`<br></br>
	 * *native declaration : :93*
	 */
	abstract fun setWorksWhenModal(flag: Boolean)

	companion object {
		/// <i>native declaration : :61</i>
		const val NSOKButton: Int = 1

		/// <i>native declaration : :62</i>
		const val NSCancelButton: Int = 0

		/// <i>native declaration : :67</i>
		const val NSUtilityWindowMask: Int = 1 shl 4

		/// <i>native declaration : :68</i>
		const val NSDocModalWindowMask: Int = 1 shl 6

		/**
		 * specify a panel that does not activate owning application<br></br>
		 * *native declaration : :73*
		 */
		const val NSNonactivatingPanelMask: Int = 1 shl 7

		/**
		 * specify a heads up display panel<br></br>
		 * *native declaration : :79*
		 */
		const val NSHUDWindowMask: Int = 1 shl 13
	}
}

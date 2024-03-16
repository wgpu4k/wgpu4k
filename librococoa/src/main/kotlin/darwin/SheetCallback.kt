package darwin

interface SheetCallback {
	/**
	 * Called after the sheet has been dismissed by the user.
	 *
	 * @param returncode Selected button
	 */
	open fun callback(returncode: Int)

	companion object {
		/**
		 * Use default option; 'OK'
		 */
		val DEFAULT_OPTION: Int = NSAlert.NSAlertDefaultReturn

		/**
		 * Cancel option
		 */
		val CANCEL_OPTION: Int = NSAlert.NSAlertOtherReturn

		/**
		 * Alternate action
		 */
		val ALTERNATE_OPTION: Int = NSAlert.NSAlertAlternateReturn
	}
}

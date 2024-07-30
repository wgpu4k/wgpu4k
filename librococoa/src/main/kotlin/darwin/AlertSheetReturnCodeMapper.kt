package darwin

class AlertSheetReturnCodeMapper {
	/**
	 * Translate return codes from sheet selection
	 *
	 * @param sender Button pressed
	 * @return Sheet callback constant
	 * @see SheetCallback.DEFAULT_OPTION
	 *
	 * @see SheetCallback.CANCEL_OPTION
	 */
	fun getOption(sender: NSButton?): Int {
		return this.getOption(sender?.tag() ?: 0)
	}

	fun getOption(option: Int): Int {
		when (option) {
			NSAlert.NSAlertFirstButtonReturn, NSPanel.NSOKButton -> return SheetCallback.DEFAULT_OPTION
			NSAlert.NSAlertSecondButtonReturn, NSPanel.NSCancelButton -> return SheetCallback.CANCEL_OPTION
			NSAlert.NSAlertThirdButtonReturn -> return SheetCallback.ALTERNATE_OPTION
		}
		return SheetCallback.DEFAULT_OPTION
	}

}

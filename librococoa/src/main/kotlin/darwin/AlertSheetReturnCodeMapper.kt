package darwin 

import org.apache.logging.log4j.LogManager

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
	fun getOption(sender: darwin.NSButton?): Int {
		return this.getOption(sender?.tag() ?: 0)
	}

	fun getOption(option: Int): Int {
		when (option) {
			darwin.NSAlert.Companion.NSAlertFirstButtonReturn, darwin.NSPanel.Companion.NSOKButton -> return darwin.SheetCallback.Companion.DEFAULT_OPTION
			darwin.NSAlert.Companion.NSAlertSecondButtonReturn, darwin.NSPanel.Companion.NSCancelButton -> return darwin.SheetCallback.Companion.CANCEL_OPTION
			darwin.NSAlert.Companion.NSAlertThirdButtonReturn -> return darwin.SheetCallback.Companion.ALTERNATE_OPTION
		}
		darwin.AlertSheetReturnCodeMapper.Companion.log.warn(String.format("Unknown return code %d", option))
		return darwin.SheetCallback.Companion.DEFAULT_OPTION
	}

	companion object {
		private val log = LogManager.getLogger(darwin.AlertSheetReturnCodeMapper::class.java)
	}
}

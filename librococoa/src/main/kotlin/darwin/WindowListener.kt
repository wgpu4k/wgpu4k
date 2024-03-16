package darwin

interface WindowListener {
	/**
	 * Sent after NSWindow.WindowWillCloseNotification is fired for this window
	 */
	open fun windowWillClose()
}

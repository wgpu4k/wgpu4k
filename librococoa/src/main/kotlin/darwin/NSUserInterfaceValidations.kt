package darwin

interface NSUserInterfaceValidations {
	open fun validateUserInterfaceItem(item: NSValidatedUserInterfaceItem?): Boolean
}

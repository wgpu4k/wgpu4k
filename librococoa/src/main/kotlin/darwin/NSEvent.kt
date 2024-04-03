package darwin

import com.sun.jna.Pointer
import org.rococoa.ObjCClass
import org.rococoa.cocoa.CGFloat
import org.rococoa.cocoa.foundation.NSPoint

abstract class NSEvent : NSObject(), NSCopying {
	interface _Class : ObjCClass {
		/**
		 * +eventWithEventRef: returns an autoreleased NSEvent corresponding to the EventRef.  The EventRef is retained by the NSEvent and will be released when the NSEvent is freed.  If there is no NSEvent corresponding to the EventRef, +eventWithEventRef will return nil.<br></br>
		 * Original signature : `NSEvent* eventWithEventRef(const void*)`<br></br>
		 * EventRef<br></br>
		 * *native declaration : :232*
		 */
		fun eventWithEventRef(eventRef: NSEvent?): NSEvent?
		/**
		 * *native declaration : :240*<br></br>
		 * Conversion Error : / **<br></br>
		 * * +eventWithCGEvent: returns an autoreleased NSEvent corresponding to the CGEventRef.  The CGEventRef is retained by the NSEvent and will be released when the NSEvent is freed.  If there is no NSEvent corresponding to the CGEventRef, +eventWithEventRef will return nil.<br></br>
		 * * Original signature : `NSEvent* eventWithCGEvent(null)`<br></br>
		 * * /<br></br>
		 * + (NSEvent*)eventWithCGEvent:(null)cgEvent; (Argument cgEvent cannot be converted)
		 */

		/**
		 * Original signature : `BOOL isMouseCoalescingEnabled()`<br></br>
		 * *native declaration : :245*
		 */
		/**
		 * Enable or disable coalescing of mouse movement events, including mouse moved, mouse dragged, and tablet events.  Coalescing is enabled by default.<br></br>
		 * Original signature : `void setMouseCoalescingEnabled(BOOL)`<br></br>
		 * *native declaration : :244*
		 */
		var isMouseCoalescingEnabled: Boolean
		/**
		 * *native declaration : :296*<br></br>
		 * Conversion Error : NSTimeInterval
		 */
		/**
		 * Original signature : `void stopPeriodicEvents()`<br></br>
		 * *native declaration : :297*
		 */
		fun stopPeriodicEvents()
		/**
		 * *native declaration : :300*<br></br>
		 * Conversion Error : NSPoint
		 */
		/**
		 * *native declaration : :301*<br></br>
		 * Conversion Error : NSPoint
		 */
		/**
		 * *native declaration : :302*<br></br>
		 * Conversion Error : NSPoint
		 */
		/**
		 * *native declaration : :303*<br></br>
		 * Conversion Error : NSPoint
		 */
		/**
		 * *native declaration : :306*<br></br>
		 * Conversion Error : NSPoint
		 */
	}

	/**
	 * these messages are valid for all events<br></br>
	 * Original signature : `NSEventType type()`<br></br>
	 * *native declaration : :177*
	 */
	abstract fun type(): Int

	/**
	 * Original signature : `NSUInteger modifierFlags()`<br></br>
	 * *native declaration : :178*
	 */
	abstract fun modifierFlags(): Int
	/**
	 * *native declaration : :179*<br></br>
	 * Conversion Error : NSTimeInterval
	 */
	/**
	 * Original signature : `NSWindow* window()`<br></br>
	 * *native declaration : :180*
	 */
	abstract fun window(): NSWindow?

	/**
	 * Original signature : `NSInteger windowNumber()`<br></br>
	 * *native declaration : :181*
	 */
	abstract fun windowNumber(): Int

	/**
	 * Original signature : `NSGraphicsContext* context()`<br></br>
	 * *native declaration : :182*
	 */
	abstract fun context(): Pointer?

	/**
	 * these messages are valid for all mouse down/up/drag events<br></br>
	 * Original signature : `NSInteger clickCount()`<br></br>
	 * *native declaration : :185*
	 */
	abstract fun clickCount(): Int

	/**
	 * Original signature : `NSInteger buttonNumber()`<br></br>
	 * for NSOtherMouse events, but will return valid constants for NSLeftMouse and NSRightMouse<br></br>
	 * *native declaration : :186*
	 */
	abstract fun buttonNumber(): Int

	/**
	 * these messages are valid for all mouse down/up/drag and enter/exit events<br></br>
	 * Original signature : `NSInteger eventNumber()`<br></br>
	 * *native declaration : :188*
	 */
	abstract fun eventNumber(): Int

	/**
	 * These messages are also valid for NSTabletPoint events on 10.4 or later<br></br>
	 * Original signature : `float pressure()`<br></br>
	 * *native declaration : :192*
	 */
	abstract fun pressure(): Float

	/**
	 * *native declaration : :193*<br></br>
	 * Conversion Error : NSPoint
	 */
	abstract fun locationInWindow(): NSPoint?

	/**
	 * these messages are valid for scroll wheel events and mouse move/drag events<br></br>
	 * Original signature : `CGFloat deltaX()`<br></br>
	 * *native declaration : :196*
	 */
	abstract fun deltaX(): CGFloat?

	/**
	 * Original signature : `CGFloat deltaY()`<br></br>
	 * *native declaration : :197*
	 */
	abstract fun deltaY(): CGFloat?

	/**
	 * Original signature : `CGFloat deltaZ()`<br></br>
	 * 0 for most scroll wheel and mouse events<br></br>
	 * *native declaration : :198*
	 */
	abstract fun deltaZ(): CGFloat?

	/**
	 * these messages are valid for keyup and keydown events<br></br>
	 * Original signature : `NSString* characters()`<br></br>
	 * *native declaration : :201*
	 */
	abstract fun characters(): String?

	/**
	 * Original signature : `NSString* charactersIgnoringModifiers()`<br></br>
	 * *native declaration : :202*
	 */
	abstract fun charactersIgnoringModifiers(): String?

	/**
	 * the chars that would have been generated, regardless of modifier keys (except shift)<br></br>
	 * Original signature : `BOOL isARepeat()`<br></br>
	 * *native declaration : :204*
	 */
	abstract val isARepeat: Boolean

	/**
	 * this message is valid for keyup, keydown and flagschanged events<br></br>
	 * Original signature : `unsigned short keyCode()`<br></br>
	 * device-independent key number<br></br>
	 * *native declaration : :206*
	 */
	abstract fun keyCode(): Short

	/**
	 * these messages are valid for enter and exit events<br></br>
	 * Original signature : `NSInteger trackingNumber()`<br></br>
	 * *native declaration : :209*
	 */
	abstract fun trackingNumber(): Int

	/**
	 * Original signature : `void* userData()`<br></br>
	 * *native declaration : :210*
	 */
	abstract fun userData(): NSObject?

	/**
	 * -trackingArea returns the NSTrackingArea that generated this event.  It is possible for there to be no trackingArea associated with the event in some cases where the event corresponds to a trackingRect installed with -[NSView addTrackingRect:owner:userData:assumeInside:], in which case nil is returned.<br></br>
	 * Original signature : `NSTrackingArea* trackingArea()`<br></br>
	 * *native declaration : :213*
	 */
	abstract fun trackingArea(): Pointer?

	/**
	 * this message is also valid for mouse events on 10.4 or later<br></br>
	 * Original signature : `short subtype()`<br></br>
	 * *native declaration : :218*
	 */
	abstract fun subtype(): Short

	/**
	 * these messages are valid for kit, system, and app-defined events<br></br>
	 * Original signature : `NSInteger data1()`<br></br>
	 * *native declaration : :221*
	 */
	abstract fun data1(): Int

	/**
	 * Original signature : `NSInteger data2()`<br></br>
	 * *native declaration : :222*
	 */
	abstract fun data2(): Int

	/**
	 * -eventRef returns an EventRef corresponding to the NSEvent.  The EventRef is retained by the NSEvent, so will be valid as long as the NSEvent is valid, and will be released when the NSEvent is freed.  You can use RetainEvent to extend the lifetime of the EventRef, with a corresponding ReleaseEvent when you are done with it.  If there is no EventRef corresponding to the NSEvent, -eventRef will return NULL.<br></br>
	 * Original signature : `const void* eventRef()`<br></br>
	 * *native declaration : :229*
	 */
	abstract fun eventRef(): NSEvent?

	/**
	 * -CGEvent returns an autoreleased CGEventRef corresponding to the NSEvent.  If there is no CGEventRef corresponding to the NSEvent, -CGEvent will return NULL.<br></br>
	 * Original signature : `CGEvent()`<br></br>
	 * *native declaration : :236*
	 */
	abstract fun CGEvent(): Pointer?

	/**
	 * this message is valid for mouse events with subtype NSTabletPointEventSubtype or NSTabletProximityEventSubtype, and for NSTabletPoint and NSTabletProximity events<br></br>
	 * Original signature : `NSUInteger deviceID()`<br></br>
	 * *native declaration : :251*
	 */
	abstract fun deviceID(): Int

	/**
	 * absolute x coordinate in tablet space at full tablet resolution<br></br>
	 * Original signature : `NSInteger absoluteX()`<br></br>
	 * *native declaration : :255*
	 */
	abstract fun absoluteX(): Int

	/**
	 * absolute y coordinate in tablet space at full tablet resolution<br></br>
	 * Original signature : `NSInteger absoluteY()`<br></br>
	 * *native declaration : :257*
	 */
	abstract fun absoluteY(): Int

	/**
	 * absolute z coordinate in tablet space at full tablet resolution<br></br>
	 * Original signature : `NSInteger absoluteZ()`<br></br>
	 * *native declaration : :259*
	 */
	abstract fun absoluteZ(): Int

	/**
	 * mask indicating which buttons are pressed.<br></br>
	 * Original signature : `NSUInteger buttonMask()`<br></br>
	 * *native declaration : :261*
	 */
	abstract fun buttonMask(): Int
	/**
	 * *native declaration : :263*<br></br>
	 * Conversion Error : NSPoint
	 */
	/**
	 * device rotation in degrees<br></br>
	 * Original signature : `float rotation()`<br></br>
	 * *native declaration : :265*
	 */
	abstract fun rotation(): Float

	/**
	 * tangential pressure on the device; range is -1 to 1<br></br>
	 * Original signature : `float tangentialPressure()`<br></br>
	 * *native declaration : :267*
	 */
	abstract fun tangentialPressure(): Float

	/**
	 * NSArray of 3 vendor defined shorts<br></br>
	 * Original signature : `vendorDefined()`<br></br>
	 * *native declaration : :269*
	 */
	abstract fun vendorDefined(): NSArray?

	/**
	 * vendor defined, typically USB vendor ID<br></br>
	 * Original signature : `NSUInteger vendorID()`<br></br>
	 * *native declaration : :273*
	 */
	abstract fun vendorID(): Int

	/**
	 * vendor defined tablet ID<br></br>
	 * Original signature : `NSUInteger tabletID()`<br></br>
	 * *native declaration : :275*
	 */
	abstract fun tabletID(): Int

	/**
	 * index of the device on the tablet.  Usually 0, except for tablets that support multiple concurrent devices<br></br>
	 * Original signature : `NSUInteger pointingDeviceID()`<br></br>
	 * *native declaration : :277*
	 */
	abstract fun pointingDeviceID(): Int

	/**
	 * system assigned unique tablet ID<br></br>
	 * Original signature : `NSUInteger systemTabletID()`<br></br>
	 * *native declaration : :279*
	 */
	abstract fun systemTabletID(): Int

	/**
	 * vendor defined pointing device type<br></br>
	 * Original signature : `NSUInteger vendorPointingDeviceType()`<br></br>
	 * *native declaration : :281*
	 */
	abstract fun vendorPointingDeviceType(): Int

	/**
	 * vendor defined serial number of pointing device<br></br>
	 * Original signature : `NSUInteger pointingDeviceSerialNumber()`<br></br>
	 * *native declaration : :283*
	 */
	abstract fun pointingDeviceSerialNumber(): Int

	/**
	 * vendor defined unique ID<br></br>
	 * Original signature : `unsigned long long uniqueID()`<br></br>
	 * *native declaration : :285*
	 */
	abstract fun uniqueID(): Long

	/**
	 * mask representing capabilities of device<br></br>
	 * Original signature : `NSUInteger capabilityMask()`<br></br>
	 * *native declaration : :287*
	 */
	abstract fun capabilityMask(): Int

	/**
	 * mask representing capabilities of device<br></br>
	 * Original signature : `NSPointingDeviceType pointingDeviceType()`<br></br>
	 * *native declaration : :289*
	 */
	abstract fun pointingDeviceType(): Int

	/**
	 * YES - entering; NO - leaving<br></br>
	 * Original signature : `BOOL isEnteringProximity()`<br></br>
	 * *native declaration : :291*
	 */
	abstract val isEnteringProximity: Boolean

	companion object {
		/// <i>native declaration : :12</i>
		const val NSLeftMouseDown: Int = 1

		/// <i>native declaration : :13</i>
		const val NSLeftMouseUp: Int = 2

		/// <i>native declaration : :14</i>
		const val NSRightMouseDown: Int = 3

		/// <i>native declaration : :15</i>
		const val NSRightMouseUp: Int = 4

		/// <i>native declaration : :16</i>
		const val NSMouseMoved: Int = 5

		/// <i>native declaration : :17</i>
		const val NSLeftMouseDragged: Int = 6

		/// <i>native declaration : :18</i>
		const val NSRightMouseDragged: Int = 7

		/// <i>native declaration : :19</i>
		const val NSMouseEntered: Int = 8

		/// <i>native declaration : :20</i>
		const val NSMouseExited: Int = 9

		/// <i>native declaration : :21</i>
		const val NSKeyDown: Int = 10

		/// <i>native declaration : :22</i>
		const val NSKeyUp: Int = 11

		/// <i>native declaration : :23</i>
		const val NSFlagsChanged: Int = 12

		/// <i>native declaration : :24</i>
		const val NSAppKitDefined: Int = 13

		/// <i>native declaration : :25</i>
		const val NSSystemDefined: Int = 14

		/// <i>native declaration : :26</i>
		const val NSApplicationDefined: Int = 15

		/// <i>native declaration : :27</i>
		const val NSPeriodic: Int = 16

		/// <i>native declaration : :28</i>
		const val NSCursorUpdate: Int = 17

		/// <i>native declaration : :29</i>
		const val NSScrollWheel: Int = 22

		/// <i>native declaration : :31</i>
		const val NSTabletPoint: Int = 23

		/// <i>native declaration : :32</i>
		const val NSTabletProximity: Int = 24

		/// <i>native declaration : :34</i>
		const val NSOtherMouseDown: Int = 25

		/// <i>native declaration : :35</i>
		const val NSOtherMouseUp: Int = 26

		/// <i>native declaration : :36</i>
		const val NSOtherMouseDragged: Int = 27

		/// <i>native declaration : :41</i>
		const val NSLeftMouseDownMask: Int = 1 shl NSLeftMouseDown

		/// <i>native declaration : :42</i>
		const val NSLeftMouseUpMask: Int = 1 shl NSLeftMouseUp

		/// <i>native declaration : :43</i>
		const val NSRightMouseDownMask: Int = 1 shl NSRightMouseDown

		/// <i>native declaration : :44</i>
		const val NSRightMouseUpMask: Int = 1 shl NSRightMouseUp

		/// <i>native declaration : :45</i>
		const val NSMouseMovedMask: Int = 1 shl NSMouseMoved

		/// <i>native declaration : :46</i>
		const val NSLeftMouseDraggedMask: Int = 1 shl NSLeftMouseDragged

		/// <i>native declaration : :47</i>
		const val NSRightMouseDraggedMask: Int = 1 shl NSRightMouseDragged

		/// <i>native declaration : :48</i>
		const val NSMouseEnteredMask: Int = 1 shl NSMouseEntered

		/// <i>native declaration : :49</i>
		const val NSMouseExitedMask: Int = 1 shl NSMouseExited

		/// <i>native declaration : :50</i>
		const val NSKeyDownMask: Int = 1 shl NSKeyDown

		/// <i>native declaration : :51</i>
		const val NSKeyUpMask: Int = 1 shl NSKeyUp

		/// <i>native declaration : :52</i>
		const val NSFlagsChangedMask: Int = 1 shl NSFlagsChanged

		/// <i>native declaration : :53</i>
		const val NSAppKitDefinedMask: Int = 1 shl NSAppKitDefined

		/// <i>native declaration : :54</i>
		const val NSSystemDefinedMask: Int = 1 shl NSSystemDefined

		/// <i>native declaration : :55</i>
		const val NSApplicationDefinedMask: Int = 1 shl NSApplicationDefined

		/// <i>native declaration : :56</i>
		const val NSPeriodicMask: Int = 1 shl NSPeriodic

		/// <i>native declaration : :57</i>
		const val NSCursorUpdateMask: Int = 1 shl NSCursorUpdate

		/// <i>native declaration : :58</i>
		const val NSScrollWheelMask: Int = 1 shl NSScrollWheel

		/// <i>native declaration : :60</i>
		const val NSTabletPointMask: Int = 1 shl NSTabletPoint

		/// <i>native declaration : :61</i>
		const val NSTabletProximityMask: Int = 1 shl NSTabletProximity

		/// <i>native declaration : :63</i>
		const val NSOtherMouseDownMask: Int = 1 shl NSOtherMouseDown

		/// <i>native declaration : :64</i>
		const val NSOtherMouseUpMask: Int = 1 shl NSOtherMouseUp

		/// <i>native declaration : :65</i>
		const val NSOtherMouseDraggedMask: Int = 1 shl NSOtherMouseDragged

		/// Failed to infer type of NSUIntegerMax
		/// Failed to infer type of NX_TABLET_POINTER_UNKNOWN
		/// Failed to infer type of NX_TABLET_POINTER_PEN
		/// Failed to infer type of NX_TABLET_POINTER_CURSOR
		/// Failed to infer type of NX_TABLET_POINTER_ERASER
		/// Failed to infer type of NX_TABLET_BUTTON_PENTIPMASK
		/// Failed to infer type of NX_TABLET_BUTTON_PENLOWERSIDEMASK
		/// Failed to infer type of NX_TABLET_BUTTON_PENUPPERSIDEMASK
		/// <i>native declaration : :313</i>
		const val NSUpArrowFunctionKey: Int = 63232

		/// <i>native declaration : :314</i>
		const val NSDownArrowFunctionKey: Int = 63233

		/// <i>native declaration : :315</i>
		const val NSLeftArrowFunctionKey: Int = 63234

		/// <i>native declaration : :316</i>
		const val NSRightArrowFunctionKey: Int = 63235

		/// <i>native declaration : :317</i>
		const val NSF1FunctionKey: Int = 63236

		/// <i>native declaration : :318</i>
		const val NSF2FunctionKey: Int = 63237

		/// <i>native declaration : :319</i>
		const val NSF3FunctionKey: Int = 63238

		/// <i>native declaration : :320</i>
		const val NSF4FunctionKey: Int = 63239

		/// <i>native declaration : :321</i>
		const val NSF5FunctionKey: Int = 63240

		/// <i>native declaration : :322</i>
		const val NSF6FunctionKey: Int = 63241

		/// <i>native declaration : :323</i>
		const val NSF7FunctionKey: Int = 63242

		/// <i>native declaration : :324</i>
		const val NSF8FunctionKey: Int = 63243

		/// <i>native declaration : :325</i>
		const val NSF9FunctionKey: Int = 63244

		/// <i>native declaration : :326</i>
		const val NSF10FunctionKey: Int = 63245

		/// <i>native declaration : :327</i>
		const val NSF11FunctionKey: Int = 63246

		/// <i>native declaration : :328</i>
		const val NSF12FunctionKey: Int = 63247

		/// <i>native declaration : :329</i>
		const val NSF13FunctionKey: Int = 63248

		/// <i>native declaration : :330</i>
		const val NSF14FunctionKey: Int = 63249

		/// <i>native declaration : :331</i>
		const val NSF15FunctionKey: Int = 63250

		/// <i>native declaration : :332</i>
		const val NSF16FunctionKey: Int = 63251

		/// <i>native declaration : :333</i>
		const val NSF17FunctionKey: Int = 63252

		/// <i>native declaration : :334</i>
		const val NSF18FunctionKey: Int = 63253

		/// <i>native declaration : :335</i>
		const val NSF19FunctionKey: Int = 63254

		/// <i>native declaration : :336</i>
		const val NSF20FunctionKey: Int = 63255

		/// <i>native declaration : :337</i>
		const val NSF21FunctionKey: Int = 63256

		/// <i>native declaration : :338</i>
		const val NSF22FunctionKey: Int = 63257

		/// <i>native declaration : :339</i>
		const val NSF23FunctionKey: Int = 63258

		/// <i>native declaration : :340</i>
		const val NSF24FunctionKey: Int = 63259

		/// <i>native declaration : :341</i>
		const val NSF25FunctionKey: Int = 63260

		/// <i>native declaration : :342</i>
		const val NSF26FunctionKey: Int = 63261

		/// <i>native declaration : :343</i>
		const val NSF27FunctionKey: Int = 63262

		/// <i>native declaration : :344</i>
		const val NSF28FunctionKey: Int = 63263

		/// <i>native declaration : :345</i>
		const val NSF29FunctionKey: Int = 63264

		/// <i>native declaration : :346</i>
		const val NSF30FunctionKey: Int = 63265

		/// <i>native declaration : :347</i>
		const val NSF31FunctionKey: Int = 63266

		/// <i>native declaration : :348</i>
		const val NSF32FunctionKey: Int = 63267

		/// <i>native declaration : :349</i>
		const val NSF33FunctionKey: Int = 63268

		/// <i>native declaration : :350</i>
		const val NSF34FunctionKey: Int = 63269

		/// <i>native declaration : :351</i>
		const val NSF35FunctionKey: Int = 63270

		/// <i>native declaration : :352</i>
		const val NSInsertFunctionKey: Int = 63271

		/// <i>native declaration : :353</i>
		const val NSDeleteFunctionKey: Int = 63272

		/// <i>native declaration : :354</i>
		const val NSHomeFunctionKey: Int = 63273

		/// <i>native declaration : :355</i>
		const val NSBeginFunctionKey: Int = 63274

		/// <i>native declaration : :356</i>
		const val NSEndFunctionKey: Int = 63275

		/// <i>native declaration : :357</i>
		const val NSPageUpFunctionKey: Int = 63276

		/// <i>native declaration : :358</i>
		const val NSPageDownFunctionKey: Int = 63277

		/// <i>native declaration : :359</i>
		const val NSPrintScreenFunctionKey: Int = 63278

		/// <i>native declaration : :360</i>
		const val NSScrollLockFunctionKey: Int = 63279

		/// <i>native declaration : :361</i>
		const val NSPauseFunctionKey: Int = 63280

		/// <i>native declaration : :362</i>
		const val NSSysReqFunctionKey: Int = 63281

		/// <i>native declaration : :363</i>
		const val NSBreakFunctionKey: Int = 63282

		/// <i>native declaration : :364</i>
		const val NSResetFunctionKey: Int = 63283

		/// <i>native declaration : :365</i>
		const val NSStopFunctionKey: Int = 63284

		/// <i>native declaration : :366</i>
		const val NSMenuFunctionKey: Int = 63285

		/// <i>native declaration : :367</i>
		const val NSUserFunctionKey: Int = 63286

		/// <i>native declaration : :368</i>
		const val NSSystemFunctionKey: Int = 63287

		/// <i>native declaration : :369</i>
		const val NSPrintFunctionKey: Int = 63288

		/// <i>native declaration : :370</i>
		const val NSClearLineFunctionKey: Int = 63289

		/// <i>native declaration : :371</i>
		const val NSClearDisplayFunctionKey: Int = 63290

		/// <i>native declaration : :372</i>
		const val NSInsertLineFunctionKey: Int = 63291

		/// <i>native declaration : :373</i>
		const val NSDeleteLineFunctionKey: Int = 63292

		/// <i>native declaration : :374</i>
		const val NSInsertCharFunctionKey: Int = 63293

		/// <i>native declaration : :375</i>
		const val NSDeleteCharFunctionKey: Int = 63294

		/// <i>native declaration : :376</i>
		const val NSPrevFunctionKey: Int = 63295

		/// <i>native declaration : :377</i>
		const val NSNextFunctionKey: Int = 63296

		/// <i>native declaration : :378</i>
		const val NSSelectFunctionKey: Int = 63297

		/// <i>native declaration : :379</i>
		const val NSExecuteFunctionKey: Int = 63298

		/// <i>native declaration : :380</i>
		const val NSUndoFunctionKey: Int = 63299

		/// <i>native declaration : :381</i>
		const val NSRedoFunctionKey: Int = 63300

		/// <i>native declaration : :382</i>
		const val NSFindFunctionKey: Int = 63301

		/// <i>native declaration : :383</i>
		const val NSHelpFunctionKey: Int = 63302

		/// <i>native declaration : :384</i>
		const val NSModeSwitchFunctionKey: Int = 63303

		/// <i>native declaration : :389</i>
		const val NSWindowExposedEventType: Int = 0

		/// <i>native declaration : :390</i>
		const val NSApplicationActivatedEventType: Int = 1

		/// <i>native declaration : :391</i>
		const val NSApplicationDeactivatedEventType: Int = 2

		/// <i>native declaration : :392</i>
		const val NSWindowMovedEventType: Int = 4

		/// <i>native declaration : :393</i>
		const val NSScreenChangedEventType: Int = 8

		/// <i>native declaration : :394</i>
		const val NSAWTEventType: Int = 16

		/// <i>native declaration : :398</i>
		const val NSPowerOffEventType: Int = 1


		const val NSAlphaShiftKeyMask: Int = 1 shl 16
		const val NSShiftKeyMask: Int = 1 shl 17
		const val NSControlKeyMask: Int = 1 shl 18
		const val NSAlternateKeyMask: Int = 1 shl 19
		const val NSCommandKeyMask: Int = 1 shl 20
		const val NSNumericPadKeyMask: Int = 1 shl 21
		const val NSHelpKeyMask: Int = 1 shl 22
		const val NSFunctionKeyMask: Int = 1 shl 23
	}
}

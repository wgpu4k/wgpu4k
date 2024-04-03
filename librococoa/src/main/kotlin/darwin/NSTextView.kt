package darwin 

import org.rococoa.ObjCClass
import org.rococoa.cocoa.CGFloat

/// <i>native declaration : :72</i>
abstract class NSTextView : NSText() {
	interface _Class : ObjCClass {
		open fun alloc(): NSTextView
	}

	abstract override fun init(): NSTextView

	/**
	 * *native declaration : :80*<br></br>
	 * Conversion Error : /// Original signature : `initWithFrame(null, NSTextContainer*)`<br></br>
	 * - (null)initWithFrame:(null)frameRect textContainer:(NSTextContainer*)container; (Argument frameRect cannot be converted)
	 */
	/**
	 * *native declaration : :83*<br></br>
	 * Conversion Error : /// Original signature : `initWithFrame(null)`<br></br>
	 * - (null)initWithFrame:(null)frameRect; (Argument frameRect cannot be converted)
	 */
	/**
	 * Original signature : `NSTextContainer* textContainer()`<br></br>
	 * *native declaration : :88*
	 */
	abstract fun textContainer(): com.sun.jna.Pointer?

	/**
	 * Original signature : `void setTextContainer(NSTextContainer*)`<br></br>
	 * *native declaration : :89*
	 */
	abstract fun setTextContainer(container: com.sun.jna.Pointer?)

	/**
	 * Original signature : `void replaceTextContainer(NSTextContainer*)`<br></br>
	 * *native declaration : :92*
	 */
	abstract fun replaceTextContainer(newContainer: com.sun.jna.Pointer?)
	/**
	 * *native declaration : :95*<br></br>
	 * Conversion Error : /// Original signature : `void setTextContainerInset(null)`<br></br>
	 * - (void)setTextContainerInset:(null)inset; (Argument inset cannot be converted)
	 */
	/**
	 * Original signature : `textContainerInset()`<br></br>
	 * *native declaration : :96*
	 */
	abstract fun textContainerInset(): NSObject?

	/**
	 * Original signature : `textContainerOrigin()`<br></br>
	 * *native declaration : :99*
	 */
	abstract fun textContainerOrigin(): NSObject?

	/**
	 * Original signature : `void invalidateTextContainerOrigin()`<br></br>
	 * *native declaration : :100*
	 */
	abstract fun invalidateTextContainerOrigin()

	/**
	 * Original signature : `NSLayoutManager* layoutManager()`<br></br>
	 * *native declaration : :103*
	 */
	abstract fun layoutManager(): NSLayoutManager?

	/**
	 * Original signature : `NSTextStorage* textStorage()`<br></br>
	 * *native declaration : :104*
	 */
	abstract fun textStorage(): NSTextStorage?
	/**
	 * *native declaration : :109*<br></br>
	 * Conversion Error : /// Original signature : `void insertText(null)`<br></br>
	 * - (void)insertText:(null)insertString; (Argument insertString cannot be converted)
	 */
	/**
	 * *native declaration : :114*<br></br>
	 * Conversion Error : /// Original signature : `void setConstrainedFrameSize(null)`<br></br>
	 * - (void)setConstrainedFrameSize:(null)desiredSize; (Argument desiredSize cannot be converted)
	 */
	/**
	 * *native declaration : :120*<br></br>
	 * Conversion Error : / **<br></br>
	 * * These two complete the set of range: type set methods. to be equivalent to the set of non-range taking varieties.<br></br>
	 * * Original signature : `void setAlignment(null, null)`<br></br>
	 * * /<br></br>
	 * - (void)setAlignment:(null)alignment range:(null)range; (Argument alignment cannot be converted)
	 */
	/**
	 * *native declaration : :122*<br></br>
	 * Conversion Error : /// Original signature : `void setBaseWritingDirection(null, null)`<br></br>
	 * - (void)setBaseWritingDirection:(null)writingDirection range:(null)range; (Argument writingDirection cannot be converted)
	 */
	/**
	 * *native declaration : :127*<br></br>
	 * Conversion Error : /// Original signature : `void turnOffKerning(null)`<br></br>
	 * - (void)turnOffKerning:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :128*<br></br>
	 * Conversion Error : /// Original signature : `void tightenKerning(null)`<br></br>
	 * - (void)tightenKerning:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :129*<br></br>
	 * Conversion Error : /// Original signature : `void loosenKerning(null)`<br></br>
	 * - (void)loosenKerning:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :130*<br></br>
	 * Conversion Error : /// Original signature : `void useStandardKerning(null)`<br></br>
	 * - (void)useStandardKerning:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :131*<br></br>
	 * Conversion Error : /// Original signature : `void turnOffLigatures(null)`<br></br>
	 * - (void)turnOffLigatures:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :132*<br></br>
	 * Conversion Error : /// Original signature : `void useStandardLigatures(null)`<br></br>
	 * - (void)useStandardLigatures:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :133*<br></br>
	 * Conversion Error : /// Original signature : `void useAllLigatures(null)`<br></br>
	 * - (void)useAllLigatures:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :134*<br></br>
	 * Conversion Error : /// Original signature : `void raiseBaseline(null)`<br></br>
	 * - (void)raiseBaseline:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :135*<br></br>
	 * Conversion Error : /// Original signature : `void lowerBaseline(null)`<br></br>
	 * - (void)lowerBaseline:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :136*<br></br>
	 * Conversion Error : /// Original signature : `void toggleTraditionalCharacterShape(null)`<br></br>
	 * - (void)toggleTraditionalCharacterShape:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :138*<br></br>
	 * Conversion Error : /// Original signature : `void outline(null)`<br></br>
	 * - (void)outline:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :144*<br></br>
	 * Conversion Error : /// Original signature : `void performFindPanelAction(null)`<br></br>
	 * - (void)performFindPanelAction:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :150*<br></br>
	 * Conversion Error : /// Original signature : `void alignJustified(null)`<br></br>
	 * - (void)alignJustified:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :151*<br></br>
	 * Conversion Error : /// Original signature : `void changeColor(null)`<br></br>
	 * - (void)changeColor:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :153*<br></br>
	 * Conversion Error : /// Original signature : `void changeAttributes(null)`<br></br>
	 * - (void)changeAttributes:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :154*<br></br>
	 * Conversion Error : /// Original signature : `void changeDocumentBackgroundColor(null)`<br></br>
	 * - (void)changeDocumentBackgroundColor:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :155*<br></br>
	 * Conversion Error : /// Original signature : `void toggleBaseWritingDirection(null)`<br></br>
	 * - (void)toggleBaseWritingDirection:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :158*<br></br>
	 * Conversion Error : /// Original signature : `void orderFrontSpacingPanel(null)`<br></br>
	 * - (void)orderFrontSpacingPanel:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :159*<br></br>
	 * Conversion Error : /// Original signature : `void orderFrontLinkPanel(null)`<br></br>
	 * - (void)orderFrontLinkPanel:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :160*<br></br>
	 * Conversion Error : /// Original signature : `void orderFrontListPanel(null)`<br></br>
	 * - (void)orderFrontListPanel:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :161*<br></br>
	 * Conversion Error : /// Original signature : `void orderFrontTablePanel(null)`<br></br>
	 * - (void)orderFrontTablePanel:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * Original signature : `void rulerView(NSRulerView*, NSRulerMarker*)`<br></br>
	 * *native declaration : :166*
	 */
	abstract fun rulerView_didMoveMarker(ruler: com.sun.jna.Pointer?, marker: com.sun.jna.Pointer?)

	/**
	 * Original signature : `void rulerView(NSRulerView*, NSRulerMarker*)`<br></br>
	 * *native declaration : :167*
	 */
	abstract fun rulerView_didRemoveMarker(ruler: com.sun.jna.Pointer?, marker: com.sun.jna.Pointer?)

	/**
	 * Original signature : `void rulerView(NSRulerView*, NSRulerMarker*)`<br></br>
	 * *native declaration : :168*
	 */
	abstract fun rulerView_didAddMarker(ruler: com.sun.jna.Pointer?, marker: com.sun.jna.Pointer?)

	/**
	 * Original signature : `BOOL rulerView(NSRulerView*, NSRulerMarker*)`<br></br>
	 * *native declaration : :169*
	 */
	abstract fun rulerView_shouldMoveMarker(ruler: com.sun.jna.Pointer?, marker: com.sun.jna.Pointer?): Boolean

	/**
	 * Original signature : `BOOL rulerView(NSRulerView*, NSRulerMarker*)`<br></br>
	 * *native declaration : :170*
	 */
	abstract fun rulerView_shouldAddMarker(ruler: com.sun.jna.Pointer?, marker: com.sun.jna.Pointer?): Boolean

	/**
	 * Original signature : `CGFloat rulerView(NSRulerView*, NSRulerMarker*, CGFloat)`<br></br>
	 * *native declaration : :171*
	 */
	abstract fun rulerView_willMoveMarker_toLocation(
		ruler: com.sun.jna.Pointer?,
		marker: com.sun.jna.Pointer?,
		location: CGFloat?
	): CGFloat?

	/**
	 * Original signature : `BOOL rulerView(NSRulerView*, NSRulerMarker*)`<br></br>
	 * *native declaration : :172*
	 */
	abstract fun rulerView_shouldRemoveMarker(ruler: com.sun.jna.Pointer?, marker: com.sun.jna.Pointer?): Boolean

	/**
	 * Original signature : `CGFloat rulerView(NSRulerView*, NSRulerMarker*, CGFloat)`<br></br>
	 * *native declaration : :173*
	 */
	abstract fun rulerView_willAddMarker_atLocation(
		ruler: com.sun.jna.Pointer?,
		marker: com.sun.jna.Pointer?,
		location: CGFloat?
	): CGFloat?

	/**
	 * Original signature : `void rulerView(NSRulerView*, NSEvent*)`<br></br>
	 * *native declaration : :174*
	 */
	abstract fun rulerView_handleMouseDown(ruler: com.sun.jna.Pointer?, event: NSEvent?)
	/**
	 * *native declaration : :178*<br></br>
	 * Conversion Error : /// Original signature : `void setNeedsDisplayInRect(null, BOOL)`<br></br>
	 * - (void)setNeedsDisplayInRect:(null)rect avoidAdditionalLayout:(BOOL)flag; (Argument rect cannot be converted)
	 */
	/**
	 * Original signature : `BOOL shouldDrawInsertionPoint()`<br></br>
	 * *native declaration : :181*
	 */
	abstract fun shouldDrawInsertionPoint(): Boolean
	/**
	 * *native declaration : :182*<br></br>
	 * Conversion Error : /// Original signature : `void drawInsertionPointInRect(null, NSColor*, BOOL)`<br></br>
	 * - (void)drawInsertionPointInRect:(null)rect color:(NSColor*)color turnedOn:(BOOL)flag; (Argument rect cannot be converted)
	 */
	/**
	 * *native declaration : :185*<br></br>
	 * Conversion Error : /// Original signature : `void drawViewBackgroundInRect(null)`<br></br>
	 * - (void)drawViewBackgroundInRect:(null)rect; (Argument rect cannot be converted)
	 */
	/**
	 * Original signature : `void updateRuler()`<br></br>
	 * *native declaration : :191*
	 */
	abstract fun updateRuler()

	/**
	 * Original signature : `void updateFontPanel()`<br></br>
	 * *native declaration : :192*
	 */
	abstract fun updateFontPanel()

	/**
	 * Original signature : `void updateDragTypeRegistration()`<br></br>
	 * *native declaration : :194*
	 */
	abstract fun updateDragTypeRegistration()
	/**
	 * *native declaration : :196*<br></br>
	 * Conversion Error : /// Original signature : `selectionRangeForProposedRange(null, NSSelectionGranularity)`<br></br>
	 * - (null)selectionRangeForProposedRange:(null)proposedCharRange granularity:(NSSelectionGranularity)granularity; (Argument proposedCharRange cannot be converted)
	 */
	/**
	 * *native declaration : :200*<br></br>
	 * Conversion Error : /// Original signature : `void clickedOnLink(null, NSUInteger)`<br></br>
	 * - (void)clickedOnLink:(null)link atIndex:(NSUInteger)charIndex; (Argument link cannot be converted)
	 */
	/**
	 * *native declaration : :205*<br></br>
	 * Conversion Error : /// Original signature : `void startSpeaking(null)`<br></br>
	 * - (void)startSpeaking:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :206*<br></br>
	 * Conversion Error : /// Original signature : `void stopSpeaking(null)`<br></br>
	 * - (void)stopSpeaking:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *native declaration : :211*<br></br>
	 * Conversion Error : /// Original signature : `NSUInteger characterIndexForInsertionAtPoint(null)`<br></br>
	 * - (NSUInteger)characterIndexForInsertionAtPoint:(null)point; (Argument point cannot be converted)
	 */
	/**
	 * *from NSCompletion native declaration : :222*<br></br>
	 * Conversion Error : /// Original signature : `void complete(null)`<br></br>
	 * - (void)complete:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * Original signature : `rangeForUserCompletion()`<br></br>
	 * *from NSCompletion native declaration : :225*
	 */
	abstract fun rangeForUserCompletion(): NSObject?
	/**
	 * *from NSCompletion native declaration : :228*<br></br>
	 * Conversion Error : /// Original signature : `NSArray* completionsForPartialWordRange(null, NSInteger*)`<br></br>
	 * - (NSArray*)completionsForPartialWordRange:(null)charRange indexOfSelectedItem:(NSInteger*)index; (Argument charRange cannot be converted)
	 */
	/**
	 * *from NSCompletion native declaration : :231*<br></br>
	 * Conversion Error : /// Original signature : `void insertCompletion(NSString*, null, NSInteger, BOOL)`<br></br>
	 * - (void)insertCompletion:(NSString*)word forPartialWordRange:(null)charRange movement:(NSInteger)movement isFinal:(BOOL)flag; (Argument charRange cannot be converted)
	 */
	/**
	 * Original signature : `NSArray* writablePasteboardTypes()`<br></br>
	 * *from NSPasteboard native declaration : :248*
	 */
	abstract fun writablePasteboardTypes(): com.sun.jna.Pointer?

	/**
	 * Original signature : `BOOL writeSelectionToPasteboard(NSPasteboard*, NSString*)`<br></br>
	 * *from NSPasteboard native declaration : :251*
	 */
	abstract fun writeSelectionToPasteboard_type(pboard: com.sun.jna.Pointer?, type: com.sun.jna.Pointer?): Boolean

	/**
	 * Original signature : `BOOL writeSelectionToPasteboard(NSPasteboard*, NSArray*)`<br></br>
	 * *from NSPasteboard native declaration : :254*
	 */
	abstract fun writeSelectionToPasteboard_types(pboard: com.sun.jna.Pointer?, types: com.sun.jna.Pointer?): Boolean

	/**
	 * Original signature : `NSArray* readablePasteboardTypes()`<br></br>
	 * *from NSPasteboard native declaration : :258*
	 */
	abstract fun readablePasteboardTypes(): NSArray?

	/**
	 * Original signature : `NSString* preferredPasteboardTypeFromArray(NSArray*, NSArray*)`<br></br>
	 * *from NSPasteboard native declaration : :261*
	 */
	abstract fun preferredPasteboardTypeFromArray_restrictedToTypesFromArray(
		availableTypes: NSArray?,
		allowedTypes: NSArray?
	): String?

	/**
	 * Original signature : `BOOL readSelectionFromPasteboard(NSPasteboard*, NSString*)`<br></br>
	 * *from NSPasteboard native declaration : :264*
	 */
	abstract fun readSelectionFromPasteboard_type(pboard: NSPasteboard?, type: String?): Boolean

	/**
	 * Original signature : `BOOL readSelectionFromPasteboard(NSPasteboard*)`<br></br>
	 * *from NSPasteboard native declaration : :267*
	 */
	abstract fun readSelectionFromPasteboard(pboard: com.sun.jna.Pointer?): Boolean

	/**
	 * Original signature : `validRequestorForSendType(NSString*, NSString*)`<br></br>
	 * *from NSPasteboard native declaration : :273*
	 */
	/**
	 * *from NSPasteboard native declaration : :276*<br></br>
	 * Conversion Error : /// Original signature : `void pasteAsPlainText(null)`<br></br>
	 * - (void)pasteAsPlainText:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *from NSPasteboard native declaration : :277*<br></br>
	 * Conversion Error : /// Original signature : `void pasteAsRichText(null)`<br></br>
	 * - (void)pasteAsRichText:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *from NSDragging native declaration : :284*<br></br>
	 * Conversion Error : /// Original signature : `BOOL dragSelectionWithEvent(NSEvent*, null, BOOL)`<br></br>
	 * - (BOOL)dragSelectionWithEvent:(NSEvent*)event offset:(null)mouseOffset slideBack:(BOOL)slideBack; (Argument mouseOffset cannot be converted)
	 */
	/**
	 * *from NSDragging native declaration : :287*<br></br>
	 * Conversion Error : /// Original signature : `NSImage* dragImageForSelectionWithEvent(NSEvent*, null)`<br></br>
	 * - (NSImage*)dragImageForSelectionWithEvent:(NSEvent*)event origin:(null)origin; (Argument origin cannot be converted)
	 */
	/**
	 * Original signature : `NSArray* acceptableDragTypes()`<br></br>
	 * *from NSDragging native declaration : :290*
	 */
	abstract fun acceptableDragTypes(): NSArray?
	/**
	 * *from NSDragging native declaration : :293*<br></br>
	 * Conversion Error : id<NSDraggingInfo>
	</NSDraggingInfo> */
	/**
	 * Original signature : `void cleanUpAfterDragOperation()`<br></br>
	 * *from NSDragging native declaration : :296*
	 */
	abstract fun cleanUpAfterDragOperation()

	/**
	 * Original signature : `NSArray* selectedRanges()`<br></br>
	 * *from NSSharing native declaration : :308*
	 */
	abstract fun selectedRanges(): NSArray?

	/**
	 * Original signature : `void setSelectedRanges(NSArray*, NSSelectionAffinity, BOOL)`<br></br>
	 * *from NSSharing native declaration : :309*
	 */
	abstract fun setSelectedRanges_affinity_stillSelecting(
		ranges: com.sun.jna.Pointer?,
		affinity: Int,
		stillSelectingFlag: Boolean
	)

	/**
	 * Original signature : `void setSelectedRanges(NSArray*)`<br></br>
	 * *from NSSharing native declaration : :310*
	 */
	abstract fun setSelectedRanges(ranges: NSArray?)
	/**
	 * *from NSSharing native declaration : :314*<br></br>
	 * Conversion Error : /// Original signature : `void setSelectedRange(null, NSSelectionAffinity, BOOL)`<br></br>
	 * - (void)setSelectedRange:(null)charRange affinity:(NSSelectionAffinity)affinity stillSelecting:(BOOL)stillSelectingFlag; (Argument charRange cannot be converted)
	 */
	/**
	 * Original signature : `NSSelectionAffinity selectionAffinity()`<br></br>
	 * *from NSSharing native declaration : :315*
	 */
	abstract fun selectionAffinity(): Int

	/**
	 * Original signature : `NSSelectionGranularity selectionGranularity()`<br></br>
	 * *from NSSharing native declaration : :316*
	 */
	abstract fun selectionGranularity(): Int

	/**
	 * Original signature : `void setSelectionGranularity(NSSelectionGranularity)`<br></br>
	 * *from NSSharing native declaration : :317*
	 */
	abstract fun setSelectionGranularity(granularity: Int)

	/**
	 * Original signature : `void setSelectedTextAttributes(NSDictionary*)`<br></br>
	 * *from NSSharing native declaration : :319*
	 */
	abstract fun setSelectedTextAttributes(attributeDictionary: NSDictionary?)

	/**
	 * Original signature : `NSDictionary* selectedTextAttributes()`<br></br>
	 * *from NSSharing native declaration : :320*
	 */
	abstract fun selectedTextAttributes(): com.sun.jna.Pointer?

	/**
	 * Original signature : `void setInsertionPointColor(NSColor*)`<br></br>
	 * *from NSSharing native declaration : :323*
	 */
	abstract fun setInsertionPointColor(color: com.sun.jna.Pointer?)

	/**
	 * Original signature : `NSColor* insertionPointColor()`<br></br>
	 * *from NSSharing native declaration : :324*
	 */
	abstract fun insertionPointColor(): com.sun.jna.Pointer?

	/**
	 * Original signature : `void updateInsertionPointStateAndRestartTimer(BOOL)`<br></br>
	 * *from NSSharing native declaration : :326*
	 */
	abstract fun updateInsertionPointStateAndRestartTimer(restartFlag: Boolean)

	/**
	 * Original signature : `void setMarkedTextAttributes(NSDictionary*)`<br></br>
	 * *from NSSharing native declaration : :328*
	 */
	abstract fun setMarkedTextAttributes(attributeDictionary: com.sun.jna.Pointer?)

	/**
	 * Original signature : `NSDictionary* markedTextAttributes()`<br></br>
	 * *from NSSharing native declaration : :329*
	 */
	abstract fun markedTextAttributes(): com.sun.jna.Pointer?

	/**
	 * Original signature : `void setLinkTextAttributes(NSDictionary*)`<br></br>
	 * *from NSSharing native declaration : :333*
	 */
	abstract fun setLinkTextAttributes(attributeDictionary: com.sun.jna.Pointer?)

	/**
	 * Original signature : `NSDictionary* linkTextAttributes()`<br></br>
	 * *from NSSharing native declaration : :334*
	 */
	abstract fun linkTextAttributes(): com.sun.jna.Pointer?

	/**
	 * Original signature : `BOOL displaysLinkToolTips()`<br></br>
	 * *from NSSharing native declaration : :339*
	 */
	abstract fun displaysLinkToolTips(): Boolean

	/**
	 * Original signature : `void setDisplaysLinkToolTips(BOOL)`<br></br>
	 * *from NSSharing native declaration : :340*
	 */
	abstract fun setDisplaysLinkToolTips(flag: Boolean)

	/**
	 * Original signature : `BOOL acceptsGlyphInfo()`<br></br>
	 * *from NSSharing native declaration : :347*
	 */
	abstract fun acceptsGlyphInfo(): Boolean

	/**
	 * Original signature : `void setAcceptsGlyphInfo(BOOL)`<br></br>
	 * *from NSSharing native declaration : :348*
	 */
	abstract fun setAcceptsGlyphInfo(flag: Boolean)

	/**
	 * Original signature : `void setRulerVisible(BOOL)`<br></br>
	 * *from NSSharing native declaration : :353*
	 */
	abstract fun setRulerVisible(flag: Boolean)

	/**
	 * Original signature : `BOOL usesRuler()`<br></br>
	 * *from NSSharing native declaration : :354*
	 */
	abstract fun usesRuler(): Boolean

	/**
	 * Original signature : `void setUsesRuler(BOOL)`<br></br>
	 * *from NSSharing native declaration : :355*
	 */
	abstract fun setUsesRuler(flag: Boolean)

	/**
	 * Original signature : `void setContinuousSpellCheckingEnabled(BOOL)`<br></br>
	 * *from NSSharing native declaration : :357*
	 */
	abstract fun setContinuousSpellCheckingEnabled(flag: Boolean)

	/**
	 * Original signature : `BOOL isContinuousSpellCheckingEnabled()`<br></br>
	 * *from NSSharing native declaration : :358*
	 */
	abstract fun isContinuousSpellCheckingEnabled(): Boolean
	/**
	 * *from NSSharing native declaration : :359*<br></br>
	 * Conversion Error : /// Original signature : `void toggleContinuousSpellChecking(null)`<br></br>
	 * - (void)toggleContinuousSpellChecking:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * Original signature : `NSInteger spellCheckerDocumentTag()`<br></br>
	 * *from NSSharing native declaration : :361*
	 */
	abstract fun spellCheckerDocumentTag(): Int

	/**
	 * Original signature : `void setGrammarCheckingEnabled(BOOL)`<br></br>
	 * *from NSSharing native declaration : :364*
	 */
	abstract fun setGrammarCheckingEnabled(flag: Boolean)

	/**
	 * Original signature : `BOOL isGrammarCheckingEnabled()`<br></br>
	 * *from NSSharing native declaration : :365*
	 */
	abstract fun isGrammarCheckingEnabled(): Boolean
	/**
	 * *from NSSharing native declaration : :366*<br></br>
	 * Conversion Error : /// Original signature : `void toggleGrammarChecking(null)`<br></br>
	 * - (void)toggleGrammarChecking:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *from NSSharing native declaration : :369*<br></br>
	 * Conversion Error : /// Original signature : `void setSpellingState(NSInteger, null)`<br></br>
	 * - (void)setSpellingState:(NSInteger)value range:(null)charRange; (Argument charRange cannot be converted)
	 */
	/**
	 * Original signature : `NSDictionary* typingAttributes()`<br></br>
	 * *from NSSharing native declaration : :373*
	 */
	abstract fun typingAttributes(): com.sun.jna.Pointer?

	/**
	 * Original signature : `void setTypingAttributes(NSDictionary*)`<br></br>
	 * *from NSSharing native declaration : :374*
	 */
	abstract fun setTypingAttributes(attrs: com.sun.jna.Pointer?)

	/**
	 * These multiple-range methods supersede the corresponding single-range methods.  For the first method, the affectedRanges argument obeys the same restrictions as the argument to setSelectedRanges:, and the replacementStrings array should either be nil (for attribute-only changes) or have the same number of elements as affectedRanges.  For the remaining three methods, the return values obey the same restrictions as that for selectedRanges, except that they will be nil if the corresponding change is not permitted, where the corresponding single-range methods return (NSNotFound, 0).<br></br>
	 * Original signature : `BOOL shouldChangeTextInRanges(NSArray*, NSArray*)`<br></br>
	 * *from NSSharing native declaration : :378*
	 */
	abstract fun shouldChangeTextInRanges_replacementStrings(
		affectedRanges: com.sun.jna.Pointer?,
		replacementStrings: com.sun.jna.Pointer?
	): Boolean

	/**
	 * Original signature : `NSArray* rangesForUserTextChange()`<br></br>
	 * *from NSSharing native declaration : :379*
	 */
	abstract fun rangesForUserTextChange(): com.sun.jna.Pointer?

	/**
	 * Original signature : `NSArray* rangesForUserCharacterAttributeChange()`<br></br>
	 * *from NSSharing native declaration : :380*
	 */
	abstract fun rangesForUserCharacterAttributeChange(): com.sun.jna.Pointer?

	/**
	 * Original signature : `NSArray* rangesForUserParagraphAttributeChange()`<br></br>
	 * *from NSSharing native declaration : :381*
	 */
	abstract fun rangesForUserParagraphAttributeChange(): com.sun.jna.Pointer?
	/**
	 * *from NSSharing native declaration : :384*<br></br>
	 * Conversion Error : /// Original signature : `BOOL shouldChangeTextInRange(null, NSString*)`<br></br>
	 * - (BOOL)shouldChangeTextInRange:(null)affectedCharRange replacementString:(NSString*)replacementString; (Argument affectedCharRange cannot be converted)
	 */
	/**
	 * Original signature : `void didChangeText()`<br></br>
	 * *from NSSharing native declaration : :385*
	 */
	abstract fun didChangeText()

	/**
	 * Original signature : `rangeForUserTextChange()`<br></br>
	 * *from NSSharing native declaration : :387*
	 */
	abstract fun rangeForUserTextChange(): NSObject?

	/**
	 * Original signature : `rangeForUserCharacterAttributeChange()`<br></br>
	 * *from NSSharing native declaration : :388*
	 */
	abstract fun rangeForUserCharacterAttributeChange(): NSObject?

	/**
	 * Original signature : `rangeForUserParagraphAttributeChange()`<br></br>
	 * *from NSSharing native declaration : :389*
	 */
	abstract fun rangeForUserParagraphAttributeChange(): NSObject?

	/**
	 * Original signature : `void setUsesFindPanel(BOOL)`<br></br>
	 * *from NSSharing native declaration : :392*
	 */
	abstract fun setUsesFindPanel(flag: Boolean)

	/**
	 * Original signature : `BOOL usesFindPanel()`<br></br>
	 * *from NSSharing native declaration : :393*
	 */
	abstract fun usesFindPanel(): Boolean

	/**
	 * Original signature : `void setAllowsDocumentBackgroundColorChange(BOOL)`<br></br>
	 * *from NSSharing native declaration : :395*
	 */
	abstract fun setAllowsDocumentBackgroundColorChange(flag: Boolean)

	/**
	 * Original signature : `BOOL allowsDocumentBackgroundColorChange()`<br></br>
	 * *from NSSharing native declaration : :396*
	 */
	abstract fun allowsDocumentBackgroundColorChange(): Boolean

	/**
	 * Original signature : `void setDefaultParagraphStyle(NSParagraphStyle*)`<br></br>
	 * *from NSSharing native declaration : :398*
	 */
	abstract fun setDefaultParagraphStyle(paragraphStyle: com.sun.jna.Pointer?)

	/**
	 * Original signature : `NSParagraphStyle* defaultParagraphStyle()`<br></br>
	 * *from NSSharing native declaration : :399*
	 */
	abstract fun defaultParagraphStyle(): com.sun.jna.Pointer?

	/**
	 * Original signature : `void setAllowsUndo(BOOL)`<br></br>
	 * *from NSSharing native declaration : :402*
	 */
	abstract fun setAllowsUndo(flag: Boolean)

	/**
	 * Original signature : `BOOL allowsUndo()`<br></br>
	 * *from NSSharing native declaration : :403*
	 */
	abstract fun allowsUndo(): Boolean

	/**
	 * Original signature : `void breakUndoCoalescing()`<br></br>
	 * *from NSSharing native declaration : :406*
	 */
	abstract fun breakUndoCoalescing()

	/**
	 * Original signature : `BOOL allowsImageEditing()`<br></br>
	 * *from NSSharing native declaration : :411*
	 */
	abstract fun allowsImageEditing(): Boolean

	/**
	 * Original signature : `void setAllowsImageEditing(BOOL)`<br></br>
	 * *from NSSharing native declaration : :412*
	 */
	abstract fun setAllowsImageEditing(flag: Boolean)
	/**
	 * *from NSSharing native declaration : :415*<br></br>
	 * Conversion Error : /// Original signature : `void showFindIndicatorForRange(null)`<br></br>
	 * - (void)showFindIndicatorForRange:(null)charRange; (Argument charRange cannot be converted)
	 */
	/**
	 * *from NSSharing native declaration : :440*<br></br>
	 * Conversion Error : /// Original signature : `void setSelectedRange(null)`<br></br>
	 * - (void)setSelectedRange:(null)charRange; (Argument charRange cannot be converted)
	 */
	/**
	 * Original signature : `BOOL smartInsertDeleteEnabled()`<br></br>
	 * *from NSSharing native declaration : :445*
	 */
	abstract fun smartInsertDeleteEnabled(): Boolean

	/**
	 * Original signature : `void setSmartInsertDeleteEnabled(BOOL)`<br></br>
	 * *from NSSharing native declaration : :446*
	 */
	abstract fun setSmartInsertDeleteEnabled(flag: Boolean)
	/**
	 * *from NSSharing native declaration : :447*<br></br>
	 * Conversion Error : /// Original signature : `smartDeleteRangeForProposedRange(null)`<br></br>
	 * - (null)smartDeleteRangeForProposedRange:(null)proposedCharRange; (Argument proposedCharRange cannot be converted)
	 */
	/**
	 * *from NSSharing native declaration : :449*<br></br>
	 * Conversion Error : /// Original signature : `void toggleSmartInsertDelete(null)`<br></br>
	 * - (void)toggleSmartInsertDelete:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * *from NSSharing native declaration : :452*<br></br>
	 * Conversion Error : /// Original signature : `void smartInsertForString(NSString*, null, NSString**, NSString**)`<br></br>
	 * - (void)smartInsertForString:(NSString*)pasteString replacingRange:(null)charRangeToReplace beforeString:(NSString**)beforeString afterString:(NSString**)afterString; (Argument charRangeToReplace cannot be converted)
	 */
	/**
	 * *from NSSharing native declaration : :453*<br></br>
	 * Conversion Error : /// Original signature : `NSString* smartInsertBeforeStringForString(NSString*, null)`<br></br>
	 * - (NSString*)smartInsertBeforeStringForString:(NSString*)pasteString replacingRange:(null)charRangeToReplace; (Argument charRangeToReplace cannot be converted)
	 */
	/**
	 * *from NSSharing native declaration : :454*<br></br>
	 * Conversion Error : /// Original signature : `NSString* smartInsertAfterStringForString(NSString*, null)`<br></br>
	 * - (NSString*)smartInsertAfterStringForString:(NSString*)pasteString replacingRange:(null)charRangeToReplace; (Argument charRangeToReplace cannot be converted)
	 */
	/**
	 * Original signature : `void setAutomaticQuoteSubstitutionEnabled(BOOL)`<br></br>
	 * *from NSSharing native declaration : :458*
	 */
	abstract fun setAutomaticQuoteSubstitutionEnabled(flag: Boolean)

	/**
	 * Original signature : `BOOL isAutomaticQuoteSubstitutionEnabled()`<br></br>
	 * *from NSSharing native declaration : :459*
	 */
	abstract fun isAutomaticQuoteSubstitutionEnabled(): Boolean
	/**
	 * *from NSSharing native declaration : :460*<br></br>
	 * Conversion Error : /// Original signature : `void toggleAutomaticQuoteSubstitution(null)`<br></br>
	 * - (void)toggleAutomaticQuoteSubstitution:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * Original signature : `void setAutomaticLinkDetectionEnabled(BOOL)`<br></br>
	 * *from NSSharing native declaration : :461*
	 */
	abstract fun setAutomaticLinkDetectionEnabled(flag: Boolean)

	/**
	 * Original signature : `BOOL isAutomaticLinkDetectionEnabled()`<br></br>
	 * *from NSSharing native declaration : :462*
	 */
	abstract fun isAutomaticLinkDetectionEnabled(): Boolean
	/**
	 * *from NSSharing native declaration : :463*<br></br>
	 * Conversion Error : /// Original signature : `void toggleAutomaticLinkDetection(null)`<br></br>
	 * - (void)toggleAutomaticLinkDetection:(null)sender; (Argument sender cannot be converted)
	 */
	/**
	 * Returns an array of locale identifiers representing input sources allowed to be enabled when the receiver has the keyboard focus.<br></br>
	 * Original signature : `NSArray* allowedInputSourceLocales()`<br></br>
	 * *from NSSharing native declaration : :470*
	 */
	abstract fun allowedInputSourceLocales(): NSArray?

	/**
	 * Original signature : `void setAllowedInputSourceLocales(NSArray*)`<br></br>
	 * *from NSSharing native declaration : :471*
	 */
	abstract fun setAllowedInputSourceLocales(localeIdentifiers: NSArray?)

	companion object {
		private val CLASS: _Class = org.rococoa.Rococoa.createClass("NSTextView", _Class::class.java)

		fun create(): NSTextView? {
			return CLASS.alloc().init()
		}
	}
}

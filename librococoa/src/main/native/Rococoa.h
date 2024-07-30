#import <Cocoa/Cocoa.h>
#include <objc/objc-runtime.h>

void callOnMainThread(void (*fn)(void), BOOL waitUntilDone);

@interface RococoaHelper : NSObject
+ (void) callback: (NSValue*) fn;
@end



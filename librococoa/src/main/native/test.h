//
//  test.h
//  rococoa
//
//  Created by Duncan McGregor on 03/12/2007.
//  Copyright 2007 __MyCompanyName__. All rights reserved.
//

#import <Cocoa/Cocoa.h>

typedef struct TestIntDoubleStruct {
	int anInt;
	double aDouble;
} TestIntDoubleStruct;

typedef struct TestStructOfStruct {
	double aDouble;
	TestIntDoubleStruct aStruct;
} TestStructOfStruct;

typedef struct TestFloatFloatStruct {
	float a;
	float b;
} TestFloatFloatStruct;

typedef struct TestIntFloatStruct {
	int a;
	float b;
} TestIntFloatStruct;

typedef struct TestIntIntStruct {
	int a;
	int b;
} TestIntIntStruct;

typedef struct TestIntLongStruct {
	int a;
	int64_t b;
} TestIntLongStruct;

TestIntDoubleStruct createIntDoubleStruct(int a, double b);

double addFieldsOfStructByValue(TestIntDoubleStruct s);

@interface TestShunt : NSObject

- (TestIntDoubleStruct) testReturnStructByValue: (int) a and: (double) b;

- (double) testAddFieldsOfStructByValue: (TestIntDoubleStruct) s;

- (TestStructOfStruct) testReturnStructOfStructByValue: (int) a and: (double) b;

- (double) testPassStructOfStructByValue: (TestStructOfStruct) s;

- (void) testNSNumberByReference: (NSNumber**) fillMeIn with: (int) aValue;

- (BOOL) valueIsYES:(BOOL) a;

- (BOOL) valueIsNO:(BOOL) a;
	
- (bool) isMainThread;



@end

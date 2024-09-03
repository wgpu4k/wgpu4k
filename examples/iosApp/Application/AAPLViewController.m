/*
See LICENSE folder for this sample’s licensing information.

Abstract:
Implementation of our cross-platform view controller
*/

#import "AAPLViewController.h"
#import <WgpuApp/WgpuApp.h>

@implementation AAPLViewController

- (void)viewDidLoad
{
    [super viewDidLoad];

    [WgpuAppMainKt configureApplicationView:(MTKView *)self.view completionHandler:^(NSError * _Nullable error) {
        if (error) {
            NSLog(@"Error : %@", error.localizedDescription);
        } else {
            NSLog(@"Success");
        }
    }];
    
}

@end

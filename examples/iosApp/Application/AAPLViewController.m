/*
See LICENSE folder for this sample’s licensing information.

Abstract:
Implementation of our cross-platform view controller
*/

#import "AAPLViewController.h"
#import <WgpuApp/WgpuApp.h>

@implementation AAPLViewController
{
    MTKView *_view;

}

- (void)viewDidLoad
{
    [super viewDidLoad];

    // Set the view to use the default device
    _view = (MTKView *)self.view;
    
    //Need to build rust library to iOs
    [WgpuAppMainKt configureApplicationView:_view completionHandler:^(NSError * _Nullable error) {
        if (error) {
            NSLog(@"Error : %@", error.localizedDescription);
        } else {
            NSLog(@"Success");
        }
    }];
    
}

@end

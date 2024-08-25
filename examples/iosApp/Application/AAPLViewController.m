/*
See LICENSE folder for this sample’s licensing information.

Abstract:
Implementation of our cross-platform view controller
*/

#import "AAPLViewController.h"
#import "AAPLRenderer.h"
#import <WgpuApp/WgpuApp.h>

@implementation AAPLViewController
{
    MTKView *_view;

    AAPLRenderer *_renderer;
}

- (void)viewDidLoad
{
    [super viewDidLoad];

    // Set the view to use the default device
    _view = (MTKView *)self.view;
    
    //Need to build rust library to iOs
    [WgpuAppMainKt initwgpuMetalLayer:(__bridge void *)_view.layer completionHandler:^(WgpuAppCommonApplication *  _Nullable application, NSError * _Nullable error) {
        if (error) {
            NSLog(@"Erreur : %@", error.localizedDescription);
        } else {
            NSLog(@"Action réussie");
            dispatch_async(dispatch_get_main_queue(), ^{
                [application renderFrameWithCompletionHandler:^(NSError * _Nullable error) {
                    if (error) {
                        NSLog(@"Erreur : %@", error.localizedDescription);
                    } else {
                        NSLog(@"Action réussie");
                    }
                }];
            });
        }
    }];
    
    /*_view.device = MTLCreateSystemDefaultDevice();
    
    NSAssert(_view.device, @"Metal is not supported on this device");
    
    _renderer = [[AAPLRenderer alloc] initWithMetalKitView:_view];
    
    NSAssert(_renderer, @"Renderer failed initialization");

    // Initialize our renderer with the view size
    [_renderer mtkView:_view drawableSizeWillChange:_view.drawableSize];

    _view.delegate = _renderer;*/
}

@end

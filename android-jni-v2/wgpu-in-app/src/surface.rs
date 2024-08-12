use jni::objects::JClass;
use jni::sys::{jint, jlong};
use jni::JNIEnv;
use jni_fn::jni_fn;
use std::ptr::{null, null_mut};
use wgpu_native::native;

#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.internal.JniInterface")]
pub unsafe fn wgpuSurfaceGetFormat(_: JNIEnv, _: JClass, surface: jlong, adapter: jlong) -> jint {
    let surface = surface as *const native::WGPUSurface;
    let adapter = adapter as *const native::WGPUAdapter;

    let mut capabilities = native::WGPUSurfaceCapabilities {
        nextInChain: null_mut(),
        usages: 0,
        formatCount: 0,
        formats: null(),
        presentModeCount: 0,
        presentModes: null(),
        alphaModeCount: 0,
        alphaModes: null(),
    };

    wgpu_native::wgpuSurfaceGetCapabilities(*surface, *adapter, Some(&mut capabilities));


    if capabilities.formatCount == 0 {
        0
    } else {
        *(capabilities.formats) as i32
    }
}


#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.internal.JniInterface")]
pub unsafe fn wgpuSurfaceConfigure(
    _: JNIEnv,
    _: JClass,
    surface: jlong,
    device: jlong,
    usages: jint,
    format: jint,
    alphaMode: jint,
    width: jint,
    height: jint
) {
    let surface = surface as *const native::WGPUSurface;
    let device = device as *const native::WGPUDevice;

    let canvas_configuration = native::WGPUSurfaceConfiguration {
        nextInChain: null(),
        device: *device,
        format: format as u32,
        usage: usages as u32,
        viewFormatCount: 0,
        viewFormats: null(),
        alphaMode: alphaMode as u32,
        width: width as u32,
        height: height as u32,
        presentMode: native::WGPUPresentMode_Fifo,
    };

    wgpu_native::wgpuSurfaceConfigure(*surface, Some(&canvas_configuration));

}
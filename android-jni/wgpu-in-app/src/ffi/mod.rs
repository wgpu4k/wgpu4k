use android_logger::Config;
use jni::JNIEnv;
use jni::objects::JClass;
use jni::sys::{jint, jlong, jobject};
use jni_fn::jni_fn;
use log::{info, LevelFilter};

use app_surface::AppSurface;

use crate::wgpu_canvas::WgpuCanvas;

#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.RustBridge")]
pub fn createWgpuInstance(_: *mut JNIEnv, _: JClass) -> jlong {
    log_panics::init();
    android_logger::init_once(Config::default().with_max_level(LevelFilter::Error));
    let backends = wgpu::Backends::VULKAN;
    let instance = wgpu::Instance::new(wgpu::InstanceDescriptor {
        backends,
        ..Default::default()
    });

    info!("WgpuCanvas instance!");
    Box::into_raw(Box::new(instance)) as jlong
}

#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.RustBridge")]
pub fn createWgpuCanvas(env: *mut JNIEnv, _: JClass, wgpu: jlong, surface: jobject, idx: jint) -> jlong {
    let wgpu = unsafe { wgpu as *const wgpu::Instance };
    log_panics::init();
    android_logger::init_once(Config::default().with_max_level(LevelFilter::Error));
    let canvas = WgpuCanvas::new(AppSurface::new(wgpu, env as *mut _, surface), idx);
    info!("WgpuCanvas created!");
    Box::into_raw(Box::new(canvas)) as jlong
}

#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.RustBridge")]
pub fn enterFrame(_env: *mut JNIEnv, _: JClass, obj: jlong) {
    let obj = unsafe { &mut *(obj as *mut WgpuCanvas) };
    obj.enter_frame();
}

#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.RustBridge")]
pub fn changeExample(_env: *mut JNIEnv, _: JClass, obj: jlong, idx: jint) {
    // 获取到指针指代的 Rust 对象的可变借用
    let obj = unsafe { &mut *(obj as *mut WgpuCanvas) };
    obj.change_example(idx);
}

#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.RustBridge")]
pub fn dropWgpuCanvas(_env: *mut JNIEnv, _: JClass, obj: jlong) {
    let _obj: Box<WgpuCanvas> = unsafe { Box::from_raw(obj as *mut _) };
}

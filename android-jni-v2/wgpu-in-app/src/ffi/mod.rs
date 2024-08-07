use android_logger::Config;
use jni::JNIEnv;
use jni::objects::JClass;
use jni::sys::{jint, jlong, jobject};
use jni_fn::jni_fn;
use log::{info, LevelFilter};

use wgpu_native::wgpuCreateInstance;

#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.RustBridge")]
pub fn createWgpuInstance2(_: *mut JNIEnv, _: JClass) -> jlong {
    log_panics::init();
    android_logger::init_once(Config::default().with_max_level(LevelFilter::Error));

    let instance = unsafe { wgpuCreateInstance(None); };

    info!("WgpuCanvas instance!");
    Box::into_raw(Box::new(instance)) as jlong
}

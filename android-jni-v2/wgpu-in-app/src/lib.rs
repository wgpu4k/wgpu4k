use android_logger::Config;
use jni::objects::{JClass, JObject};
use jni::sys::jlong;
use jni::JNIEnv;
use jni_fn::jni_fn;
use log::{info, LevelFilter};
use std::ptr::null;

use wgpu_native::{native, wgpuCreateInstance};

#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.internal.JniInterface")]
pub unsafe fn wgpuCreateInstance(mut env: JNIEnv, _: JClass, backendHolder: JObject) -> jlong {
    log_panics::init();
    android_logger::init_once(Config::default().with_max_level(LevelFilter::Error));

    let instance = if backendHolder.is_null() {
        wgpuCreateInstance(None)
    } else {
        let backend = env.call_method(backendHolder, "getValue", "()I", &[])
            .unwrap().i().unwrap() as u32;

        let next_in_chain = native::WGPUInstanceExtras {
            chain: native::WGPUChainedStruct {
                next: null(),
                sType: native::WGPUSType_InstanceExtras,
            },
            backends: backend,
            flags: 0,
            dx12ShaderCompiler: 0,
            gles3MinorVersion: 0,
            dxilPath: null(),
            dxcPath: null(),
        };

        let descriptor = native::WGPUInstanceDescriptor {
            nextInChain: &next_in_chain as *const _ as *const _,
        };

        wgpuCreateInstance(Some(&descriptor))
    };

    info!("WgpuCanvas instance!");
    Box::into_raw(Box::new(instance)) as jlong
}


#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.internal.JniInterface")]
pub unsafe fn wgpuInstanceRelease(_: JNIEnv, _: JClass, wgpu: jlong) {
    let wgpu = wgpu as *const native::WGPUInstance;
    wgpu_native::wgpuInstanceRelease(*wgpu)
}
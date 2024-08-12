use android_logger::Config;
use jni::objects::{JClass, JObject};
use jni::sys::{jlong, jobject};
use jni::JNIEnv;
use jni_fn::jni_fn;
use log::{error, LevelFilter};
use std::mem::MaybeUninit;
use std::ptr::null;

use wgpu_native::native::{WGPUChainedStruct, WGPUSType_SurfaceDescriptorFromAndroidNativeWindow};
use wgpu_native::{native, wgpuCreateInstance};

#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.internal.JniInterface")]
pub unsafe fn wgpuCreateInstance(mut env: JNIEnv, _: JClass, backendHolder: JObject) -> jlong {
    log_panics::init();
    android_logger::init_once(Config::default().with_max_level(LevelFilter::Error));

    let instance = if backendHolder.is_null() {
        error!("backendHolder is null!");
        wgpuCreateInstance(None)
    } else {
        let backend = env.call_method(backendHolder, "getValue", "()I", &[])
            .unwrap().i().unwrap();

        error!("backendHolder value is {}", backend);

        let next_in_chain = native::WGPUInstanceExtras {
            chain: native::WGPUChainedStruct {
                next: null(),
                sType: native::WGPUSType_InstanceExtras,
            },
            backends: backend as u32,
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

    error!("WgpuCanvas instance!");
    Box::into_raw(Box::new(instance)) as jlong
}


#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.internal.JniInterface")]
pub unsafe fn wgpuInstanceRelease(_: JNIEnv, _: JClass, wgpu: jlong) {
    let wgpu = wgpu as *const native::WGPUInstance;
    wgpu_native::wgpuInstanceRelease(*wgpu)
}

#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.internal.JniInterface")]
pub unsafe fn wgpuInstanceCreateSurface(env: *mut JNIEnv, _: JClass, wgpu: jlong, surface: jobject) -> jlong {
    let wgpu = wgpu as *const native::WGPUInstance;
    let native_window = ndk_sys::ANativeWindow_fromSurface(env as *mut _, surface);

    let next_in_chain = native::WGPUSurfaceDescriptorFromAndroidNativeWindow {
        chain: WGPUChainedStruct {
            next: null(),
            sType: WGPUSType_SurfaceDescriptorFromAndroidNativeWindow
        },
        window: native_window as *mut _,
    };

    let descriptor = native::WGPUSurfaceDescriptor {
        nextInChain: &next_in_chain as *const _ as *const _,
        label: null(),
    };

    let surface = wgpu_native::wgpuInstanceCreateSurface(*wgpu, Some(&descriptor));
    Box::into_raw(Box::new(surface)) as jlong
}

#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.internal.JniInterface")]
pub unsafe fn wgpuInstanceRequestAdapter(mut env: JNIEnv, _: JClass, wgpu: jlong, powerPreference: JObject) -> jlong {
    let wgpu = wgpu as *const native::WGPUInstance;

    let power_preference =  if powerPreference.is_null() {
        0
    } else {
        env.call_method(powerPreference, "getValue", "()I", &[])
            .unwrap().i().unwrap() as u32
    };

    let options = native::WGPURequestAdapterOptions {
        nextInChain: null(),
        compatibleSurface: null(),
        powerPreference : power_preference,
        backendType: 0,
        forceFallbackAdapter: 0,
    };

    let mut adapter : MaybeUninit<native::WGPUAdapter> = MaybeUninit::uninit();
    let adapter_ptr: *mut std::os::raw::c_void = adapter.as_mut_ptr() as *mut _;

    wgpu_native::wgpuInstanceRequestAdapter(*wgpu, Some(&options), Some(my_adapter_callback), adapter_ptr);

    Box::into_raw(Box::new(adapter)) as jlong
}

unsafe extern "C" fn my_adapter_callback(
    status: native::WGPURequestAdapterStatus,
    adapter: native::WGPUAdapter,
    message: *const ::std::os::raw::c_char,
    userdata: *mut ::std::os::raw::c_void,
) {
    if status != native::WGPURequestAdapterStatus_Success {
        panic!("my_adapter_callback with status error and message {}", crate::c_char_ptr_to_string(message));
    }

    let userdata = userdata as *mut native::WGPUAdapter;
    *userdata = adapter;
}
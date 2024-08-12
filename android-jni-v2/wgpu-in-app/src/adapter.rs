use jni::objects::{JClass, JObject};
use jni::sys::jlong;
use jni::JNIEnv;
use jni_fn::jni_fn;
use log::error;
use std::ffi::CStr;
use std::mem::MaybeUninit;
use wgpu_native::native;

#[no_mangle]
#[jni_fn("io.ygdrasil.wgpu.internal.JniInterface")]
pub unsafe fn wgpuAdapterRequestDevice(_: JNIEnv, _: JClass, adapter: jlong, _: JObject) -> jlong {
    let adapter = adapter as *const native::WGPUAdapter;

    let mut device : MaybeUninit<native::WGPUDevice> = MaybeUninit::uninit();
    let device_ptr: *mut std::os::raw::c_void = device.as_mut_ptr() as *mut _;

    wgpu_native::wgpuAdapterRequestDevice(*adapter, None, Some(my_device_callback), device_ptr);

    Box::into_raw(Box::new(device)) as jlong
}

unsafe extern "C" fn my_device_callback(
    status: native::WGPURequestDeviceStatus,
    device: native::WGPUDevice,
    message: *const std::os::raw::c_char,
    userdata: *mut std::os::raw::c_void,
) {
    if status == native::WGPURequestDeviceStatus_Error {
        panic!("my_device_callback with status error and message {}", crate::c_char_ptr_to_string(message));
    }
    error!("my_device_callback with status {}", status);
    let userdata = userdata as *mut native::WGPUDevice;
    *userdata = device;
}


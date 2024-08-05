use std::convert::TryFrom;

use jni::JNIEnv;
use jni::objects::JObject;

#[allow(dead_code)]
mod lib_example;

pub static FILES_PATH: &str = "/data/user/0/com.example.android/files/";

// Return a i32 from another i32
#[no_mangle]
pub unsafe extern "C" fn Java_com_example_android_MainActivity_returni32(
    _env: JNIEnv,
    _: JObject,
    int: i32,
) -> i32 {
    lib_example::int_times_2(int)
}

// Return a bool (true when a specific file in our folder exists)
#[no_mangle]
pub unsafe extern "C" fn Java_com_example_android_MainActivity_getFileStatus(
    _env: JNIEnv,
    _: JObject,
) -> bool {
    lib_example::get_file_status(&FILES_PATH)
}

use std::ffi::CStr;

mod instance;
mod adapter;
mod surface;

fn c_char_ptr_to_string(c_char_ptr: *const std::os::raw::c_char) -> String {
    // Créez un CStr à partir du pointeur
    let c_str = unsafe { CStr::from_ptr(c_char_ptr) };
    // Convertissez le CStr en &str
    let str_slice = c_str.to_str().ok().unwrap();
    // Convertissez le &str en String
    str_slice.to_owned()
}
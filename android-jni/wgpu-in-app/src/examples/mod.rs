use app_surface::AppSurface;
pub use boids::Boids;
pub use cube::Cube;
pub use hdr_image_view::HDRImageView;
pub use msaa_line::MSAALine;
pub use shadow::Shadow;
pub use water::Water;

#[allow(dead_code)]
pub trait Example {
    fn resize(&mut self, _app_surface: &AppSurface) {}
    fn enter_frame(&mut self, app_surface: &AppSurface);
}

pub struct Empty;
impl Empty {
    pub fn new(_app_surface: &AppSurface) -> Self {
        Empty {}
    }
}
impl Example for Empty {
    fn enter_frame(&mut self, _app_surface: &AppSurface) {}
}

mod boids;
mod msaa_line;
mod cube;
mod point_gen;
mod water;
mod shadow;
mod hdr_image_view;

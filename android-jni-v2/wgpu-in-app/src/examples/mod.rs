use app_surface::AppSurface;

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
pub use boids::Boids;

mod msaa_line;
pub use msaa_line::MSAALine;

mod cube;
pub use cube::Cube;

mod point_gen;
mod water;
pub use water::Water;

mod shadow;
pub use shadow::Shadow;

mod hdr_image_view;
pub use hdr_image_view::HDRImageView;

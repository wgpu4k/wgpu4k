import {io} from "../../out/kotlin-libs/wgpu-webgpu-samples-ts";
import RotatingCubeScene = io.ygdrasil.wgpu.examples.scenes.basic.RotatingCubeScene;
import jsApplication = io.ygdrasil.wgpu.examples.jsApplication;

const canvas = document.querySelector('canvas') as HTMLCanvasElement;
const application = await jsApplication(canvas)
application.changeScene(new RotatingCubeScene())
application.run()

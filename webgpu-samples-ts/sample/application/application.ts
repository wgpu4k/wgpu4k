import {io} from "../../out/kotlin-libs/wgpu-webgpu-samples-ts";
import Application = io.ygdrasil.wgpu.examples.Application;
import RenderingContext = io.ygdrasil.wgpu.RenderingContext;
import Device = io.ygdrasil.wgpu.Device;

export async function fromCanvas(canvas: HTMLCanvasElement): Promise<TSApplication> {
    const adapter = await navigator.gpu.requestAdapter();
    const device = await adapter.requestDevice();
    const context = canvas.getContext('webgpu') as GPUCanvasContext;

    return new TSApplication(new RenderingContext(context), new Device(device));
}

export class TSApplication extends Application {

    constructor(renderingContext: io.ygdrasil.wgpu.RenderingContext, device: io.ygdrasil.wgpu.Device) {
        super(renderingContext, device, undefined);

    }

    run() {

    }
}
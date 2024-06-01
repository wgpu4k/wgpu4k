import {mat4Multiply, TransformKt} from "../../spookyball.js";


export class Transform {
    actual

    constructor(options = {}) {
        this.actual = new TransformKt(options)
    }

    get position() {
        this.#makeDirty();
        return this.actual.position;
    }

    set position(value) {
        this.#makeDirty();
        this.actual.position.set(value);
    }

    getWorldPosition(out, position) {
        this.actual.getWorldPosition(out, position);
    }

    get orientation() {
        this.#makeDirty();
        return this.actual.orientation;
    }

    set orientation(value) {
        this.#makeDirty();
        this.actual.orientation.set(value);
    }

    get scale() {
        this.#makeDirty();
        return this.actual.scale;
    }

    set scale(value) {
        this.#makeDirty();
        this.actual.scale.set(value);
    }

    get worldMatrix() {
        if (this.actual.worldMatrixDirty) {
            if (!this.parent) {
                this.actual.worldMatrix.set(this.actual.resolveLocalMatrix());
            } else {
                mat4Multiply(this.actual.worldMatrix, this.parent.worldMatrix, this.actual.resolveLocalMatrix());
            }
            this.actual.worldMatrixDirty = false;
        }

        return this.actual.worldMatrix;
    }

    addChild(transform) {
        if (transform.parent && transform.parent != this) {
            transform.parent.removeChild(transform);
        }

        if (!this.actual.children) {
            this.actual.children = new Set();
        }
        this.actual.children.add(transform);
        transform.actual.parent = this;
        transform.#makeDirty(false);
    }

    removeChild(transform) {
        const removed = this.actual.children?.delete(transform);
        if (removed) {
            transform.actual.parent = null;
            transform.#makeDirty(false);
        }
    }

    get children() {
        return this.actual.children?.values() || [];
    }

    get parent() {
        return this.actual.parent;
    }

    makeDirtyJs(markLocalDirty = true) {
        this.#makeDirty(markLocalDirty)
    }

    #makeDirty(markLocalDirty = true) {
        this.actual.makeDirty(markLocalDirty)
    }

}

export class TransformPool {
    #buffer;
    #transforms = [];

    constructor(size) {
        this.#buffer = new Float32Array(42 * size).buffer;

        for (let i = 0; i < size; ++i) {
            this.#transforms[i] = new Transform({
                externalStorage: {
                    buffer: this.#buffer,
                    offset: (i * 42 * Float32Array.BYTES_PER_ELEMENT),
                }
            });
        }
    }

    get size() {
        return this.#transforms.length;
    }

    getTransform(index) {
        return this.#transforms[index];
    }

    clone() {
        const out = new TransformPool(this.size);
        // Copy the entire buffer from this pool to the new one.
        new Float32Array(out.#buffer).set(new Float32Array(this.#buffer));
        return out;
    }
}

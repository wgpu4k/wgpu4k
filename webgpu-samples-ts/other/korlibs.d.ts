export declare namespace korlibs.math.geom {
    class Matrix4 {
        constructor();

        static get Companion(): {
            get dummy(): string;
        };
    }

    class Angle {

        get radians(): Number;

        static get Companion(): {
            fromRadians(radians: Number): Angle
        }
    }
}

export as namespace korlibs_math_geom;
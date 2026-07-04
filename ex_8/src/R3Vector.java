public class R3Vector {
    public final double x, y, z;

    public R3Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public R3Vector(int x, int y, int z) {
        this((double) x, (double) y, (double) z);
    }

    public R3Vector(float x, float y, float z) {
        this((double) x, (double) y, (double) z);
    }

    public R3Vector(long x, long y, long z) {
        this((double) x, (double) y, (double) z);
    }
    
    public static R3Vector add(R3Vector v1, R3Vector v2) {
        return v1.add(v2);
    }

    public R3Vector add(R3Vector other) {
        return new R3Vector(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public static R3Vector multiply(R3Vector v, double scalar) {
        return v.multiply(scalar);
    }

    public R3Vector multiply(double scalar) {
        return new R3Vector(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public static double dot(R3Vector v1, R3Vector v2) {
        return v1.dot(v2);
    }

    public double dot(R3Vector other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public static R3Vector cross(R3Vector v1, R3Vector v2) {
        return v1.cross(v2);
    }

    public R3Vector cross(R3Vector other) {
        return new R3Vector(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }

    public static boolean equals(R3Vector v1, R3Vector v2) {
        return v1.equals(v2);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof R3Vector)) return false;
        R3Vector other = (R3Vector) obj;
        return Double.compare(this.x, other.x) == 0 &&
               Double.compare(this.y, other.y) == 0 &&
               Double.compare(this.z, other.z) == 0;
    }
}
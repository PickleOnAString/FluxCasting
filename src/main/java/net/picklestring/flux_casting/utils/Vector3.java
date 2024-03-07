package net.picklestring.flux_casting.utils;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class Vector3 {
	private double x;
	private double y;
	private double z;

	public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

	public double getX() {
        return x;
    }

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public Vector3 setX(double value) {
		this.x = value;
        return this;
	}

	public Vector3 setY(double value) {
		this.y = value;
        return this;
	}

	public Vector3 setZ(double value) {
		this.z = value;
        return this;
	}

	public Vec3i VectorToVec3i()
	{
		return new Vec3i((int) x, (int) y, (int) z);
	}

	public static Vector3 Vec3iToVector3(Vec3i vec) {
		return new Vector3(vec.getX(), vec.getY(), vec.getZ());
	}

	public Vec3d VectorToVec3d()
	{
		return new Vec3d(x, y, z);
	}

	public static Vector3 Vec3dToVector3(Vec3d vec) {
		return new Vector3(vec.getX(), vec.getY(), vec.getZ());
	}

	public double getMagnitude()
	{
		// Stores the sum of squares
		// of coordinates of a vector
		double sum = this.x * this.x + this.y * this.y + this.z * this.z;

		// Return the magnitude
		return Math.sqrt(sum);
	}
}

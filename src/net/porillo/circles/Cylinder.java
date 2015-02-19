package net.porillo.circles;

//******************************************************************************
//   Cylinder.java      Nick Porillo
//
//   Solution to Programming Project 8.6
//   - Provides surface area and volume functions
//   - Handles textual data output 
//******************************************************************************

import static java.lang.Math.pow;
import static java.lang.Math.PI;

public class Cylinder extends Circle {

	private double height;

	// ---------------------------------------------------------------------------
	// Sets up the cylinder by entering its radius and height.
	// ---------------------------------------------------------------------------
	public Cylinder(double radius, double height) {
		super(radius);
		this.height = height;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the surface area.
	// ---------------------------------------------------------------------------
	public double computeSurfaceArea() {
		// 2 (pi * radius * height + pi * radius^2)
		double area = 2 * (PI * getRadius() * this.height + PI
				* pow(getRadius(), 2));
		return area;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the volume.
	// ---------------------------------------------------------------------------
	public double computeVolume() {
		return Math.PI * Math.pow(getRadius(), 2) * this.height;
	}

	// ---------------------------------------------------------------------------
	// Returns the double value of the height.
	// ---------------------------------------------------------------------------
	public double getHeight() {
		return this.height;
	}

	// ---------------------------------------------------------------------------
	// Returns pertinent information about the cylinder.
	// ---------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Cylinder: " + "\nSurface area is "
				+ form.format(computeSurfaceArea()) + ", volume is "
				+ form.format(computeVolume()) + "\nCircumference is "
				+ form.format(computePerimeter()) + "\n";
	}
}

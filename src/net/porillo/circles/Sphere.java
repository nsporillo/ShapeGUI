package net.porillo.circles;

//******************************************************************************
//  Sphere.java      Nick Porillo
//
//  Solution to Programming Project 8.6
//
//  - Sphere is a subclass of Circle, which is of Shape
//  - Provides surface area and volume functions
//  - Prints textual data output 
//******************************************************************************

import static java.lang.Math.pow;
import static java.lang.Math.PI;

public class Sphere extends Circle {

	public Sphere(double radius) {
		super(radius);
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the surface area.
	// ---------------------------------------------------------------------------
	public double computeSurfaceArea() {
		return 4 * PI * pow(getRadius(), 2);
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the volume.
	// ---------------------------------------------------------------------------
	public double computeVolume() {
		return (4 / 3) * PI * pow(getRadius(), 3);
	}

	// ---------------------------------------------------------------------------
	// Returns pertinent information about the sphere.
	// ---------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Sphere: " + "\nSurface area is "
				+ form.format(computeSurfaceArea()) + "\nVolume is "
				+ form.format(computeVolume()) + ", circumference is "
				+ form.format(computePerimeter()) + "\n";
	}
}

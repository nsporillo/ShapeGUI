package net.porillo.triangles;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
//******************************************************************************
//  Tetrahedron.java       Java Foundations
//
//  Solution to Programming Project 8.6
//
//  - Handles volume, area, height,..., functions 
//******************************************************************************

public class Tetrahedron extends Triangle {
	private double height;

	// ---------------------------------------------------------------------------
	// Sets up the pyramid by entering its base side length and height.
	// ---------------------------------------------------------------------------
	public Tetrahedron(double sid, double hei) {
		super(sid);
		this.height = hei;
	}

	// ---------------------------------------------------------------------------
	// Returns the double value of the height.
	// ---------------------------------------------------------------------------
	public double getPyramidHeight() {
		return this.height;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of a face height.
	// ---------------------------------------------------------------------------
	public double faceHeight() {
		return sqrt(pow(this.height, 2) + pow(getHeight() / 2, 2));
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of a face area.
	// ---------------------------------------------------------------------------
	public double faceArea() {
		return faceHeight() * this.side / 2;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the surface area.
	// ---------------------------------------------------------------------------
	@Override
	public double computeArea() {
		return 3 * faceArea() + super.computeArea();
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the volume.
	// ---------------------------------------------------------------------------
	public double computeVolume() {
		return super.computeArea() * this.height / 3;
	}

	// ---------------------------------------------------------------------------
	// Returns pertinent information about the pyramid.
	// ---------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Triangle Pyramid: " + "\nHeight is " + form.format(this.height)
				+ ", side is " + form.format(this.side)
				+ "\nPerimeter of base is " + form.format(computePerimeter())
				+ ", area is " + form.format(computeArea()) + "\nVolume is "
				+ form.format(computeVolume()) + "\n";
	}
}
package net.porillo.rectangles;

//******************************************************************************
//  Pentahedron.java        Nick Porillo
//
//  Solution to Programming Project 8.6
//
//  - Handles volume, area, height,..., functions
//******************************************************************************

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Pentahedron extends Rectangle {
	private double height;

	// ---------------------------------------------------------------------------
	// Sets up the pyramid by entering its width, height and length.
	// ---------------------------------------------------------------------------
	public Pentahedron(double wid, double len, double hei) {
		super(wid, len);
		this.height = hei;
	}

	// ---------------------------------------------------------------------------
	// Returns the double value of the height.
	// ---------------------------------------------------------------------------
	public double getHeight() {
		return this.height;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the long face height.
	// ---------------------------------------------------------------------------
	public double lengthFaceHeight() {
		return sqrt(pow(this.height, 2) + pow(this.width / 2, 2));
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the wide face height.
	// ---------------------------------------------------------------------------
	public double widthFaceHeight() {
		return sqrt(pow(this.height, 2) + pow(this.length / 2, 2));
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the long face area.
	// ---------------------------------------------------------------------------
	public double lengthFaceArea() {
		return lengthFaceHeight() * this.length / 2;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the wide face area.
	// ---------------------------------------------------------------------------
	public double widthFaceArea() {
		return widthFaceHeight() * this.width / 2;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the surface area.
	// ---------------------------------------------------------------------------
	public double computeArea() {
		return 2 * lengthFaceArea() + 2 * widthFaceArea() + super.computeArea();
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
	public String toString() {
		return "Square Pyramid: \nWidth is " + form.format(this.width)
				+ ", length is " + form.format(this.length) + ", height is "
				+ form.format(this.height) + "\nPerimeter of base is "
				+ form.format(computePerimeter()) + ", volume is "
				+ form.format(computeVolume()) + "\nSurface area is "
				+ form.format(computeArea()) + "\n";
	}
}
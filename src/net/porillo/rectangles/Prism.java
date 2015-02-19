package net.porillo.rectangles;

//******************************************************************************
//  Prism.java       Nick Porillo
//
//  Solution to Programming Project 8.6
//
//  - Handles volume, area, height,..., functions
//******************************************************************************

public class Prism extends Rectangle {
	private double height;

	// ---------------------------------------------------------------------------
	// Sets up the prism by entering its width, height and length.
	// ---------------------------------------------------------------------------
	public Prism(double wid, double len, double hei) {
		super(wid, len);
		this.height = hei;
	}

	// ---------------------------------------------------------------------------
	// Returns the double value of the length.
	// ---------------------------------------------------------------------------
	public double getHeight() {
		return this.height;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the surface area.
	// ---------------------------------------------------------------------------
	public double computeSurfaceArea() {
		return 2 * super.computeArea() + 2 * this.width * this.height + 2
				* this.length * this.height;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the volume.
	// ---------------------------------------------------------------------------
	public double computeVolume() {
		return super.computeArea() * this.height;
	}

	// ---------------------------------------------------------------------------
	// Returns pertinent information about the prism.
	// ---------------------------------------------------------------------------
	public String toString() {
		return "Prism: \n" + "Width is " + form.format(this.width)
				+ ", length is " + form.format(this.length) + ", height is "
				+ form.format(this.height) + "\nPerimeter of base is "
				+ form.format(computePerimeter()) + ", area is "
				+ form.format(computeArea()) + "\nVolume is "
				+ form.format(computeVolume()) + "\n";
	}
}
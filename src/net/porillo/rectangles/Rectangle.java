package net.porillo.rectangles;

//******************************************************************************
//  Rectangle.java       Java Foundations
//
//  Solution to Programming Project 8.6
//
//  - Parent class to @Prism and @Pentahedron
//  - Handles basic shape area & volume functions
//******************************************************************************

import net.porillo.Shape;

public class Rectangle extends Shape {

	protected double width;
	protected double length;

	// ---------------------------------------------------------------------------
	// Sets up the rectangle by entering its width and length.
	// ---------------------------------------------------------------------------
	public Rectangle(double wid, double len) {
		this.width = wid;
		this.length = len;
	}

	// ---------------------------------------------------------------------------
	// Returns the double value of the width.
	// ---------------------------------------------------------------------------
	public double getWidth() {
		return this.width;
	}

	// ---------------------------------------------------------------------------
	// Returns the double value of the length.
	// ---------------------------------------------------------------------------
	public double getLength() {
		return this.length;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the area.
	// ---------------------------------------------------------------------------
	@Override
	public double computeArea() {
		return this.length * this.width;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the perimeter.
	// ---------------------------------------------------------------------------
	@Override
	public double computePerimeter() {
		return 2 * (this.length + this.width);
	}

	// ---------------------------------------------------------------------------
	// Returns pertinent information about the rectangle.
	// ---------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Rectangle: \nWidth is " + form.format(this.width)
				+ ", length is " + form.format(this.length) + "\nPerimeter is "
				+ form.format(computePerimeter()) + ", area is "
				+ form.format(computeArea());
	}
}

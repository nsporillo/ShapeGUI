package net.porillo.circles;

//******************************************************************************
//  Circle.java      Nick Porillo
//
//  Solution to Programming Project 8.6
//
//  - Circle is the parent to @Cylinder and @Sphere
//  - Circle implements shape compute functions 
//  - Prints textual data output 
//******************************************************************************

import net.porillo.Shape;

public class Circle extends Shape {

	private double radius;

	// ---------------------------------------------------------------------------
	// Sets up the circle by entering its radius.
	// ---------------------------------------------------------------------------
	public Circle(double radius) {
		this.radius = radius;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the area.
	// ---------------------------------------------------------------------------
	@Override
	public double computeArea() {
		return Math.PI * (this.radius * this.radius);
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the circumference.
	// ---------------------------------------------------------------------------
	@Override
	public double computePerimeter() {
		return 2 * Math.PI * this.radius;
	}

	// ---------------------------------------------------------------------------
	// Returns the double value of radius
	// ---------------------------------------------------------------------------
	public double getRadius() {
		return this.radius;
	}

	// ---------------------------------------------------------------------------
	// Returns pertinent information about the circle.
	// ---------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Circle: " + "\nCircumference is "
				+ form.format(computePerimeter()) + "\nArea is "
				+ form.format(computeArea()) + "\n";
	}
}

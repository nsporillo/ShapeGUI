package net.porillo.triangles;

//******************************************************************************
//  Triangle.java       Java Foundations
//
//  Solution to Programming Project 8.6
//
//  - Parent class to @Tetrahedron
//  - Handles basic area and perimeter functions
//******************************************************************************

import net.porillo.Shape;

public class Triangle extends Shape {
	protected double side;
	
	// ---------------------------------------------------------------------------
	// Sets up the triangle by entering the length of a side.
	// ---------------------------------------------------------------------------
	public Triangle(double sid) {
		this.side = sid;
	}

	// ---------------------------------------------------------------------------
	// Returns the double value of the side.
	// ---------------------------------------------------------------------------
	public double getSide() {
		return this.side;
	}

	// ---------------------------------------------------------------------------
	// Returns the double value of the height of the triangle.
	// ---------------------------------------------------------------------------
	public double getHeight() {
		return Math.sqrt(Math.pow(this.side, 2) - Math.pow(this.side / 2, 2));
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the area.
	// ---------------------------------------------------------------------------
	@Override
	public double computeArea() {
		return this.side * getHeight() / 2;
	}

	// ---------------------------------------------------------------------------
	// Returns the calculated value of the perimeter.
	// ---------------------------------------------------------------------------
	@Override
	public double computePerimeter() {
		return this.side * 3;
	}

	// ---------------------------------------------------------------------------
	// Returns pertinent information about the triangle.
	// ---------------------------------------------------------------------------
	public String toString() {
		return "Triangle: \nSide length is " + form.format(this.side)
				+ "\nPerimeter is " + form.format(computePerimeter())
				+ ", area is " + form.format(computeArea());
	}
}

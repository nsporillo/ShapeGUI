package net.porillo;

import java.text.DecimalFormat;

//******************************************************************************
//  Shape.java      Nick Porillo
//
//  Solution to Programming Project 8.6
//
//  Class is parent to all shapes
//  Forces subclasses to implement computeArea() and computerPerimeter()
//  Grant all subclasses access to decimal format 
//******************************************************************************

public abstract class Shape {

	public static DecimalFormat form = new DecimalFormat("0.##");
	
	abstract public double computeArea();

	abstract public double computePerimeter();
}

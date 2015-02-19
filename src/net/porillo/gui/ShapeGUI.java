//******************************************************************************
//  ShapeGUI.java       Author: Nick Porillo
//
//  Solution to Programming Project 8.6
//
//  ShapeGUI is the main class for the "ShapeTester" project
//  - Handles shapes lexical analysis (loading shape objects from stringed data)
//  - Sets up the GUI 
//******************************************************************************

package net.porillo.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.porillo.Shape;
import net.porillo.circles.Circle;
import net.porillo.circles.Cylinder;
import net.porillo.circles.Sphere;
import net.porillo.rectangles.Pentahedron;
import net.porillo.rectangles.Prism;
import net.porillo.rectangles.Rectangle;
import net.porillo.triangles.Tetrahedron;
import net.porillo.triangles.Triangle;

public class ShapeGUI {

	protected JFrame frmShapegui;

	// shapes storage
	protected List<Shape> shapes = new ArrayList<Shape>();

	// render panel background color (Gray)
	protected final Color backColor = new Color(220, 220, 220);

	// object rendering color, primary face (blueish)
	protected Color rendColor = new Color(90, 205, 255);

	//
	// Launch the application.
	//
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShapeGUI window = new ShapeGUI();
					window.frmShapegui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//
	// Create the application.
	//
	public ShapeGUI() {
		initialize();
	}

	//
	// Initialize the contents of the frame.
	//
	private void initialize() {
		this.frmShapegui = new JFrame();
		this.frmShapegui.setTitle("ShapeGUI");
		this.frmShapegui.setResizable(false);
		this.frmShapegui.setBounds(100, 100, 600, 400);
		this.frmShapegui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmShapegui.getContentPane().setLayout(null);

		JTextArea console = new JTextArea();
		console.setBounds(185, 285, 400, 75);
		console.setEditable(false);
		this.frmShapegui.getContentPane().add(console);

		JPanel panel = new JPanel();
		panel.setBounds(185, 10, 400, 250);
		panel.setBackground(this.backColor);
		this.frmShapegui.getContentPane().add(panel);

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		this.fillList();

		// Fill list with simple class names of the shapes
		// All shape subclass names are added to the JList
		for (Shape shape : this.shapes) {
			String name = shape.getClass().getSimpleName().toLowerCase();
			listModel.addElement(name);
		}
		final JList<String> list = new JList<String>(listModel);
		list.setBounds(10, 10, 165, 250);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.frmShapegui.getContentPane().add(list);

		final JButton renderButton = new JButton("Render!");
		renderButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		renderButton.setBackground(new Color(102, 204, 255));
		renderButton.setBounds(10, 285, 165, 75);
		renderButton.setEnabled(false);
		renderButton.addActionListener(new RenderListener(ShapeGUI.this, panel,
				list, console));
		this.frmShapegui.getContentPane().add(renderButton);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {

					// Determine if button can be pressed
					// Essentially a null test, blocks user from
					// trying to render a shape they didnt pick yet
					if (list.getSelectedIndex() == -1) {
						// No selection, disable render button.
						renderButton.setEnabled(false);
					} else {
						// Selection, enable the render button.
						renderButton.setEnabled(true);
					}
				}
			}
		});
	}

	//
	// Lexical analysis
	// Scan shapes.dat by line
	// Based on input, instantiate and store shapes
	public void fillList() {
		try {
			Scanner scan = new Scanner(new File("shapes.dat"));
			double width, length, height, side, radius;

			// Read the data from the input file
			while (scan.hasNext()) {

				// First entry on line, make lower case
				String shapeType = scan.next().toLowerCase();

				if (shapeType.equals("prism")) {
					width = scan.nextDouble();
					length = scan.nextDouble();
					height = scan.nextDouble();
					this.shapes.add(new Prism(width, length, height));
				} else if (shapeType.equals("pentahedron")) {
					width = scan.nextDouble();
					length = scan.nextDouble();
					height = scan.nextDouble();
					this.shapes.add(new Pentahedron(width, length, height));
				} else if (shapeType.equals("tetrahedron")) {
					side = scan.nextDouble();
					height = scan.nextDouble();
					this.shapes.add(new Tetrahedron(side, height));
				} else if (shapeType.equals("sphere")) {
					radius = scan.nextDouble();
					this.shapes.add(new Sphere(radius));
				} else if (shapeType.equals("cylinder")) {
					radius = scan.nextDouble();
					height = scan.nextDouble();
					this.shapes.add(new Cylinder(radius, height));
				} else if (shapeType.equalsIgnoreCase("circle")) {
					radius = scan.nextDouble();
					this.shapes.add(new Circle(radius));
				} else if (shapeType.equalsIgnoreCase("triangle")) {
					side = scan.nextDouble();
					this.shapes.add(new Triangle(side));
				} else if (shapeType.equalsIgnoreCase("rectangle")) {
					width = scan.nextDouble();
					length = scan.nextDouble();
					this.shapes.add(new Rectangle(width, length));
				}
			}
			// close the scanner resource
			scan.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

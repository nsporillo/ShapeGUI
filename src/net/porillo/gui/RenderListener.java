package net.porillo.gui;

//******************************************************************************
//  RenderListener.java       Author: Nick Porillo
//
//  Solution to Programming Project 8.6
//
//  - This class handles the graphical drawing of shapes
//  - All shapes are drawn from center of the draw panel 
//  - All 2D shapes are pretty much standard, but 3D shapes
//  - required a more involved approach. 
// 
//  - NO changes to the original class files required for the project were made
//  - That is why render is handled here and not in each shape 
//  - net.porillo.gui package is the only additions above the assignment
//******************************************************************************

import static java.lang.Math.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import net.porillo.Shape;
import net.porillo.circles.Circle;
import net.porillo.circles.Cylinder;
import net.porillo.circles.Sphere;
import net.porillo.rectangles.Pentahedron;
import net.porillo.rectangles.Prism;
import net.porillo.rectangles.Rectangle;
import net.porillo.triangles.Tetrahedron;
import net.porillo.triangles.Triangle;

public class RenderListener implements ActionListener {

	private Map<Point, Integer> colorMap = new HashMap<Point, Integer>();
	private Color[] shades = new Color[10];
	private double[] shading = { 45, 45, -45 };
	private JList<String> list;
	private ShapeGUI shapeGui;
	private JTextArea console;
	private Color frust = new Color(255, 0, 0, 80);
	private JPanel panel;

	//
	// RenderListener constructor
	// - set up references
	// - generate shading map for sphere
	//
	public RenderListener(ShapeGUI gui, JPanel jp, JList<String> list,
			JTextArea con) {
		this.shapeGui = gui;
		this.panel = jp;
		this.list = list;
		this.console = con;

		for (int i = 0; i < 10; i++) {
			// Color goes from 'white' to blue
			this.shades[i] = new Color(0, 0, 255, i*10);
		}

		// normalize shading array
		this.normalize(this.shading);
	}

	// handle render button click
	// (draw everything)
	@Override
	public void actionPerformed(ActionEvent e) {
		// Obtain shape based on list selection
		Shape shape = this.shapeGui.shapes.get(this.list.getSelectedIndex());

		// Output shape information to console
		this.console.setText(shape.toString());

		// Get our graphics panel
		Graphics2D g2d = (Graphics2D) this.panel.getGraphics();

		// Clear any previous renderings
		g2d.clearRect(0, 0, this.panel.getWidth(), this.panel.getWidth());

		// Set rendering color
		g2d.setColor(this.shapeGui.rendColor);

		// Set rendering hints to improve output
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Determine center point of graphics panel
		int x = this.panel.getWidth() / 2;
		int y = this.panel.getHeight() / 2;

		if (shape instanceof Circle) {
			if (shape instanceof Cylinder) {
				Cylinder cyl = (Cylinder) shape;
				// amplify height and radius for visible render
				int height = (int) cyl.getHeight() * 15;
				int radius = (int) cyl.getRadius() * 15;
				int offR = radius / 2;

				// Draws cylinder about the origin (middle of panel)
				g2d.drawLine(x - offR, y + 5, x - offR, y + height + 5);
				g2d.drawLine(x + offR, y + 5, x + offR, y + height + 5);
				g2d.setColor(this.frust);
				g2d.fillOval(x - offR, y, radius, 10);
				g2d.fillOval(x - offR, y + height, radius, 10);
			} else if (shape instanceof Sphere) {
				Sphere sph = (Sphere) shape;
				int radius = (int) sph.getRadius() * 15;
				this.genSphere(radius, sph.getRadius(), .05);

				
				// Draws Sphere about the origin (middle of panel)
				// Renders every pixel individually
				// Horribily inefficient
				// TODO:
				// - When generating shading map, sort into polygons
				// - Polygons can be filled, so only the edge points are needed
				//
				// For this purpose, rendering pixel by pixel suffices
				for (Entry<Point, Integer> es : this.colorMap.entrySet()) {
					Color color = this.shades[es.getValue()];
					Point p = es.getKey();
					g2d.setColor(color);
					g2d.drawOval(p.x + x, p.y + y, 1, 1);
				}
				this.colorMap.clear();
			} else {
				Circle circle = (Circle) shape;
				int radius = (int) circle.getRadius() * 15;

				// Draws circle about the origin (middle of panel)
				g2d.fillOval(x - (radius / 2), y - (radius / 2), radius, radius);
			}
		} else if (shape instanceof Rectangle) {
			if (shape instanceof Prism) {
				Prism prism = (Prism) shape;
				int len = (int) prism.getLength() * 10;
				int hgt = (int) prism.getHeight() * 10;
				int hHgt = hgt / 2;
				int hLen = len / 2;

				// Draws top frustum
				g2d.setColor(this.frust);
				java.awt.Rectangle rec = new java.awt.Rectangle();
				rec.setBounds(x - hLen, y - hHgt, len, hgt);
				g2d.fill(rec);

				// Draws bottom frustum
				java.awt.Rectangle rec2 = (java.awt.Rectangle) rec.clone();
				rec2.setLocation(x + hLen, y + hHgt);
				g2d.fill(rec2);

				// Draws the connecting and defining lines
				g2d.setColor(Color.BLACK);
				g2d.drawLine(x - hLen, y + hHgt, x + hLen, y + hgt + hHgt);
				g2d.drawLine(x + hLen, y + hHgt, x + len + hLen, y + hgt + hHgt);
				g2d.drawLine(x + hLen, y - hHgt, x + hLen + len, y + hHgt);
				g2d.draw(rec);
				g2d.draw(rec2);
			} else if (shape instanceof Pentahedron) {
				Pentahedron pent = (Pentahedron) shape;
				int side = (int) pent.getWidth() * 12;
				int length = (int) pent.getLength() * 12;
				int height = (int) pent.getHeight() * 12;

				java.awt.Rectangle rect = new java.awt.Rectangle();
				rect.setBounds((x - side / 2), (y - length / 2 + (height/4)), side, length);
				Rectangle2D r2d = rect.getBounds2D();
				
				Point[] bnds = new Point[4];
				bnds[0] = new Point((int) r2d.getMinX(), (int) r2d.getMinY());
				bnds[1] = new Point((int) r2d.getMinX(), (int) r2d.getMaxY());
				bnds[2] = new Point((int) r2d.getMaxX(), (int) r2d.getMaxY());
				bnds[3] = new Point((int) r2d.getMaxX(), (int) r2d.getMinY());
				
				double diagLen = sqrt((pow(side, 2) + pow(side, 2))) / 2;
				Point vertex = new Point(x, y - (int) diagLen - height/4);
				Rectangle2D frustum = new Rectangle2D.Double(bnds[0].x,
						bnds[0].y, side, length);
				g2d.setColor(new Color(255, 0, 0, 80));
				g2d.fill(frustum);
				int i = 1;
				g2d.setColor(Color.BLACK);
				for (Point p : bnds) {
					g2d.drawLine(vertex.x, vertex.y, p.x, p.y);
					int dex = i + 1 > 3 ? 3 : i;
					g2d.drawLine(p.x, p.y, bnds[dex].x, bnds[dex].y);
					i++;
				}
				Line2D.Double line = new Line2D.Double();
				line.setLine(bnds[0].x, bnds[0].y, bnds[3].x, bnds[3].y);
				g2d.fill(getDottedLine(line));

			} else {
				Rectangle rect = (Rectangle) shape;
				int width = (int) rect.getWidth() * 12;
				int height = (int) rect.getLength() * 12;
				// Draws rectangle about the origin (center of panel)
				g2d.fillRect(x - (width / 2), y - (width / 2), width, height);
			}
		} else if (shape instanceof Triangle) {
			if (shape instanceof Tetrahedron) {
				Tetrahedron tet = (Tetrahedron) shape;
				int side = (int) tet.getSide() * 15;
				int height = (int) tet.getHeight() * 2;

				// generate polygon about the origin
				Polygon poly = makeTri(x, y, side);

				// render polygon
				g2d.fillPolygon(poly);
				g2d.setColor(Color.BLACK);

				// get lines from origin to each vertex of triangle
				Point[] vertex = makeTetra(x,y,side);
				for (Point p : vertex) {
					g2d.drawLine(p.x, p.y, x, y + height);
				}
				
				// color in plane to help user see shape 
				Polygon pfrust = new Polygon();
				pfrust.addPoint(x, y + height);
				pfrust.addPoint(vertex[1].x, vertex[1].y);
				pfrust.addPoint(vertex[2].x, vertex[2].y);
				g2d.setColor(this.frust);
				g2d.fillPolygon(pfrust);
				
				g2d.setColor(Color.BLACK);
				g2d.drawLine(vertex[0].x, vertex[0].y, vertex[1].x, vertex[1].y);
				g2d.drawLine(vertex[1].x, vertex[1].y, vertex[2].x, vertex[2].y);
				g2d.drawLine(vertex[2].x, vertex[2].y, vertex[0].x, vertex[0].y);
				
			} else {
				Triangle tri = (Triangle) shape;
				int side = (int) tri.getSide() * 15;

				// generate and fill polygon about the origin
				g2d.fillPolygon(makeTri(x, y, side));
			}
		}
	}

	private static java.awt.Shape getDottedLine(Line2D.Double line) {
		float[] dash = { 2F, 6F };
		Stroke dashedStroke = new BasicStroke(2F, BasicStroke.CAP_SQUARE,
				BasicStroke.JOIN_MITER, 2F, dash, 1F);
		return dashedStroke.createStrokedShape(line);
	}

	//
	// Generate triangle around the point x,y Uses trig to do this right
	//
	static Polygon makeTri(int x, int y, int side) {
		Polygon poly = new Polygon();
		int halfSide = (side / 2);

		// Generates top most vertex of triangle (max y point)
		int topY = (int) (y - ((side * Math.sin(PI / 3)) / Math.sin(PI / 2)));
		poly.addPoint(x, topY);

		// Generates bottom most point of triangle (bottom base of triangle)
		int botY = (int) (y + ((halfSide * Math.sin(PI / 6)) / Math.sin(PI / 3)));
		poly.addPoint(x - halfSide, botY);
		poly.addPoint(x + halfSide, botY);
		return poly;
	}

	//
	// Generate list of points that make up the tetrahedron. Used to generate
	// the simple lines to indicate a 3d object on a 2d plane
	//
	static Point[] makeTetra(int x, int y, int side) {
		Point[] points = new Point[3];
		int halfSide = (side / 2);
		int topY = (int) (y - ((side * Math.sin(PI / 3)) / Math.sin(PI / 2)));
		points[0] = new Point(x, topY);
		int botY = (int) (y + ((halfSide * Math.sin(PI / 6)) / Math.sin(PI / 3)));
		points[1] = new Point(x - halfSide, botY);
		points[2] = new Point(x + halfSide, botY);
		return points;
	}

	//
	// Normalize 3 doubles
	// Used in sphere gen method
	//
	public void normalize(double[] v) {
		double dotProd = v[0] * v[0] + v[1] * v[1] + v[2] * v[2];
		double len = Math.sqrt(dotProd);
		v[0] /= len;
		v[1] /= len;
		v[2] /= len;
	}

	//
	// Returns 0 if dot product above 0
	// Returns -d, if dot product under 0
	//
	public double dot(double[] x, double[] y) {
		double d = x[0] * y[0] + x[1] * y[1] + x[2] * y[2];
		return d < 0 ? -d : 0;
	}

	//
	// Method for calculating every pixel in a sphere
	// R is radius, shader determines size of sphere shine
	// ambient is the intensity modifier
	//
	public void genSphere(double R, double shader, double ambient) {
		double[] vec = new double[3];
		for (int i = (int) Math.floor(-R); i <= (int) Math.ceil(R); i++) {
			double x = i + 0.5;
			for (int j = (int) Math.floor(-2 * R); j <= (int) Math.ceil(2 * R); j++) {
				double y = j / 2.0 + 0.5;
				// Check if (x,y) is within the 2D circle radius
				if (x * x + y * y <= R * R) {
					vec[0] = x;
					vec[1] = y;
					vec[2] = Math.sqrt(R * R - x * x - y * y);
					normalize(vec);

					double b = Math.pow(dot(this.shading, vec), shader)
							+ ambient;
					int intensity = 0;

					if (b <= 0)
						intensity = this.shades.length - 2;

					intensity = (int) Math.max((1 - b)
							* (this.shades.length - 1), 0);
					Point point = new Point();
					point.setLocation(x, y);

					// store coordinate and shading intensity
					this.colorMap.put(point, intensity);
				}
			}
		}
	}
}

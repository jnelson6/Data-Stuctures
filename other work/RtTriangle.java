/**
 * @author Koffman and Wolfgang
 */
package simpleshapes;

import java.util.Scanner;

/**
 * Represents a right triangle. Extends Shape.
 */
public class RtTriangle extends Shape {
    // Data Fields

    /** The base of the RtTriangle. */
    private double base;
    /** The height of the RtTriangle. */
    private double height;

    // Constructors
    /** Constructs a default RtTriangle */
    public RtTriangle() {
	this(0);
    }

    /** Constructs an isoscele RtTriangle
     * @param side the common value of base and height
     */
    public RtTriangle(double side) {
	 this(side, side);
    }
    
    /**
     * Constructs a Right Triangle of the specified size.
     * @param base the base
     * @param height the height
     */
    public RtTriangle(double base, double height) {
        super("Right Triangle");
        this.base = base;
        this.height = height;
    }

    // Methods
    /**
     * Get the base.
     *
     * @return The base
     */
    public double getBase() {
        return base;
    }

    /**
     * Get the height.
     *
     * @return The height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Compute the area.
     *
     * @return The area of the RtTriangle
     */
    @Override
    public double computeArea() {
        return 0.5 * height * base;
    }

    /**
     * Compute the perimeter.
     *
     * @return The perimeter of the RtTriangle
     */
    @Override
    public double computePerimeter() {
        double hyp = Math.sqrt((base * base) + (height * height));

        return hyp + base + height;
    }

    /**
     * Read the attributes of the Right Triangle.
     */
    @Override
    public void readShapeData() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the base of the Right Triangle");
        base = in.nextDouble();
        System.out.println("Enter the height of the Right Triangle");
        height = in.nextDouble();
    }

    /**
     * Create a string representation of the RtTriangle.
     *
     * @return A string representation of the RtTriangle
     */
    @Override
    public String toString() {
        return super.toString() + ": base is " + base
                + ", height is " + height;
    }
}

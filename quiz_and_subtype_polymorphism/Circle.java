package classes;

public class Circle extends Shape {
	//Data Fields
	private double radius;

	// Constructors
	Circle(double r) {
		super("Blue");
		radius = r;
	}

	Circle(double r, String color) {
		super(color);
		radius = r;
		
	}

	// Methods

	public double getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

//	public String m() {
//		return "Circle";
//	}
		

	public String toString() {
		return "I am a circle of radius " +  radius + ". "+ super.toString();
	}
	
}

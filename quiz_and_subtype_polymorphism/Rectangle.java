package classes;

public class Rectangle extends Shape{

	// Data fields
	private int base;
	private int height;
	
	private static int noOfRectangles;
	
	// Constructors
	Rectangle(int height, int base, String color) {
		this.height=height;
		this.base=base;		
		this.setColor(color);
		noOfRectangles++;
	}
		
	Rectangle (int height, int base) {
		this.height=height;
		this.base=base;
		this.setColor("Blue");
	}
	// Methods
	public int perimeter() {
		return 2*base+2*height;
	}
	
	public int getBase() {
		return base;
	}


	public void setBase(int base) {
		this.base = base;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	

	public int area() {
		return base*height;
	}
	
	@Override
	public String getInfo() {
		return "my height is "+ height + "; my base is "+base+";" +super.getInfo();
	}
	
	public void setColor(int i) {
		this.setColor("Blue");
	}

	public void setColor(double i) {
		this.setColor("Yellow");
	}

//	public String m() {
//		return "Rectangle";
//		
//	}
		public String toString() {
		return "I am a rectangle of base "
				+ base + " and height " +height + "." + super.toString();
	}

//	public static void main(String[] args) {
//		 Rectangle r = new Rectangle(2,3);
//		 Rectangle s = new Rectangle(7,5);
////		 r.setHeight(2);
////		 r.setBase(3);
//		 System.out.println("The height of r is " + r.getHeight());
//		 System.out.println("The perimeter of r is " + r.perimeter());
//		 System.out.println("The area of r is " + r.area());
//
//		 System.out.println("The perimeter of s is " + s.perimeter());
//		 System.out.println("The area of s is " + s.area());
//
//		 System.out.println("The number of rectangles created up until now is" + Rectangle.noOfRectangles);
//
//		 
//	}
}

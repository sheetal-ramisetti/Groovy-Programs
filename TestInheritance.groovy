import java.lang.Math
import java.text.DecimalFormat


class TestInheritance {

	static void main(String[] args) {
	
		Point point = new Point(30, 50)
		Circle circle = new Circle(2.7, 120, 89)
		println "$point"
		println "$circle"
		point.setPoint(1, 2)
		println "$point"
		circle.setPoint(10, 20)
		println "$circle"
		println "${circle.printPoint()}"
		println "\n\nArea: ${circle.area()}"
	}	
}

class Point {

	protected int x
	protected int y
	
	public Point() { setPoint(0, 0) }
	
	public Point(int a, int b) { setPoint(a, b) }
	
	public int getX() { x }
	
	public int getY() { y }
	
	public void setPoint(int a, int b) {
		x = a
		y = b
	}
	
	public String toString() { "Point p: [$x, $y]" }
}

class Circle extends Point {

	private static DecimalFormat df = new DecimalFormat("#########.00")

	protected BigDecimal radius
	
	public Circle() {
		super(0, 0)
		radius = 0.0
	}
	
	public Circle(double r, int a, int b) {
		super(a, b)
		radius =  r
	}
	
	public double getRadius() { radius }
	
	public void setRadius(double radius) { this.radius = radius }
	
	public double area() { Math.PI*2*radius }
	
	public String toString() { "Circle c: Center = [$x, $y]; Radius = ${df.format(radius)}" }
	
	public String printPoint() { "Center of Circle c: [$x, $y]" }
}
	
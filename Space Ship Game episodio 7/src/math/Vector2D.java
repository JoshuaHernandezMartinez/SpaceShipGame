package math;

public class Vector2D {
	private double x,y;
	
	public Vector2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2D()
	{
		x = 0;
		y = 0;
	}
	
	public double getMagnitude()
	{
		return Math.sqrt(x*x + y*y);
	}
	
	public Vector2D setDirection(double angle)
	{
		return new Vector2D(Math.cos(angle)*getMagnitude(), Math.sin(angle)*getMagnitude());
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
}

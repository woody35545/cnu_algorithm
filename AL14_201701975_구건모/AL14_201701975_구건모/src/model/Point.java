package model;

public class Point {
	public int x;
	public int y;

	public Point(int givenX, int givenY) {
		this.x = givenX;
		this.y = givenY;
	}

	public Point() {
		this(0, 0);

	}
	
	public long distanceTo(Point other) {
		if(other == null) {
			return Integer.MAX_VALUE;
		}
		else
		{
			long differenceOfX = this.x - other.x;
			long differenceOfY = this.y - other.y;
			return (differenceOfX*differenceOfX + differenceOfY*differenceOfY);
			
		}		
	}
	


}

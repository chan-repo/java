/*
 * 4. Point를 상속받아 색을 가진 점을 나타내는 ColorPoint2 클래스를 작성하라. 다음 main() 메소드를 포함하여 실행 결과와 같이 출력되게 하라.
 */

package Chapter5;

class Point_01 {
	private int x, y;

	public Point_01(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	protected void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class ColorPoint_01 extends Point_01 {
	private String color;
	
	ColorPoint_01(int x, int y, String color) {
		super(x, y);
		this.color = color;
	}
	
	void setXY(int x, int y) {
		super.move(x, y);
	}
	
	void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return this.color + "색의 (" + super.getX() + ", " + super.getY() + ")의 점";
	}
}

public class ColorPoint2 extends ColorPoint_01 {
	ColorPoint2() {
		super(0, 0, "WHITE");
	}
	ColorPoint2(int x, int y) {
		super(x, y, "BLACK");
	}
	ColorPoint2(int x, int y, String color) {
		super(x, y, color);
	}
	
	void set(String color) {
		super.setColor(color);
	}
	void set(int x, int y) {
		super.setXY(x, y);
	}
	
	double getDistance(ColorPoint2 cp) {
		int x = super.getX() - cp.getX();
		int y = super.getY() - cp.getY();
		double distance = Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), 0.5);
		return distance;
	}
	
	public static void main(String[] args) {
		ColorPoint2 zeroPoint = new ColorPoint2();	// (0,0) 위치의 "WHITE" 색 점
		System.out.println(zeroPoint.toString() + "입니다.");
		
		ColorPoint2 cp = new ColorPoint2(10, 10, "RED");
		
		cp.set("BLUE");
		cp.set(10, 20);
		System.out.println(cp.toString() + "입니다.");
		ColorPoint2 thresholdPoint = new ColorPoint2(100,100);	// (100,100) 위치의 "BLACK" 점
		
		System.out.println("cp에서 임계점까지의 거리는 " + cp.getDistance(thresholdPoint));
	}
}

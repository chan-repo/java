/*
 * 7. Point 클래스를 상속받아 3차원 색 점을 나타내는 Point3DColor 클래스를 작성하라. 다음 main() 메소드를 포함하여 실행 결과와 같이 출력되게 하라.
 */

package Chapter5;

class Point_04 {
	private int x, y;

	public Point_04(int x, int y) {
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

public class Point3DColor extends Point_04 {
	private int z;
	private String color;
	
	Point3DColor(int x, int y, int z, String color){
		super(x, y);
		this.z = z;
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "(" + super.getX() + "," + super.getY() + "," + this.z + ")" + this.color + "점";
	}
	
	void move(Point3DColor p) {
		super.move(p.getX(), p.getY());
		this.z = p.z;
	}
	
	boolean equals(Point3DColor p) {
		if(this.color == p.color && this.z == p.z && super.getX() == p.getX() && super.getY() == p.getY()) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Point3DColor p = new Point3DColor(10, 20, 30, "RED");
		System.out.println(p.toString() + "입니다.");
		
		Point3DColor q = new Point3DColor(1, 2, 3, "BLUE");
		p.move(q);
		System.out.println(p.toString() + "입니다.");
		
		Point3DColor r = new Point3DColor(1, 2, 3, "RED");
		
		if(p.equals(r)) {
			System.out.println("예, 같은 위치 같은 색깔의 점입니다.");
		}
		else {
			System.out.println("아니오");
		}
	}
}

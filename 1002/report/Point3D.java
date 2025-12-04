/*
 * 5. Point 클래스를 상속받아 3차원의 점을 나타내는 Point3D 클래스를 작성하라. 다음 main() 메소드를 포함하여 실행 결과와 같이 출력되게 하라.
 */

package Chapter5;

class Point_02 {
	private int x, y;

	public Point_02(int x, int y) {
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

public class Point3D extends Point_02 {
	private int z;
	
	Point3D(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}
	
	void moveUp(int z) {
		this.z += z;
	}
	
	void moveDown(int z) {
		this.z -= z;
	}
	
	void move(int x, int y, int z) {
		super.move(x, y);
		this.z = z;
	}
	
	@Override
	public String toString() {
		return "(" + super.getX() + "," + super.getY() + "," + this.z + ")의 점";
	}
	
	
	public static void main(String[] args) {
		Point3D p = new Point3D(3, 2, 1);
		System.out.println(p.toString() + "입니다.");
		
		p.moveUp(3);	// z축으로 3 이동
		System.out.println(p.toString() + "입니다.");
		
		p.moveDown(2);	// z축으로 -2 이동
		System.out.println(p.toString() + "입니다.");
		
		p.move(5, 5);	// x=5, y=5으로 이동
		System.out.println(p.toString() + "입니다.");
		
		p.move(100, 200, 300);
		System.out.println(p.toString() + "입니다.");
	}
}

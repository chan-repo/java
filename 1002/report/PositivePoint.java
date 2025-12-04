/*
 * 6. Point 클래스를 상속받아 양수만 가능한 점을 나타내는 PositiePoint 클래스를 작성하라. 다음 main() 메소드를 포함하여 실행 결과와 같이 출력되게 하라.
 */

package Chapter5;

class Point_03 {
	private int x, y;

	public Point_03(int x, int y) {
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

public class PositivePoint extends Point_03 {
	PositivePoint(int x, int y) {
		super(1, 1);
		if(x >= 0 && y >= 0) {
			super.move(x, y);
		}
	}
	
	@Override
	protected void move(int x, int y) {
		if(x >= 0 && y >= 0) {
			super.move(x, y);
		}
	}
	
	@Override
	public String toString() {
		return "(" + super.getX() + "," + super.getY() + ")의 점";
	}
	
	public static void main(String[] args) {
		PositivePoint p = new PositivePoint(10, 10);	// (10, 10)의 점
		p.move(5, 5);	// p는 (5, 5) 점
		System.out.println(p.toString() + "입니다.");
		
		p.move(2, -2);	// 점 p는 양수 공간만 가능. 그러므로 이동 없음
		System.out.println(p.toString() + "입니다.");
		
		PositivePoint q = new PositivePoint(-10, -10);	// 음수 점 불가. 디폴트 (1, 1)의 점 생성
		System.out.println(q.toString() + "입니다.");
		
	}
}

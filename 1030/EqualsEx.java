package week6;

class Point {
	int x, y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		Point p = (Point)obj;
		
		if(x == p.x && y == p.y) {
			return true;
		} else {
			return false;
		}
	}
}

public class EqualsEx {
	public static void main(String[] args) {
		Point a = new Point(2,3);
		Point b = new Point(2,3);
		Point c = new Point(3,4);
		
		if(a.equals(b)) {
			System.out.println("a와 b는 같습니다.");
		} else {
			System.out.println("a와 b는 같지 않습니다.");
		}
		
		if(a.equals(c)) {
			System.out.println("a와 c는 같습니다.");
		} else {
			System.out.println("a와 c는 같지 않습니다.");
		}
		
	}
}

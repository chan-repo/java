/*
 * 9. Vector<Shape>을 이용하여 그래픽 편집기를 만들어보자.
 * 본문 5.6절과 5.7절에서 사례로 든 추상 클래스 Shape과 Line, Rect, Circle 클래스 코드를 완성하고
 * 이를 활용하여 "삽입", "삭제", "모두 보기", "종료"의 4가지 그래픽 편집 기능을 가진 프로그램을 작성하라.
 * 5장 실습문제 14번을 Vector<Shape>을 이용하여 재작성하는 연습이다.
 * Vector를 이용하여 5장 실습문제 14번보다 훨씬 간단히 작성됨을 경험할 수 있다.
 */

package Practice;

import java.util.Scanner;
import java.util.Vector;

abstract class Shape {
	private Shape next;
	public Shape() { next = null;}
	public void setNext(Shape obj) {next = obj;} // 링크 연결
	public Shape getNext() {return next;}
	public abstract void draw(); // 추상 메소드
}

class Circle extends Shape {
	@Override
	public void draw() {
		 System.out.println("Circle");
	}
}

class Rect extends Shape {
	@Override
	public void draw() {
		 System.out.println("Rect");
	}
}

class Line extends Shape {
	@Override
	public void draw() {
		 System.out.println("Line");
	}
}

public class GraphicEditor {
	private String editorName;
	private Scanner scanner = new Scanner(System.in);
	private Vector<Shape> v = new Vector<Shape>();
	
	public GraphicEditor(String editorName) {
		this.editorName = editorName;
	}
	
	public void run() {
		System.out.println("그래픽 에디터 " + editorName + "를 실행합니다.");
		int menu = 0;
		while (menu != 4) { 
			int shape, index;
			System.out.print("삽입(1), 삭제(2), 모두 보기(3), 종료(4)>>");
			menu = scanner.nextInt();
			switch (menu) {
				case 1:	// 삽입
					System.out.print("Line(1), Rect(2), Circle(3)>>");
					shape = scanner.nextInt();
					if (shape < 1 || shape > 3) {
						System.out.println("잘못 선택하셨습니다.");
						break;
					}
					insert(shape);
					break;
				case 2:	// 삭제
					System.out.print("삭제할 도형의 위치>>");
					index = scanner.nextInt();
					if (!delete(index)) { 
						System.out.println("삭제할 수 없습니다.");
					}
					break;
				case 3:	// 모두 보기
					view();
					break;
				case 4:	// 끝내기
					break;
				default:
					System.out.println("잘못 입력하셨습니다.");
			}
		}
		System.out.println(editorName + "를 종료합니다.");	
	}

	private void view() {
		for(int i=0; i<v.size(); i++) 
			v.get(i).draw(); 
	}
	
	private boolean delete(int index) {
		if (v.size() == 0 || index >= v.size()) 
			return false;
		v.remove(index);
		return true;
	}

	private void insert(int choice) {
		Shape shape=null;
		switch (choice) {
			case 1: // Line
				shape = new Line();
				break;
			case 2: // Rect
				shape = new Rect();
				break;
			case 3: // Circle
				shape = new Circle();
		}
		v.add(shape);
	}
	
	public static void main(String [] args) {
		GraphicEditor ge = new GraphicEditor("Beauty Graphic Editor");
		ge.run();
	}
}
/*
 * 14. 텍스트로 입출력하는 간단한 그래픽 편집기를 만들어보자.
 * 본문 5.6절과 5.7절에서 사례로 보인 추상 클래스 Shape과 Line, Rect, Circle 클래스 코드를 잘 완성하고 
 * 이를 활용하여 다음 실행 예시처럼 "삽입", "삭제", "모두 보기", "종료"의 4가지 그래픽 편집 기능을 가진 클래스 GraphicEditor를 작성하라.
 */

package Chapter5;

import java.util.Scanner;

abstract class Shape {
    private Shape next;
    public Shape() { next = null; }
    public void setNext(Shape obj) { next = obj; } // 링크 연결
    public Shape getNext() { return next; }
    public abstract void draw(); // 추상 메소드
}

class Line extends Shape {
	@Override
	public void draw() {
		System.out.println("Line");
	}
}

class Rect extends Shape {
	@Override
	public void draw() {
		System.out.println("Rect");
	}
}

class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}


//GraphicEditor 클래스
public class GraphicEditor {
	private Shape head; // 연결 리스트의 첫 번째 도형
	
	public GraphicEditor() {
	    head = null;
	}
	
	// 도형 삽입
	public void insert(Shape shape) {
	    if (head == null) {
	        head = shape;
	    } else {
	        Shape current = head;
	        while (current.getNext() != null) {
	            current = current.getNext();
	        }
	        current.setNext(shape);
	    }
	}
	
	// 저장된 모든 도형 출력
	public void printAll() {
	    Shape current = head;
	    while (current != null) {
	        current.draw();
	        current = current.getNext();
	    }
	}
	
	// 특정 위치의 도형 삭제
	public boolean delete(int position) {
	    if (head == null || position < 1) {
	        return false;
	    }
	
	    if (position == 1) { // 첫 번째 도형 삭제
	    	head = head.getNext();
	    	return true;
	    }
	
	    Shape current = head;
	    Shape previous = null;
	    int count = 1;
	
	    while (current != null && count < position) {
	    	previous = current;
	    	current = current.getNext();
	    	count++;
	    }
	
	    if (current == null) {
	    	return false; // 위치가 잘못된 경우
	    }
	
	    previous.setNext(current.getNext()); // 현재 위치의 도형을 삭제
	    return true;
	 }
	
	 // 프로그램 실행
	 public void run() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("그래픽 에디터 Beauty Graphic Editor를 실행합니다.");
	
	    while (true) {
	    	System.out.print("삽입(1), 삭제(2), 모두 보기(3), 종료(4) >> ");
	    	int choice = scanner.nextInt();
	
			switch (choice) {
			    case 1:
			        System.out.print("Line(1), Rect(2), Circle(3) >> ");
			        int type = scanner.nextInt();
			        Shape shape = null;
			
			        if (type == 1) {
			        	shape = new Line();
			        }
			        else if (type == 2) {
			        	shape = new Rect();
			        }
			        else if (type == 3) {
			        	shape = new Circle();
			        }
			
			        if (shape != null) {
			            insert(shape);
			        } else {
			            System.out.println("잘못된 입력입니다.");
			        }
			        break;
			
			    case 2:
			        System.out.print("삭제할 도형의 위치 >> ");
			        int position = scanner.nextInt();
			        if (!delete(position)) {
			            System.out.println("삭제할 수 없습니다.");
			        }
			        break;
			
			    case 3:
			        printAll();
			        break;
			
			    case 4:
			        System.out.println("Beauty Graphic Editor를 종료합니다.");
			        scanner.close();
			        return;
			
			    default:
			        System.out.println("잘못된 입력입니다.");
	                break;
			 }
	     }
	 }
	 public static void main(String[] args) {
	     GraphicEditor editor = new GraphicEditor();
	     editor.run();
	 }
}

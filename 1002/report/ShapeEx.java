/*
 * 다음은 도형을 묘사한 Shape 인터페이스이다.
 * 다음 main() 메소드와 실행 결과를 참고하여, Shape 인터페이스를 상속받아 구현한 클래스 Circle, Oval, Rect를 작성하고
 * main() 메소드를 포함하는 ShapeEx 클래스를 작성하라.
 */

package Chapter5;

interface Shape_01 {
	static final double PI = 3.14;
	void draw(); // 도형을 그리는 추상 메소드.
	double getArea(); // 도형의 면적을 리턴하는 추상 메소드.
	default public void redraw() { // 디폴트 메소드
		System.out.print("--- 다시 그립니다. ");
		draw();	
	}
}

class Circle_01 implements Shape_01 {
	private int radius;

	public Circle_01(int radius) { 
		this.radius = radius;
	}
	
	@Override
	public void draw() {
		System.out.println("반지름이 " + radius + "인 원");
	}
	
	@Override
	public double getArea() { 
		return PI*radius*radius;
	}
}

class Oval implements Shape_01 {
	private int width, height;
	
	public Oval(int width, int height) { 
		this.width = width; 
		this.height = height; 
	}
	
	@Override
	public void draw() { 
		System.out.println(width + "x" + height + "에 내접하는 타원");
	}
	
	@Override
	public double getArea() { 
		return PI*(width/2)*(height/2);
	}
}

class Rect_01 implements Shape_01 {
	private int width, height;
	
	public Rect_01(int width, int height) { 
		this.width = width; this.height = height;
	}
	
	@Override
	public void draw() { 
		System.out.println(width + "x" + height + "크기의 사각형");
	}
	
	@Override
	public double getArea() { 
		return width*height;
	}
}

public class ShapeEx {

	public static void main(String[] args) {
		Shape_01 [] list = new Shape_01[3]; // Shape을 상속받은 클래스 객체의 레퍼런스 배열
		list[0] = new Circle_01(5); // 반지름이 5인 원 객체
		list[1] = new Oval(20, 30); // 20x30 사각형에 내접하는 타원
		list[2] = new Rect_01(10, 40); // 10x40 크기의 사각형
		for(int i=0; i<list.length; i++) list[i].redraw();
		for(int i=0; i<list.length; i++) System.out.println("면적은 " + list[i].getArea());
	}

}
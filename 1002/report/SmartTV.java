/*
 * 	2. 다음 main() 메소드와 실행 결과를 참고하여 ColorTV를 상속받는 smartTV 클래스를 작성하라.
 */

package Chapter5;

class TV_01 {
	private int size;
	
	public TV_01(int size) {
		this.size = size;
	}
	
	protected int getSize() {
		return size;
	}
}

class ColorTV_01 extends TV_01 {
	private int color;
	
	ColorTV_01(int size, int color) {
		super(size);
		this.color = color;
	}
	
	void printProperty() {
		System.out.println(super.getSize() + "인치 " + this.color + "컬러");
	}
}

public class SmartTV extends ColorTV_01 {
	private String ip;
	
	SmartTV(String ip, int size, int color) {
		super(size, color);
		this.ip = ip;
	}
	
	@Override
	void printProperty() {
		System.out.print("나의 SmartTV는 " + this.ip + " 주소의 ");
		super.printProperty();
	}
	
	public static void main(String[] args) {
		SmartTV smartTV = new SmartTV("192.168.0.5", 77, 20000000);	// 65인치 크기에 65536 컬러 TV
		smartTV.printProperty();
	}
}

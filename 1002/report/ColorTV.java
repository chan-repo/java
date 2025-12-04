/*
 * 다음 TV 클래스가 있다.
 * 
 * class TV {
 * 		private int size;
 * 		public TV(int size) { this.size = size; }
 * 		protected int getSize() { return size; }
 * 
 * 	1. 다음 main() 메소드와 실행 결과를 참고하여 TV를 상속받은 ColorTV 클래스를 작성하라.
 */

package Chapter5;

class TV {
	private int size;
	
	public TV(int size) {
		this.size = size;
	}
	
	protected int getSize() {
		return size;
	}
}

public class ColorTV extends TV {
	private int color;
	
	ColorTV(int size, int color) {
		super(size);
		this.color = color;
	}
	
	void printProperty() {
		System.out.println(super.getSize() + "인치 " + this.color + "컬러");
	}
	
	public static void main(String[] args) {
		ColorTV myTv = new ColorTV(65, 65536);	// 65인치 크기에 65536 컬러 TV
		myTv.printProperty();
	}
}

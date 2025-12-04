/*
 * 9. BaseArray 클래스를 상속받아 임계값(threshold)보다 크면 1, 아니면 0의 값을 가지는 BinaryArray를 작성하라.
 * 다음 main() 메소드를 포함하고 실행 결과와 같이 출력되게 하라.
 */

package Chapter5;

import java.util.Scanner;

class BaseArray {
	protected int array [];	// 배열 메모리
	protected int nextIndex = 0;	// 다음에 삽입할 배열에 대한 인덱스
	
	public BaseArray(int size) {
		array = new int [size];
	}
	public int length() { return array.length; }
	public void add(int n) {	// 정수 n을 배열 끝에 추가
		if(nextIndex == array.length) {
			return;	// 배열이 꽉 찼으면 추가 안 함
		}
		array[nextIndex] = n;
		nextIndex++;
	}
	public void print() {
		for(int n : array) {
			System.out.print(n + " ");
		}
		System.out.println();
	}
}

public class BinaryArray extends BaseArray {
	private int t;
	
	BinaryArray(int size, int t) {
		super(size);
		this.t = t;
	}
	
	@Override
	public void print() {
		for(int i=0; i < array.length; i++) {
			if(array[i] > t) {
				array[i] = 1;
			}
			else {
				array[i] = 0;
			}
		}
		super.print();
	}
	
	public static void main(String[] args) {
		int threshold = 50;	// 임계값 50
		BinaryArray bArray = new BinaryArray(10, threshold);
		// bArray 객체는 threshold 보다 크면 1, 아니면 0의 값만 가지는 배열처럼 행동
		
		Scanner scn = new Scanner(System.in);
		System.out.print(">>");
		for(int i=0; i<bArray.length(); i++) {	// bArray.length()는 10
			int n = scn.nextInt();
			bArray.add(n);
		}
		bArray.print();
		scn.close();
	}
}

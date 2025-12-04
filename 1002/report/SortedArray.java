/*
 * 10. BaseArray 클래스를 상속받아 값이 큰 순서로 배열을 항상 유지하는 SortedArray를 작성하라.
 * 다음 main() 메소드를 포함하고 실행 결과와 같이 출력되게 하라.
 */

package Chapter5;

import java.util.Scanner;

class BaseArray_01 {
	protected int array [];	// 배열 메모리
	protected int nextIndex = 0;	// 다음에 삽입할 배열에 대한 인덱스
	
	public BaseArray_01(int size) {
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

public class SortedArray extends BaseArray_01 {
	SortedArray(int size) {
		super(size);
	}
	
	@Override
	public void add(int n) {
		if(super.nextIndex == super.array.length) {
			return;
		}
		int i = super.nextIndex - 1;
	
	    while (i >= 0 && super.array[i] > n) {
	        super.array[i + 1] = super.array[i];
	        i--;
	    }
	    super.array[i + 1] = n;

		super.nextIndex++;
	}
	
	public static void main(String[] args) {
		SortedArray sArray = new SortedArray(10);
		Scanner scn = new Scanner(System.in);
		System.out.print(">>");
		for(int i=0; i<sArray.length(); i++) {	// sArray.length()는 10
			int n = scn.nextInt();
			sArray.add(n);
		}
		sArray.print();
		scn.close();
	}
}

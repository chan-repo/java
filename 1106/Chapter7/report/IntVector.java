/*
 * 1. Scanner 클래스로 -1이 입력될 때까지 양의 정수를 입력받아 벡터 Vector<Integer>에 저장하라.
 * 그리고 벡터를 검색하여 가장 작은 수를 출력하는 프로그램을 작성하라.
 */

package Practice;

import java.util.Scanner;
import java.util.Vector;

public class IntVector {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Vector<Integer> numbers = new Vector<Integer>();
	
		System.out.print("정수 입력(-1이면 입력 끝)>>");
		while(true) {
			int num = scanner.nextInt();
			if(num==-1) {
				break;
			}
			numbers.add(num);
		}
		int min = numbers.get(0);
		
		for(int i=0; i<numbers.size(); i++) {
			if(min>numbers.get(i)) {
				min=numbers.get(i);
			}
		}
		
		System.out.println("제일 작은 수는 "+ min);
		scanner.close();
	}

}
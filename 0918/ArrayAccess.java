package array;

import java.util.Scanner;

public class ArrayAccess {
	public static void main(String[] args) {
		int intArray[] = new int[5];
		Scanner scn = new Scanner(System.in);
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		System.out.print("정수 값 " + intArray.length + "개 입력 : ");
		for(int i =0; i < intArray.length; i++) {
			intArray[i] = scn.nextInt();
			if(intArray[i] > max) {
				max = intArray[i];
			}
			if(intArray[i] < min) {
				min = intArray[i];
			}
		}
		System.out.println("최댓값 = " + max);
		System.out.println("최솟값 = " + min);
	}
}

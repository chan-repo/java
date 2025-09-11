package chapter2;

import java.util.Scanner;

public class Rectangle {
	public static void main(String[] args) {
		/*
		 * 사각형의 height와 width를 입력받아 면적을 출력하는 프로그램 작성 
		 */
		Scanner scn = new Scanner(System.in);
		System.out.print("높이 입력 ");
		int height = scn.nextInt();
		System.out.print("넓이 입력");
		int width = scn.nextInt();
		int area = height * width;
		System.out.println("높이 = "+height+", 넓이 = "+width+"안 사각형의 면젹 = "+area);
		scn.close();
	}

}

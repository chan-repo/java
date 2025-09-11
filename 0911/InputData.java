package chapter2;

import java.util.Scanner;

public class InputData {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("이름, 도시, 나이, 체중, 독신여부를 빈칸으로 구분하여 입력하세요.");
		String name = scanner.next();
		String city = scanner.next();
		int age = scanner.nextInt();
		double weight = scanner.nextDouble();
		boolean single = scanner.nextBoolean();
		
		System.out.println("이름 = " + name);
		System.out.println("도시 = " + city);
		System.out.println("나이 = " + age);
		System.out.println("무게 = " + weight);
		System.out.println("싱글 = " + single);
	}
}

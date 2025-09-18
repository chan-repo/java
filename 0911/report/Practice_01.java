//$1=1200원입니다. 달러를 입력하세요>>253
//$253은 303600원입니다.

package basic;

import java.util.Scanner;

public class scanner {

	public static void main(String[] args) {
		System.out.print("$1=1200원입니다. 달러를 입력하세요>>");
		
		Scanner s = new Scanner(System.in);
		int dollar = s.nextInt();
		int won = dollar * 1200;
		
		System.out.print("$" + dollar + "는 " + won + "원입니다.");
		
		s.close();
	}

}

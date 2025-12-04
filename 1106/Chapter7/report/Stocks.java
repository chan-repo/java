/*
 * 3. "그만"이 입력될 때까지 주식 종목과 주가를 입력받아 해시맵에 저장하고, 다시 주식 종목을 입력받아 주가를 출력하는 프로그램을 작성하라.
 * 해시맵은 HashMap<String, Integer>을 이용하면 된다.
 */

package Practice;

import java.util.HashMap;
import java.util.Scanner;

public class Stocks {
	private HashMap<String, Integer> h = new HashMap<String, Integer>();
	Scanner scanner = new Scanner(System.in);
	
	public void read() {
		System.out.println("주식 종목과 주가를 입력하세요(예:삼송전자 75000)");
		while(true) {
			System.out.print("종목, 주가>>");
			String name = scanner.next();
			if(name.equals("그만")) {
				return;
			}
			
			int value = scanner.nextInt();
			h.put(name, value);
		}
	}

	public void search() {
		System.out.println("주가를 검색합니다.");
		while(true) {
			System.out.print("종목>>");
			String input = scanner.next();
			if(input.equals("그만")) {
				return;
			}
			find(input);
		}	
	}
		
	public boolean find(String input) {
	    if(h.containsKey(input)) {
	        System.out.println(input + "의 주가는 " + h.get(input) + "원");
	        return true;
	    }
	    System.out.println(input + "은 없는 종목입니다.");
	    return false;
	}

	public static void main(String[] args) {
		Stocks stocks = new Stocks();
		stocks.read();
		stocks.search();
	}

}

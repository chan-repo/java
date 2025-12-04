/*
 * 5. 항공사에서 고객의 마일리지를 관리하는 프로그램을 해시맵을 이용하여 작성하라.
 * 프로그램은 고객의 이름과 마일리지를 함께 저장하며, 마일리지는 누적 저장된다.
 */

package Practice;

import java.util.HashMap;
import java.util.Scanner;

public class AirlineMileage {
	private HashMap<String, Integer> h = new HashMap<String, Integer>();
	Scanner scanner = new Scanner(System.in);

	public void printAll() {
	    System.out.print("(");
	    int count = 0;
	    for(String key : h.keySet()) {
	        System.out.print(key + "," + h.get(key) + ")");
	        count++;
	        if(count < h.size()) {
	            System.out.print("(");
	        }
	    }
	    System.out.println();
	}
	
	public void calculate() {  
		String maxUser = "";
		int maxMileage = 0;
		
		for(String name : h.keySet()) {  
			int mileage = h.get(name);   
			if(maxMileage < mileage) {
				maxMileage = mileage;
				maxUser = name;
			}
		}
		if(!maxUser.equals("")) {
			System.out.println("가장 마일리지가 높은 고객은 " + maxUser + "입니다.");
		}
	}
	
	public void run() {
	    System.out.println("*** 마일리지 관리 프로그램입니다.***");
	    while(true) {
	        System.out.print("이름과 마일리지>>");
	        String name = scanner.next(); 
	        if(name.equals("그만")) {
	        	break;
	        }
	        int mileage = scanner.nextInt();
	        
	        // 기존 마일리지가 있다면 누적
	        if(h.containsKey(name)) {
	            int currentMileage = h.get(name);
	            h.put(name, currentMileage + mileage);
	        } else {
	            h.put(name, mileage);
	        }
	    }
	    printAll();
	    calculate();  
	    System.out.println("프로그램을 종료합니다.");
	}
	
	public static void main(String[] args) {
		AirlineMileage am = new AirlineMileage();
		am.run();
	}
}
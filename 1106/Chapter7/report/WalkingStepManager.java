/*
 * 11. 사람별로 매일의 걸음 수를 기록하는 프로그램을 작성하라. 이름을 '키'로 하고 걸음 수를 저장하는
 * ArrayList를 '값'으로 하는 해시맵을 활용하라. 프로그램 내에서 황기태, 이재문, 정인환의 특정 사람으로 고정하지 말라.
 */

package Practice;

import java.util.HashMap;
import java.util.Scanner;
import java.util.*;

public class WalkingStepManager {
	private HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
	private Scanner scanner = new Scanner(System.in);

	public WalkingStepManager() { }
	
	private void read() {
		while(true) {
			System.out.print("이름과 걸음수>>");
			String line = scanner.nextLine(); 
			if(line.equals("그만"))
				break;	
			
			String [] tokens = line.split(" "); 
			String name = tokens[0];
			ArrayList<Integer> list = map.get(name); 
			if(list == null) { 
				list = new ArrayList<Integer>(); 
				map.put(name, list); 
			}
		
			for(int i=0; i<tokens.length - 1; i++) 
				list.add(Integer.parseInt(tokens[i+1]));
		}	
	}
	
	private void decideBest() {
		Set<String> keys = map.keySet(); 
		Iterator<String> it = keys.iterator();
		String bestName = ""; 
		int bestWalkSum = 0; 
		while(it.hasNext()) {
			String name = it.next();
			ArrayList<Integer> list = map.get(name); 
			
			int sum = 0;
			for(int walk : list) 
				sum += walk;
			
			if(bestWalkSum < sum) {
				bestWalkSum = sum;
				bestName = name;
			}
		}		
		System.out.println("걸음수가 가장 많은 사람은 " + bestName + " " + bestWalkSum + "보");
	}

	private void search() {
		while(true) {
			System.out.print("검색할 이름>>");
			String name = scanner.next(); 
			if(name.equals("그만"))
				break;		
			
			ArrayList<Integer> list = map.get(name);
			if(list == null) { 
				System.out.println(name + "은 없는 학생입니다.");
				continue;
			}
			
			int sum = 0;
			for(int score : list) {
				System.out.print(score + " ");
				sum += score;
			}
			
			System.out.println("평균 " + sum/list.size());			
		}	
	}
	
	public void run() {
		read();
		decideBest();
		search();
	}
	
	public static void main(String[] args) {
		WalkingStepManager man = new WalkingStepManager();
		man.run();
	}
}
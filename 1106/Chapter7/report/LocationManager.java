/*
 * 7. 도시 이름, 위도, 경도 정보를 가진 Location 클래스를 작성하고 다음 문제를 풀어라.
 * (1) 도시 이름을 '키'로 하는 HashMap<String, Location> 컬렉션을 만들고,
 * 사용자로부터 입력 받아 4개의 도시를 저장하라. 그리고 도시 이름으로 검색하는 LocationManager 클래스를 작성하라.
 */

package Practice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class LocationManager {

	private Scanner scanner = new Scanner(System.in);
	private HashMap<String, Location> locMap = new HashMap<String, Location>();
	
	private void read() {
		System.out.println("도시,경도,위도를 입력하세요.");
		for (int i=0; i<4; i++) {
			System.out.print(">> ");
			String text = scanner.nextLine();
			StringTokenizer st = new StringTokenizer(text, ",");
			String city = st.nextToken().trim();
			double logitude = Double.parseDouble(st.nextToken().trim());
			double latitude = Double.parseDouble(st.nextToken().trim());
			
			Location loc = new Location(city, logitude, latitude);
			locMap.put(city, loc); //해시맵에 저장
		}			
	}
	
	private void printAll() {
		Set<String> key = locMap.keySet();
		Iterator<String> it = key.iterator();
		System.out.println("---------------------------");
		while (it.hasNext()) {
			String city = it.next(); 
			Location loc = locMap.get(city); 

			System.out.print(loc.getCity() + "\t");
			System.out.print(loc.getLongitude() + "\t");
			System.out.println(loc.getLatitude());
		}
		System.out.println("---------------------------");		
	}

	private void processQuery() {
		while(true) {
			System.out.print("도시 이름 >> ");
			String city = scanner.nextLine(); 
			if(city.equals("그만"))
				return; // 종료
			
			Location loc = locMap.get(city);
			if(loc == null) {
				System.out.println(city + "는 없습니다.");
			}
			else {
				System.out.print(loc.getCity()  + "\t");
				System.out.print(loc.getLongitude() + "\t");
				System.out.println(loc.getLatitude());
			}
		}
	}
	
	public void run() {
		read();
		printAll();
		processQuery();
	}
	
	public static void main (String[] args) {
		LocationManager man = new LocationManager();
		man.run();
	}

}
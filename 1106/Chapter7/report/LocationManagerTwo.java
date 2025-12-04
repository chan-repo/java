/*
 * 7. 도시 이름, 위도, 경도 정보를 가진 Location 클래스를 작성하고 다음 문제를 풀어라.
 * (2) HashMap<String, Location> 대신 ArrayList<Location>를 이용하여 다시 작성하라.
 */

package Practice;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LocationManagerTwo {

	private Scanner scanner = new Scanner(System.in);
	private ArrayList<String> cityList = new ArrayList<String>();      // 도시 이름 리스트
	private ArrayList<Location> locList = new ArrayList<Location>();   // 도시 정보 리스트
	
	private void read() {
		System.out.println("도시,경도,위도를 입력하세요.");
		for (int i = 0; i < 4; i++) {
			System.out.print(">> ");
			String text = scanner.nextLine();
			StringTokenizer st = new StringTokenizer(text, ",");
			
			String city = st.nextToken().trim();
			double longitude = Double.parseDouble(st.nextToken().trim());
			double latitude = Double.parseDouble(st.nextToken().trim());
			
			cityList.add(city);
			locList.add(new Location(city, longitude, latitude));
		}
	}
	
	private void printAll() {
		System.out.println("---------------------------");
		for (int i = 0; i < cityList.size(); i++) {
			Location loc = locList.get(i);
			System.out.print(cityList.get(i) + "\t");
			System.out.print(loc.getLongitude() + "\t");
			System.out.println(loc.getLatitude());
		}
		System.out.println("---------------------------");
	}

	private void processQuery() {
		while (true) {
			System.out.print("도시 이름 >> ");
			String city = scanner.nextLine();
			if (city.equals("그만"))
				return;

			boolean found = false;
			for (int i = 0; i < cityList.size(); i++) {
				if (cityList.get(i).equals(city)) {
					Location loc = locList.get(i);
					System.out.print(loc.getCity() + "\t");
					System.out.print(loc.getLongitude() + "\t");
					System.out.println(loc.getLatitude());
					found = true;
					break;
				}
			}
			if (!found)
				System.out.println(city + "는 없습니다.");
		}
	}
	
	public void run() {
		read();
		printAll();
		processQuery();
	}
	
	public static void main(String[] args) {
		LocationManagerTwo man = new LocationManagerTwo();
		man.run();
	}
}

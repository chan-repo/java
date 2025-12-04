/*
 * 8. 학생 정보를 나타내는 Student 클래스에서는 이름, 전공, 학번, 학점 평균을 저장하는 필드가 있다.
 * 다음 조건에 맞춘 프로그램을 작성하라.
 * (2) Vector<Student> 대신 '학생 이름'을 '키'로 하는 HashMap<String, Student> 컬렉션을
 * 활용하여 다시 프로그램을 작성하라. HashMap을 사용하여 저장된 학생들을 모두 출력하면
 * 저장 순으로 출력되지 않을 수 있음에 유의하라.
 */

package Practice;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StudentManagerTwo {
	private Scanner scanner = new Scanner(System.in);
	private HashMap<String, Student> students = new HashMap<String, Student>(); // 이름 → Student 객체
	
	private void read() {
		System.out.println("4명 이름, 전공, 학번, 학점 입력");
		for (int i = 0; i < 4; i++) {
			System.out.print(">> ");
			String text = scanner.nextLine();
			StringTokenizer st = new StringTokenizer(text, ",");
			
			String name = st.nextToken().trim();
			String major = st.nextToken().trim();
			int studentId = Integer.parseInt(st.nextToken().trim());
			double grade = Double.parseDouble(st.nextToken().trim());
			
			Student std = new Student(name, major, studentId, grade);
			students.put(name, std); // HashMap에 이름을 key로 저장
		}
	}
	
	private void printAll() {
		System.out.println("---------------------------");
		for (String name : students.keySet()) {
			Student std = students.get(name);
			System.out.print("이름:" + std.getName() + "\t");
			System.out.print("전공:" + std.getMajor() + "\t");
			System.out.print("학번:" + std.getStudentId() + "\t");
			System.out.println("학점평균:" + std.getGrade());
		}
		System.out.println("---------------------------");
	}

	private void printScholar() {
		System.out.print("장학생: ");
		for (Student std : students.values()) {
			if (std.getGrade() > 4.0)
				System.out.print(std.getName() + " ");
		}
		System.out.println();
		System.out.println("---------------------------");
	}
	
	private void processQuery() {
		while (true) {
			System.out.print("학생 이름 >> ");
			String name = scanner.nextLine();
			if (name.equals("그만")) 
				return;
			
			Student std = students.get(name);
			if (std == null) {
				System.out.println(name + " 학생이 없습니다.");
			} else {
				System.out.print(std.getName() + ", ");
				System.out.print(std.getMajor() + ", ");
				System.out.print(std.getStudentId() + ", ");
				System.out.println(std.getGrade());
			}
		}
	}
	
	public void run() {
		read();
		printAll();
		printScholar();
		processQuery();
	}
	
	public static void main(String[] args) {
		StudentManagerTwo app = new StudentManagerTwo();
		app.run();
	}
}
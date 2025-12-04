/*
 * 8. 학생 정보를 나타내는 Student 클래스에서는 이름, 전공, 학번, 학점 평균을 저장하는 필드가 있다.
 * 다음 조건에 맞춘 프로그램을 작성하라.
 * (1) 학생마다 Student 객체를 생성하고 4명의 학생 정보를 Vector<Student> 컬렉션에 저장한 후,
 * 벡터에 저장된 모든 학생의 정보를 출력하고, 장학생(학점 평균 4.0 이상)을 선발하여 출력하라.
 * 또한 학생의 이름을 입력받아 학생의 정보를 출력하라.
 */

package Practice;

import java.util.*;

public class StudentManager {
	private Scanner scanner = new Scanner(System.in);
	private Vector<Student> students  = new Vector<Student>();
			
	private void read() {
		System.out.println("4명 이름, 전공, 학번, 학점 입력");
		for (int i=0; i<4; i++) {
			System.out.print(">> ");
			String text = scanner.nextLine();
			StringTokenizer st = new StringTokenizer(text, ",");
			String name = st.nextToken().trim();
			String major = st.nextToken().trim();
			int studentId = Integer.parseInt(st.nextToken().trim());
			double grade = Double.parseDouble(st.nextToken().trim());
			
			Student std = new Student(name, major, studentId, grade);
			students.add(std);
		}			
	}
	
	private void printAll() {
		System.out.println("---------------------------");
		for(Student std : students) {
			System.out.print("이름:" + std.getName() + "\t");
			System.out.print("전공:" + std.getMajor() + "\t");
			System.out.print("학번:" + std.getStudentId() + "\t");
			System.out.println("학점평균:" + std.getGrade());
		}
		System.out.println("---------------------------");		
	}

	private void printScholar(){
		System.out.print("장학생: ");
		for(Student std : students){
			if(std.getGrade() > 4.0)
				System.out.print(std.getName() + " ");
		}
		System.out.println();
		System.out.println("---------------------------");		
	}
	
	private void processQuery() {
		while(true) {
			boolean isFound = false;
			System.out.print("학생 이름 >> ");
			String name = scanner.nextLine();
			if(name.equals("그만")) return; // 종료
			
			for(Student std : students) {
				if(name.equals(std.getName())) {
					System.out.print(std.getName() + ", ");
					System.out.print(std.getMajor() + ", ");
					System.out.print(std.getStudentId() + ", ");
					System.out.println(std.getGrade());
					isFound = true;
					break;
				}
			}
			if(!isFound) System.out.println(name+" 학생이 없습니다.");
		}
	}
	
	public void run() {
		read();
		printAll();
		printScholar();
		processQuery();
	}
	
	public static void main (String[] args) {
		StudentManager app = new StudentManager();
		app.run();
	}

}
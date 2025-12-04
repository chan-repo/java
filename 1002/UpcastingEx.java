package basic;
public class UpcastingEx {
	public static void main(String[] args) {
		Person p;
		Student s = new Student("홍길동");
		p = s;
		// p는 person 객체(부모) 이므로 student 객체(자식)를 담을 수 있고, 권한은 Person과 동일하다.(단, p와 s는 상속 관계여야 한다.)
	}
}

class Person {
	String name;
	String id;
	
	public Person(String name) {
		this.name = name;
	}
}

class Student extends Person {
	String grade;
	String department;
	
	public Student(String name) {
		super(name);
	}
}
package week6.report;

class Student {
    private final String name;
    private final int id;

    public Student(String name, int id) {
        this.name=name;
        this.id=id;
    }

    @Override
    public String toString() {
        return "학번이 " + id + "인 " + name;
    }

    @Override
    public boolean equals(Object obj) {
        Student student = (Student)obj;
        return this.name.equals(student.name) && this.id == student.id;
    }

}

public class StudentApp {

    public static void main(String[] args) {
        Student a = new Student("황기태", 23);
        Student b = new Student("황기태", 77);
        System.out.println(a);
        if(a.equals(b))
            System.out.println("같은 학생입니다.");
        else
            System.out.println("다른 학생입니다.");
    }

}
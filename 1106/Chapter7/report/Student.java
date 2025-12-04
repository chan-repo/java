package Practice;

public class Student {
	private String name;
	private String major;
	private int studentId; // 학번
	private double grade; // 학점
	
	public Student(String name, String major, int studentId, double grade) {
		this.name=name;
		this.major=major;
		this.studentId=studentId;
		this.grade=grade;
	}
	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	public void setMajor(String major) {this.major = major;}
	public String getMajor() {return major;}
	public void setStudentId(int studentId) {this.studentId = studentId;}
	public int getStudentId() {return studentId;}
	public void setGrade(double grade) {this.grade = grade;}
	public double getGrade() {return grade;}
}
package Intro;

public class TestArg {
	int a, b, c, d;
	
	public TestArg(int a, int b, int c, int d) {
		this.a = a; this.b = b; this.c = c; this.d = d;
	}
	public TestArg(int a, int b, int c) {
		this(a, b, c, 10);
	}
	public TestArg(int a, int b) {
		this(a, b, 20, 10);
	}
	public TestArg(int a) {
		this(a, 30, 20, 10);
	}
	public TestArg() {
		this(40, 30, 20, 10);
	}

	public static void main(String[] args) {
		TestArg ta1 = new TestArg();
		TestArg ta2 = new TestArg(1);
		TestArg ta3 = new TestArg(1,2);
		TestArg ta4 = new TestArg(1,2,3);
		TestArg ta5 = new TestArg(1,2,3,4);
		
		System.out.println(ta1.a + " " + ta1.b + " " + ta1.c + " " + ta1.d);
		System.out.println(ta2.a + " " + ta2.b + " " + ta2.c + " " + ta2.d);
		System.out.println(ta3.a + " " + ta3.b + " " + ta3.c + " " + ta3.d);
		System.out.println(ta4.a + " " + ta4.b + " " + ta4.c + " " + ta4.d);
		System.out.println(ta5.a + " " + ta5.b + " " + ta5.c + " " + ta5.d);
		
	}
}

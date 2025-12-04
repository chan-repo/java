package control;

public class DoWhileExam01 {
	public static void main(String[] args) {
		char c = 'A';	// 'a' : 61h, 97 / 'A' : 41h, 65  대문자로 바꿀 때 32를 빼거나 더함.
		
		do {
			System.out.print(c);
			c = (char)(c + 1);
		} while (c <= 'Z');
	}
}

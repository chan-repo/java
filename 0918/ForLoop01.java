package control;

public class ForLoop01 {
	public static void main(String[] args) {
		char c = '*';
		
		for(int i = 1; i <= 5; i++) {
			for(int j = 1; j <= i; j++) {
				System.out.print(c);
			}
			for(int k = 1; k <= 5-i; k++) {
				System.out.print((char)(c-10));
			}
			System.out.println();
		}
	}
}

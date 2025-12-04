package control;

public class ForLoop02 {
	public static void main(String[] args) {
		char c = ' ';
		
		for(int i = 1; i <= 5; i++) {
			for(int j = 1; j <= 5-i; j++) {
				System.out.print(c);
			}
			for(int k = 1; k <= i; k++) {
				System.out.print((char)(c+10));
			}
			System.out.println();
		}
	}
}

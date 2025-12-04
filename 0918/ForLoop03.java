package control;

public class ForLoop03 {
	public static void main(String[] args) {
		char c = ' ';
		int i, j, k, l;
		
		for(i = 1; i <= 5; i++) {
			for(j = 1; j <= 5-i; j++) {
				System.out.print(c);
			}
			for(k = 1; k <= 9-((j-1)*2); k++) {
				System.out.print((char)(c+10));
			}
			for(l = 5; l <= 5-i; l++) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
}

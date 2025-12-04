package Intro;

public class Book {
	String title;
	String author;
	
	public Book(String t) {
		title = t;
		author = "작자미상";
	}
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	public static void main(String[] args) {
		Book littleprince = new Book("어린왕자", "생텍쥐페리");
		Book lovestory = new Book("춘향전");
		
		System.out.println(littleprince.author + "	" + littleprince.title);
		System.out.println(lovestory.author + "	" + lovestory.title);
	}
}

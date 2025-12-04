package week6.report;

class Book {
    private final String author;
    private final String name;
    private final String buyer;

    public Book(String author, String name, String buyer) {
        this.author = author;
        this.name = name;
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return buyer+"이(가) 구입한 도서: " + author+ "의 " + name;
    }

    @Override
    public boolean equals(Object obj) {
        Book b = (Book)obj;
        if(author.equals(b.author) && name.equals(b.name)) {
            return true;
        }
        else {
            return false;
        }
    }

}

public class BookApp {

    public static void main(String[] args) {
        Book a = new Book("황기태", "명품자바", "김하진");
        Book b = new Book("황기태", "명품자바", "하여린");
        System.out.println(a);
        System.out.println(b);

        if(a.equals(b))
            System.out.println("같은 책");
        else
            System.out.println("다른 책");
    }

}
package week4.report;

class Song {
    String title;
    String name;
    int year;
    String country;

    Song(String title, String name, int year, String country) {
        this.title = title;
        this.name = name;
        this.year = year;
        this.country = country;
    }

    void show(){
        System.out.println(year + "년 " + country + "의 " + name + "가 부른 " + title);
    }
}

public class week4_5 {
    public static void main(String[] args) {
        Song s1 = new Song("가로수 그늘 아래 서면", "이문세", 1988, "한국");
        s1.show();
    }
}

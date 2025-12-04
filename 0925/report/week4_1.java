package week4.report;
class Tv {
    String name;
    int won;
    int size;

    Tv(String n, int s, int w) {
        this.name = n;
        this.won = w;
        this.size = s;
    }

    void show() {  // static 제거
        System.out.println(name + "에서 만든 " + won + "만원짜리의 " + size + "인치 TV");
    }
}

public class week4_1 {
    public static void main(String[] args) {
        Tv tv = new Tv("samsung", 50, 300);
        tv.show();   // 객체 메소드로 호출
    }
}

package week4.report;

class Rectangle{
    int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isSquare() {
        return (width==height);
    }

    public boolean contains(Rectangle r) {
        return (r.x >= this.x && r.y >= this.y &&
                (r.x + r.width) <= (this.x + this.width) &&
                (r.y + r.height) <= (this.y + this.height));
    }

    public void show() {
        System.out.println("(" + x + "," + y + ")에서 크기 " + width + "x" + height + " 사각형");
    }
}

public class week4_6 {
    public static void main(String[] args) {
        Rectangle a = new Rectangle(3, 3, 6, 6);  // (3,3) 점에서 6x6 크기의 사각형
        Rectangle b = new Rectangle(4, 4, 2, 3);  // (4,4) 점에서 2x3 크기의 사각형

        a.show();
        if(a.isSquare()) System.out.println("a는 정사각형입니다.");
        else System.out.println("a는 직사각형입니다.");
        if(a.contains(b)) System.out.println("a는 b를 포함합니다.");
    }
}

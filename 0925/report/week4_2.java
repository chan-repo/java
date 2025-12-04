package week4.report;

class Cube {
    int height;
    int width;
    int depth;

    Cube(int w, int h, int d) {
        height = h;
        width = w;
        depth = d;
    }

    int getVolume() {
        return (height * width * depth);
    }

    void increase(int w, int h, int d) {
        height += h;
        width += w;
        depth += d;
    }

    boolean isZero(){
        return height * width * depth == 0;
    }
}

public class week4_2 {
    public static void main(String[] args) {
        Cube cube = new Cube(1, 2, 3);
        System.out.println("큐브의 부피는 " + cube.getVolume());
        cube.increase(1, 2, 3);
        System.out.println("큐브의 부피는 " + cube.getVolume());
        if(cube.isZero()){
            System.out.println("큐브의 부피는 0");
        }
        else{
            System.out.println("큐브의 부피는 0이 아님");
        }
    }
}

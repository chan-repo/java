package week4.report;

class VArray {
    int size;
    int[] array;

    public VArray(int num) {
        size = 0;
        array = new int[num];
    }

    public int capacity() {
        return array.length;
    }

    public int size() {
        return size;
    }

    public void add(int n) {
        if(size == array.length) {
            resize();
        }
        array[size++] = n;
    }

    public void insert(int idx, int n) {
        if(idx<0 || idx>size) return;

        if (size == array.length) {
            resize();
        }
        for (int i = size; i > idx; i--) {
            array[i] = array[i - 1];
        }
        array[idx] = n;
        size++;
    }

    private void resize() {
        int newCapacity = array.length * 2;
        int[] newArray = new int[newCapacity];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    public void remove(int idx) {
        if(idx >= size || idx < 0) return;
        for (int i = idx; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = 0;
    }

    public void printAll() {
        for(int i=0; i<size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

public class week4_14 {

    public static void main(String[] args) {
        VArray v = new VArray(5); // 5개의 정수를 저장하는 가변 배열 객체 생성
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());

        for (int i = 0; i < 7; i++) // 7개 저장
            v.add(i); // 배열에 순서대로 정수 i 값 저장
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();

        v.insert(3, 100); // 배열의 인덱스 3에 100 삽입
        v.insert(5, 200); // 배열의 인덱스 5에 200 삽입
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();

        v.remove(10); // 배열의 인덱스 10의 정수 삭제
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();

        for (int i = 50; i < 55; i++) // 5개 저장
            v.add(i); // 배열에 순서대로 정수 i 값 저장
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();
    }
}
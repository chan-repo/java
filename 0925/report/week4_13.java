package week4.report;

import java.util.Scanner;

class Seats {
    private String seat;

    public Seats() { seat = "---"; }
    public String getSeat() { return seat; }
    public void setSeat(String name) { seat = name; }
}

class Section {
    Seats seats[] = new Seats[10];

    public Section() {
        for (int i = 0; i < seats.length; i++)
            seats[i] = new Seats();
    }

    public void show() {
        for (int i = 0; i < seats.length; i++) {
            System.out.print(seats[i].getSeat() + " ");
        }
        System.out.println();
    }

    public void book(String name, int num) {
        if (num < 1 || num > seats.length) {
            System.out.println("잘못된 좌석 번호입니다.");
            return;
        }
        if (!seats[num - 1].getSeat().equals("---")) {
            System.out.println("이미 예약된 좌석입니다.");
            return;
        }
        seats[num - 1].setSeat(name);
    }

    public void cancel(String name) {
        boolean found = false;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i].getSeat().equals(name)) {
                seats[i].setSeat("---");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("예약자 이름을 찾을 수 없습니다.");
        }
    }
}

class Concert {
    Scanner scanner = new Scanner(System.in);
    Section sSection = new Section();
    Section aSection = new Section();
    Section bSection = new Section();

    public void inputBook() {
        System.out.print("좌석구분 S(1), A(2), B(3)>>");
        int section = scanner.nextInt();
        Section selectedSection;

        switch (section) {
            case 1:
                selectedSection = sSection;
                System.out.print("S>> ");
                break;
            case 2:
                selectedSection = aSection;
                System.out.print("A>> ");
                break;
            case 3:
                selectedSection = bSection;
                System.out.print("B>> ");
                break;
            default:
                System.out.println("유효하지 않은 섹션입니다.");
                return;
        }

        selectedSection.show();

        System.out.print("이름>>");
        String name = scanner.next();
        System.out.print("번호>>");
        int seatNum = scanner.nextInt();

        selectedSection.book(name, seatNum);
    }

    public void printAllSection() {
        System.out.print("S>> ");
        sSection.show();
        System.out.print("A>> ");
        aSection.show();
        System.out.print("B>> ");
        bSection.show();
        System.out.println("<<<조회를 완료하였습니다.>>>");
    }

    public void cancel() {
        System.out.print("좌석구분 S(1), A(2), B(3)>>");
        int section = scanner.nextInt();
        Section selectedSection;

        switch (section) {
            case 1: selectedSection = sSection; break;
            case 2: selectedSection = aSection; break;
            case 3: selectedSection = bSection; break;
            default:
                System.out.println("유효하지 않은 섹션입니다.");
                return;
        }

        selectedSection.show();

        System.out.print("이름>>");
        String name = scanner.next();

        selectedSection.cancel(name);
    }

}

public class week4_13 {
    public static void main(String[] args) {
        Concert concert = new Concert();
        Scanner scanner = new Scanner(System.in);

        System.out.println("명품콘서트홀 예약 시스템입니다.");

        while (true) {
            System.out.print("예약:1, 조회:2, 취소:3, 끝내기:4>>");
            int input = scanner.nextInt();

            switch (input) {
                case 1:
                    concert.inputBook();
                    break;
                case 2:
                    concert.printAllSection();
                    break;
                case 3:
                    concert.cancel();
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("유효한 메뉴의 숫자를 입력해주세요.");
                    break;
            }
        }
    }
}
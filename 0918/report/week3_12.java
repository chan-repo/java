package week3.report;

import java.util.Scanner;

public class week3_12 {
    public static void main(String[] args) {
        String boyMiddleList[] = {"기", "민", "용", "종", "현", "진", "재", "승", "소", "상", "지"};
        String boyLastList [] = {"태", "진", "광", "혁", "우", "철", "빈", "준", "구", "호", "석"};
        String girlMiddleList [] = {"은", "원", "경", "수", "현", "예", "여", "송", "서", "채", "하"};
        String girlLastList [] = {"진", "연", "경", "서", "리", "숙", "미", "원", "란", "희", "수"};
        Scanner sc = new Scanner(System.in);
        while(true) {
            int n = (int)(Math.random()*boyMiddleList.length);
            int n2 = (int)(Math.random()*boyMiddleList.length);
            System.out.print("남/여 선택 >>");
            String sex = sc.nextLine();

            if(!sex.equals("남") && !sex.equals("여")){
                System.out.println("남/여/그만 중에서 입력하세요!");
                sex = sc.nextLine();

                if(sex.equals("그만")) break;
            }

            if(sex.equals("남") || sex.equals("여")){
                String first = sc.nextLine();

                System.out.print("추천이름:");
                if(sex.equals("남")) System.out.println(first + boyMiddleList[n] + boyLastList[n2]);
                else System.out.println(first + girlMiddleList[n] + girlLastList[n2]);
            }
        }

    }
}

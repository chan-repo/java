/*
 * 4. 쇼핑 후 전체 경비를 계산하는 프로그램을 작성하라.
 * 구입 가능한 품목은 실행 예시와 같이, 고추장, 만두 등 8가지이다.
 * (2) HashMap<String, Integer> 대신, 품목과 가격을 ArrayList<String>과 ArrayList<Integer>에 각각 저장해두고,
 * 사용자가 구입한 물건들의 전체 가격을 계산하도록 작성하라.
 */

package Practice;

import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCostTwo {
    private ArrayList<String> itemNames = new ArrayList<String>();
    private ArrayList<Integer> itemPrices = new ArrayList<Integer>();
    Scanner scanner = new Scanner(System.in);

    public ShoppingCostTwo() {
        // 상품 이름과 가격을 각각 같은 인덱스에 맞춰 저장
        itemNames.add("고추장");  itemPrices.add(3000);
        itemNames.add("만두");    itemPrices.add(500);
        itemNames.add("새우깡");  itemPrices.add(1500);
        itemNames.add("콜라");    itemPrices.add(600);
        itemNames.add("참치캔");  itemPrices.add(2000);
        itemNames.add("치약");    itemPrices.add(1000);
        itemNames.add("연어");    itemPrices.add(2500);
        itemNames.add("삼겹살");  itemPrices.add(2500);
    }

    public void printAll() {
        System.out.print("[");
        for (int i = 0; i < itemNames.size(); i++) {
            System.out.print(itemNames.get(i) + "," + itemPrices.get(i) + "]");
            if (i < itemNames.size() - 1) System.out.print("[");
        }
        System.out.println();
    }

    public void run() {
        System.out.println("쇼핑 비용을 계산해드립니다. 구입 가능 물건과 가격은 다음과 같습니다.");
        printAll();
        while (true) {
            System.out.print("물건과 개수를 입력하세요>>");
            String s = scanner.nextLine();
            if (s.equals("그만")) break;
            calculate(s);
        }
    }

    public void calculate(String input) {
        String[] items = input.split(" ");
        int sum = 0;

        try {
            for (int i = 0; i < items.length; i += 2) {
                String item = items[i];
                int quantity = Integer.parseInt(items[i + 1]);
                int index = itemNames.indexOf(item);

                if (index != -1) {
                    sum += itemPrices.get(index) * quantity;
                } else {
                    System.out.println(item + "은 없는 상품입니다!");
                    return;
                }
            }
            System.out.println("전체 비용은 " + sum + "원입니다.");

        } catch (Exception e) {
            System.out.println("입력에 문제가 있습니다!");
        }
    }

    public static void main(String[] args) {
        ShoppingCostTwo sc = new ShoppingCostTwo();
        sc.run();
    }
}
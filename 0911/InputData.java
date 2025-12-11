import java.util.Scanner;

public class InputData {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("이름, 도시, 나이, 체중, 독신 여부를 빈칸으로 구분하여 입력해주세요.");
        System.out.println("이름을 입력해주세요.");
        String name = scanner.next();
        System.out.println("도시이름을 입력해주세요.");
        String city = scanner.next();
        System.out.println("나이를 입력해주세요.");
        int age = scanner.nextInt();
        System.out.println("체중을 입력해주세요.");
        double weight = scanner.nextDouble();
        System.out.println("독신여부를 입력해주세요. (true/false)");
        boolean single = scanner.nextBoolean();

        System.out.println("이름: " + name);
        System.out.println("도시: " + city);
        System.out.println("나이: " + age);
        System.out.println("체중: " + weight);
        System.out.println("독신여부: " + single);
    }
}

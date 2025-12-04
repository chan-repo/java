package week6.report;

import java.util.Scanner;
import java.util.Random;

public class WordQuiz {
    Scanner scanner = new Scanner(System.in);
    Random r = new Random();

    private String[] words = {"happy", "morning", "package", "together"};

    public WordQuiz() {
    }

    private void mixWord(int no) {
        char[] arr = words[no].toCharArray();

        for (int i = arr.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public void run() {
        System.out.println("10초 안에 단어를 맞추세요!!");
        for(int i=0; i<words.length; i++) {
            mixWord(i);
            double startTime = System.currentTimeMillis(); // 시작 시간
            System.out.print(">>");
            String answer = scanner.next();

            if(answer.equals("그만")) System.exit(0); // 종료

            double endTime = System.currentTimeMillis(); // 종료 시간
            double totalTime = (endTime-startTime)/1000; // 소요 시간

            if(totalTime>10)
                System.out.print("실패!!! 10초 초과. ");
            else if(words[i].equals(answer))
                System.out.print("성공!!! ");
            else
                System.out.print("실패!!! " + words[i] + " 입니다. ");

            System.out.println(totalTime + "초 경과");
        }

    }
    public static void main(String[] args) {
        WordQuiz quiz = new WordQuiz();
        quiz.run();
    }

}
/*
 * 10. 한 라인을 입력받아 각 문자열을 ArrayList<String>에 저장하고, ArrayList에 중복된 문자열을
 * 제거하여 중복된 문자열이 없는 상태로 만들라. 그러고 나서 이를 출력하는 프로그램을 작성하라.
 */

package Practice;

import java.util.*;

public class RemoveDuplicateWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
            System.out.print("문자열을 입력하세요>>");
            String input = scanner.nextLine();
            
            if(input.equals("그만")) break;
            
            String[] words = input.split(" ");
            ArrayList<String> uniqueWords = new ArrayList<>();
            
            // 중복되지 않은 단어만 ArrayList에 추가
            for(String word : words) {
                if(!uniqueWords.contains(word)) {
                    uniqueWords.add(word);
                }
            }
            
            // 결과 출력
            for(String word : uniqueWords) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
        
        scanner.close();
    }
}
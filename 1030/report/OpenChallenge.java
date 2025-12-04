package week6.report;

import java.util.Scanner;

/*
    텍스트를 키보드로 입력받아 알파벳이 아닌 문자는 제외하고 영문자 히스토그램을 만들어보자.
    대문자와 소문자는 모두 같은것으로 간주하고, 세미콜론(;)만 있는 라인을 만나면 입력의 끝으로 해석하다.
 */
public class OpenChallenge {
    static String readString(){
        StringBuffer sb = new StringBuffer();
        Scanner sc = new Scanner(System.in);
        while(true){
            String line = sc.nextLine();
            if(line.equals(";")){
                break;
            }
            sb.append(line);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        int cnt[]= new int[26];

        String res = readString().toLowerCase();

        for(int i=0;i<res.length();i++)
        {
            if(res.charAt(i)>='a'&&res.charAt(i)<='z')
                cnt[(int)(res.charAt(i)-'a')]++;
        }

        for(int i=0;i<26;i++)
        {
            System.out.print((char)(i+'a'));
            for(int j=0;j<cnt[i];j++)
                System.out.print("-");
            System.out.println();
        }
    }
}

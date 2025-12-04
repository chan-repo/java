package week4.report;

import java.util.Scanner;

class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void upScore() {
        score++;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void show() {
        System.out.print(name+ ":" + score + " ");
    }

}

class GuessGame{
    private int numOfPlayer; // 플레이어 수
    private Player[] players;
    private int[] answers;	// 플레이어 입력 저장
    private Scanner scanner = new Scanner(System.in);

    public GuessGame() {
        System.out.println("*** 예측 게임을 시작합니다. ***");
        inputPlayers();
        playGame();
    }

    // 플레이어 입력
    public void inputPlayers() {
        System.out.print("게임에 참여할 선수 수>>");
        this.numOfPlayer = scanner.nextInt();
        players = new Player[numOfPlayer];
        answers = new int[numOfPlayer];

        for(int i=0; i<numOfPlayer; i++) {
            System.out.print("선수 이름>> ");
            players[i] = new Player(scanner.next());
        }
    }

    // 난수 생성
    public int generateRandomNumber() {
        System.out.println("1~100사이의 숫자가 결정되었습니다. 선수들은 맞추어 보세요.");
        int r = (int)(Math.random()*100+1);
        return r;
    }

    // 플레이어 답 입력
    public void inputAnswers() {
        for(int i=0; i<numOfPlayer; i++) {
            System.out.print(players[i].getName() + ">>");
            answers[i] = scanner.nextInt();
        }
    }

    // 게임 종료 시 총점 및 승리자 출력
    public void showResult() {
        int winnerIndex=0;

        for(int i=0; i<numOfPlayer; i++) {
            if(players[i].getScore() > players[winnerIndex].getScore())
                winnerIndex = i;
            players[i].show();
        }
        System.out.println();
        System.out.println(players[winnerIndex].getName() + "이 최종 승리하였습니다.");
    }

    public void playGame() {
        while (true) {
            int hiddenAnswer = generateRandomNumber();
            inputAnswers();

            int correctIndex = 0;
            int diff = Math.abs(hiddenAnswer-answers[0]);

            for (int j = 1; j < numOfPlayer; j++) {
                int currentDiff = Math.abs(hiddenAnswer - answers[j]);
                if (currentDiff < diff) {
                    correctIndex = j;
                    diff = currentDiff;
                }
            }
            // 라운드 결과 출력
            players[correctIndex].upScore();
            System.out.print("정답은 " + hiddenAnswer + ". ");
            System.out.println( players[correctIndex].getName() + "이 이겼습니다. 승점 1점 확보!");
            System.out.print("계속하려면 yes 입력>>");
            // 게임 종료
            if(!scanner.next().equals("yes")) {
                showResult();
                break;
            }
        }
        scanner.close();
    }
}

public class week4_9 {

    public static void main(String[] args) {
        GuessGame game = new GuessGame();
    }

}

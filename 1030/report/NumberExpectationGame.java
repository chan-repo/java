package week6.report;


import java.util.*;

public class NumberExpectationGame {
    private List<Player2> players;
    private int[] numbers;
    Scanner scanner = new Scanner(System.in);
    Random r = new Random();

    public NumberExpectationGame() {
        System.out.print("게임에 참여할 선수들 이름>>");
        String names = scanner.nextLine();

        StringTokenizer st = new StringTokenizer(names, " ");
        players = new ArrayList<>();
        numbers = new int[15];

        int i = 0;
        while(st.hasMoreTokens()) {
            String playerName = st.nextToken();
            players.add(new Player2(playerName));
            i++;
        }
    }

    // 난수 생성 후 출력
    public void randomNumbers() {
        for(int i=0; i<numbers.length; i++) {
            numbers[i] = r.nextInt(10) + 1;
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }

    public void getEnterKey() {
        System.out.print("Enter키 입력>>");
        scanner.nextLine();
    }

    // 맞춘 개수 count 후 출력
    public void countMatchNumber() {
        for(Player2 p : players) {
            int count = 0;
            for(int j = 0; j < numbers.length; j++) {
                if(numbers[j] == p.getNumber())
                    count++;
            }
            p.setMatchCount(count);
            System.out.println("[" + p.getName() + "] 맞춘 개수: " + p.getMatchCount());
        }
    }

    public void checkWinner() {
        final int maxCount = findMaxCount();

        players.removeIf(p -> p.getMatchCount() == maxCount);

        if(players.size() == 1) {
            System.out.println("최종 패자는 " + players.get(0).getName() + "입니다.");
            System.exit(0);
        } else {
            System.out.print("현재 패자들: ");
            for(int i = 0; i < players.size(); i++) {
                System.out.print(players.get(i).getName());
                if(i < players.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private int findMaxCount() {
        int max = 0;
        for(Player2 p : players) {
            if(p.getMatchCount() > max) {
                max = p.getMatchCount();
            }
        }
        return max;
    }

    public void run() {
        for(Player2 p : players) {
            p.selectNum();
        }

        while(true) {
            getEnterKey();
            randomNumbers();
            countMatchNumber();
            checkWinner();
        }
    }

    public static void main(String[] args) {
        NumberExpectationGame game = new NumberExpectationGame();
        game.run();
    }

}
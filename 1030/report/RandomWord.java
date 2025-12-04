package week6.report;

import java.util.Scanner;

public class RandomWord {
    private final static char BASE_CHAR = '-';
    private char [][] board = new char[5][5];

    public RandomWord() {
        clearBoard();
    }

    private void clearBoard() {
        for(int i=0; i<board.length; i++) {
            for(int j = 0; j<board[i].length; j++) {
                board[i][j] = BASE_CHAR;
            }
        }
    }

    private void printBoard() {
        for(int i=0; i<board.length; i++) {
            for(int j = 0; j<board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void put(int row, int col, int dir, String word) {
        switch(dir) {
            case 0:
                for(int i=0; i<word.length(); i++) {
                    board[row][col] = word.charAt(i);
                    row--;
                }
                break;
            case 1:
                for(int i=0; i<word.length(); i++) {
                    board[row][col] = word.charAt(i);
                    row--; col++;
                }
                break;
            case 2:
                for(int i=0; i<word.length(); i++) {
                    board[row][col] = word.charAt(i);
                    col++;
                }
                break;
            case 3:
                for(int i=0; i<word.length(); i++) {
                    board[row][col] = word.charAt(i);
                    row++; col++;
                }
                break;
            case 4:
                for(int i=0; i<word.length(); i++) {
                    board[row][col] = word.charAt(i);
                    row++;
                }
                break;
            case 5:
                for(int i=0; i<word.length(); i++) {
                    board[row][col] = word.charAt(i);
                    row++; col--;
                }
                break;
            case 6:
                for(int i=0; i<word.length(); i++) {
                    board[row][col] = word.charAt(i);
                    col--;
                }
                break;
            case 7:
                for(int i=0; i<word.length(); i++) {
                    board[row][col] = word.charAt(i);
                    row--; col--;
                }
                break;
        }
    }

    private boolean isPossible(int row, int col, int dir, int length) {
        int rowSpan = 0; // 행 방향(아래위)로 저장 가능한 글자 개수
        int colSpan = 0; // 열 방향(좌우)으로 저장 가능한 글자 개수
        switch(dir) {
            case 0:
                rowSpan = row + 1;
                if(rowSpan < length) return false;
                else return true;
            case 1:
                rowSpan = row + 1;
                colSpan = board[row].length - col;
                if(rowSpan < length || colSpan < length) return false;
                else return true;
            case 2:
                colSpan = board[row].length - col;
                if(colSpan < length) return false;
                else return true;
            case 3:
                rowSpan = board.length - row;
                colSpan = board[row].length - col;
                if(rowSpan < length || colSpan < length) return false;
                else return true;
            case 4:
                rowSpan = board.length - row;
                if(rowSpan < length) return false;
                else return true;
            case 5:
                rowSpan = board.length - row;
                colSpan = col + 1;
                if(rowSpan < length || colSpan < length) return false;
                else return true;
            case 6:
                colSpan = col + 1;
                if(colSpan < length) return false;
                else return true;
            case 7:
                rowSpan = row + 1;
                colSpan = col + 1;
                if(rowSpan < length || colSpan < length) return false;
                else return true;
            default:
                return false;
        }
    }

    private void place(String word) {
        while(true) {
            int row = (int)(Math.random()*board.length);
            int col = (int)(Math.random()*board[row].length);

            int dir [] = new int[8]; // 8개의 방향을 저장하기 위한 배열
            //0:위, 1:오른쪽 위, 2: 오른쪽,... 시계 방향, 7:왼쪽 위
            int index = (int)(Math.random()*8); // 시작 방향 결정
            for(int i=0; i<8; i++) {
                dir[index] = i;
                index++;
                index %= 8;
            }

            // dir[] 배열에 정해진 순서대로 최대 8방향으로 배치 가능하면 단어 내장
            for(int i=0; i<8; i++) {
                if(isPossible(row, col, dir[i], word.length())) {
                    put(row, col, dir[i], word);
                    return;
                }
            }
        }
    }

    private void fill() {
        for(int i=0; i<board.length; i++) {
            for(int j = 0; j<board[i].length; j++) {
                if(board[i][j] == BASE_CHAR) {
                    board[i][j] = (char)(Math.random()*26);
                    board[i][j] += 'a';
                }
            }
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("단어>>");
            String word = scanner.next();
            if(word.equals("그만"))
                break;
            else if(word.length() > 5 || word.length() > 5) {
                System.out.println("단어가 보드 크기보다 깁니다.");
                continue;
            }
            clearBoard();
            place(word);
            fill(); // 빈 칸에 랜덤글자 채우기
            printBoard();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        RandomWord we = new RandomWord();
        we.run();
    }

}
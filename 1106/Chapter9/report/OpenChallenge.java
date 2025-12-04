/*
 * OpenChallenge
 * 어릴 적 구슬치기를 하던 생각이 난다. 구슬들을 주먹으로 쥐고 홀인지 짝인지 맞추는 게임이다.
 * 이 게임을 실행 예시와 같이 스윙으로 작성하라.
 * 프로그램이 실행되면 '?'가 출력된 박스에 1에서 10 사이의 정수를 숨기면, 사용자가 이 정수가 홀인지 짝인지 맞추는 게임이다.
 * 그림 (a)는 초기에 출력된 화면이며 '?'의 박스에는 랜덤하게 생성된 정수(현재 3)가 숨겨져 있다. 그림(b)에서
 * 사용자가 "홀"이라고 판단되면, "홀"버튼과 "확인"버튼을 순차적으로 누르면 된다. 이경우 숨겨진 정수가 3이므로 성공한 경우이다.
 * (c)는 숨겨진 정수가 5인 경우로, 사용자가 "짝"버튼과 "확인"버튼을 순차적으로 누르는 경우, 실패하게 된다.
 * 그림 (d)는 "홀"이나 "짝"버튼을 누르지 않고 "확인"버튼을 누른 경우로, "홀이나 짝 먼저 선택"을 출력하여 경고한다.
 * 언제든 "다시" 버튼을 누르면 '?'의 박스에 새로운 랜덤한 정수를 숨겨둔다.
 */

package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class OpenChallenge extends JFrame {

    public OpenChallenge() {
        // 1. 프레임 기본 설정
        setTitle("홀짝 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 230); // 문제에서 요구한 프레임 크기

        // 2. 문제의 힌트에 따라, JPanel을 상속받은 GamePanel을 생성하여
        //    프레임의 컨텐트팬으로 설정합니다.
        setContentPane(new GamePanel());

        // 3. 프레임을 화면에 표시
        setVisible(true);
    }

    /**
     * 게임의 모든 컴포넌트와 로직을 담고 있는 내부 패널 클래스
     * JPanel을 상속받고, ActionListener를 구현하여 이벤트를 직접 처리합니다.
     */
    class GamePanel extends JPanel implements ActionListener {

        private int hiddenNumber;   // 숨겨진 난수 (1~100)
        private String userBet = null; // 사용자의 선택 ("홀" 또는 "짝")
        private Random random = new Random();

        // GUI 컴포넌트 선언
        private JLabel numberLabel;  // 숫자("?")가 표시될 레이블
        private JLabel messageLabel; // 메시지("무엇일까요?")가 표시될 레이블
        private JButton oddButton;    // "홀" 버튼
        private JButton evenButton;   // "짝" 버튼
        private JButton confirmButton;// "확인" 버튼
        private JButton againButton;  // "다시" 버튼

        public GamePanel() {
            // 1. GamePanel의 레이아웃을 BorderLayout으로 설정
            setLayout(new BorderLayout());

            // --- 상단 (North) : 숫자 표시 레이블 ---
            // (a)의 "?" 표시부
            numberLabel = new JLabel("?", SwingConstants.CENTER); // 텍스트 중앙 정렬
            numberLabel.setFont(new Font("맑은 고딕", Font.BOLD, 48)); // 폰트 크게
            numberLabel.setOpaque(true); // 배경색을 칠하기 위해 Opaque 설정
            numberLabel.setBackground(Color.LIGHT_GRAY); // (a)의 박스 색상
            numberLabel.setPreferredSize(new Dimension(100, 100)); // 크기 고정
            add(numberLabel, BorderLayout.NORTH);

            // --- 중앙 (Center) : 메시지 레이블 ---
            // "무엇일까요?" 메시지 표시부
            messageLabel = new JLabel("무엇일까요?", SwingConstants.CENTER);
            messageLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
            add(messageLabel, BorderLayout.CENTER);

            // --- 하단 (South) : 버튼 4개 ---
            JPanel southPanel = new JPanel(); // 버튼 4개를 담을 패널 (기본 FlowLayout)
            oddButton = new JButton("홀");
            evenButton = new JButton("짝");
            confirmButton = new JButton("확인");
            againButton = new JButton("다시");

            // 모든 버튼에 ActionListener(this) 추가
            oddButton.addActionListener(this);
            evenButton.addActionListener(this);
            confirmButton.addActionListener(this);
            againButton.addActionListener(this);

            southPanel.add(oddButton);
            southPanel.add(evenButton);
            southPanel.add(confirmButton);
            southPanel.add(againButton);
            add(southPanel, BorderLayout.SOUTH);

            // 2. 첫 게임 시작
            startNewGame();
        }

        /**
         * 새 게임을 시작하는 메소드 (난수 생성 및 GUI 초기화)
         */
        private void startNewGame() {
            // 1. 1~100 사이의 난수 생성
            hiddenNumber = random.nextInt(100) + 1;
            
            // 2. GUI를 (a) 초기 화면 상태로 리셋
            numberLabel.setText("?");
            numberLabel.setBackground(Color.LIGHT_GRAY); // (b)에서 색이 바뀔 수 있으므로
            messageLabel.setText("무엇일까요?");
            userBet = null; // 사용자 선택 초기화

            // (디버깅용) 콘솔에 정답 출력
            // System.out.println("새 게임 정답: " + hiddenNumber);
        }

        /**
         * 4개 버튼의 클릭 이벤트를 모두 처리하는 메소드
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand(); // 클릭된 버튼의 텍스트

            switch (command) {
                case "홀":
                    userBet = "홀";
                    messageLabel.setText("홀 선택됨"); // 사용자에게 피드백
                    break;

                case "짝":
                    userBet = "짝";
                    messageLabel.setText("짝 선택됨"); // 사용자에게 피드백
                    break;

                case "확인":
                    // (d) 홀/짝을 선택하지 않고 '확인'을 누른 경우
                    if (userBet == null) {
                        messageLabel.setText("홀이나 짝 먼저 선택");
                        return; // 여기서 함수 종료
                    }

                    // 정답 공개
                    numberLabel.setText(Integer.toString(hiddenNumber));

                    // 실제 정답(홀/짝) 확인
                    boolean isHiddenOdd = (hiddenNumber % 2 != 0);

                    // (b) 맞춘 경우 (정답이 홀이고 "홀" 선택 OR 정답이 짝이고 "짝" 선택)
                    if ((isHiddenOdd && userBet.equals("홀")) || 
                        (!isHiddenOdd && userBet.equals("짝"))) {
                        messageLabel.setText("맞았어요.");
                        numberLabel.setBackground(Color.GREEN); // 정답 표시
                    } 
                    // (c) 틀린 경우
                    else {
                        messageLabel.setText("아쉽군요");
                        numberLabel.setBackground(Color.PINK); // 오답 표시
                    }
                    break;

                case "다시":
                    // (a) 상태로 게임 리셋
                    startNewGame();
                    break;
            }
        }
    }

    // --- main 메소드 ---
    public static void main(String[] args) {
        // Swing GUI는 이벤트 디스패치 스레드(EDT)에서 실행
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OpenChallenge();
            }
        });
    }
}

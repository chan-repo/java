/*
 * 8. 숫자가 적힌 12장의 카드(JLabel)가 있고 2개의 카드를 순서대로 클릭하면 두 카드의 번호를 서로
 * 바꾸는 프로그램을 실행 예시를 참고하여 작성하라.
 */

package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chapter10_8 extends JFrame {

    private JLabel[] cardLabels = new JLabel[12]; // 12개의 카드 레이블 배열
    private JLabel firstSelectedLabel = null; // 첫 번째로 선택한 카드

    private final Color defaultColor = Color.YELLOW; // 기본 카드 배경색
    private final Color selectedColor = new Color(170, 0, 170); // 선택된 카드 배경색 (보라색 계열)

    public Chapter10_8() {
        // 1. 프레임 기본 설정
        setTitle("카드 스위치 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2. 컨텐트 팬(Content Pane) 설정
        Container c = getContentPane();
        // 3행 4열의 그리드 레이아웃 사용, 카드 간 상하좌우 5픽셀 간격
        c.setLayout(new GridLayout(3, 4, 5, 5));

        // 3. 카드(JLabel) 생성 및 이벤트 리스너 추가
        MyMouseListener listener = new MyMouseListener(); // 마우스 리스너 인스턴스 생성

        for (int i = 0; i < 12; i++) {
            // 레이블 생성 (1부터 12까지의 숫자)
            String number = Integer.toString(i + 1);
            cardLabels[i] = new JLabel(number);

            // 레이블 속성 설정
            cardLabels[i].setHorizontalAlignment(SwingConstants.CENTER); // 텍스트 중앙 정렬
            cardLabels[i].setBackground(defaultColor); // 기본 배경색 (노랑)
            cardLabels[i].setOpaque(true); // 배경색이 보이도록 설정
            cardLabels[i].setFont(new Font("Arial", Font.BOLD, 24)); // 폰트 설정

            // 모든 레이블에 동일한 마우스 리스너 추가
            cardLabels[i].addMouseListener(listener);

            // 4. 컨텐트 팬에 레이블 추가
            c.add(cardLabels[i]);
        }

        // 5. 프레임 크기 설정 및 화면에 표시
        setSize(400, 300);
        setVisible(true);
    }

    /**
     * 마우스 이벤트를 처리하는 내부 클래스 (MouseAdapter 사용)
     */
    class MyMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            // 1. 클릭된 JLabel 객체 가져오기
            JLabel clickedLabel = (JLabel) e.getSource();

            // 2. 첫 번째 클릭인지, 두 번째 클릭인지 확인
            if (firstSelectedLabel == null) {
                // 첫 번째 클릭인 경우
                firstSelectedLabel = clickedLabel;
                firstSelectedLabel.setBackground(selectedColor); // (b)처럼 배경색 변경
            
            } else {
                // 두 번째 클릭인 경우
                
                // 3. 만약 첫 번째 카드와 두 번째 카드를 동일한 것을 클릭했다면
                if (firstSelectedLabel == clickedLabel) {
                    // 선택 취소
                    firstSelectedLabel.setBackground(defaultColor);
                    firstSelectedLabel = null;
                } else {
                    // 4. 서로 다른 카드라면, 두 카드의 텍스트(숫자)를 교환 (c)
                    String tempText = firstSelectedLabel.getText();
                    firstSelectedLabel.setText(clickedLabel.getText());
                    clickedLabel.setText(tempText);

                    // 5. 첫 번째 카드 배경색을 원래대로 되돌림
                    firstSelectedLabel.setBackground(defaultColor);

                    // 6. 다음 클릭을 위해 '첫 번째 선택' 상태 초기화
                    firstSelectedLabel = null;
                }
            }
        }
    }


    public static void main(String[] args) {
        // Swing GUI는 이벤트 디스패치 스레드(EDT)에서 실행
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Chapter10_8();
            }
        });
    }
}
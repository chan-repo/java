package Example;

import javax.swing.*;
import java.awt.*;

public class MyName extends JFrame {
    public MyName() {
        setTitle("내이름 출력하기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        setSize(500, 500);
        setVisible(true);
    }

    class MyPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);

            // ==========================================
            // 1. '성' 그리기 (대략 x 영역: 30 ~ 80)
            // ==========================================

            // 'ㅅ' (시옷) - 작게
            g.drawLine(45, 30, 30, 60);
            g.drawLine(45, 30, 60, 60);

            // 'ㅓ' (어)
            g.drawLine(75, 25, 75, 65);  // 세로
            g.drawLine(65, 45, 75, 45);  // 가로

            // 받침 'ㅇ' (이응) - 크기 30x30
            g.drawOval(45, 75, 30, 30);


            // ==========================================
            // 2. '윤' 그리기 (대략 x 영역: 90 ~ 150)
            // ==========================================

            // 초성 'ㅇ' (이응)
            g.drawOval(105, 30, 30, 30);

            // 'ㅠ' (유)
            g.drawLine(95, 65, 145, 65);   // 긴 가로
            g.drawLine(110, 65, 110, 90);  // 왼쪽 세로 다리
            g.drawLine(130, 65, 130, 90);  // 오른쪽 세로 다리

            // 받침 'ㄴ' (니은)
            g.drawLine(100, 80, 100, 105); // 세로
            g.drawLine(100, 105, 145, 105); // 가로


            // ==========================================
            // 3. '수' 그리기 (대략 x 영역: 160 ~ 210)
            // ==========================================

            // 'ㅅ' (시옷)
            g.drawLine(185, 30, 170, 60);
            g.drawLine(185, 30, 200, 60);

            // 'ㅜ' (우)
            g.drawLine(160, 75, 210, 75);   // 가로
            g.drawLine(185, 75, 185, 105);  // 세로 내려긋기
            
            //===========================================
            // ❤️ 하트 모양 추가
            g.setColor(Color.RED);
            int x = 220, y = 40; // 하트 위치
            // 왼쪽 원
            g.fillOval(x, y, 20, 20);
            // 오른쪽 원
            g.fillOval(x + 15, y, 20, 20);
            // 아래 삼각형
            int[] heartX = {x, x + 35, x + 16};
            int[] heartY = {y + 12, y + 12, y + 40};
            g.fillPolygon(heartX, heartY, 3);
        }
    }

    public static void main(String[] args) {
        new MyName();
    }
}
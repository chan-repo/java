/*
 * 4. 실행 예시와 같이 상하좌우 키를 입력하여 바둑판에서 파란색 블록을 이동시키는 스윙 프로그램을 작성하라.
 */

package Practice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Chapter10_4 extends JFrame {
    private JLabel label;
    private MyPanel panel;

    public Chapter10_4() {
        setTitle("상하좌우 키로 블록을 이동시키는 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 450);
        setLayout(new BorderLayout());

        label = new JLabel("상하좌우 키로 블록을 이동시킬 수 있습니다.", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        panel = new MyPanel();
        add(panel, BorderLayout.CENTER);

        panel.setFocusable(true);
        panel.requestFocus();

        setVisible(true);
    }

    class MyPanel extends JPanel {
        private int x, y; // 블록의 현재 위치 (격자 좌표)
        private final int SIZE = 80; // 한 칸 크기
        private final int GRID = 5;  // 가로/세로 격자 개수 (5x5)
        private final int BLOCK_SIZE = 80; // 블록 크기 (격자와 동일)

        public MyPanel() {
            // 중앙 시작 위치 (격자 단위)
            x = GRID / 2 * SIZE;
            y = GRID / 2 * SIZE;

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    switch (keyCode) {
                        case KeyEvent.VK_UP:
                            if (y > 0) y -= SIZE;
                            break;
                        case KeyEvent.VK_DOWN:
                            if (y < (GRID - 1) * SIZE) y += SIZE;
                            break;
                        case KeyEvent.VK_LEFT:
                            if (x > 0) x -= SIZE;
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (x < (GRID - 1) * SIZE) x += SIZE;
                            break;
                    }
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 격자 그리기
            g.setColor(Color.LIGHT_GRAY);
            for (int i = 0; i <= GRID * SIZE; i += SIZE) {
                g.drawLine(i, 0, i, GRID * SIZE);
                g.drawLine(0, i, GRID * SIZE, i);
            }

            // 파란색 블록
            g.setColor(Color.BLUE);
            g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(GRID * SIZE, GRID * SIZE);
        }
    }

    public static void main(String[] args) {
        new Chapter10_4();
    }
}
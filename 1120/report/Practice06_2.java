/*
 * 6. 아래 그림과 같은 기하학적인 모양을 그려라. 프레임의 크기를 조절하면 자동으로 크기가 조절된다.
 * 2) 컨텐트팬에 꽉차는 마름모 10개 그리기
 */

package Practice;

import javax.swing.*;
import java.awt.*;

public class Practice06_2 extends JFrame {

    public Practice06_2() {
        setTitle("Nested Diamonds Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int width = getWidth();
                int height = getHeight();

                // 가로 방향으로 10개 마름모 배치
                int diamondWidth = width / 10;
                int diamondHeight = height;

                for (int i = 0; i < 10; i++) {
                    int centerX = i * diamondWidth + diamondWidth / 2;
                    int centerY = height / 2;

                    // 바깥 마름모부터 안쪽 마름모까지 반복
                    for (int k = 0; k < 5; k++) { // 5단계 중첩
                        double scale = 1.0 - k * 0.2; // 점점 줄어드는 비율

                        int[] xPoints = {
                            centerX,
                            centerX + (int)(diamondWidth/2 * scale),
                            centerX,
                            centerX - (int)(diamondWidth/2 * scale)
                        };
                        int[] yPoints = {
                            centerY - (int)(diamondHeight/2 * scale),
                            centerY,
                            centerY + (int)(diamondHeight/2 * scale),
                            centerY
                        };

                        // 색상 번갈아가며 표시
                        if (k % 2 == 0) {
                            g.setColor(Color.BLUE);
                        } else {
                            g.setColor(Color.CYAN);
                        }

                        g.drawPolygon(xPoints, yPoints, 4);
                    }
                }
            }
        };

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Practice06_2();
    }
}
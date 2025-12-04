/*
 * 5. "C:\\java\\workspace\\class11_20\\open.png"이미지를 그래픽으로 컨텐트팬의
 * (10, 10) 위치에 원본 크기로 그리고, + 키를 입력하면 10% 확대하고 -키를 입력하며너 이미지를 10%
 * 축소시키는 스윙 응용 프로그램을 작성하라.
 */
package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Practice05 extends JFrame {
    private Image image;
    private double scale = 1.0; // 초기 배율 (100%)

    public Practice05() {
        setTitle("Image Zoom Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // 이미지 불러오기
        image = Toolkit.getDefaultToolkit().getImage("C:\\Users\\SeongYoonsoo\\Pictures\\Cat.jpg");
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(image, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 커스텀 패널 생성
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    int imgWidth = image.getWidth(this);
                    int imgHeight = image.getHeight(this);
                    int drawWidth = (int)(imgWidth * scale);
                    int drawHeight = (int)(imgHeight * scale);

                    // (10, 10) 위치에 원본 크기 또는 확대/축소된 크기로 출력
                    g.drawImage(image, 10, 10, drawWidth, drawHeight, this);
                }
            }
        };

        // 키 이벤트 처리
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == '+') {
                    scale += 0.1; // 10% 확대
                } else if (e.getKeyChar() == '-') {
                    scale -= 0.1; // 10% 축소
                    if (scale < 0.1) scale = 0.1; // 최소 크기 제한
                }
                panel.repaint();
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Practice05();
    }
}
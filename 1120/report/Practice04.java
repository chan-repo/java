/*
 * 4. 앞의 3번 문제를 수정하여 JLabel을 이용하지 않고, 컨텐트팬에 그래픽으로 이미지를 출력하도록 하라.
 * 그리고 3번 문제와 동일하게 이미지 영역 위에 마우스를 누르고 드래깅 하여 이미지를 이동시켜 보자.
 */
package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Practice04 extends JFrame {
    private Image image;
    private int imgX = 100; // 이미지 시작 X 좌표
    private int imgY = 100; // 이미지 시작 Y 좌표
    private int imgWidth;
    private int imgHeight;
    private Point initialClick;
    private boolean dragging = false;

    public Practice04() {
        setTitle("Draggable Image without JLabel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // 이미지 불러오기
        image = Toolkit.getDefaultToolkit().getImage("C:\\Users\\SeongYoonsoo\\Pictures\\banana.png");
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(image, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imgWidth = image.getWidth(this);
        imgHeight = image.getHeight(this);

        // 커스텀 패널 생성
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, imgX, imgY, this);
                }
            }
        };

        // 마우스 이벤트 처리
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                // 클릭 위치가 이미지 내부인지 확인
                if (mouseX >= imgX && mouseX <= imgX + imgWidth &&
                    mouseY >= imgY && mouseY <= imgY + imgHeight) {
                    dragging = true;
                    initialClick = e.getPoint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragging = false;
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    int dx = e.getX() - initialClick.x;
                    int dy = e.getY() - initialClick.y;
                    imgX += dx;
                    imgY += dy;
                    initialClick = e.getPoint(); // 기준점 갱신
                    panel.repaint();
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Practice04();
    }
}
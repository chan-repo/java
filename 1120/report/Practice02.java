/*
 * 2. 문제 1번에서 주어진 이미지를 배경으로 출력하고 그 위에 반지름이 20 픽셀인 원을 그리고,
 * 마우스를 드래깅하면 원을 이동시키는 프로그램을 작성하라. 원의 내부는 초록색으로 채운다. 모두 paintComponent()로 작성하라.
 * 
 */
package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Practice02 extends JFrame {
    private ImageIcon backgroundIcon;
    private int circleX = 100; // 원의 중심 X 좌표
    private int circleY = 100; // 원의 중심 Y 좌표
    private final int radius = 20; // 반지름
    private boolean dragging = false; // 드래깅 여부

    public Practice02() {
        setTitle("Image Background with Movable Circle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // 이미지 불러오기
        File imgFile = new File("C:\\Users\\SeongYoonsoo\\Pictures\\rabbit.jpg");
        if (imgFile.exists()) {
            backgroundIcon = new ImageIcon("C:\\Users\\SeongYoonsoo\\Pictures\\rabbit.jpg");
        } else {
            JOptionPane.showMessageDialog(this, "rabbit.jpg 파일이 없습니다!");
        }

        // 커스텀 패널 생성
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 배경 이미지 출력
                if (backgroundIcon != null) {
                    g.drawImage(backgroundIcon.getImage(), 0, 0,
                            getWidth(), getHeight(), this);
                }
                // 초록색 원 출력
                g.setColor(Color.GREEN);
                g.fillOval(circleX - radius, circleY - radius,
                           radius * 2, radius * 2);
            }
        };

        // 마우스 이벤트 처리
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 클릭한 위치가 원 내부인지 확인
                int dx = e.getX() - circleX;
                int dy = e.getY() - circleY;
                if (dx * dx + dy * dy <= radius * radius) {
                    dragging = true;
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
                    circleX = e.getX();
                    circleY = e.getY();
                    panel.repaint(); // 원 위치 갱신
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Practice02();
    }
}

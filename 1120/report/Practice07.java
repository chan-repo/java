/*
 * 7. 마우스로 점을 찍으면 점들을 계속 연결하여 폐다각형으로 그려지도록 프로그램을 작성하라.
 */
package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Practice07 extends JFrame {
    private ArrayList<Point> points = new ArrayList<>();

    public Practice07() {
        setTitle("Polygon Drawing Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(Color.RED);

                // 점 찍기
                for (Point p : points) {
                    g.fillOval(p.x - 3, p.y - 3, 6, 6);
                }

                // 점들을 연결하여 폐다각형 그리기
                if (points.size() > 1) {
                    for (int i = 0; i < points.size() - 1; i++) {
                        Point p1 = points.get(i);
                        Point p2 = points.get(i + 1);
                        g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                }
            }
        };

        // 마우스 클릭 이벤트 처리
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.add(e.getPoint()); // 클릭한 좌표 저장
                panel.repaint();          // 다시 그리기
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Practice07();
    }
}
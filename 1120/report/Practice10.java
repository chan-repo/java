/*
 * 10. paintComponent() 를 사용하여 이미지를 패널에 출력할 때 4등분하고, 서로 상, 하, 좌, 우 10픽셀씩
 * 떨어져서 그려지도록 하라. 이미지는 컴포넌트를 사용하지 말고 그래픽으로만 구현하라. 동일한 이미지를 4번 로딩하여도 안된다.
 * GridLayout을 사용해도 안된다.
 */
package Practice;

import javax.swing.*;
import java.awt.*;

public class Practice10 extends JFrame {
    private Image image;

    public Practice10() {
        setTitle("Image Split into 4 Parts");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        // 이미지 한 번만 로딩
        image = Toolkit.getDefaultToolkit().getImage("C:\\Users\\SeongYoonsoo\\Pictures\\shih_tzu.jpg");
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(image, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (image == null) return;

                int imgW = image.getWidth(this);
                int imgH = image.getHeight(this);

                // 원본 이미지를 4등분
                int halfW = imgW / 2;
                int halfH = imgH / 2;

                // 각 부분을 10픽셀씩 떨어져서 출력
                int offset = 10;

                // 왼쪽 위
                g.drawImage(image,
                        offset, offset, offset + halfW, offset + halfH, // destination rectangle
                        0, 0, halfW, halfH,                             // source rectangle
                        this);

                // 오른쪽 위
                g.drawImage(image,
                        offset + halfW + offset, offset, offset + halfW*2 + offset, offset + halfH,
                        halfW, 0, imgW, halfH,
                        this);

                // 왼쪽 아래
                g.drawImage(image,
                        offset, offset + halfH + offset, offset + halfW, offset + halfH*2 + offset,
                        0, halfH, halfW, imgH,
                        this);

                // 오른쪽 아래
                g.drawImage(image,
                        offset + halfW + offset, offset + halfH + offset,
                        offset + halfW*2 + offset, offset + halfH*2 + offset,
                        halfW, halfH, imgW, imgH,
                        this);
            }
        };

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Practice10();
    }
}














/*
 * OpenChallenge
 * 클리핑 영역 키보드로 움직이기
 * 클리핑 기능을 이용하는 재미있는 오락용 프로그램을 작성해 보자. 아래 그림과 같이 패널 전체에 이미지를 하나 그리고
 * 50*50 크기의 클리핑 영역을 지정하라. 그리고 상, 하, 좌, 우 키로 클리핑 영역을 움직여서 패널의 바탕에 그려진 그림을
 * 구석구석 살펴보도록 하라. 키 한번에 10픽셀씩 움직이도록 하라. 정답 프로그램을 실행하면 글자가 숨어있다. 독자들이 키를 이용하여 클리핑 영역을 움직이면서
 * 숨어 있는 글자를 찾아보라.
 */
package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class OpenChallenge extends JFrame {
    public OpenChallenge() {
        setTitle("Clipping Viewport Explorer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        ClippingPanel panel = new ClippingPanel();
        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 패널: 이미지 그리기 + 클리핑 영역 이동
    static class ClippingPanel extends JPanel {
        private BufferedImage image;
        private int clipX = 10;
        private int clipY = 10;
        private final int clipSize = 50;
        private final int step = 10;

        ClippingPanel() {
            setBackground(Color.DARK_GRAY);

            // 이미지 한 번만 로드 (경로를 환경에 맞게 변경)
            try {
                // 예시 경로: 프로젝트 루트 또는 절대 경로로 교체하세요
                image = ImageIO.read(new File("C:\\Users\\SeongYoonsoo\\Pictures\\ant.jpg"));
            } catch (Exception e) {
                // 이미지 실패 시 대체용 패턴 생성
                image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = image.createGraphics();
                g2.setPaint(new GradientPaint(0, 0, Color.LIGHT_GRAY, 800, 600, Color.GRAY));
                g2.fillRect(0, 0, 800, 600);
                g2.setColor(new Color(255, 255, 255, 120));
                g2.setFont(new Font("Arial", Font.BOLD, 72));
                g2.drawString("FIND ME!", 420, 380); // 숨겨진(?) 텍스트 힌트
                g2.dispose();
            }

            // 키 바인딩 (포커스 걱정 없이 동작)
            setupKeyBindings();
        }

        private void setupKeyBindings() {
            int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
            InputMap im = getInputMap(condition);
            ActionMap am = getActionMap();

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");

            am.put("left", new AbstractAction() {
                @Override public void actionPerformed(ActionEvent e) {
                    clipX = Math.max(0, clipX - step);
                    repaint();
                }
            });
            am.put("right", new AbstractAction() {
                @Override public void actionPerformed(ActionEvent e) {
                    clipX = Math.min(getWidth() - clipSize, clipX + step);
                    repaint();
                }
            });
            am.put("up", new AbstractAction() {
                @Override public void actionPerformed(ActionEvent e) {
                    clipY = Math.max(0, clipY - step);
                    repaint();
                }
            });
            am.put("down", new AbstractAction() {
                @Override public void actionPerformed(ActionEvent e) {
                    clipY = Math.min(getHeight() - clipSize, clipY + step);
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image == null) return;

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            int pw = getWidth();
            int ph = getHeight();

            // 배경을 어둡게 칠해 "창" 느낌 강조
            g2.setColor(new Color(0, 0, 0, 160));
            g2.fillRect(0, 0, pw, ph);

            // 클리핑 영역 설정 후 이미지를 패널 크기에 맞춰 그리기
            Shape oldClip = g2.getClip();
            g2.setClip(new Rectangle(clipX, clipY, clipSize, clipSize));
            g2.drawImage(image, 0, 0, pw, ph, null);
            g2.setClip(oldClip);

            // 클리핑 영역 테두리 표시
            g2.setStroke(new BasicStroke(2f));
            g2.setColor(Color.WHITE);
            g2.drawRect(clipX, clipY, clipSize, clipSize);

            // 안내 텍스트
            g2.setFont(new Font("Arial", Font.PLAIN, 14));
            g2.setColor(Color.WHITE);
            g2.drawString("Use arrow keys (10px/step). Explore to find the hidden text!", 12, 20);

            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OpenChallenge::new);
    }
}
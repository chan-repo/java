package Example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class PaintApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaintFrame().setVisible(true));
    }
}

class PaintFrame extends JFrame {
    private final CanvasPanel canvas = new CanvasPanel();

    public PaintFrame() {
        setTitle("Java 그림판");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 메뉴바 추가
        setJMenuBar(buildMenuBar());

        // 툴바 추가
        add(buildToolBar(), BorderLayout.NORTH);

        // 캔버스 추가
        add(canvas, BorderLayout.CENTER);
    }

    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // 파일 메뉴
        JMenu fileMenu = new JMenu("파일");
        JMenuItem newFile = new JMenuItem("새로 만들기");
        JMenuItem openFile = new JMenuItem("열기");
        JMenuItem saveFile = new JMenuItem("저장");
        JMenuItem saveAsFile = new JMenuItem("다른 이름으로 저장");
        JMenuItem exit = new JMenuItem("종료");

        newFile.addActionListener(e -> canvas.clear());
        openFile.addActionListener(e -> canvas.openImage());
        saveFile.addActionListener(e -> canvas.saveImage());
        saveAsFile.addActionListener(e -> canvas.saveAsImage());
        exit.addActionListener(e -> System.exit(0));

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        // 편집 메뉴
        JMenu editMenu = new JMenu("편집");
        JMenuItem undo = new JMenuItem("실행 취소");
        JMenuItem redo = new JMenuItem("다시 실행");
        undo.addActionListener(e -> canvas.undo());
        redo.addActionListener(e -> canvas.redo());
        editMenu.add(undo);
        editMenu.add(redo);

        // 보기 메뉴
        JMenu viewMenu = new JMenu("보기");
        JMenuItem userInfo = new JMenuItem("사용자 정보");
        userInfo.addActionListener(e -> MyName.main(new String[]{})); // MyName 실행
        viewMenu.add(userInfo);

        JMenuItem zoomIn = new JMenuItem("확대");
        JMenuItem zoomOut = new JMenuItem("축소");
        zoomIn.addActionListener(e -> canvas.zoom(1.2));
        zoomOut.addActionListener(e -> canvas.zoom(0.8));
        viewMenu.add(zoomIn);
        viewMenu.add(zoomOut);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        return menuBar;
    }

    private JToolBar buildToolBar() {
        JToolBar toolBar = new JToolBar();

        JButton saveButton = createIconButton(
        		"C:\\Users\\SeongYoonsoo\\Pictures\\save.png", "저장", () -> canvas.saveImage()
        );
        JButton openButton = createIconButton(
        		"C:\\Users\\SeongYoonsoo\\Pictures\\open.png", "열기", () -> canvas.openImage()
        );
        JButton newButton = createIconButton(
        		"C:\\Users\\SeongYoonsoo\\Pictures\\pen.png", "새로 만들기", () -> canvas.clear()
        );

        // 브러시 색 선택 버튼
        JButton brushColorButton = new JButton("브러시 색");
        brushColorButton.addActionListener(e -> {
            Color chosen = JColorChooser.showDialog(this, "브러시 색 선택", canvas.getBrushColor());
            if (chosen != null) {
                canvas.setBrushColor(chosen);
            }
        });

        // 글자 색 선택 버튼
        JButton textColorButton = new JButton("글자 색");
        textColorButton.addActionListener(e -> {
            Color chosen = JColorChooser.showDialog(this, "글자 색 선택", canvas.getTextColor());
            if (chosen != null) {
                canvas.setTextColor(chosen);
            }
        });

        toolBar.add(newButton);
        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.add(brushColorButton);
        toolBar.add(textColorButton);

        return toolBar;
    }

    private JButton createIconButton(String path, String tooltip, Runnable action) {
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        JButton button = new JButton(new ImageIcon(scaled));
        button.setToolTipText(tooltip);
        button.addActionListener(e -> action.run());
        return button;
    }
}

class CanvasPanel extends JPanel {
    private BufferedImage buffer;
    private Graphics2D g2;
    private double scale = 1.0;
    private Color brushColor = Color.BLACK;
    private Color textColor = Color.BLACK;

    // Undo/Redo 스택
    private java.util.Deque<BufferedImage> undoStack = new java.util.ArrayDeque<>();
    private java.util.Deque<BufferedImage> redoStack = new java.util.ArrayDeque<>();

    public CanvasPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
        ensureBuffer();

        MouseAdapter mouse = new MouseAdapter() {
            Point last;
            @Override public void mousePressed(MouseEvent e) {
                // 작업 시작 전에 현재 상태 저장
                pushUndo();

                if (SwingUtilities.isRightMouseButton(e)) {
                    String text = JOptionPane.showInputDialog("입력할 글자:");
                    if (text != null && !text.isEmpty()) {
                        g2.setColor(textColor);
                        g2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
                        g2.drawString(text, e.getX(), e.getY());
                        repaint();
                    }
                } else {
                    last = e.getPoint();
                    drawPoint(last);
                }
            }
            @Override public void mouseDragged(MouseEvent e) {
                Point p = e.getPoint();
                drawLine(last, p);
                last = p;
            }
        };
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public void saveAsImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("다른 이름으로 저장");
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                // 확장자 없으면 .png 붙여주기
                if (!file.getName().toLowerCase().endsWith(".png")) {
                    file = new File(file.getAbsolutePath() + ".png");
                }
                ImageIO.write(buffer, "png", file);
                JOptionPane.showMessageDialog(this, "저장 완료: " + file.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void ensureBuffer() {
        buffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        g2 = buffer.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
    }

    private void drawPoint(Point p) {
        g2.setColor(brushColor);
        g2.fillOval(p.x, p.y, 3, 3);
        repaint();
    }

    private void drawLine(Point a, Point b) {
        g2.setColor(brushColor);
        g2.drawLine(a.x, a.y, b.x, b.y);
        repaint();
    }

    public void clear() {
        pushUndo();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
        g2.setColor(brushColor);
        repaint();
    }

    // 현재 상태를 undoStack에 저장
    private void pushUndo() {
        BufferedImage snapshot = new BufferedImage(buffer.getWidth(), buffer.getHeight(), buffer.getType());
        Graphics g = snapshot.getGraphics();
        g.drawImage(buffer, 0, 0, null);
        g.dispose();
        undoStack.push(snapshot);
        redoStack.clear(); // 새로운 작업이 시작되면 redo는 초기화
    }

    // Undo 기능
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(copyImage(buffer));
            buffer = undoStack.pop();
            g2 = buffer.createGraphics();
            g2.setStroke(new BasicStroke(3));
            repaint();
        } else {
            JOptionPane.showMessageDialog(this, "되돌릴 작업이 없습니다.");
        }
    }

    // Redo 기능
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(copyImage(buffer));
            buffer = redoStack.pop();
            g2 = buffer.createGraphics();
            g2.setStroke(new BasicStroke(3));
            repaint();
        } else {
            JOptionPane.showMessageDialog(this, "다시 실행할 작업이 없습니다.");
        }
    }

    // 이미지 복사
    private BufferedImage copyImage(BufferedImage src) {
        BufferedImage copy = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        Graphics g = copy.getGraphics();
        g.drawImage(src, 0, 0, null);
        g.dispose();
        return copy;
    }

    public void saveImage() {
        try {
            File file = new File("output.png");
            ImageIO.write(buffer, "png", file);
            JOptionPane.showMessageDialog(this, "저장 완료: " + file.getAbsolutePath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void openImage() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                pushUndo();
                buffer = ImageIO.read(chooser.getSelectedFile());
                g2 = buffer.createGraphics();
                repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void zoom(double factor) {
        scale *= factor;
        repaint();
    }

    public Color getBrushColor() { return brushColor; }
    public void setBrushColor(Color c) { this.brushColor = c; }
    public Color getTextColor() { return textColor; }
    public void setTextColor(Color c) { this.textColor = c; }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gg = (Graphics2D) g.create();
        gg.scale(scale, scale);
        gg.drawImage(buffer, 0, 0, null);
        gg.dispose();
    }
}
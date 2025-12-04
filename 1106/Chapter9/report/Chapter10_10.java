/*
 * 10. 실행 예시를 참고하여 스윙 프로그램을 작성하라.
 * 'm'키를 입력할 때마다 80x80 크기의 블록을 (100,100) 위치에 랜덤한 배경색으로 만들고,
 * 만들어진 모든 블록들은 마우스로 드레깅하면 원하는 위치로 이동시킬 수 있게 한다.
 */

package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Chapter10_10 extends JFrame {

    private Container contentPane; // 컴포넌트가 추가될 컨테이너
    private Random random = new Random(); // 랜덤 색상 생성을 위한 객체

    public Chapter10_10() {
        // 1. 프레임 기본 설정
        setTitle("블록 생성 및 드래그");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // 윈도우 크기

        // 2. 컨텐트 팬 설정
        contentPane = getContentPane();
        // 레이아웃 매니저를 null로 설정하여 절대 위치(x, y)에 컴포넌트 배치
        contentPane.setLayout(null);

        // 3. 키 이벤트 리스너 추가
        contentPane.addKeyListener(new MyKeyListener());

        // 4. 컨텐트 팬이 키 이벤트를 받을 수 있도록 설정
        contentPane.setFocusable(true); // 포커스를 받을 수 있도록 설정
        contentPane.requestFocus();   // 시작 시 바로 키 입력을 받도록 포커스 요청

        setVisible(true); // 프레임 보이기
    }

    /**
     * 'm' 키 입력을 감지하는 내부 클래스 (KeyAdapter 사용)
     */
    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // 'm' 키가 눌렸는지 확인
            if (e.getKeyChar() == 'm') {
                // 새로운 블록을 생성하는 메소드 호출
                createNewBlock();
            }
        }
    }

    /**
     * 새로운 블록(JLabel)을 생성하고 컨테일에 추가하는 메소드
     */
    private void createNewBlock() {
        // 1. 새 JLabel 생성
        JLabel newBlock = new JLabel();

        // 2. 랜덤 배경색 생성
        int r = random.nextInt(256); // 0~255
        int g = random.nextInt(256); // 0~255
        int b = random.nextInt(256); // 0~255
        newBlock.setBackground(new Color(r, g, b));
        newBlock.setOpaque(true); // 배경색이 보이도록 설정

        // 3. 크기 및 위치 설정 (문제 요구사항)
        newBlock.setSize(80, 80);        // 80x80 크기
        newBlock.setLocation(100, 100);  // (100, 100) 위치

        // 4. 마우스 드래그를 위한 리스너 생성 및 추가
        MyMouseDragListener dragListener = new MyMouseDragListener();
        newBlock.addMouseListener(dragListener); // 마우스 클릭/해제 감지
        newBlock.addMouseMotionListener(dragListener); // 마우스 드래그 감지

        // 5. 컨텐트 팬에 새 블록 추가
        contentPane.add(newBlock);
        
        // 6. (중요) 새 컴포넌트가 추가되었으므로 화면 갱신
        contentPane.revalidate();
        contentPane.repaint(); // add()가 보통 갱신을 유발하지만, null 레이아웃에선 명시하면 좋음
    }

    /**
     * 마우스 드래그 이벤트를 처리하는 리스너
     * MouseAdapter를 상속하고 MouseMotionListener를 구현합니다.
     */
    class MyMouseDragListener extends MouseAdapter implements MouseMotionListener {

        private Point offset; // 클릭 위치와 블록 좌상단 모서리 간의 차이

        @Override
        public void mousePressed(MouseEvent e) {
            // 1. 클릭된 블록(JLabel) 객체 가져오기
            Component pressedComp = e.getComponent();
            
            // 2. (개선) 클릭된 블록을 맨 앞으로 가져오기
            contentPane.setComponentZOrder(pressedComp, 0);
            
            // 3. 클릭된 위치(e.getPoint())를 offset으로 저장
            offset = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // 1. 드래그 중인 블록 객체 가져오기
            Component draggedComp = e.getComponent();
            
            // 2. 마우스의 현재 위치(컨테이너 기준) 가져오기
            // e.getPoint()는 블록 *내부* 좌표이므로, 부모(컨테이너)의 마우스 위치를 가져와야 함.
            Point containerMousePos = draggedComp.getParent().getMousePosition();

            if (containerMousePos == null) {
                // 마우스가 창 밖으로 나간 경우
                return;
            }

            // 3. 블록의 새 좌상단 위치 계산
            // (컨테이너 마우스 위치) - (처음 클릭 시 블록 내부 offset)
            int newX = containerMousePos.x - offset.x;
            int newY = containerMousePos.y - offset.y;

            // 4. 블록 위치 이동
            draggedComp.setLocation(newX, newY);
            
            // 5. (개선) 드래그 중 잔상을 없애기 위해 컨테이너를 다시 그림
            // setLocation()이 내부적으로 repaint()를 호출하므로 보통은 불필요하지만,
            // null 레이아웃에서는 명시적으로 호출하는 것이 더 부드러울 수 있습니다.
            // contentPane.repaint(); 
        }

        // MouseMotionListener 인터페이스의 필수 메소드 (내용은 없어도 됨)
        @Override
        public void mouseMoved(MouseEvent e) {
            // 사용하지 않음
        }
    }


    public static void main(String[] args) {
        // Swing GUI는 이벤트 디스패치 스레드(EDT)에서 실행하는 것이 안전합니다.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Chapter10_10();
            }
        });
    }
}
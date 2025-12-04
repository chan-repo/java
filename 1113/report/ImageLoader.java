/*
 * 2. "파일" 메뉴에 "열기" 메뉴아이템을 하나 만든다.
 * 이 메뉴아이템을 선택하면 파일 열기 다이얼로그를 출력하고 사용자가 JPG 이미지 파일을 선택하면 이 이미지를 프레임의
 * 바탕화면 전체(컨텐츠팬)에 그리는 스윙 응용프로그램을 작성하라.
 * 이미지를 그리기 위해서 JLabel을 이용하지 말고 Graphics의 drawImage() 메소드를 이용하라.
 */

package Practice;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;

public class ImageLoader extends JFrame {
	private Image img = null;
	private MyContentPanel contentPane = new MyContentPanel();
	
	public ImageLoader() {
		setTitle("메뉴로 배경 이미지 로딩하기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);
		
		createMenu();
		setSize(400, 400);
		setVisible(true);
	}
	
	public void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("파일");
		JMenuItem mi = new JMenuItem("열기");
		
		mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                
                // JPG 파일만 보이게 필터 설정 (선택사항이지만 문제 의도에 맞음)
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
                chooser.setFileFilter(filter);

                int ret = chooser.showOpenDialog(null);
                if (ret != JFileChooser.APPROVE_OPTION) {
                    return; // 취소했으면 종료
                }
                
                // 선택한 파일의 경로를 가져옴
                String filePath = chooser.getSelectedFile().getPath();
                
                // 4. 파일 경로로 이미지를 로딩해서 변수에 저장
                img = new ImageIcon(filePath).getImage();
                
                // 5. 화면을 다시 그리라고 요청 (이게 호출되면 paintComponent가 실행됨)
                repaint(); 
            }
        });
		
		file.add(mi);
		mb.add(file);
		setJMenuBar(mb);
	}
	
	// 내부 클래스: 이미지를 그릴 패널
    class MyContentPanel extends JPanel {
        // 6. 그림을 그리는 핵심 메소드 (문제 요구사항: Graphics 사용)
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // 원래 기능(배경색 채우기 등) 수행
            
            // 이미지가 로딩되어 있다면 그린다
            if (img != null) {
                // g.drawImage(이미지, x, y, 폭, 높이, 옵저버)
                // 폭과 높이를 this.getWidth(), this.getHeight()로 하면 창 크기에 맞춰 늘어남
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }
	
	public static void main(String[] args) {
		new ImageLoader();
	}
	
}

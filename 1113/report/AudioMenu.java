/*
 * 7. 메뉴를 이용하여 오디오를 연주하는 프로그램을 작성하라. "오디오" 메뉴에 "연주"와 "종료" 메뉴아이템을 두고,
 * "연주" 메뉴아이템이 선택되면 JFileChooser를 이용하여 wav 파일을 선택하여 연주를 시작하고, "종료" 메뉴아이템이
 * 선택되면 연주를 종료한다.
 */

package Practice;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class AudioMenu extends JFrame {
    private JLabel label; // 상태를 보여줄 라벨
    private Clip clip;    // 오디오 제어 객체 (멤버 변수로 선언해야 제어 가능)

    public AudioMenu() {
        setTitle("오디오 파일을 찾아 연주/종료 제어");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        // 초기 라벨 설정
        label = new JLabel("오디오 파일을 선택하세요");
        label.setFont(new Font("Gothic", Font.PLAIN, 20));
        c.add(label);

        createMenu(); // 메뉴 생성

        setSize(500, 300);
        setVisible(true);
    }

    private void createMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu audioMenu = new JMenu("오디오");

        JMenuItem playItem = new JMenuItem("연주");
        JMenuItem exitItem = new JMenuItem("종료");

        // 1. "연주" 메뉴 아이템 동작
        playItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                
                // 오디오 파일만 보이도록 필터 설정
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Audio Files(wav,au,mid,rmf)", "wav", "au", "mid", "rmf");
                chooser.setFileFilter(filter);
                
                // (선택사항) 프로젝트 폴더 안의 audio 폴더를 기본 위치로 설정
                File initialDir = new File("./audio"); 
                if(initialDir.exists()) chooser.setCurrentDirectory(initialDir);

                int ret = chooser.showOpenDialog(null);
                if (ret != JFileChooser.APPROVE_OPTION) {
                    return;
                }

                // 선택된 파일 정보 가져오기
                File selectedFile = chooser.getSelectedFile();
                playAudio(selectedFile); // 오디오 재생 메소드 호출
            }
        });

        // 2. "종료" 메뉴 아이템 동작
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 종료 전 재생 중인 음악 끄기 (깔끔한 종료)
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                    clip.close();
                }
                System.exit(0); // 프로그램 종료
            }
        });

        audioMenu.add(playItem);
        audioMenu.add(exitItem);
        mb.add(audioMenu);
        setJMenuBar(mb);
    }

    // 오디오 재생을 전담하는 메소드
    private void playAudio(File file) {
        try {
            // **중요**: 이미 재생 중인 곡이 있다면 멈추고 자원을 해제해야 함
            if (clip != null) {
                if (clip.isRunning()) clip.stop();
                clip.close();
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start(); // 재생 시작

            label.setText(file.getName() + " 를 연주하고 있습니다.");

        } catch (Exception e) {
            label.setText("오디오 재생 오류!");
            JOptionPane.showMessageDialog(this, "재생할 수 없는 파일입니다.", "에러", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AudioMenu();
    }
}
/*
 * 5. 프로그램이 시작되면 바로 오디오를 재생하라. 그리고 마우스가 프로그램을 벗어나면 연주를 일시 중단시키고,
 * 다시 마우스가 프로그램으로 올라오면 연주를 계속하도록 하라.
 */

package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class AudioPlayer extends JFrame {
    // 1. 오디오 제어를 위한 Clip 객체
    private Clip clip; 
    private JLabel label; // 상태를 보여줄 라벨

    public AudioPlayer() {
        setTitle("오디오 시작 중단 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        // 2. 상태 표시용 라벨 생성
        label = new JLabel("오디오 파일을 로딩 중입니다...");
        label.setFont(new Font("Gothic", Font.PLAIN, 20));
        c.add(label);

        // 3. 오디오 로딩 및 재생 시작
        loadAudio("C:/Windows/Media/Alarm10.wav");

        // 4. 마우스 리스너 추가 (핵심 부분)
        c.addMouseListener(new MouseAdapter() {
            // 마우스가 컨텐트팬 안으로 들어오면
            @Override
            public void mouseEntered(MouseEvent e) {
                if (clip != null) {
                    clip.start(); // 멈춘 위치에서 계속 재생
                    label.setText("윈도우 알람 연주 계속");
                }
            }

            // 마우스가 컨텐트팬 밖으로 나가면
            @Override
            public void mouseExited(MouseEvent e) {
                if (clip != null) {
                    clip.stop(); // 재생 중단
                    label.setText("윈도우 알람 연주 일시 중단");
                }
            }
        });

        setSize(400, 200);
        setVisible(true);
    }

    // 오디오 파일을 불러오는 메소드
    private void loadAudio(String path) {
        try {
            // 오디오 파일에 대한 스트림 열기
            File audioFile = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            
            // Clip 객체 생성 및 열기
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            // 프로그램 시작하자마자 재생
            clip.start();
            label.setText("윈도우 알람 연주 중");

        } catch (Exception e) {
            label.setText("오디오 파일 로딩 실패!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AudioPlayer();
    }
}
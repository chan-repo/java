/*
 * 8. 다음 실행 에시와 같이 연주할 곡을 체크박스로 만들고 사용자가 체크한 곡만 순서대로 연주하는 프로그램을 작성하라.
 * 연주시작 버튼을 누르면 연주가 시작되고 다음 곡에 대해서는 체크박스를 선택/해제할 수 있다. 
 * 연주되고 있는 곡명의 글자색을 달라하는 것은 옵션이다. 
 * 연주끝 버튼을 누르면 연주가 멈추고, 연주시작 버튼을 누르면 처음부터 시작한다.
 * 연주될 wav 파일은 프로젝트의 audio 폴더에 두면 된다.
 */

package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class CheckBoxAudio extends JFrame {
    // 책의 그림에 나온 파일명 배열
    private String[] audioNames = {
    		"C:/Windows/Media/Alarm01.wav",
    		"C:/Windows/Media/Alarm02.wav", 
    		"C:/Windows/Media/Alarm03.wav", 
    		"C:/Windows/Media/Alarm04.wav"
    };
    
    private JCheckBox[] chks = new JCheckBox[audioNames.length];
    private Clip currentClip = null; // 현재 재생 중인 클립
    private boolean isStopPressed = false; // '연주 끝' 버튼이 눌렸는지 확인하는 플래그

    public CheckBoxAudio() {
        setTitle("체크박스, 오디오 재생, LineListener");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // 1. 상단 안내 문구
        JLabel infoLabel = new JLabel("체크된 곡만 순서대로 한 번 연주합니다.");
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setBackground(Color.LIGHT_GRAY);
        infoLabel.setOpaque(true);
        c.add(infoLabel, BorderLayout.NORTH);

        // 2. 중앙 체크박스 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 가운데 정렬
        
        // 체크박스를 세로로 배치하기 위해 Box 컨테이너 사용 (혹은 GridLayout)
        Box box = Box.createVerticalBox(); 
        for (int i = 0; i < audioNames.length; i++) {
            chks[i] = new JCheckBox(audioNames[i]);
            box.add(chks[i]);
        }
        centerPanel.add(box);
        c.add(centerPanel, BorderLayout.CENTER);

        // 3. 하단 버튼 패널
        JPanel btnPanel = new JPanel();
        JButton startBtn = new JButton("연주 시작");
        JButton stopBtn = new JButton("연주 끝");
        
        btnPanel.add(startBtn);
        btnPanel.add(stopBtn);
        c.add(btnPanel, BorderLayout.SOUTH);

        // 4. 버튼 이벤트 처리
        
        // [연주 시작] 버튼
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 기존에 재생 중인 게 있으면 멈춤
                if (currentClip != null && currentClip.isRunning()) {
                    currentClip.stop();
                }
                isStopPressed = false; // 정지 플래그 초기화
                playMusic(0); // 0번 곡부터 탐색 시작
            }
        });

        // [연주 끝] 버튼
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStopPressed = true; // 강제 정지 플래그 설정
                if (currentClip != null && currentClip.isRunning()) {
                    currentClip.stop(); // 현재 곡 정지 (LineListener가 호출되지만 플래그 때문에 다음 곡 재생 안 함)
                }
                // 모든 체크박스 색상 검정으로 원상복구
                for(JCheckBox chk : chks) chk.setForeground(Color.BLACK);
            }
        });

        setSize(400, 350);
        setVisible(true);
    }

    // 순차 재생을 위한 핵심 재귀 메소드
    private void playMusic(int index) {
        // 1. 종료 조건: 인덱스가 배열 범위를 넘어가면 끝
        if (index >= audioNames.length) return;

        // 2. 체크가 안 되어 있으면 다음 곡으로 바로 넘김
        if (!chks[index].isSelected()) {
            playMusic(index + 1);
            return;
        }

        // 3. 체크 되어 있으면 재생 시도
        try {
            File audioFile = new File(audioNames[index]);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            
            // 이벤트 리스너 추가: 곡이 끝나면 무엇을 할지 설정
            currentClip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    // 음악이 멈췄을 때 (끝까지 다 재생했거나, stop()이 호출됐을 때)
                    if (event.getType() == LineEvent.Type.STOP) {
                        chks[index].setForeground(Color.BLACK); // 색상 복구
                        currentClip.close();
                        
                        // 사용자가 '연주 끝' 버튼을 누른 게 아니라면 다음 곡 재생
                        if (!isStopPressed) {
                            playMusic(index + 1);
                        }
                    }
                }
            });

            // 재생 시작 및 글자색 변경
            currentClip.start();
            chks[index].setForeground(Color.RED); // 현재 연주 중인 곡 빨간색

        } catch (Exception e) {
            e.printStackTrace();
            // 에러 나도 다음 곡 시도
            playMusic(index + 1); 
        }
    }

    public static void main(String[] args) {
        new CheckBoxAudio();
    }
}
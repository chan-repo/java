package com.materials;

import com.formdev.flatlaf.FlatLightLaf;
import com.materials.config.DatabaseConfig;
import com.materials.ui.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // FlatLaf 테마 적용
            FlatLightLaf.setup();
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
            UIManager.put("TextComponent.arc", 10);
        } catch (Exception e) {
            System.err.println("테마 적용 실패: " + e.getMessage());
        }

        // 데이터베이스 초기화
        DatabaseConfig.initializeDatabase();

        // 로그인 화면 실행
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}

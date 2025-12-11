package com.materials.ui.panel;

import com.materials.model.User;

import javax.swing.*;
import java.awt.*;

public class UserDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JComboBox<String> roleComboBox;
    private JCheckBox enabledCheckBox;
    private boolean confirmed = false;
    private User user;
    private boolean isEditMode;

    public UserDialog(Frame owner, User user) {
        super(owner, user == null ? "사용자 추가" : "사용자 수정", true);
        this.user = user;
        this.isEditMode = (user != null);
        initComponents();
        if (user != null) {
            fillData(user);
        }
    }

    private void initComponents() {
        setSize(500, 600);
        setLocationRelativeTo(getOwner());
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 폼 패널
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // 사용자명
        addFormField(formPanel, gbc, row++, "사용자명*:", usernameField = new JTextField(20));
        
        if (!isEditMode) {
            // 비밀번호 (추가 시만)
            addFormField(formPanel, gbc, row++, "비밀번호*:", passwordField = new JPasswordField(20));
            
            // 비밀번호 확인
            addFormField(formPanel, gbc, row++, "비밀번호 확인*:", confirmPasswordField = new JPasswordField(20));
        }
        
        // 이름
        addFormField(formPanel, gbc, row++, "이름*:", nameField = new JTextField(20));
        
        // 이메일
        addFormField(formPanel, gbc, row++, "이메일:", emailField = new JTextField(20));
        
        // 전화번호
        addFormField(formPanel, gbc, row++, "전화번호:", phoneField = new JTextField(20));
        
        // 권한
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        JLabel roleLabel = new JLabel("권한*:");
        roleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        formPanel.add(roleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        roleComboBox = new JComboBox<>(new String[]{"사용자", "관리자"});
        roleComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        formPanel.add(roleComboBox, gbc);
        row++;
        
        // 활성화
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        JLabel enabledLabel = new JLabel("활성화:");
        enabledLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        formPanel.add(enabledLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        enabledCheckBox = new JCheckBox();
        enabledCheckBox.setSelected(true);
        formPanel.add(enabledCheckBox, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton saveButton = new JButton("저장");
        saveButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        saveButton.setBackground(new Color(46, 204, 113));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(e -> save());
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("취소");
        cancelButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        cancelButton.setBackground(new Color(149, 165, 166));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        panel.add(jLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        if (field instanceof JTextField) {
            field.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        }
        panel.add(field, gbc);
    }

    private void fillData(User user) {
        usernameField.setText(user.getUsername());
        usernameField.setEnabled(false); // 수정 시 사용자명 변경 불가
        nameField.setText(user.getName());
        emailField.setText(user.getEmail() != null ? user.getEmail() : "");
        phoneField.setText(user.getPhone() != null ? user.getPhone() : "");
        roleComboBox.setSelectedIndex(user.getRole() == User.UserRole.ROLE_ADMIN ? 1 : 0);
        enabledCheckBox.setSelected(user.getEnabled());
    }

    private void save() {
        String username = usernameField.getText().trim();
        String name = nameField.getText().trim();

        if (username.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "필수 항목을 모두 입력해주세요.",
                    "입력 오류",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 비밀번호 검증 (추가 시만)
        String password = "";
        if (!isEditMode) {
            password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "비밀번호를 입력해주세요.",
                        "입력 오류",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this,
                        "비밀번호가 일치하지 않습니다.",
                        "입력 오류",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (password.length() < 4) {
                JOptionPane.showMessageDialog(this,
                        "비밀번호는 최소 4자 이상이어야 합니다.",
                        "입력 오류",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        User.UserRole role = roleComboBox.getSelectedIndex() == 1 ? 
                            User.UserRole.ROLE_ADMIN : User.UserRole.ROLE_USER;

        user = User.builder()
                .username(username)
                .password(password) // 수정 시에는 DAO에서 기존 비밀번호 유지
                .name(name)
                .email(emailField.getText().trim().isEmpty() ? null : emailField.getText().trim())
                .phone(phoneField.getText().trim().isEmpty() ? null : phoneField.getText().trim())
                .role(role)
                .enabled(enabledCheckBox.isSelected())
                .build();

        confirmed = true;
        dispose();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public User getUser() {
        return user;
    }
}

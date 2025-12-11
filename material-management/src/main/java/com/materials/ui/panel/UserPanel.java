package com.materials.ui.panel;

import com.materials.dao.UserDAO;
import com.materials.model.User;
import com.materials.util.SessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserPanel extends JPanel {
    private UserDAO userDAO;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public UserPanel() {
        this.userDAO = new UserDAO();
        initComponents();
        loadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(236, 240, 241));

        // 상단 패널 (검색 및 버튼)
        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.setOpaque(false);

        // 검색 패널
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);

        JLabel searchLabel = new JLabel("검색:");
        searchLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        searchPanel.add(searchLabel);

        searchField = new JTextField(20);
        searchField.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        searchPanel.add(searchField);

        JButton searchButton = new JButton("검색");
        searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        searchButton.setBackground(new Color(52, 152, 219));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(e -> search());
        searchPanel.add(searchButton);

        JButton refreshButton = new JButton("새로고침");
        refreshButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        refreshButton.setBackground(new Color(149, 165, 166));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> refresh());
        searchPanel.add(refreshButton);

        topPanel.add(searchPanel, BorderLayout.WEST);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton addButton = new JButton("사용자 추가");
        addButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> addUser());
        buttonPanel.add(addButton);

        JButton editButton = new JButton("수정");
        editButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        editButton.setBackground(new Color(52, 152, 219));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.addActionListener(e -> editUser());
        buttonPanel.add(editButton);

        JButton passwordButton = new JButton("비밀번호 변경");
        passwordButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        passwordButton.setBackground(new Color(155, 89, 182));
        passwordButton.setForeground(Color.WHITE);
        passwordButton.setFocusPainted(false);
        passwordButton.addActionListener(e -> changePassword());
        buttonPanel.add(passwordButton);

        JButton deleteButton = new JButton("삭제");
        deleteButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        deleteButton.setBackground(new Color(231, 76, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(e -> deleteUser());
        buttonPanel.add(deleteButton);

        topPanel.add(buttonPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // 테이블 패널
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        String[] columns = {"ID", "사용자명", "이름", "이메일", "전화번호", "권한", "상태", "생성일"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        userTable = new JTable(tableModel);
        userTable.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        userTable.setRowHeight(35);
        userTable.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 12));
        userTable.getTableHeader().setBackground(new Color(52, 73, 94));
        userTable.getTableHeader().setForeground(Color.WHITE);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // ID 컬럼 숨기기
        userTable.getColumnModel().getColumn(0).setMinWidth(0);
        userTable.getColumnModel().getColumn(0).setMaxWidth(0);
        userTable.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scrollPane = new JScrollPane(userTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<User> users = userDAO.findAll();
        for (User user : users) {
            tableModel.addRow(new Object[]{
                    user.getId(),
                    user.getUsername(),
                    user.getName(),
                    user.getEmail() != null ? user.getEmail() : "-",
                    user.getPhone() != null ? user.getPhone() : "-",
                    user.getRole().getDescription(),
                    user.getEnabled() ? "활성" : "비활성",
                    user.getCreatedAt().toString().substring(0, 10)
            });
        }
    }

    private void search() {
        String searchText = searchField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            loadData();
            return;
        }

        tableModel.setRowCount(0);
        List<User> users = userDAO.findAll();
        for (User user : users) {
            if (user.getUsername().toLowerCase().contains(searchText) ||
                user.getName().toLowerCase().contains(searchText) ||
                (user.getEmail() != null && user.getEmail().toLowerCase().contains(searchText))) {
                tableModel.addRow(new Object[]{
                        user.getId(),
                        user.getUsername(),
                        user.getName(),
                        user.getEmail() != null ? user.getEmail() : "-",
                        user.getPhone() != null ? user.getPhone() : "-",
                        user.getRole().getDescription(),
                        user.getEnabled() ? "활성" : "비활성",
                        user.getCreatedAt().toString().substring(0, 10)
                });
            }
        }
    }

    public void refresh() {
        loadData();
    }

    private void addUser() {
        UserDialog dialog = new UserDialog((Frame) SwingUtilities.getWindowAncestor(this), null);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            User user = dialog.getUser();
            
            // 중복 확인
            if (userDAO.existsByUsername(user.getUsername())) {
                JOptionPane.showMessageDialog(this, 
                        "이미 존재하는 사용자명입니다.", 
                        "오류", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            userDAO.save(user);
            JOptionPane.showMessageDialog(this, "사용자가 추가되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
            loadData();
        }
    }

    private void editUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "수정할 사용자를 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Long userId = (Long) tableModel.getValueAt(selectedRow, 0);
        User user = userDAO.findById(userId).orElse(null);
        if (user == null) {
            JOptionPane.showMessageDialog(this, "사용자 정보를 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserDialog dialog = new UserDialog((Frame) SwingUtilities.getWindowAncestor(this), user);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            User updatedUser = dialog.getUser();
            updatedUser.setId(userId);
            updatedUser.setPassword(user.getPassword()); // 기존 비밀번호 유지
            userDAO.save(updatedUser);
            JOptionPane.showMessageDialog(this, "사용자 정보가 수정되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
            loadData();
        }
    }

    private void changePassword() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "사용자를 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Long userId = (Long) tableModel.getValueAt(selectedRow, 0);
        String username = (String) tableModel.getValueAt(selectedRow, 1);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        JPasswordField newPasswordField = new JPasswordField(20);
        JPasswordField confirmPasswordField = new JPasswordField(20);

        panel.add(new JLabel("새 비밀번호:"));
        panel.add(newPasswordField);
        panel.add(new JLabel("비밀번호 확인:"));
        panel.add(confirmPasswordField);

        int result = JOptionPane.showConfirmDialog(this, panel, 
                username + " 비밀번호 변경", 
                JOptionPane.OK_CANCEL_OPTION, 
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            userDAO.updatePassword(userId, newPassword);
            JOptionPane.showMessageDialog(this, "비밀번호가 변경되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "삭제할 사용자를 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Long userId = (Long) tableModel.getValueAt(selectedRow, 0);
        String username = (String) tableModel.getValueAt(selectedRow, 1);

        // 자기 자신은 삭제 불가
        if (username.equals(SessionManager.getCurrentUsername())) {
            JOptionPane.showMessageDialog(this, "자기 자신은 삭제할 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                username + " 사용자를 삭제하시겠습니까?",
                "삭제 확인",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            userDAO.delete(userId);
            JOptionPane.showMessageDialog(this, "사용자가 삭제되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
            loadData();
        }
    }
}

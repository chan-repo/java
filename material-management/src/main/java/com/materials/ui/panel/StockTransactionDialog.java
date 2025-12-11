package com.materials.ui.panel;

import com.materials.model.Material;
import com.materials.model.StockHistory;

import javax.swing.*;
import java.awt.*;

public class StockTransactionDialog extends JDialog {
    private JTextField quantityField;
    private JTextArea noteArea;
    private boolean confirmed = false;
    private int quantity;
    private String note;

    public StockTransactionDialog(Frame owner, Material material, StockHistory.TransactionType type) {
        super(owner, type.getDescription(), true);
        initComponents(material, type);
    }

    private void initComponents(Material material, StockHistory.TransactionType type) {
        setSize(450, 400);
        setLocationRelativeTo(getOwner());
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 정보 패널
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("자재 정보"));

        infoPanel.add(createInfoLabel("자재코드:"));
        infoPanel.add(createInfoValue(material.getCode()));
        
        infoPanel.add(createInfoLabel("자재명:"));
        infoPanel.add(createInfoValue(material.getName()));
        
        infoPanel.add(createInfoLabel("현재고:"));
        infoPanel.add(createInfoValue(String.valueOf(material.getCurrentStock())));

        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // 입력 패널
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 수량
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        JLabel quantityLabel = new JLabel("수량*:");
        quantityLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        inputPanel.add(quantityLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        quantityField = new JTextField(20);
        quantityField.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        inputPanel.add(quantityField, gbc);

        // 비고
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        JLabel noteLabel = new JLabel("비고:");
        noteLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        inputPanel.add(noteLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        noteArea = new JTextArea(5, 20);
        noteArea.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        noteArea.setLineWrap(true);
        noteArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(noteArea);
        inputPanel.add(scrollPane, gbc);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        Color buttonColor = type == StockHistory.TransactionType.IN ? 
                           new Color(26, 188, 156) : new Color(230, 126, 34);
        
        JButton confirmButton = new JButton(type.getDescription());
        confirmButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        confirmButton.setBackground(buttonColor);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);
        confirmButton.addActionListener(e -> confirm());
        buttonPanel.add(confirmButton);

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

    private JLabel createInfoLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        return label;
    }

    private JLabel createInfoValue(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        return label;
    }

    private void confirm() {
        String quantityStr = quantityField.getText().trim();
        
        if (quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "수량을 입력해주세요.",
                    "입력 오류",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this,
                        "수량은 0보다 커야 합니다.",
                        "입력 오류",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            note = noteArea.getText().trim();
            confirmed = true;
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "올바른 숫자를 입력해주세요.",
                    "입력 오류",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getNote() {
        return note;
    }
}

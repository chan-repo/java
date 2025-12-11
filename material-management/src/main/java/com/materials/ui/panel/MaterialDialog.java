package com.materials.ui.panel;

import com.materials.model.Material;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class MaterialDialog extends JDialog {
    private JTextField codeField;
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField unitField;
    private JTextField priceField;
    private JTextField minStockField;
    private JTextField locationField;
    private JTextArea descriptionArea;
    private boolean confirmed = false;
    private Material material;

    public MaterialDialog(Frame owner, Material material) {
        super(owner, material == null ? "자재 추가" : "자재 수정", true);
        this.material = material;
        initComponents();
        if (material != null) {
            fillData(material);
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

        // 자재코드
        addFormField(formPanel, gbc, row++, "자재코드*:", codeField = new JTextField(20));
        
        // 자재명
        addFormField(formPanel, gbc, row++, "자재명*:", nameField = new JTextField(20));
        
        // 카테고리
        addFormField(formPanel, gbc, row++, "카테고리:", categoryField = new JTextField(20));
        
        // 단위
        addFormField(formPanel, gbc, row++, "단위:", unitField = new JTextField(20));
        
        // 단가
        addFormField(formPanel, gbc, row++, "단가:", priceField = new JTextField(20));
        
        // 최소재고
        addFormField(formPanel, gbc, row++, "최소재고*:", minStockField = new JTextField(20));
        minStockField.setText("0");
        
        // 위치
        addFormField(formPanel, gbc, row++, "위치:", locationField = new JTextField(20));
        
        // 설명
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        JLabel descLabel = new JLabel("설명:");
        descLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        formPanel.add(descLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        descriptionArea = new JTextArea(4, 20);
        descriptionArea.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        formPanel.add(scrollPane, gbc);

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

    private void addFormField(JPanel panel, GridBagConstraints gbc, int row, String label, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        panel.add(jLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        field.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        panel.add(field, gbc);
    }

    private void fillData(Material material) {
        codeField.setText(material.getCode());
        codeField.setEnabled(false); // 수정 시 코드 변경 불가
        nameField.setText(material.getName());
        categoryField.setText(material.getCategory() != null ? material.getCategory() : "");
        unitField.setText(material.getUnit() != null ? material.getUnit() : "");
        priceField.setText(material.getUnitPrice() != null ? material.getUnitPrice().toString() : "");
        minStockField.setText(String.valueOf(material.getMinStock()));
        locationField.setText(material.getLocation() != null ? material.getLocation() : "");
        descriptionArea.setText(material.getDescription() != null ? material.getDescription() : "");
    }

    private void save() {
        String code = codeField.getText().trim();
        String name = nameField.getText().trim();
        String minStockStr = minStockField.getText().trim();

        if (code.isEmpty() || name.isEmpty() || minStockStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "필수 항목을 모두 입력해주세요.",
                    "입력 오류",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int minStock = Integer.parseInt(minStockStr);
            BigDecimal price = null;
            if (!priceField.getText().trim().isEmpty()) {
                price = new BigDecimal(priceField.getText().trim());
            }

            material = Material.builder()
                    .code(code)
                    .name(name)
                    .category(categoryField.getText().trim().isEmpty() ? null : categoryField.getText().trim())
                    .unit(unitField.getText().trim().isEmpty() ? null : unitField.getText().trim())
                    .unitPrice(price)
                    .currentStock(material != null ? material.getCurrentStock() : 0)
                    .minStock(minStock)
                    .location(locationField.getText().trim().isEmpty() ? null : locationField.getText().trim())
                    .description(descriptionArea.getText().trim().isEmpty() ? null : descriptionArea.getText().trim())
                    .active(true)
                    .build();

            confirmed = true;
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "숫자 형식이 올바르지 않습니다.",
                    "입력 오류",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Material getMaterial() {
        return material;
    }
}

package com.materials.ui.panel;

import com.materials.dao.MaterialDAO;
import com.materials.dao.StockHistoryDAO;
import com.materials.model.Material;
import com.materials.model.StockHistory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashboardPanel extends JPanel {
    private MaterialDAO materialDAO;
    private StockHistoryDAO historyDAO;
    private JLabel totalMaterialsLabel;
    private JLabel lowStockLabel;
    private JLabel recentTransactionsLabel;
    private JTable lowStockTable;
    private JTable recentHistoryTable;

    public DashboardPanel() {
        this.materialDAO = new MaterialDAO();
        this.historyDAO = new StockHistoryDAO();
        initComponents();
        loadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(236, 240, 241));

        // 상단 통계 패널
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        statsPanel.setOpaque(false);
        statsPanel.setPreferredSize(new Dimension(0, 120));

        // 통계 카드들
        totalMaterialsLabel = new JLabel("0");
        lowStockLabel = new JLabel("0");
        recentTransactionsLabel = new JLabel("0");

        statsPanel.add(createStatCard("총 자재 수", totalMaterialsLabel, new Color(52, 152, 219)));
        statsPanel.add(createStatCard("재고 부족 자재", lowStockLabel, new Color(231, 76, 60)));
        statsPanel.add(createStatCard("최근 거래", recentTransactionsLabel, new Color(46, 204, 113)));

        add(statsPanel, BorderLayout.NORTH);

        // 중앙 패널 - 재고 부족 자재와 최근 입출고 이력
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 15));
        centerPanel.setOpaque(false);

        // 재고 부족 자재 테이블
        JPanel lowStockPanel = new JPanel(new BorderLayout());
        lowStockPanel.setBackground(Color.WHITE);
        lowStockPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lowStockTitle = new JLabel("재고 부족 자재");
        lowStockTitle.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        lowStockPanel.add(lowStockTitle, BorderLayout.NORTH);

        String[] lowStockColumns = {"자재코드", "자재명", "현재고", "최소재고", "위치"};
        DefaultTableModel lowStockModel = new DefaultTableModel(lowStockColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        lowStockTable = new JTable(lowStockModel);
        lowStockTable.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        lowStockTable.setRowHeight(30);
        lowStockTable.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 12));
        lowStockTable.getTableHeader().setBackground(new Color(52, 73, 94));
        lowStockTable.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane lowStockScrollPane = new JScrollPane(lowStockTable);
        lowStockScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        lowStockPanel.add(lowStockScrollPane, BorderLayout.CENTER);

        centerPanel.add(lowStockPanel);

        // 최근 입출고 이력 테이블
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBackground(Color.WHITE);
        historyPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel historyTitle = new JLabel("최근 입출고 이력");
        historyTitle.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        historyPanel.add(historyTitle, BorderLayout.NORTH);

        String[] historyColumns = {"일시", "자재명", "구분", "수량", "수행자"};
        DefaultTableModel historyModel = new DefaultTableModel(historyColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        recentHistoryTable = new JTable(historyModel);
        recentHistoryTable.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        recentHistoryTable.setRowHeight(30);
        recentHistoryTable.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 12));
        recentHistoryTable.getTableHeader().setBackground(new Color(52, 73, 94));
        recentHistoryTable.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane historyScrollPane = new JScrollPane(recentHistoryTable);
        historyScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        historyPanel.add(historyScrollPane, BorderLayout.CENTER);

        centerPanel.add(historyPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel createStatCard(String title, JLabel valueLabel, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(127, 140, 141));

        valueLabel.setFont(new Font("맑은 고딕", Font.BOLD, 36));
        valueLabel.setForeground(color);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    public void loadData() {
        // 통계 데이터 로드
        List<Material> allMaterials = materialDAO.findActive();
        List<Material> lowStockMaterials = materialDAO.findLowStock();
        List<StockHistory> recentHistories = historyDAO.findAll();

        totalMaterialsLabel.setText(String.valueOf(allMaterials.size()));
        lowStockLabel.setText(String.valueOf(lowStockMaterials.size()));
        recentTransactionsLabel.setText(String.valueOf(Math.min(recentHistories.size(), 100)));

        // 재고 부족 자재 테이블
        DefaultTableModel lowStockModel = (DefaultTableModel) lowStockTable.getModel();
        lowStockModel.setRowCount(0);
        for (Material material : lowStockMaterials) {
            lowStockModel.addRow(new Object[]{
                    material.getCode(),
                    material.getName(),
                    material.getCurrentStock(),
                    material.getMinStock(),
                    material.getLocation() != null ? material.getLocation() : "-"
            });
        }

        // 최근 입출고 이력 테이블
        DefaultTableModel historyModel = (DefaultTableModel) recentHistoryTable.getModel();
        historyModel.setRowCount(0);
        for (int i = 0; i < Math.min(recentHistories.size(), 10); i++) {
            StockHistory history = recentHistories.get(i);
            historyModel.addRow(new Object[]{
                    history.getCreatedAt().toString().substring(0, 19),
                    history.getMaterialName(),
                    history.getType().getDescription(),
                    history.getQuantity(),
                    history.getPerformedBy()
            });
        }
    }

    public void refresh() {
        loadData();
    }
}

package com.materials.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockHistory {
    private Long id;
    private Long materialId;
    private String materialCode;
    private String materialName;
    private TransactionType type;
    private Integer quantity;
    private Integer stockBefore;
    private Integer stockAfter;
    private String note;
    private String performedBy;
    private LocalDateTime createdAt;

    public enum TransactionType {
        IN("입고"),
        OUT("출고"),
        ADJUST("조정");

        private final String description;

        TransactionType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}

package com.materials.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    private Long id;
    private String code;
    private String name;
    private String category;
    private String unit;
    private BigDecimal unitPrice;
    private Integer currentStock;
    private Integer minStock;
    private String location;
    private String description;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

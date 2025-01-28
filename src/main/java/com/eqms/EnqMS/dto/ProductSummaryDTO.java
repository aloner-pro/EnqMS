package com.eqms.EnqMS.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductSummaryDTO {
    private Long id;
    private String name;

    // Constructor, Getters, and Setters

    public ProductSummaryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
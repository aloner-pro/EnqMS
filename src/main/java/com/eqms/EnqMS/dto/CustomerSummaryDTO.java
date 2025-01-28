package com.eqms.EnqMS.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CustomerSummaryDTO {
    private Long id;
    private String name;
    private List<ProductSummaryDTO> products;

    // Constructor, Getters, and Setters

    public CustomerSummaryDTO(Long id, String name, List<ProductSummaryDTO> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

}
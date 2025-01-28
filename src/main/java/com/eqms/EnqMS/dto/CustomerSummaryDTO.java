package com.eqms.EnqMS.dto;

import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductSummaryDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSummaryDTO> products) {
        this.products = products;
    }
}
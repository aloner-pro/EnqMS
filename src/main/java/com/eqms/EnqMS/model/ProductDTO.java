package com.eqms.EnqMS.model;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


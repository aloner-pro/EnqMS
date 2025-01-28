package com.eqms.EnqMS.model;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class CustomerDTO {
    private String name;
    private List<ProductDTO> products;

    public CustomerDTO(String name, List<ProductDTO> products) {
        this.name = name;
        this.products = products;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}

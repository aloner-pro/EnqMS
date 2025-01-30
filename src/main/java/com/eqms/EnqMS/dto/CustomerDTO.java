package com.eqms.EnqMS.dto;

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
}

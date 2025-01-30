package com.eqms.EnqMS.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductSummaryDTO {
    private Long id;
    private String name;

    public ProductSummaryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
package com.eqms.EnqMS.model;

import jakarta.persistence.*;
import com.eqms.EnqMS.dto.CustomerSummaryDTO;
import com.eqms.EnqMS.dto.ProductSummaryDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public CustomerSummaryDTO toDTO() {
        List<ProductSummaryDTO> productSummaries = products.stream()
                .map(product -> new ProductSummaryDTO(product.getId(), product.getName()))
                .collect(Collectors.toList());
        return new CustomerSummaryDTO(id, name, productSummaries);
    }
}


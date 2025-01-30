package com.eqms.EnqMS.service;
import com.eqms.EnqMS.dto.CustomerSummaryDTO;
import com.eqms.EnqMS.dto.ProductDTO;
import com.eqms.EnqMS.dto.ProductSummaryDTO;
import com.eqms.EnqMS.model.Customer;
import com.eqms.EnqMS.dto.CustomerDTO;
import com.eqms.EnqMS.model.Product;
import com.eqms.EnqMS.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private List<ProductSummaryDTO> convertToProductSummaryDTO(List<Product> products) {
        return products.stream()
                .map(product -> new ProductSummaryDTO(product.getId(), product.getName()))
                .collect(Collectors.toList());
    }


    @Transactional
    public Customer addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());

        List<Product> products = customerDTO.getProducts().stream().map(productDTO -> {
            Product product = new Product();
            product.setName(productDTO.getName());
            product.setCustomer(customer);
            return product;
        }).collect(Collectors.toList());

        customer.setProducts(products);

        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<CustomerSummaryDTO> getAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> new CustomerSummaryDTO(
                        customer.getId(),
                        customer.getName(),
                        convertToProductSummaryDTO(customer.getProducts())
                ))
                .toList();
    }
}


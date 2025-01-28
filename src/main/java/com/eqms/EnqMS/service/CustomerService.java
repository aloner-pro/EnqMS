package com.eqms.EnqMS.service;

import com.eqms.EnqMS.model.Customer;
import com.eqms.EnqMS.model.CustomerDTO;
import com.eqms.EnqMS.model.Product;
import com.eqms.EnqMS.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

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

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }
}


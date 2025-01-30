package com.eqms.EnqMS.controller;

import com.eqms.EnqMS.model.Customer;
import com.eqms.EnqMS.dto.CustomerDTO;
import com.eqms.EnqMS.dto.CustomerSummaryDTO;
import com.eqms.EnqMS.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer createdCustomer = customerService.addCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @GetMapping("/{id}")
    public CustomerSummaryDTO getCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        return customer.toDTO();
    }
}


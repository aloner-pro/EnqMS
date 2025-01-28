package com.eqms.EnqMS.repository;

import com.eqms.EnqMS.model.Customer;
import com.eqms.EnqMS.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}


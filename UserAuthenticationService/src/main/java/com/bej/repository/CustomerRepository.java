package com.bej.repository;

import com.bej.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByCustomerIdAndPassword(String customerId, String password);
}

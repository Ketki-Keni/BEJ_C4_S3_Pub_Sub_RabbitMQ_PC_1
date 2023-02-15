package com.bej.service;

import com.bej.domain.Customer;
import com.bej.exception.UserAlreadyExistsException;
import com.bej.exception.UserNotFoundException;

public interface CustomerService {
    Customer saveUser(Customer customer) throws UserAlreadyExistsException;
    Customer findByCustomerIdAndPassword(int customerId, String password) throws UserNotFoundException;
}

/*
 * Author : Ketki Keni
 * Date : 13-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.service;

import com.bej.domain.Customer;
import com.bej.exception.UserAlreadyExistsException;
import com.bej.exception.UserNotFoundException;
import com.bej.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveUser(Customer customer) throws UserAlreadyExistsException {
//        if(customerRepository.findById(customer.getUserId()).isPresent())
//        {
//            throw new UserAlreadyExistsException();
//        }
        System.out.println(customer);
        return customerRepository.save(customer);
    }

    @Override
    public Customer findByCustomerIdAndPassword(int customerId, String password) throws UserNotFoundException {
        System.out.println("userId: "+customerId);
        System.out.println("password: "+password);
        Customer loggedInCustomer = customerRepository.findByCustomerIdAndPassword(customerId,password);
        System.out.println(loggedInCustomer);
        if(loggedInCustomer == null)
        {
            throw new UserNotFoundException();
        }
        return loggedInCustomer;
    }
}

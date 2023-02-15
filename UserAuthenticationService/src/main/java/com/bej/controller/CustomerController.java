/*
 * Author : Ketki Keni
 * Date : 13-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.controller;

import com.bej.domain.Customer;
import com.bej.exception.UserAlreadyExistsException;
import com.bej.exception.UserNotFoundException;
import com.bej.service.SecurityTokenGenerator;
import com.bej.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    private CustomerServiceImpl userService;
    SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public CustomerController(CustomerServiceImpl userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    //Uri : http://localhost:8082/api/v1/user : Method : Post
    @PostMapping("/customer")
    public ResponseEntity<?> saveUser(@RequestBody Customer customer) throws UserAlreadyExistsException {
        return new ResponseEntity<>(userService.saveUser(customer), HttpStatus.CREATED);
    }

    //Uri : http://localhost:8082/api/v1/login : Method : Post
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Customer customer) throws UserNotFoundException
    {
        Customer retrievedCustomer = userService.findByCustomerIdAndPassword(customer.getCustomerId(), customer.getPassword());
        if(retrievedCustomer ==null)
        {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>(securityTokenGenerator.generateToken(customer),HttpStatus.OK);
    }
}

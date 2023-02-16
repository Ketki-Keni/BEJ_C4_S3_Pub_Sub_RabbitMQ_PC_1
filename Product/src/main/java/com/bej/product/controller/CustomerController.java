/*
 * Author : Ketki Keni
 * Date : 30-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.product.controller;

import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;
import com.bej.product.exception.ProductNotFoundException;
import com.bej.product.repository.CustomerRepository;
import com.bej.product.service.CustomerService;
import com.bej.product.service.CustomerServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class CustomerController {
    CustomerServiceImpl customerService;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService,
                              CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    //Uri : http://localhost:8083/api/v2/demo : Method : Get
    @GetMapping("/user/demo")
    public ResponseEntity<String> get(){
        return new ResponseEntity<String>("Sample Demo",HttpStatus.OK);
    }

    //Uri : http://localhost:8083/api/v2/register : Method : Post
    @PostMapping("/register")
    public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer){
        Customer newCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);
    }

    //Uri : http://localhost:8083/api/v2/customers : Method : Get
    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }


    //Uri : http://localhost:8082/api/user/v2/101/product : Method : Post
    @PostMapping("/user/{customerId}/product")
    public ResponseEntity<?> insertProduct(@RequestBody Product product, @PathVariable String customerId, HttpServletRequest request){
        return new ResponseEntity<>(customerService.addProductToList(product, customerId), HttpStatus.CREATED);
    }

    //Uri : http://localhost:8082/api/user/v2/101/products : Method : Get
    @GetMapping("/user/{customerId}/products")
    public ResponseEntity<?> getAllProductsFromList(@PathVariable String customerId, HttpServletRequest request){
        return new ResponseEntity<>(customerService.getAllProducts(customerId), HttpStatus.OK);
    }

    //Uri : http://localhost:8082/api/v2/user/103/product/3 : Method : Delete
    @DeleteMapping("user/{customerId}/product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String customerId, @PathVariable String productId) throws ProductNotFoundException {

            return new ResponseEntity<>(customerService.deleteProductFromList(customerId,productId),HttpStatus.OK);
    }
}

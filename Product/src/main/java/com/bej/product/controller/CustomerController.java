/*
 * Author : Ketki Keni
 * Date : 30-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.product.controller;

import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;
import com.bej.product.service.CustomerService;
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
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //Uri : http://localhost:8083/api/v1/customer : Method : Post
    @PostMapping("/customer")
    public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer){
        Customer customer1 = customerService.saveCustomer(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    //Uri : http://localhost:8083/api/v1/customers : Method : Get
    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

//    ////Uri : http://localhost:8083/api/v1/CustomerByProduct/Samsung Phone : Method : Get
//    @GetMapping("/CustomerByProduct/{customerProduct}")
//    public ResponseEntity<?> getAllCustomerByProduct(@PathVariable String customerProduct){
//        List<Customer> customers=customerService.getAllCustomerByProduct(customerProduct);
//        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
//    }




    //Uri : http://localhost:8082/api/v1/product : Method : Post
    @PostMapping("/product")
    public ResponseEntity<?> insertProduct(@RequestBody Product product, HttpServletRequest request){
        System.out.println("header" +request.getHeader("Authorization"));
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("email from claims :: " + claims.getSubject());
        String email = claims.getSubject();
        System.out.println("email :: "+email);
        return new ResponseEntity<>(customerService.addProductToList(product, email), HttpStatus.CREATED);
    }

    //Uri : http://localhost:8082/api/v1/products : Method : Get
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(HttpServletRequest request){
        System.out.println("header" +request.getHeader("Authorization"));
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("email from claims :: " + claims.getSubject());
        String email = claims.getSubject();
        System.out.println("email :: "+email);
        return new ResponseEntity<>(customerService.getAllProducts(email), HttpStatus.OK);
    }

//
//    //Uri : http://localhost:8082/api/v1/product/103 : Method : Delete
//    @DeleteMapping("/product/{id}")
//    public ResponseEntity<?> deleteProduct(@PathVariable int id){
//        if(productService.deleteProduct(id)) {
//            return new ResponseEntity<String>("Product Deleted",HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<String>("Error Occurred",HttpStatus.NOT_FOUND);
//        }
//    }
}

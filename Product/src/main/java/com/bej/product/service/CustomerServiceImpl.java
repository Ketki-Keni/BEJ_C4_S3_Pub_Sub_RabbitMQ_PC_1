/*
 * Author : Ketki Keni
 * Date : 30-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.product.service;

import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;
import com.bej.product.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

//    @Override
//    public List<Customer> getAllCustomerByProduct(String productName) {
//        return customerRepository.findAllCustomerByCustomerProduct(productName);
//    }

    @Override
    public Customer addProductToList(Product product,String email) {
        Customer customer = customerRepository.findByCustomerEmail(email);
        if(customer.getProductList() == null)
        {
            customer.setProductList(Arrays.asList(product));
        }
        else {
            List<Product> products = customer.getProductList();
            products.add(product);
            customer.setProductList(products);
        }
        return customerRepository.save(customer);

    }

    @Override
    public List<Product> getAllProducts(String email) {
        return  customerRepository.findByCustomerEmail(email).getProductList();
    }

//    @Override
//    public boolean deleteProductFromList(int productId) {
//        customerRepository.deleteById(productId);
//        return true;
//    }
}

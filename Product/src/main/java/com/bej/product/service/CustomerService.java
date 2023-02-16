package com.bej.product.service;

import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;
import com.bej.product.exception.CustomerAlreadyExistsException;
import com.bej.product.exception.CustomerNotFoundException;
import com.bej.product.exception.ProductNotFoundException;

import java.util.List;

public interface CustomerService {
    public Customer saveCustomer(Customer customer) throws CustomerAlreadyExistsException;
    public List<Customer> getAllCustomers() throws CustomerNotFoundException;
    public Customer addProductToList(Product product, String customerId);
    public List<Product> getAllProducts(String customerId) throws ProductNotFoundException;
    public Customer deleteProductFromList(String customerId, String productId) throws ProductNotFoundException;

}

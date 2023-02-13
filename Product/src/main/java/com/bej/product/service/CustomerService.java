package com.bej.product.service;

import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;

import java.util.List;

public interface CustomerService {
    public Customer saveCustomer(Customer customer);
    public List<Customer> getAllCustomers();
//    public List<Customer> getAllCustomerByProduct(String productName);
    public Customer addProductToList(Product product, String email);
    public List<Product> getAllProducts(String email);
//    public boolean deleteProductFromList(int productId);

}

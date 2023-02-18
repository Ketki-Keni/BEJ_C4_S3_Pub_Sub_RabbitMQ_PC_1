/*
 * Author : Ketki Keni
 * Date : 30-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.product.service;

import com.bej.product.config.ProductDTO;
import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;
import com.bej.product.exception.ProductNotFoundException;
import com.bej.product.proxy.CustomerProxy;
import com.bej.product.repository.CustomerRepository;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerProxy customerProxy;
    CustomerRepository customerRepository;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    DirectExchange exchange;

    @Autowired
    public CustomerServiceImpl(CustomerProxy customerProxy, CustomerRepository customerRepository) {
        this.customerProxy = customerProxy;
        this.customerRepository = customerRepository;
    }
    @Override
    public Customer saveCustomer(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        System.out.println(newCustomer);
        if (!(newCustomer.getCustomerEmail().isEmpty())){
            ResponseEntity responseEntity=customerProxy.saveCustomerInAuthService(newCustomer);
            System.out.println(responseEntity.getBody());
        }
        return newCustomer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer addProductToList(Product product,String customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId);
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
    public List<Product> getAllProducts(String customerId) {
//        return  customerRepository.findById(customerId).get().getProductList();

        ProductDTO productDTO = new ProductDTO();
        List<Product> products= customerRepository.findById(customerId).get().getProductList();
        List<Product> notInStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (!product.isInStock()){
                notInStockProducts.add(product);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("notListenedToTracks",notInStockProducts);
        jsonObject.put("Id", customerId);
        productDTO.setJsonObject(jsonObject);
        rabbitTemplate.convertAndSend(exchange.getName(),"product-routing",productDTO);
        return products;
    }

    @Override
    public Customer deleteProductFromList(String customerId, String productId) throws ProductNotFoundException {

        boolean productIdIsPresent = false;
        Customer customer = customerRepository.findById(customerId).get();
        List<Product> products = customer.getProductList();
        productIdIsPresent = products.removeIf(x->x.getProductId().equals(productId));
        if(!productIdIsPresent)
        {
            throw new ProductNotFoundException();
        }
        customer.setProductList(products);
        return customerRepository.save(customer);
    }
}

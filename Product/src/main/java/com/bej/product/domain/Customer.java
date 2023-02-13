/*
 * Author : Ketki Keni
 * Date : 30-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.product.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Customer {
    @Id
    private String customerEmail;
    private String customerName;
    private String customerPhoneNo;
    private List<Product> productList;

    public Customer() {
    }

    public Customer(String customerEmail, String customerName, String customerPhoneNo, List<Product> productList) {
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.customerPhoneNo = customerPhoneNo;
        this.productList = productList;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public void setCustomerPhoneNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerEmail +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNo='" + customerPhoneNo + '\'' +
                ", customerProduct=" + productList +
                '}';
    }
}

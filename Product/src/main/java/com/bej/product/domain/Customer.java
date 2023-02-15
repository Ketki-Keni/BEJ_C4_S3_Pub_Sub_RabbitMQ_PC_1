/*
 * Author : Ketki Keni
 * Date : 30-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.product.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Customer {
    @Id
    private int customerId;
    private String customerEmail;
    private String password;
    private String customerName;
    private String customerPhoneNo;
    private List<Product> productList;
}

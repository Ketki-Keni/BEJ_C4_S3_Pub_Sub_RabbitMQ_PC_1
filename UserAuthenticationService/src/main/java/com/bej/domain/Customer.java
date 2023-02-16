/*
 * Author : Ketki Keni
 * Date : 13-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Customer {
    @Id
    private String customerId;
    private String password;

}

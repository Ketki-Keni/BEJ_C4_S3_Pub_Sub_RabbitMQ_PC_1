/*
 * Author : Ketki Keni
 * Date : 17-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.domain;

import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;

public class Notification {
    @Id
    private String customerId;
    private String notificationMessage;
    JSONObject productNames;


    public Notification() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public JSONObject getProductNames() {
        return productNames;
    }

    public void setProductNames(JSONObject productNames) {
        this.productNames = productNames;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id='" + customerId + '\'' +
                ", notificationMessage='" + notificationMessage + '\'' +
                ", productNames=" + productNames +
                '}';
    }
}

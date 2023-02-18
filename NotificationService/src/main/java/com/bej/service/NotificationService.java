package com.bej.service;


import com.bej.config.ProductDTO;
import com.bej.domain.Notification;


public interface NotificationService {
    public Notification getNotification(String customerId);
    void saveNotifications(ProductDTO productDTO);
}




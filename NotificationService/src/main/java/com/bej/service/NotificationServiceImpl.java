package com.bej.service;

import com.bej.config.ProductDTO;
import com.bej.domain.Notification;
import com.bej.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification getNotification(String customerId) {
        return notificationRepository.findById(customerId).get();
    }

    @RabbitListener(queues = "product-queue")
    @Override
    public void saveNotifications(ProductDTO productDTO) {
        Notification notification=new Notification();
        String Id=productDTO.getJsonObject().get("Id").toString();
        if(notificationRepository.findById(Id).isEmpty())
        {
            notification.setCustomerId(Id);
        }
        notification.setNotificationMessage("The list of Products that are not in stock");
        notification.setProductNames(productDTO.getJsonObject());
        notificationRepository.save(notification);
    }
}


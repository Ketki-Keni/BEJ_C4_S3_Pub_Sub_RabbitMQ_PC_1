package com.bej.controller;

import com.bej.service.NotificationService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v3")
public class NotificationController {
    private NotificationService notificationService;
    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications/{customerId}")
    public ResponseEntity<?> notifications(@PathVariable String customerId){
//        Claims claims = (Claims) request.getAttribute("claims");
//        String id = claims.getSubject();
//        System.out.println("user ID from claims(notification) :: " + claims.getSubject());
//        System.out.println("ID " + id);
        return new ResponseEntity<>(notificationService.getNotification(customerId),HttpStatus.OK);
    }
}

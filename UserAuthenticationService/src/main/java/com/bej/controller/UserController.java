/*
 * Author : Ketki Keni
 * Date : 13-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.controller;

import com.bej.domain.User;
import com.bej.exception.UserAlreadyExistsException;
import com.bej.exception.UserNotFoundException;
import com.bej.service.SecurityTokenGenerator;
import com.bej.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private UserService userService;
    SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }
    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException
    {
        User retrievedUser = userService.findByEmailAndPassword(user.getEmail(),user.getPassword());
        if(retrievedUser==null)
        {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>(securityTokenGenerator.generateToken(user),HttpStatus.OK);
    }
}

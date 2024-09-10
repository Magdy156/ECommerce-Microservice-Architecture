package com.ecommerce.wallet_service.user;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@Configuration
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService service;

    
    @GetMapping
    public List<User> getUsers(){
        return service.getUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = service.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody UserLogin user) {        
        return service.loginUser(user);
    }
    

}

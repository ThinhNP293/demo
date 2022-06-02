package vn.alpaca.demo.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.alpaca.demo.Domain.UserEntity;
import vn.alpaca.demo.Services.UserService;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String viewAllUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/register")
    public String viewRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestBody UserEntity user){
        userService.createUser(user);
        return "/";
    }
}

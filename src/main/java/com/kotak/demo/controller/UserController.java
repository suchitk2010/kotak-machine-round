package com.kotak.demo.controller;

import com.kotak.demo.dto.CreateUserRequest;
import com.kotak.demo.dto.UserResponse;
import com.kotak.demo.entity.User;
import com.kotak.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

     @PostMapping
     public UserResponse create(@RequestBody CreateUserRequest r){
       return service.create(r);
     }

     @GetMapping
     public List<UserResponse> all(){
        return service.findAll();
     }

}

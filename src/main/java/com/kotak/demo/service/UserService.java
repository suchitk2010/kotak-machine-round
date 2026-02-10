package com.kotak.demo.service;

import com.kotak.demo.dto.CreateUserRequest;
import com.kotak.demo.dto.UserResponse;
import com.kotak.demo.entity.User;

import java.util.List;

public interface UserService {

   UserResponse create(CreateUserRequest request);
   List<UserResponse> findAll();

}

package com.kotak.demo.service.impl;

import com.kotak.demo.dto.CreateUserRequest;
import com.kotak.demo.dto.UserResponse;
import com.kotak.demo.entity.User;
import com.kotak.demo.mapper.UserMapper;
import com.kotak.demo.repository.UserRepository;
import com.kotak.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

 @Autowired
 private UserRepository repo;

 public UserResponse create(CreateUserRequest req){
   return UserMapper.toDto(repo.save(new User(null,req.getName(),req.getEmail())));
 }

 public List<UserResponse> findAll(){
   return repo.findAll().stream().map(UserMapper::toDto).toList();
 }
}

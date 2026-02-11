package com.kotak.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class HomeController {

     @PostMapping("health")
     public ResponseEntity<String> getHealth(){
       return ResponseEntity.ok("OK");
     }

}

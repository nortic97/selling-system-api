package com.nortic.posdash.es.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/es/users")
public class usersController {

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Hello World");
    }

}

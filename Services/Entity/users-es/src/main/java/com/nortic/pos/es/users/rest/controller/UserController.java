package com.nortic.pos.es.users.rest.controller;

import com.nortic.pos.es.users.rest.gen.api.UsersApi;
import com.nortic.pos.es.users.rest.gen.model.RestModelUser;
import com.nortic.pos.es.users.util.UserMapper;
import com.nortic.posdash.models.dto.UserDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController implements UsersApi {

    private final UserMapper userMapper;

    @Autowired
    UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<RestModelUser> getUserById(UUID id) {
        return new ResponseEntity<>(userMapper.toRestModel(new UserDTO()), HttpStatus.OK);
    }

    @Hidden
    @RequestMapping("**")
    public ResponseEntity<Map<String, Object>> handleUnknownRoutes() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("code", 404);
        response.put("status", "Request Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}

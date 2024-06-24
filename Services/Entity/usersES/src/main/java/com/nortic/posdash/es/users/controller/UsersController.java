package com.nortic.posdash.es.users.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nortic.posdash.es.users.service.UserService;
import com.nortic.posdash.es.users.utils.CustomException;
import com.nortic.posdash.es.users.utils.ResponseBody;
import com.nortic.posdash.models.dto.UserDTO;
import com.nortic.posdash.models.enums.StatusEnum;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/es/users")
public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("Start addUser Controller");
        try {
            UserDTO user = userService.addUser(userDTO);
            log.info("End addUser Controller");
            return ResponseEntity.ok(new ResponseBody<>(user, StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        log.info("Start getUserById Controller");
        try {
            UserDTO user = userService.getUserById(id);
            log.info("End getUserById Controller");
            return ResponseEntity.ok(new ResponseBody<>(user, StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        log.info("Start getAllUsers Controller");
        try {
            List<UserDTO> userDTO = userService.getAllUsers();
            log.info("End getAllUsers Controller");
            return ResponseEntity.ok(new ResponseBody<>(userDTO, StatusEnum.SUCCESS).getBody());
        }catch (CustomException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(e.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @GetMapping("/getAllUsers/{page}/{size}")
    public ResponseEntity<?> getAllUsers(@PathVariable int size, @PathVariable int page) {
        log.info("Start getAllUsers by page Controller");
        try {
            Page<UserDTO> userDTO = userService.getAllUsers(page,size);
            log.info("End getAllUsers by page Controller");
            return ResponseEntity.ok(new ResponseBody<>(userDTO, StatusEnum.SUCCESS).getBody());
        }catch (CustomException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(e.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @Valid @RequestBody UserDTO userDTO) {
        log.info("Start updateUser Controller");
        try {
            UserDTO updatedUser = userService.updateUser(id, userDTO);
            log.info("End updateUser Controller");
            return ResponseEntity.ok(new ResponseBody<>(updatedUser, StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        log.info("Start deleteUser Controller");
        try {
            userService.deleteById(id);
            log.info("End deleteUser Controller");
            return ResponseEntity.ok(new ResponseBody<>("Deleted", StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @Hidden
    @RequestMapping("/**")
    public ResponseEntity<?> handleInvalidRequests(HttpServletRequest request) {
        log.error("The url {} does not exist", request.getRequestURI());
        ResponseBody<Object> responseBody = new ResponseBody<>(new CustomException(request.getRequestURI()).getMessage(), StatusEnum.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody.getBody());
    }
}

package com.nortic.posdash.es.users.controller;

import com.nortic.posdash.es.users.service.UserService;
import com.nortic.posdash.es.users.utils.CustomException;
import com.nortic.posdash.es.users.utils.ResponseBody;
import com.nortic.posdash.us.models.dto.UserDTO;
import com.nortic.posdash.us.models.enums.StatusEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
        log.info("Start addUser Controller");
        try {
            if(bindingResult.hasErrors()){
                List<String> errors = new ArrayList<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.add(error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(new ResponseBody<>(errors, StatusEnum.ERROR).getBody());
            }
            UserDTO user = userService.addUser(userDTO);
            log.info("End addUser Controller");
            return ResponseEntity.ok(new ResponseBody<>(user, StatusEnum.SUCCESS).getBody());
        }catch (CustomException ce){
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id){
    log.info("Start deleteUser Controller");
    try {
        userService.deleteById(id);
        log.info("End deleteUser Controller");
        return ResponseEntity.ok(new ResponseBody<>("Deleted", StatusEnum.SUCCESS).getBody());
    }catch (CustomException ce){
        return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @RequestMapping("/**")
    public ResponseEntity<?> handleInvalidRequests(HttpServletRequest request) {
        log.error("The url {} does not exist", request.getRequestURI());
        ResponseBody<Object> responseBody = new ResponseBody<>(new CustomException(request.getRequestURI()).getMessage(), StatusEnum.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody.getBody());
    }

}

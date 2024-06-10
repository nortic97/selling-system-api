package com.nortic.posdash.es.users.controller;

import com.nortic.posdash.es.users.service.UserService;
import com.nortic.posdash.es.users.utils.CustomException;
import com.nortic.posdash.es.users.utils.ResponseBody;
import com.nortic.posdash.us.models.dto.UserDTO;
import com.nortic.posdash.us.models.enums.StatusEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/es/users")
public class usersController {

    private static final Logger log = LoggerFactory.getLogger(usersController.class);

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){
        log.info("Start addUser Controller");
        try {
            UserDTO user = userService.addUser(userDTO);
            log.info("End addUser Controller");
            return ResponseEntity.ok(new ResponseBody<>(user, StatusEnum.SUCCESS).getBody());
        }catch (Exception e){
            log.error(e.getMessage());
            ResponseBody<Object> responseBody = new ResponseBody<>(new CustomException().getMessage(), StatusEnum.ERROR);
            return ResponseEntity.internalServerError().body(responseBody.getBody());
        }
    }

    @RequestMapping("/**")
    public ResponseEntity<?> handleInvalidRequests(HttpServletRequest request) {
        log.error("The url {} does not exist", request.getRequestURI());
        ResponseBody<Object> responseBody = new ResponseBody<>(new CustomException(request.getRequestURI()).getMessage(), StatusEnum.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody.getBody());
    }

}

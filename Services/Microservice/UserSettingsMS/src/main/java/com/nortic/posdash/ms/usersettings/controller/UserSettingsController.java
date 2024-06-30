package com.nortic.posdash.ms.usersettings.controller;

import com.nortic.posdash.models.dto.MenuByRoleDTO;
import com.nortic.posdash.models.enums.StatusEnum;
import com.nortic.posdash.ms.usersettings.service.UserSettingsService;
import com.nortic.posdash.ms.usersettings.utils.CustomException;
import com.nortic.posdash.ms.usersettings.utils.ResponseBody;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ms/userSettings")
public class UserSettingsController {

    private static final Logger log = LoggerFactory.getLogger(UserSettingsController.class);
    private final UserSettingsService userSettingsService;

    @Autowired
    public UserSettingsController(UserSettingsService userSettingsService) {
        this.userSettingsService = userSettingsService;
    }

    @GetMapping("/role/{roleId}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> getMenusByRoleId(@PathVariable UUID roleId) {
        log.info("Start getMenusByRoleId Controller");
        List<MenuByRoleDTO> menus = userSettingsService.getMenusByRoleId(roleId);
        log.info("End getMenusByRoleId Controller");
        return ResponseEntity.ok().body(new ResponseBody<>(menus,StatusEnum.SUCCESS).getBody());
    }

    @Hidden
    @RequestMapping("/**")
    public ResponseEntity<?> handleInvalidRequests(HttpServletRequest request) {
        log.error("The url {} does not exist", request.getRequestURI());
        ResponseBody<Object> responseBody = new ResponseBody<>(new CustomException(request.getRequestURI()).getMessage(), StatusEnum.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody.getBody());
    }

}

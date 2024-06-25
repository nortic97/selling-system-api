package com.nortic.posdash.ms.usersettings.controller;

import com.nortic.posdash.models.dto.MenuByRoleDTO;
import com.nortic.posdash.ms.usersettings.service.UserSettingsService;
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

    private final UserSettingsService userSettingsService;

    public UserSettingsController(UserSettingsService userSettingsService) {
        this.userSettingsService = userSettingsService;
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<?> getMenusByRoleId(@PathVariable UUID roleId) {
        List<MenuByRoleDTO> menus = userSettingsService.getMenusByRoleId(roleId);
        return ResponseEntity.ok(menus);
    }

}

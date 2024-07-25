package com.nortic.posdash.es.roles.controller;

import com.nortic.posdash.es.roles.service.RolesServiceImpl;
import com.nortic.posdash.es.roles.utils.CustomException;
import com.nortic.posdash.es.roles.utils.ResponseBody;
import com.nortic.posdash.models.dto.RoleDTO;
import com.nortic.posdash.models.enums.StatusEnum;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/es/roles")
public class RolesController {

    private static final Logger log = LoggerFactory.getLogger(RolesController.class);

    private final RolesServiceImpl rolesService;
    private final RolesServiceImpl rolesServiceImpl;

    @Autowired
    public RolesController(RolesServiceImpl rolesService, RolesServiceImpl rolesServiceImpl) {
        this.rolesService = rolesService;
        this.rolesServiceImpl = rolesServiceImpl;
    }

    @PostMapping("/addRole")
    public ResponseEntity<?> addRole(@Valid @RequestBody RoleDTO role) {
        log.info("Adding role: {}", role.getName());
        try {
            RoleDTO roleDTO = rolesService.addRole(role);
            return ResponseEntity.ok(new ResponseBody<>(roleDTO, StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @GetMapping("/getRoleById/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable UUID id) throws CustomException {
        log.info("Getting role by id: {}", id);
        try {
            RoleDTO roleDTO = rolesService.getRoleById(id);
            log.info("End getting role by id: {}", id);
            return ResponseEntity.ok(new ResponseBody<>(roleDTO, StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<?> getAllRoles() throws CustomException {
        log.info("Start getting all roles");
        try {
            List<RoleDTO> roleDTOS = rolesService.getAllRoles();
            log.info("End getting all roles");
            return ResponseEntity.ok(new ResponseBody<>(roleDTOS, StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @GetMapping("/getAllRoles/{page}/{size}")
    public ResponseEntity<?> getAllRoles(@PathVariable int page, @PathVariable int size) throws CustomException {
        log.info("Start getting all roles by page: {} and size {}", page, size);
        try {
            Page<RoleDTO> roleDTOPage = rolesService.getAllRoles(page, size);
            log.info("End getting all roles by page: {} and size {}", page, size);
            return ResponseEntity.ok(new ResponseBody<>(roleDTOPage, StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @GetMapping("/getAllDeletedRoles/{page}/{size}")
    public ResponseEntity<?> getAllDeletedRoles(@PathVariable int page, @PathVariable int size) throws CustomException {
        log.info("Start getting all deleted roles");
        try {
            Page<RoleDTO> roleDTOS = rolesService.getAllDeletedRoles(page, size);
            log.info("End getting all deleted roles");
            return ResponseEntity.ok(new ResponseBody<>(roleDTOS, StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @PutMapping("/updateRole/{id}")
    public ResponseEntity<?> updateRole(@PathVariable UUID id, @Valid @RequestBody RoleDTO roleDTO) throws CustomException {
        log.info("Updating role: {}", roleDTO.getName());
        try {
            RoleDTO role = rolesService.updateRole(id, roleDTO);
            log.info("End updating role: {}", role.getName());
            return ResponseEntity.ok(new ResponseBody<>(role, StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @DeleteMapping("/deleteRoleById/{id}")
    public ResponseEntity<?> deleteRoleById(@PathVariable UUID id) throws CustomException {
        log.info("Mark as deleted role: {}", id);
        try {
            rolesService.deleteRoleById(id);
            log.info("End mark as deleted role: {}", id);
            return ResponseEntity.ok(new ResponseBody<>("Mark as Deleted", StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @DeleteMapping("/permanentlyDeleteRoleById/{id}")
    public ResponseEntity<?> permanentlyDeleteRoleById(@PathVariable UUID id) throws CustomException {
        log.info("Deleting role: {}", id);
        try {
            rolesService.permanentlyDeleteRoleById(id);
            log.info("End deleting role: {}", id);
            return ResponseEntity.ok(new ResponseBody<>("Deleted Role", StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @PutMapping("updateRoleMenus/{roleId}")
    public ResponseEntity<?> updateRoleMenus(@PathVariable UUID roleId, @RequestBody List<UUID> menuIds) {
        log.info("Start updateRoleMenus Controller");
        try {
            RoleDTO updatedRole = rolesServiceImpl.updateRoleMenus(roleId, menuIds);
            log.info("End updateRoleMenus Controller");
            return ResponseEntity.ok(new ResponseBody<>(updatedRole, StatusEnum.SUCCESS).getBody());
        } catch (CustomException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(e.getMessage(), StatusEnum.ERROR).getBody());
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

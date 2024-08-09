package com.nortic.posdash.es.menus.controller;

import com.nortic.posdash.es.menus.service.MenusSvcImpl;
import com.nortic.posdash.es.menus.utils.CustomException;
import com.nortic.posdash.es.menus.utils.ResponseBody;
import com.nortic.posdash.models.dto.MenuDTO;
import com.nortic.posdash.models.enums.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/v1/es/menus")
public class MenusController {

    private static final Logger log = LoggerFactory.getLogger(MenusController.class);

    private final MenusSvcImpl menusSvcImpl;

    @Autowired
    public MenusController(MenusSvcImpl menusSvcImpl) {
        this.menusSvcImpl = menusSvcImpl;
    }

    @GetMapping("/menusWithSubmenus")
    public ResponseEntity<?> menusWithSubmenus(){
        log.info("Start menusWithSubmenus Controller");
        try {
            List<MenuDTO> roleDTO = menusSvcImpl.menusWithSubmenus();
            log.info("End menusWithSubmenus Controller");
            return ResponseEntity.ok(new ResponseBody<>(roleDTO, StatusEnum.SUCCESS).getBody());
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

}

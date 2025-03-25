package com.nortic.posdash.models.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("role_navigation_menus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleNavigationMenuEntity {

    @Id
    @Field("_id")
    private String roleId;

    private List<NavigationMenu> navigationMenus;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
   public static class NavigationMenu {
       private String name;
       private Integer position;
       private Boolean hasSubmenu;
       private String url;
       private String icon;
       private String description;
       private Boolean status;
       private List<SubMenu> submenus;
   }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubMenu {
        private String name;
        private Integer position;
        private String description;
        private String url;
        private Boolean status;
    }
}


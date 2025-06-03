package com.nortic.pos.models.document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("role_navigation_menus")
@TypeAlias("RoleNavigationMenuDocument")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleNavigationMenuDocument {
    @Id
    private String id;
    private List<NavigationMenuDocument> navigationMenus;
}

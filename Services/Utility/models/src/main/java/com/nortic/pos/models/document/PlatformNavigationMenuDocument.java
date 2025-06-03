package com.nortic.pos.models.document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("platform_navigation_menus")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformNavigationMenuDocument {
    @Id
    private String id;
    private List<NavigationMenuDocument> navigationMenus;
}

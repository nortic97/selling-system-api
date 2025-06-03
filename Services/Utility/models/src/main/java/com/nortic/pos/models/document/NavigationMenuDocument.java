package com.nortic.pos.models.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NavigationMenuDocument {
    private String id;
    private String name;
    private int position;
    private String description;
    private String url;
    private String icon;
    private boolean hasSubmenu;
    private boolean isActive;
    private List<SubmenuDocument> submenu;
}

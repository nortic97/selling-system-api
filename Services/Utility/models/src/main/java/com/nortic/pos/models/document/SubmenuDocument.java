package com.nortic.pos.models.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmenuDocument {
    private String id;
    private String name;
    private int position;
    private String description;
    private String url;
    private boolean isActive;
}

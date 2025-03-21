package com.nortic.posdash.models.dto;
import lombok.Data;

import java.util.UUID;

@Data
public class SubMenuDTO {
    private UUID id;
    private String name;
    private Integer position;
    private String description;
    private String url;
    private Boolean status;
}

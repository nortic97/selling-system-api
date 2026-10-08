package com.nortic.pos.es.permission.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequestDTO {

    private String code;

    private String module;

    private String name;

    private String description;

}

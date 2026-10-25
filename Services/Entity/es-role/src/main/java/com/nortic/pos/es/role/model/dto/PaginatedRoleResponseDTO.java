package com.nortic.pos.es.role.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedRoleResponseDTO {

    private List<RoleDTO> content;

    private Long totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer number;

    private Boolean first;

    private Boolean last;

}

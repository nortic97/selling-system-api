package com.nortic.pos.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePaginationDTO {
    private List<RoleDTO> data;
    private int page;
    private int size;
    private int totalItems;
    private int totalPages;
}

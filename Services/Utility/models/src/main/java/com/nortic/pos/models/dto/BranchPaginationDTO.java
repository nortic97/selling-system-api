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
public class BranchPaginationDTO {
    private Integer totalItems;
    private Integer totalPages;
    private Integer page;
    private Integer limit;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private List<BranchDTO> branchesList;
}

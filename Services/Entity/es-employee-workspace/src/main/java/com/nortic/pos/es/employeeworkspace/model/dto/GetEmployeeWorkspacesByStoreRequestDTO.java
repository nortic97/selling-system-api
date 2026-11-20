package com.nortic.pos.es.employeeworkspace.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetEmployeeWorkspacesByStoreRequestDTO {

    private UUID storeId;
    private Integer page;
    private Integer size;
    private List<String> sort;
}

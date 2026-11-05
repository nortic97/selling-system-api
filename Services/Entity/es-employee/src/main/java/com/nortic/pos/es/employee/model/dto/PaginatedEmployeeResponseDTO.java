package com.nortic.pos.es.employee.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedEmployeeResponseDTO {

    private List<EmployeeDTO> content;

    private Long totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer number;

    private Boolean first;

    private Boolean last;

}

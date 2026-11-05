package com.nortic.pos.es.employee.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeQueryRequestDTO {

    private String query;

    @Builder.Default
    private Integer page = 0;

    @Builder.Default
    private Integer size = 10;

    @Builder.Default
    private List<String> sort = new ArrayList<>();

}

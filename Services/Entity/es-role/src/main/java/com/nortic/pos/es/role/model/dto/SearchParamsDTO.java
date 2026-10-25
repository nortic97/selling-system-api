package com.nortic.pos.es.role.model.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchParamsDTO {
    private String value;
    private Pageable pageable;
}

package com.nortic.pos.es.branch.model.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchParamsDTO {
    private String value;
    private Pageable pageable;
}

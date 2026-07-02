package com.nortic.pos.es.user.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

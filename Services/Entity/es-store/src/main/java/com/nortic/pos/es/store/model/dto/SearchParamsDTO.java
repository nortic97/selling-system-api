package com.nortic.pos.es.store.model.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchParamsDTO {
    private String value;
    private UUID ownerId;
    private Pageable pageable;
}

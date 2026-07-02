package com.nortic.pos.es.user.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParamsDTO {
    private UUID id;
    private UUID correlationId;
    private String consumerId;
    private Pageable pageable;
    private List<String> sort;
}

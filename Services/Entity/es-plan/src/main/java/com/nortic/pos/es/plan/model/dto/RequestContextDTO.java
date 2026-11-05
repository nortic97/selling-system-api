package com.nortic.pos.es.plan.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestContextDTO {

    private UUID correlationId;

    private String consumerId;

}

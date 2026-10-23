package com.nortic.pos.es.branch.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StatusRequest {

    private Boolean active;
}

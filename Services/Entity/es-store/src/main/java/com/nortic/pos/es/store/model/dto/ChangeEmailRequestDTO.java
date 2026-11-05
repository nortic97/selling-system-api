package com.nortic.pos.es.store.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChangeEmailRequestDTO {

    private String contactEmail;
}

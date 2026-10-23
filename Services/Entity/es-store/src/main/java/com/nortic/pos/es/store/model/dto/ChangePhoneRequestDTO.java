package com.nortic.pos.es.store.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChangePhoneRequestDTO {

    private String phoneNumber;
}

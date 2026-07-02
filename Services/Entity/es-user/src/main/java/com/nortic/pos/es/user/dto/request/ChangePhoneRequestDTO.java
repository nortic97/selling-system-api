package com.nortic.pos.es.user.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChangePhoneRequestDTO {

    private String phone;

}

package com.nortic.pos.es.employee.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateRequestDTO {

    private String username;

    private String password;

}

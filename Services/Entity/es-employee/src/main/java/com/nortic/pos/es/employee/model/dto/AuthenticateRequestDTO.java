package com.nortic.pos.es.employee.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateRequestDTO {

    private UUID storeId;

    private String username;

    private String password;

}

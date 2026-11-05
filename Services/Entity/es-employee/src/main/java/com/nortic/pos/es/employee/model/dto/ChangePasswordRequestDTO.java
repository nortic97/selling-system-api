package com.nortic.pos.es.employee.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequestDTO {

    private String currentPassword;

    private String newPassword;

}

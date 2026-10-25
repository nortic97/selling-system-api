package com.nortic.pos.es.role.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeStatusRequestDTO {

    private Boolean active;

}

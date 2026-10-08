package com.nortic.pos.es.permission.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorDetailResponseDTO {

    private String field;
    private String message;
    private Object rejectedValue;

}

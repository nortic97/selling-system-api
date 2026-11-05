package com.nortic.pos.es.branch.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorDetailDTO {

    private String field;

    private String message;

    private String rejectedValue;

}

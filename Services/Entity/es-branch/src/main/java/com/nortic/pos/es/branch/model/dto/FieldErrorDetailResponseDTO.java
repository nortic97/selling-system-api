package com.nortic.pos.es.branch.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldErrorDetailResponseDTO {

    private String field;

    private String rejectedValue;

    private String message;

}

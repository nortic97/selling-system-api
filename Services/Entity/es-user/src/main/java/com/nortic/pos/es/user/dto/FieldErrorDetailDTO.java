package com.nortic.pos.es.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldErrorDetailDTO {

    private String field;

    private String rejectedValue;

    private String message;

}

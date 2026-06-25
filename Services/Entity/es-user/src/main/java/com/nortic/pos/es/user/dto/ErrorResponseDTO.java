package com.nortic.pos.es.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO {

    private String code;

    private String message;

    private OffsetDateTime timestamp;

    private List<FieldErrorDetailDTO> details;

}

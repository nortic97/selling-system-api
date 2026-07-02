package com.nortic.pos.es.user.dto;

import com.nortic.pos.es.user.rest.gen.model.RestModelUserResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedUserDTO {

    private List<RestModelUserResponseDTO> content;

    private Long totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer number;

    private Boolean first;

    private Boolean last;

}

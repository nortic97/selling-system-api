package com.nortic.pos.es.user.dto.response;

import com.nortic.pos.es.user.dto.UserDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedUserResponseDTO {

    private List<UserDTO> content;

    private Long totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer number;

    private Boolean first;

    private Boolean last;

}

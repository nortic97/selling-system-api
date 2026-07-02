package com.nortic.pos.es.user.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryRequestDTO {

    private String query;

    private Integer page = 0;

    private Integer size = 10;

    private List<String> sort = new ArrayList<>();

}

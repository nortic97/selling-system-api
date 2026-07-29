package com.nortic.pos.es.store.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChangeEmailRequestDTO {

    private String email;

}

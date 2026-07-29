package com.nortic.pos.es.store.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChangeStatusRequestDTO {

    private Boolean active;

}

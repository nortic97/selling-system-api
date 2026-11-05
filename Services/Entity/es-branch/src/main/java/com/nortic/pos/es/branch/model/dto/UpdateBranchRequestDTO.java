package com.nortic.pos.es.branch.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBranchRequestDTO {

    private String name;
    private String address;
    private String phoneNumber;
    private String email;
}

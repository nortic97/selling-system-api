package com.nortic.pos.es.branch.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

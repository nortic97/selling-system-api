package com.nortic.pos.es.user.model.dto;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUsernameRequestDTO {

    private String username;

}

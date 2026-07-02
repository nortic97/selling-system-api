package com.nortic.pos.es.user.dto.request;

import com.nortic.pos.es.user.dto.enums.DocumentTypeEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchRequestDTO {

    private  String firstName;

    private  String lastName;

    private DocumentTypeEnum documentType;

    private  String documentNumber;

    private  String username;

    private  String email;

    private  String phone;

    private  String address;

    private LocalDate dateOfBirth;

    private  String photo;

    private  Boolean isActive;

    private Integer page = 0;

    private Integer size = 10;

    private List<String> sort = new ArrayList<>();
    
}

package com.nortic.pos.es.user.dto.request;

import com.nortic.pos.es.user.dto.enums.DocumentTypeEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private  Boolean isActive;

    @Builder.Default
    private Integer page = 0;

    @Builder.Default
    private Integer size = 10;

    @Builder.Default
    private List<String> sort = new ArrayList<>();
    
}

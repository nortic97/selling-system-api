package com.nortic.pos.es.employee.model.dto;

import com.nortic.pos.es.employee.model.enums.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSearchRequestDTO {

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

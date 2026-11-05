package com.nortic.pos.es.employee.mapper;

import com.google.protobuf.Timestamp;
import com.nortic.pos.es.employee.grpc.EmployeeProto;
import com.nortic.pos.es.employee.model.dto.AuthenticateRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangeEmailRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangeEmployeeStatusRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangePasswordRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangePhoneRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangeusernameRequestDTO;
import com.nortic.pos.es.employee.model.dto.EmployeeDTO;
import com.nortic.pos.es.employee.model.dto.EmployeeQueryRequestDTO;
import com.nortic.pos.es.employee.model.dto.EmployeeSearchRequestDTO;
import com.nortic.pos.es.employee.model.dto.PaginatedEmployeeResponseDTO;
import com.nortic.pos.es.employee.model.enums.DocumentTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.OffsetDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public abstract class EmployeeGrpcMapper {



    public EmployeeProto.PaginatedEmployeeResponse toPaginatedEmployeeResponse(PaginatedEmployeeResponseDTO dto) {

        var builder = EmployeeProto.PaginatedEmployeeResponse.newBuilder();

        builder.setNumber(dto.getNumber());
        builder.setSize(dto.getSize());
        builder.setTotalElements(dto.getTotalElements());
        builder.setTotalPages(dto.getTotalPages());
        builder.setLast(dto.getLast());
        builder.setSize(dto.getSize());

        if (dto.getContent() != null) {
            builder.addAllContent(dto.getContent().stream().map(this::toEmployeeResponse).toList());
        }

        return builder.build();
    }

    @Mapping(target = "usernameBytes", ignore = true)
    @Mapping(target = "unknownFields", ignore = true)
    @Mapping(target = "photoUrlBytes", ignore = true)
    @Mapping(target = "phoneBytes", ignore = true)
    @Mapping(target = "mergeUpdatedAt", ignore = true)
    @Mapping(target = "mergeUnknownFields", ignore = true)
    @Mapping(target = "mergeFrom", ignore = true)
    @Mapping(target = "mergeDeletedAt", ignore = true)
    @Mapping(target = "mergeCreatedAt", ignore = true)
    @Mapping(target = "lastNameBytes", ignore = true)
    @Mapping(target = "idBytes", ignore = true)
    @Mapping(target = "firstNameBytes", ignore = true)
    @Mapping(target = "emailBytes", ignore = true)
    @Mapping(target = "documentTypeBytes", ignore = true)
    @Mapping(target = "documentNumberBytes", ignore = true)
    @Mapping(target = "dateOfBirthBytes", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    @Mapping(target = "addressBytes", ignore = true)
    @Mapping(target = "createdAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "updatedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "deletedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    public abstract EmployeeProto.EmployeeResponse toEmployeeResponse(EmployeeDTO dto);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(
            target = "documentType",
            source = "documentType",
            qualifiedByName = "mapDocumentType"
    )
    public abstract EmployeeDTO toEmployeeDTO(EmployeeProto.CreateEmployeeRequest employee);

    @Named("offsetDateTimeToTimestamp")
    protected Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Timestamp.newBuilder().setSeconds(offsetDateTime.toEpochSecond()).setNanos(offsetDateTime.getNano()).build();
    }

    @Named("mapDocumentType")
    protected DocumentTypeEnum mapDocumentType(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return DocumentTypeEnum.valueOf(value.toUpperCase());
    }

    public abstract ChangeEmailRequestDTO toChangeEmailRequestDTO(EmployeeProto.ChangeEmailRequest request);

    public abstract ChangePasswordRequestDTO toChangePasswordRequestDTO(EmployeeProto.ChangeEmployeePasswordRequest request);

    public abstract ChangePhoneRequestDTO toChangePhoneRequestDTO(EmployeeProto.ChangePhoneRequest request);

    public abstract ChangeEmployeeStatusRequestDTO toChangeEmployeeStatusRequestDTO(EmployeeProto.ChangeEmployeeStatusRequest request);

    public abstract ChangeUsernameRequestDTO toChangeUsernameRequestDTO(EmployeeProto.ChangeUsernameRequest request);

    public abstract AuthenticateRequestDTO toAuthenticateRequestDTO(EmployeeProto.AuthenticateRequest request);

    @Mapping(target = "sort", source = "sortList")
    public abstract EmployeeQueryRequestDTO toEmployeeQueryRequestDTO(EmployeeProto.EmployeeQueryRequest request);

    @Mapping(target = "sort", source = "sortList")
    public abstract EmployeeSearchRequestDTO toEmployeeSearchRequestDTO(EmployeeProto.SearchEmployeesRequest request);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(
            target = "documentType",
            source = "documentType",
            qualifiedByName = "mapDocumentType"
    )
    public abstract EmployeeDTO toEmployeeDTO(EmployeeProto.UpdateEmployeeRequest employee);
}
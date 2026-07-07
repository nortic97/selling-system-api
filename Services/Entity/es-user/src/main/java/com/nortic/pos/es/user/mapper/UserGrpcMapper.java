package com.nortic.pos.es.user.mapper;

import com.google.protobuf.Timestamp;
import com.nortic.pos.es.user.dto.UserDTO;
import com.nortic.pos.es.user.dto.enums.DocumentTypeEnum;
import com.nortic.pos.es.user.dto.request.ChangeEmailRequestDTO;
import com.nortic.pos.es.user.dto.request.ChangePasswordRequestDTO;
import com.nortic.pos.es.user.dto.request.ChangePhoneRequestDTO;
import com.nortic.pos.es.user.dto.request.ChangeUserStatusRequestDTO;
import com.nortic.pos.es.user.dto.request.ChangeUsernameRequestDTO;
import com.nortic.pos.es.user.dto.request.UserQueryRequestDTO;
import com.nortic.pos.es.user.dto.request.UserSearchRequestDTO;
import com.nortic.pos.es.user.dto.response.PaginatedUserResponseDTO;
import com.nortic.pos.es.user.grpc.UserProto;
import org.mapstruct.*;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public abstract class UserGrpcMapper {



    public UserProto.PaginatedUserResponse toPaginatedUserResponse(PaginatedUserResponseDTO dto) {

        var builder = UserProto.PaginatedUserResponse.newBuilder();

        builder.setNumber(dto.getNumber());
        builder.setSize(dto.getSize());
        builder.setTotalElements(dto.getTotalElements());
        builder.setTotalPages(dto.getTotalPages());
        builder.setLast(dto.getLast());
        builder.setSize(dto.getSize());

        if (dto.getContent() != null) {
            builder.addAllContent(dto.getContent().stream().map(this::toUserResponse).toList());
        }

        return builder.build();
    }

    @Mapping(target = "usernameBytes", ignore = true)
    @Mapping(target = "unknownFields", ignore = true)
    @Mapping(target = "photoBytes", ignore = true)
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
    public abstract UserProto.UserResponse toUserResponse(UserDTO dto);

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
    public abstract UserDTO toUserDTO(UserProto.CreateUserRequest user);

    @Named("offsetDateTimeToTimestamp")
    protected Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Timestamp.newBuilder().setSeconds(offsetDateTime.toEpochSecond()).setNanos(offsetDateTime.getNano()).build();
    }

    @Named("timestampToOffsetDateTime")
    protected OffsetDateTime timestampToOffsetDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return OffsetDateTime.ofInstant(Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos()), ZoneOffset.UTC);
    }

    @Named("mapDocumentType")
    protected DocumentTypeEnum mapDocumentType(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return DocumentTypeEnum.valueOf(value.toUpperCase());
    }

    public abstract ChangeEmailRequestDTO toChangeEmailRequestDTO(UserProto.ChangeEmailRequest request);

    public abstract ChangePasswordRequestDTO toChangePasswordRequestDTO(UserProto.ChangeUserPasswordRequest request);

    public abstract ChangePhoneRequestDTO toChangePhoneRequestDTO(UserProto.ChangePhoneRequest request);

    public abstract ChangeUserStatusRequestDTO toChangeUserStatusRequestDTO(UserProto.ChangeUserStatusRequest request);

    public abstract ChangeUsernameRequestDTO toChangeUsernameRequestDTO(UserProto.ChangeUsernameRequest request);

    @Mapping(target = "sort", source = "sortList")
    public abstract UserQueryRequestDTO toUserQueryRequestDTO(UserProto.UserQueryRequest request);

    @Mapping(target = "sort", source = "sortList")
    public abstract UserSearchRequestDTO toUserSearchRequestDTO(UserProto.SearchUsersRequest request);

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
    public abstract UserDTO toUserDTO(UserProto.UpdateUserRequest user);
}
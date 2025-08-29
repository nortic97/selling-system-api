package com.nortic.pos.es.user.util;

import com.nortic.pos.es.user.grpc.gen.*;
import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.enums.AccountTypeEnum;
import com.nortic.pos.models.enums.DocumentTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface GrpcMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "stringToUUID")
    @Mapping(target = "storeId", source = "storeId", qualifiedByName = "stringToUUID")
    @Mapping(target = "branchId", source = "branchId", qualifiedByName = "emptyStringToNullUuid")
    @Mapping(target = "roleId", source = "roleId", qualifiedByName = "stringToUUID")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phone", source = "phone", qualifiedByName = "emptyToNull")
    @Mapping(target = "address", source = "address", qualifiedByName = "emptyToNull")
    @Mapping(target = "photo", source = "photo", qualifiedByName = "emptyToNull")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "protoDocumentTypeToEnum")
    @Mapping(target = "accountType", source = "accountType", qualifiedByName = "protoAccountTypeToEnum")
    @Mapping(target = "birthDate", source = "birthDate", qualifiedByName = "stringToLocalDate")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping(target = "deletedAt", source = "deletedAt", qualifiedByName = "stringToLocalDateTime")
    UserDTO protoUserToDTO(User protoUser);

    @Mapping(target = "id", source = "id", qualifiedByName = "uuidToString")
    @Mapping(target = "storeId", source = "storeId", qualifiedByName = "uuidToString")
    @Mapping(target = "branchId", source = "branchId", qualifiedByName = "nullUuidToEmpty")
    @Mapping(target = "roleId", source = "roleId", qualifiedByName = "uuidToString")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phone", source = "phone", qualifiedByName = "nullToEmpty")
    @Mapping(target = "address", source = "address", qualifiedByName = "nullToEmpty")
    @Mapping(target = "photo", source = "photo", qualifiedByName = "nullToEmpty")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "enumToProtoDocumentType")
    @Mapping(target = "accountType", source = "accountType", qualifiedByName = "enumToProtoAccountType")
    @Mapping(target = "birthDate", source = "birthDate", qualifiedByName = "localDateToString")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "localDateTimeToString")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "localDateTimeToString")
    @Mapping(target = "deletedAt", source = "deletedAt", qualifiedByName = "localDateTimeToString")
    User dtoToProtoUser(UserDTO userDTO);

    @Named("getUsersRequestToPageable")
    default Pageable getUsersRequestToPageable(GetUsersRequest request) {

        if (request.hasPage() && request.getPage() < 1) {
            throw new CustomException(3, "El número de página debe ser mayor o igual a 1");
        }

        int page = request.hasPage() ? request.getPage() - 1 : 0;
        int size = request.hasSize() ? request.getSize() : 20;

        Sort sort = Sort.unsorted();
        if (request.hasSort() && !request.getSort().isEmpty()) {
            String[] sortParts = request.getSort().split(",");
            if (sortParts.length == 2) {
                String property = sortParts[0];
                String direction = sortParts[1];
                sort = "desc".equalsIgnoreCase(direction) ?
                        Sort.by(Sort.Direction.DESC, property) :
                        Sort.by(Sort.Direction.ASC, property);
            }
        }

        return PageRequest.of(page, size, sort);
    }

    default UserListResponse pageToUserListResponse(Page<UserDTO> page) {

        UserListResponse.Builder builder = UserListResponse.newBuilder()
                .setTotalElements((int) page.getTotalElements())
                .setTotalPages(page.getTotalPages())
                .setCurrentPage(page.getNumber())
                .setPageSize(page.getSize())
                .setHasNext(page.hasNext())
                .setHasPrevious(page.hasPrevious());

        for (UserDTO userDTO : page.getContent()) {
            builder.addUsers(dtoToProtoUser(userDTO));
        }

        return builder.build();
    }

    @Named("stringToUUID")
    default UUID stringToUUID(String value) {
        if (value == null || value.isEmpty()) return null;
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : "";
    }

    @Named("protoDocumentTypeToEnum")
    default DocumentTypeEnum protoDocumentTypeToEnum(DocumentType protoType) {
        if (protoType == null) return null;
        return switch (protoType) {
            case CC -> DocumentTypeEnum.CC;
            case TI -> DocumentTypeEnum.TI;
            case PP -> DocumentTypeEnum.PP;
            case CE -> DocumentTypeEnum.CE;
            default -> null;
        };
    }

    @Named("enumToProtoDocumentType")
    default DocumentType enumToProtoDocumentType(DocumentTypeEnum enumType) {
        if (enumType == null) return DocumentType.CC;
        return switch (enumType) {
            case CC -> DocumentType.CC;
            case TI -> DocumentType.TI;
            case PP -> DocumentType.PP;
            case CE -> DocumentType.CE;
        };
    }

    @Named("protoAccountTypeToEnum")
    default AccountTypeEnum protoAccountTypeToEnum(AccountType protoType) {
        if (protoType == null) return null;
        return switch (protoType) {
            case INTERNAL -> AccountTypeEnum.INTERNAL;
            case STORE_OWNER -> AccountTypeEnum.STORE_OWNER;
            case EMPLOYEE -> AccountTypeEnum.EMPLOYEE;
            default -> null;
        };
    }

    @Named("enumToProtoAccountType")
    default AccountType enumToProtoAccountType(AccountTypeEnum enumType) {
        if (enumType == null) return AccountType.EMPLOYEE;
        return switch (enumType) {
            case INTERNAL -> AccountType.INTERNAL;
            case STORE_OWNER -> AccountType.STORE_OWNER;
            case EMPLOYEE -> AccountType.EMPLOYEE;
        };
    }

    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) return null;
        try {
            return LocalDate.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    @Named("localDateToString")
    default String localDateToString(LocalDate date) {
        return date != null ? date.toString() : "";
    }

    @Named("stringToLocalDateTime")
    default LocalDateTime stringToLocalDateTime(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.isEmpty()) return null;
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception e) {
            return null;
        }
    }

    @Named("localDateTimeToString")
    default String localDateTimeToString(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "";
    }

    @Named("nullToEmpty")
    default String nullToEmpty(String value) {
        return value != null ? value : "";
    }

    @Named("emptyToNull")
    default String emptyToNull(String value) {
        return (value == null || value.isEmpty()) ? null : value;
    }

    @Named("nullUuidToEmpty")
    default String nullUuidToEmpty(UUID uuid) {
        return uuid != null ? uuid.toString() : "";
    }

    @Named("emptyStringToNullUuid")
    default UUID emptyStringToNullUuid(String value) {
        if (value == null || value.isEmpty()) return null;
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Named("nullBooleanToFalse")
    default boolean nullBooleanToFalse(Boolean value) {
        return value != null ? value : false;
    }

    @Named("booleanToNullable")
    default Boolean booleanToNullable(boolean value) {
        return value;
    }
}

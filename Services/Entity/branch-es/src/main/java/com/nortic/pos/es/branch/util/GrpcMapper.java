package com.nortic.pos.es.branch.util;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GrpcMapper {
//    DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
//
//    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
//    @Mapping(source = "logo", target = "logo", qualifiedByName = "nullToEmpty")
//    @Mapping(source = "slogan", target = "slogan", qualifiedByName = "nullToEmpty")
//    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateTimeToString")
//    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "localDateTimeToString")
//    @Mapping(source = "deletedAt", target = "deletedAt", qualifiedByName = "localDateTimeToString")
//    Store dtoToGrpcModel(StoreDTO storeDTO);
//
//    @Mapping(source = "id", target = "id", qualifiedByName = "stringToUuid")
//    @Mapping(source = "taxId", target = "taxId", qualifiedByName = "emptyStringToNull")
//    @Mapping(source = "name", target = "name", qualifiedByName = "emptyStringToNull")
//    @Mapping(source = "logo", target = "logo", qualifiedByName = "emptyStringToNull")
//    @Mapping(source = "description", target = "description", qualifiedByName = "emptyStringToNull")
//    @Mapping(source = "slogan", target = "slogan", qualifiedByName = "emptyStringToNull")
//    @Mapping(source = "address", target = "address", qualifiedByName = "emptyStringToNull")
//    @Mapping(source = "stateProvince", target = "stateProvince", qualifiedByName = "emptyStringToNull")
//    @Mapping(source = "city", target = "city", qualifiedByName = "emptyStringToNull")
//    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedByName = "emptyStringToNull")
//    @Mapping(source = "email", target = "email", qualifiedByName = "emptyStringToNull")
//    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "stringToLocalDateTime")
//    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "stringToLocalDateTime")
//    @Mapping(source = "deletedAt", target = "deletedAt", qualifiedByName = "stringToLocalDateTime")
//    StoreDTO grpcModelToDto(Store store);
//
//    @Named("uuidToString")
//    default String uuidToString(UUID uuid) {
//        return uuid != null ? uuid.toString() : "";
//    }
//
//    @Named("emptyStringToNull")
//    default String emptyStringToNull(String value) {
//        return (value == null || value.trim().isEmpty()) ? null : value;
//    }
//
//    @Named("nullToEmpty")
//    default String nullToEmpty(String string) {
//        return string != null ? string : "";
//    }
//
//    @Named("stringToUuid")
//    default UUID stringToUuid(String id) {
//        return id != null && !id.isEmpty() ? UUID.fromString(id) : null;
//    }
//
//    @Named("localDateTimeToString")
//    default String localDateTimeToString(LocalDateTime dateTime) {
//        return dateTime != null ? dateTime.format(FORMATTER) : "";
//    }
//
//    @Named("stringToLocalDateTime")
//    default LocalDateTime stringToLocalDateTime(String dateTime) {
//        return dateTime != null && !dateTime.isEmpty() ? LocalDateTime.parse(dateTime, FORMATTER) : null;
//    }
}

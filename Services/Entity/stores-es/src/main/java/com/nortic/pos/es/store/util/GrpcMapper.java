package com.nortic.pos.es.store.util;

import com.nortic.pos.es.store.grpc.gen.Store;
import com.nortic.pos.models.dto.StoreDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface GrpcMapper {
    DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
    @Mapping(source = "logo", target = "logo", qualifiedByName = "defaultString")
    @Mapping(source = "slogan", target = "slogan", qualifiedByName = "defaultString")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateTimeToString")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "localDateTimeToString")
    @Mapping(source = "deletedAt", target = "deletedAt", qualifiedByName = "localDateTimeToString")
    Store dtoToGrpcModel(StoreDTO storeDTO);

    @Mapping(source = "id", target = "id", qualifiedByName = "stringToUuid")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "stringToLocalDateTime")
    @Mapping(source = "deletedAt", target = "deletedAt", qualifiedByName = "stringToLocalDateTime")
    StoreDTO grpcModelToDto(Store store);

    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : "";
    }

    @Named("defaultString")
    default String defaultString(String string) {
        return string != null ? string : "";
    }

    @Named("stringToUuid")
    default UUID stringToUuid(String id) {
        return id != null && !id.isEmpty() ? UUID.fromString(id) : null;
    }

    @Named("localDateTimeToString")
    default String localDateTimeToString(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(FORMATTER) : "";
    }

    @Named("stringToLocalDateTime")
    default LocalDateTime stringToLocalDateTime(String dateTime) {
        return dateTime != null && !dateTime.isEmpty() ? LocalDateTime.parse(dateTime, FORMATTER) : null;
    }
}

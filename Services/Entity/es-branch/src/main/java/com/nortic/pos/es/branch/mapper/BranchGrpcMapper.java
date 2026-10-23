package com.nortic.pos.es.branch.mapper;

import com.google.protobuf.Timestamp;
import com.nortic.pos.es.branch.grpc.BranchProto;
import com.nortic.pos.es.branch.model.dto.BranchDTO;
import com.nortic.pos.es.branch.model.dto.PaginatedBranchResponseDTO;
import org.mapstruct.*;

import java.time.OffsetDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public abstract class BranchGrpcMapper {

    public BranchProto.PaginatedBranchResponse toPaginatedBranchResponse(PaginatedBranchResponseDTO dto) {

        var builder = BranchProto.PaginatedBranchResponse.newBuilder();

        builder.setNumber(dto.getNumber());
        builder.setSize(dto.getSize());
        builder.setTotalElements(dto.getTotalElements());
        builder.setTotalPages(dto.getTotalPages());
        builder.setLast(dto.getLast());
        builder.setFirst(dto.getFirst());

        if (dto.getContent() != null) {
            builder.addAllContent(dto.getContent().stream().map(this::toBranchResponse).toList());
        }

        return builder.build();
    }

    @Mapping(target = "unknownFields", ignore = true)
    @Mapping(target = "mergeUpdatedAt", ignore = true)
    @Mapping(target = "mergeUnknownFields", ignore = true)
    @Mapping(target = "mergeFrom", ignore = true)
    @Mapping(target = "mergeDeletedAt", ignore = true)
    @Mapping(target = "mergeCreatedAt", ignore = true)
    @Mapping(target = "idBytes", ignore = true)
    @Mapping(target = "storeIdBytes", ignore = true)
    @Mapping(target = "nameBytes", ignore = true)
    @Mapping(target = "addressBytes", ignore = true)
    @Mapping(target = "phoneNumberBytes", ignore = true)
    @Mapping(target = "emailBytes", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    @Mapping(target = "createdAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "updatedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "deletedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "storeId", source = "storeId", qualifiedByName = "uuidToString")
    @Mapping(target = "id", source = "id", qualifiedByName = "uuidToString")
    public abstract BranchProto.BranchResponse toBranchResponse(BranchDTO dto);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "storeId", source = "storeId", qualifiedByName = "stringToUuid")
    public abstract BranchDTO toBranchDTO(BranchProto.BranchRequest request);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "storeId", ignore = true)
    public abstract BranchDTO toBranchDTO(BranchProto.UpdateBranchRequest request);

    @Named("offsetDateTimeToTimestamp")
    protected Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Timestamp.newBuilder().setSeconds(offsetDateTime.toEpochSecond()).setNanos(offsetDateTime.getNano()).build();
    }

    @Named("uuidToString")
    protected String uuidToString(java.util.UUID uuid) {
        if (uuid == null) {
            return null;
        }
        return uuid.toString();
    }

    @Named("stringToUuid")
    protected java.util.UUID stringToUuid(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        return java.util.UUID.fromString(string);
    }
}

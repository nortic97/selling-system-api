package com.nortic.pos.es.employeeworkspace.mapper;

import com.google.protobuf.Timestamp;
import com.nortic.pos.es.employeeworkspace.grpc.EmployeeWorkspaceProto;
import com.nortic.pos.es.employeeworkspace.model.dto.CreateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.EmployeeWorkspaceDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.PaginatedEmployeeWorkspaceResponseDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.UpdateEmployeeWorkspaceRequestDTO;
import org.mapstruct.*;

import java.time.OffsetDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public abstract class EmployeeWorkspaceGrpcMapper {

    public EmployeeWorkspaceProto.PaginatedEmployeeWorkspaceResponse toPaginatedEmployeeWorkspaceResponse(PaginatedEmployeeWorkspaceResponseDTO dto) {

        var builder = EmployeeWorkspaceProto.PaginatedEmployeeWorkspaceResponse.newBuilder();

        builder.setNumber(dto.getNumber());
        builder.setSize(dto.getSize());
        builder.setTotalElements(dto.getTotalElements());
        builder.setTotalPages(dto.getTotalPages());
        builder.setLast(dto.getLast());

        if (dto.getContent() != null) {
            builder.addAllContent(dto.getContent().stream().map(this::toEmployeeWorkspaceResponse).toList());
        }

        return builder.build();
    }

    @Mapping(target = "unknownFields", ignore = true)
    @Mapping(target = "storeIdBytes", ignore = true)
    @Mapping(target = "roleIdBytes", ignore = true)
    @Mapping(target = "mergeUpdatedAt", ignore = true)
    @Mapping(target = "mergeUnknownFields", ignore = true)
    @Mapping(target = "mergeLastAccessAt", ignore = true)
    @Mapping(target = "mergeFrom", ignore = true)
    @Mapping(target = "mergeDeletedAt", ignore = true)
    @Mapping(target = "mergeCreatedAt", ignore = true)
    @Mapping(target = "idBytes", ignore = true)
    @Mapping(target = "employeeIdBytes", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "branchIdBytes", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    @Mapping(target = "createdAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "updatedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "deletedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "lastAccessAt", qualifiedByName = "offsetDateTimeToTimestamp")
    public abstract EmployeeWorkspaceProto.EmployeeWorkspaceResponse toEmployeeWorkspaceResponse(EmployeeWorkspaceDTO dto);

    public abstract CreateEmployeeWorkspaceRequestDTO toCreateEmployeeWorkspaceRequestDTO(EmployeeWorkspaceProto.CreateEmployeeWorkspaceRequest request);

    public abstract UpdateEmployeeWorkspaceRequestDTO toUpdateEmployeeWorkspaceRequestDTO(EmployeeWorkspaceProto.UpdateEmployeeWorkspaceRequest request);

    @Named("offsetDateTimeToTimestamp")
    protected Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Timestamp.newBuilder().setSeconds(offsetDateTime.toEpochSecond()).setNanos(offsetDateTime.getNano()).build();
    }
}

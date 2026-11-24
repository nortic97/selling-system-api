package com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.mapper;

import com.google.protobuf.Timestamp;
import com.nortic.pos.es.employee.grpc.EmployeeProto;
import com.nortic.pos.ms.auth.domain.model.Employee;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class EmployeeGrpcMapper {

    public abstract Employee toEmployee(EmployeeProto.EmployeeResponse employeeResponse);

    @Mapping(target = "usernameBytes", ignore = true)
    @Mapping(target = "unknownFields", ignore = true)
    @Mapping(target = "storeIdBytes", ignore = true)
    @Mapping(target = "passwordBytes", ignore = true)
    @Mapping(target = "mergeUnknownFields", ignore = true)
    @Mapping(target = "mergeFrom", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    public abstract EmployeeProto.AuthenticateRequest toAuthenticateRequest(LoginCredentials loginCredentials);

    protected OffsetDateTime map(Timestamp value) {
        if (value == null) {
            return null;
        }

        return Instant.ofEpochSecond(
                value.getSeconds(),
                value.getNanos()
        ).atOffset(ZoneOffset.UTC);
    }
}

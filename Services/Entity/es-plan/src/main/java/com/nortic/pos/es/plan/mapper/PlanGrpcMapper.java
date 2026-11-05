package com.nortic.pos.es.plan.mapper;

import com.google.protobuf.Timestamp;
import com.nortic.pos.es.plan.grpc.PlanProto;
import com.nortic.pos.es.plan.model.dto.PaginatedPlanResponseDTO;
import com.nortic.pos.es.plan.model.dto.PlanDTO;
import com.nortic.pos.es.plan.model.enums.PlanTypeOfPlanEnum;
import org.mapstruct.*;

import java.time.OffsetDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public abstract class PlanGrpcMapper {

    public PlanProto.PaginatedPlanResponse toPaginatedPlanResponse(PaginatedPlanResponseDTO dto) {

        var builder = PlanProto.PaginatedPlanResponse.newBuilder();

        builder.setNumber(dto.getNumber());
        builder.setSize(dto.getSize());
        builder.setTotalElements(dto.getTotalElements());
        builder.setTotalPages(dto.getTotalPages());
        builder.setLast(dto.getLast());
        builder.setFirst(dto.getFirst());

        if (dto.getContent() != null) {
            builder.addAllContent(dto.getContent().stream().map(this::toPlanResponse).toList());
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
    @Mapping(target = "nameBytes", ignore = true)
    @Mapping(target = "descriptionBytes", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    @Mapping(target = "createdAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "updatedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "deletedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "typeOfPlan", qualifiedByName = "typeOfPlanEnumToProto")
    public abstract PlanProto.PlanResponse toPlanResponse(PlanDTO dto);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "typeOfPlan", source = "typeOfPlan", qualifiedByName = "protoToTypeOfPlanEnum")
    public abstract PlanDTO toPlanDTO(PlanProto.PlanRequest request);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "typeOfPlan", source = "typeOfPlan", qualifiedByName = "protoToTypeOfPlanEnum")
    public abstract PlanDTO toPlanDTO(PlanProto.UpdatePlanRequest request);

    @Named("offsetDateTimeToTimestamp")
    protected Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Timestamp.newBuilder().setSeconds(offsetDateTime.toEpochSecond()).setNanos(offsetDateTime.getNano()).build();
    }

    @Named("typeOfPlanEnumToProto")
    protected PlanProto.TypeOfPlan typeOfPlanEnumToProto(PlanTypeOfPlanEnum typeOfPlan) {
        if (typeOfPlan == null) {
            return null;
        }
        return switch (typeOfPlan) {
            case FREE -> PlanProto.TypeOfPlan.FREE;
            case BASIC -> PlanProto.TypeOfPlan.BASIC;
            case STANDARD -> PlanProto.TypeOfPlan.STANDARD;
            case PREMIUM -> PlanProto.TypeOfPlan.PREMIUM;
            case ENTERPRISE -> PlanProto.TypeOfPlan.ENTERPRISE;
        };
    }

    @Named("protoToTypeOfPlanEnum")
    protected PlanTypeOfPlanEnum protoToTypeOfPlanEnum(PlanProto.TypeOfPlan typeOfPlan) {
        if (typeOfPlan == null) {
            return null;
        }
        return switch (typeOfPlan) {
            case FREE -> PlanTypeOfPlanEnum.FREE;
            case BASIC -> PlanTypeOfPlanEnum.BASIC;
            case STANDARD -> PlanTypeOfPlanEnum.STANDARD;
            case PREMIUM -> PlanTypeOfPlanEnum.PREMIUM;
            case ENTERPRISE -> PlanTypeOfPlanEnum.ENTERPRISE;
            case UNRECOGNIZED -> throw new IllegalArgumentException("Unrecognized type of plan");
        };
    }
}

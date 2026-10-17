package com.nortic.pos.es.feature.mapper;

import com.google.protobuf.Timestamp;
import com.nortic.pos.es.feature.grpc.FeatureProto;
import com.nortic.pos.es.feature.model.dto.FeatureDTO;
import com.nortic.pos.es.feature.model.dto.PaginatedFeatureResponseDTO;
import com.nortic.pos.es.feature.model.enums.FeatureTypeEnum;
import org.mapstruct.*;

import java.time.OffsetDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public abstract class FeatureGrpcMapper {

    public FeatureProto.PaginatedFeatureResponse toPaginatedFeatureResponse(PaginatedFeatureResponseDTO dto) {

        var builder = FeatureProto.PaginatedFeatureResponse.newBuilder();

        builder.setNumber(dto.getNumber());
        builder.setSize(dto.getSize());
        builder.setTotalElements(dto.getTotalElements());
        builder.setTotalPages(dto.getTotalPages());
        builder.setLast(dto.getLast());
        builder.setFirst(dto.getFirst());

        if (dto.getContent() != null) {
            builder.addAllContent(dto.getContent().stream().map(this::toFeatureResponse).toList());
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
    @Mapping(target = "codeBytes", ignore = true)
    @Mapping(target = "nameBytes", ignore = true)
    @Mapping(target = "descriptionBytes", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    @Mapping(target = "createdAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "updatedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "deletedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "type", qualifiedByName = "featureTypeEnumToProto")
    public abstract FeatureProto.FeatureResponse toFeatureResponse(FeatureDTO dto);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "type", source = "type", qualifiedByName = "protoToFeatureTypeEnum")
    public abstract FeatureDTO toFeatureDTO(FeatureProto.FeatureRequest request);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "type", source = "type", qualifiedByName = "protoToFeatureTypeEnum")
    public abstract FeatureDTO toFeatureDTO(FeatureProto.UpdateFeatureRequest request);

    @Named("offsetDateTimeToTimestamp")
    protected Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Timestamp.newBuilder().setSeconds(offsetDateTime.toEpochSecond()).setNanos(offsetDateTime.getNano()).build();
    }

    @Named("featureTypeEnumToProto")
    protected FeatureProto.FeatureType featureTypeEnumToProto(FeatureTypeEnum featureType) {
        if (featureType == null) {
            return null;
        }
        return switch (featureType) {
            case BOOLEAN -> FeatureProto.FeatureType.BOOLEAN;
            case LIMIT -> FeatureProto.FeatureType.LIMIT;
            case UNLIMITED -> FeatureProto.FeatureType.UNLIMITED;
        };
    }

    @Named("protoToFeatureTypeEnum")
    protected FeatureTypeEnum protoToFeatureTypeEnum(FeatureProto.FeatureType featureType) {
        if (featureType == null) {
            return null;
        }
        return switch (featureType) {
            case BOOLEAN -> FeatureTypeEnum.BOOLEAN;
            case LIMIT -> FeatureTypeEnum.LIMIT;
            case UNLIMITED -> FeatureTypeEnum.UNLIMITED;
            case UNRECOGNIZED -> throw new IllegalArgumentException("Unrecognized feature type");
        };
    }
}

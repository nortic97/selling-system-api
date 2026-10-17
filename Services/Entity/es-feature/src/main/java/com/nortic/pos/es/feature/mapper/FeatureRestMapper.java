package com.nortic.pos.es.feature.mapper;

import com.nortic.pos.es.feature.model.dto.FeatureDTO;
import com.nortic.pos.es.feature.model.dto.PaginatedFeatureResponseDTO;
import com.nortic.pos.es.feature.rest.gen.model.RestModelFeatureRequestDTO;
import com.nortic.pos.es.feature.rest.gen.model.RestModelFeatureResponseDTO;
import com.nortic.pos.es.feature.rest.gen.model.RestModelPaginatedFeatureResponseDTO;
import com.nortic.pos.es.feature.rest.gen.model.RestModelUpdateFeatureRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class FeatureRestMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract FeatureDTO toFeatureDTO(RestModelFeatureRequestDTO feature);

    public abstract RestModelFeatureResponseDTO toFeatureResponseDTO(FeatureDTO feature);

    public abstract RestModelPaginatedFeatureResponseDTO toListFeaturesResponseDTO(PaginatedFeatureResponseDTO paginatedFeatureResponseDTO);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract FeatureDTO toUpdateFeatureRequestDTO(RestModelUpdateFeatureRequestDTO restModelUpdateFeatureRequestDTO);
}

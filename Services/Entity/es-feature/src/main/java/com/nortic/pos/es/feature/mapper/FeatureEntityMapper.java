package com.nortic.pos.es.feature.mapper;

import com.nortic.pos.es.feature.model.dto.FeatureDTO;
import com.nortic.pos.es.feature.model.dto.PaginatedFeatureResponseDTO;
import com.nortic.pos.es.feature.model.entity.FeatureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class FeatureEntityMapper {

    public abstract FeatureEntity toFeatureEntity(FeatureDTO featureDTO);

    public abstract FeatureDTO toFeatureDTO(FeatureEntity featureEntity);

    @Mapping(target = "number", expression = "java(page.getNumber() + 1)")
    public abstract PaginatedFeatureResponseDTO toListFeatureDTO(Page<FeatureEntity> page);

    public abstract void updateFeatureFromDTO(FeatureDTO featureDTO, @MappingTarget FeatureEntity featureEntity);

}

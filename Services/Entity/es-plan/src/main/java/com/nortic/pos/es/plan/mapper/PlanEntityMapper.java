package com.nortic.pos.es.plan.mapper;

import com.nortic.pos.es.plan.model.dto.PaginatedPlanResponseDTO;
import com.nortic.pos.es.plan.model.dto.PlanDTO;
import com.nortic.pos.es.plan.model.entity.PlanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class PlanEntityMapper {

    public abstract PlanEntity toPlanEntity(PlanDTO planDTO);

    public abstract PlanDTO toPlanDTO(PlanEntity planEntity);

    @Mapping(target = "number", expression = "java(page.getNumber() + 1)")
    public abstract PaginatedPlanResponseDTO toListPlanDTO(Page<PlanEntity> page);

    public abstract void updatePlanFromDTO(PlanDTO planDTO, @MappingTarget PlanEntity planEntity);

}

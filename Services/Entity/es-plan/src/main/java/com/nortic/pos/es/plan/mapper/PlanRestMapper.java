package com.nortic.pos.es.plan.mapper;

import com.nortic.pos.es.plan.model.dto.PaginatedPlanResponseDTO;
import com.nortic.pos.es.plan.model.dto.PlanDTO;
import com.nortic.pos.es.plan.rest.gen.model.RestModelPaginatedPlanResponseDTO;
import com.nortic.pos.es.plan.rest.gen.model.RestModelPlanRequestDTO;
import com.nortic.pos.es.plan.rest.gen.model.RestModelPlanResponseDTO;
import com.nortic.pos.es.plan.rest.gen.model.RestModelUpdatePlanRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class PlanRestMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract PlanDTO toPlanDTO(RestModelPlanRequestDTO plan);

    public abstract RestModelPlanResponseDTO toPlanResponseDTO(PlanDTO plan);

    public abstract RestModelPaginatedPlanResponseDTO toListPlansResponseDTO(PaginatedPlanResponseDTO paginatedPlanResponseDTO);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract PlanDTO toUpdatePlanRequestDTO(RestModelUpdatePlanRequestDTO restModelUpdatePlanRequestDTO);
}

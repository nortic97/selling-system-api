package com.nortic.pos.es.role.util;

import com.nortic.pos.models.dto.RoleDTO;
import com.nortic.pos.models.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    RoleEntity dtoToEntity(RoleDTO roleDTO);
    RoleDTO entityToDTO(RoleEntity roleEntity);
}

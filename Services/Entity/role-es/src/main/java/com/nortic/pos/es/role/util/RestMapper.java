package com.nortic.pos.es.role.util;

import com.nortic.pos.es.role.model.*;
import com.nortic.pos.models.dto.RoleDTO;
import com.nortic.pos.models.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestMapper {
    RoleDTO restModelToDTO(RestModelRole restModelRole);
    RoleEntity dtoToEntity(RoleDTO roleDTO);
    RoleDTO entityToDTO(RoleEntity roleEntity);
    RestModelRole dtoToRestModel(RoleDTO roleDTO);
}

package com.nortic.posdash.es.users.utils;

import com.nortic.posdash.us.models.dto.UserDTO;
import com.nortic.posdash.us.models.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    private final ModelMapper modelMapper;

    public Mapper() {
        this.modelMapper = new ModelMapper();
    }

    public <T, D> D convertToDto(T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <T, D> T convertToEntity(D dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public <T, D> List<D> convertToDtoList(List<T> entityList, Class<D> dtoClass) {
        return entityList.stream()
                .map(entity -> convertToDto(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public <T, D> List<T> convertToEntityList(List<D> dtoList, Class<T> entityClass) {
        return dtoList.stream()
                .map(dto -> convertToEntity(dto, entityClass))
                .collect(Collectors.toList());
    }

    public <T, D> Page<D> convertToDtoPage(Page<T> entityPage, Class<D> dtoClass) {
        List<D> dtoList = entityPage.getContent().stream()
                .map(entity -> convertToDto(entity, dtoClass))
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, entityPage.getPageable(), entityPage.getTotalElements());
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}

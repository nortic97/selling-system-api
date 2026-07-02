package com.nortic.pos.es.user.service;

import com.nortic.pos.es.user.dto.request.UserQueryRequestDTO;
import com.nortic.pos.es.user.dto.request.UserSearchRequestDTO;
import com.nortic.pos.es.user.model.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

public interface UserSpecificationService {

    Specification<UserEntity> buildSearchSpecification(UserSearchRequestDTO searchRequest);

    Specification<UserEntity> buildQuerySpecification(UserQueryRequestDTO queryRequest);

}

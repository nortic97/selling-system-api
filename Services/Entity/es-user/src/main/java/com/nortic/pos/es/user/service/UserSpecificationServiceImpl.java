package com.nortic.pos.es.user.service;

import com.nortic.pos.es.user.dto.request.UserQueryRequestDTO;
import com.nortic.pos.es.user.dto.request.UserSearchRequestDTO;
import com.nortic.pos.es.user.entity.UserEntity;
import com.nortic.pos.es.user.service.contract.UserSpecificationService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserSpecificationServiceImpl implements UserSpecificationService {

    @Override
    public Specification<UserEntity> buildSearchSpecification(UserSearchRequestDTO searchRequest) {

        Specification<UserEntity> spec = (
                root,
                query,
                criteriaBuilder
        ) -> criteriaBuilder.conjunction();

        if (searchRequest.getFirstName() != null && !searchRequest.getFirstName().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("firstName")), "%" + searchRequest.getFirstName().toLowerCase() + "%"));
        }

        if (searchRequest.getLastName() != null && !searchRequest.getLastName().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("lastName")), "%" + searchRequest.getLastName().toLowerCase() + "%"));
        }

        if (searchRequest.getUsername() != null && !searchRequest.getUsername().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("username")), "%" + searchRequest.getUsername().toLowerCase() + "%"));
        }

        if (searchRequest.getEmail() != null && !searchRequest.getEmail().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("email")), "%" + searchRequest.getEmail().toLowerCase() + "%"));
        }

        if (searchRequest.getPhone() != null && !searchRequest.getPhone().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("phone"), searchRequest.getPhone()));
        }

        if (searchRequest.getDocumentType() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("documentType"), searchRequest.getDocumentType()));
        }

        if (searchRequest.getDocumentNumber() != null && !searchRequest.getDocumentNumber().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("documentNumber")), "%" + searchRequest.getDocumentNumber().toLowerCase() + "%"));
        }

        if (searchRequest.getIsActive() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("isActive"), searchRequest.getIsActive()));
        }

        if (searchRequest.getDateOfBirth() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("dateOfBirth"), searchRequest.getDateOfBirth()));
        }

        if (searchRequest.getAddress() != null && searchRequest.getAddress().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("address"), "%" + searchRequest.getAddress().toLowerCase() + "%"));
        }

        return spec;
    }

    @Override
    public Specification<UserEntity> buildQuerySpecification(UserQueryRequestDTO queryRequest) {
        if (queryRequest.getQuery() == null || queryRequest.getQuery().isBlank()) {
            return (root, query, cb) -> cb.conjunction();
        }

        String searchTerm = "%" + queryRequest.getQuery().toLowerCase() + "%";

        return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.get("firstName")), searchTerm),
                cb.like(cb.lower(root.get("lastName")), searchTerm),
                cb.like(cb.lower(root.get("username")), searchTerm),
                cb.like(cb.lower(root.get("email")), searchTerm),
                cb.like(cb.lower(root.get("documentNumber")), searchTerm),
                cb.like(cb.lower(root.get("phone")), searchTerm),
                cb.like(cb.lower(root.get("address")), searchTerm)
        );
    }
}

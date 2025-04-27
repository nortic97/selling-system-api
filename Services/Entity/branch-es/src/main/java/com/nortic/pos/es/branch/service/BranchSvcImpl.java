package com.nortic.pos.es.branch.service;

import com.nortic.pos.es.branch.repository.BranchRepository;
import com.nortic.pos.es.branch.util.CustomException;
import com.nortic.pos.es.branch.util.RestMapper;
import com.nortic.pos.models.dto.BranchDTO;
import com.nortic.pos.models.entity.BranchEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class BranchSvcImpl implements IBranchSvc{

    private final RestMapper restMapper;
    private final BranchRepository branchRepository;

    @Autowired
    BranchSvcImpl(RestMapper restMapper, BranchRepository branchRepository) {
        this.restMapper = restMapper;
        this.branchRepository = branchRepository;
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) {
        log.info("Create Branch");
        BranchEntity branch = restMapper.dtoToEntity(branchDTO);
        return restMapper.entityToDTO(branchRepository.save(branch));
    }

    @Override
    public BranchDTO getBranchById(UUID id) {
        log.info("Getting branch by id: {}", id);
        return branchRepository.findById(id)
                .map(restMapper::entityToDTO)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));
    }

    @Override
    public BranchDTO updateBranch(UUID id, BranchDTO branchDTO) {
        log.info("Updating branch with id {}", id);

        BranchEntity branchEntity = branchRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));

        branchEntity.setName(branchDTO.getName());
        branchEntity.setAddress(branchDTO.getAddress());
        branchEntity.setPhoneNumber(branchDTO.getPhoneNumber());
        branchEntity.setEmail(branchDTO.getEmail());

        return restMapper.entityToDTO(branchRepository.save(branchEntity));
    }

    @Override
    public void deleteBranch(UUID id) {
        log.info("Deleting branch with id {}", id);

        if(!branchRepository.existsById(id)) {
           throw  new CustomException(404, "ID: " + id + " Not Found");
        }

        branchRepository.deleteById(id);
    }

    @Override
    public void softDeleteBranch(UUID id) {
        log.info("Soft delete branch with id {}", id);

        BranchEntity branchEntity = branchRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));

        if(branchEntity.getDeletedAt() != null){
            log.error("Branch with ID: {} is already deleted", id);
            throw new CustomException(409, "Branch with ID: " + id + " is already deleted");
        }

        branchEntity.markAsDeleted();

        branchRepository.save(branchEntity);
    }

    @Override
    public void changeBranchStatus(UUID id, Boolean status) {

        log.info("Changing branch status with id {}", id);

        BranchEntity branchEntity = branchRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));

        if(Objects.equals(branchEntity.getIsActive(), status)) {
            log.warn("Branch with id {} already has status {}", id, status);
            throw new CustomException(409, "Branch with id " + id + " already has status " + status);
        }

        branchEntity.changeStatus(status);

        branchRepository.save(branchEntity);
    }


}

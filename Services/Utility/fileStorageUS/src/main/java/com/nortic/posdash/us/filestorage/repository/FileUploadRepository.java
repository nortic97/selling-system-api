package com.nortic.posdash.us.filestorage.repository;

import com.nortic.posdash.models.entity.FileUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUploadEntity, UUID> {
}

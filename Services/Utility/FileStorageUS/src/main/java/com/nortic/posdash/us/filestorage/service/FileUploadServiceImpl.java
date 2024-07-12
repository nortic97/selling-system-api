package com.nortic.posdash.us.filestorage.service;

import com.nortic.posdash.models.dto.FileUploadDTO;
import com.nortic.posdash.models.entity.FileUploadEntity;
import com.nortic.posdash.models.enums.FileTypeEnum;
import com.nortic.posdash.us.filestorage.repository.FileUploadRepository;
import com.nortic.posdash.us.filestorage.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImpl implements IFileUpload {

    private static final Logger log = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Value("${file.storage.root}")
    private String rootDirectory;

    private final FileUploadRepository fileUploadRepository;
    private final Mapper mapper;

    @Autowired
    public FileUploadServiceImpl(FileUploadRepository fileUploadRepository, Mapper mapper) {
        this.fileUploadRepository = fileUploadRepository;
        this.mapper = mapper;
    }

    @Override
    public FileUploadDTO fileUpload(MultipartFile file, String fileUUID, FileTypeEnum fileType) throws IOException {
        log.info("Start FileUpload Service");

        Path fileStorageLocation = getPath(fileType, fileUUID);

        if (!Files.exists(fileStorageLocation)) {
            Files.createDirectories(fileStorageLocation);
        }

        String fileName = fileUUID + getFileExtension(file);
        Path targetLocation = fileStorageLocation.resolve(fileName);

        Files.copy(file.getInputStream(), targetLocation);

        FileUploadDTO fileUpload = new FileUploadDTO();
        fileUpload.setFileName(file.getOriginalFilename());
        fileUpload.setFileType(file.getContentType());
        fileUpload.setFileSize(file.getSize());
        fileUpload.setFileUrl(targetLocation.toString());
        fileUpload.setUploadDir(fileStorageLocation.toString());

        fileUploadRepository.save(mapper.convertToEntity(fileUpload, FileUploadEntity.class));

        log.info("Target location: {}", targetLocation);
        log.info("End FileUpload Service");
        return fileUpload;
    }

    private Path getPath(FileTypeEnum fileType, String userUUID) {
        return switch (fileType) {
            case PROFILE_PIC_FOLDER ->
                    Paths.get(rootDirectory, fileType.getDirectoryName(), userUUID).toAbsolutePath().normalize();
            case INVOICES_FOLDER, OTHER ->
                    Paths.get(rootDirectory, fileType.getDirectoryName()).toAbsolutePath().normalize();
        };
    }

    private String getFileExtension(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        if (originalFileName != null) {
            int lastIndex = originalFileName.lastIndexOf('.');
            if (lastIndex != -1) {
                return originalFileName.substring(lastIndex);
            }
        }
        return "";
    }

}

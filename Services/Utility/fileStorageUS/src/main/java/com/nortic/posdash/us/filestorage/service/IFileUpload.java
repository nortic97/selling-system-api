package com.nortic.posdash.us.filestorage.service;

import com.nortic.posdash.models.dto.FileUploadDTO;
import com.nortic.posdash.models.enums.FileTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileUpload {
    FileUploadDTO fileUpload(MultipartFile file, String fileUUID, FileTypeEnum fileType) throws IOException;
}

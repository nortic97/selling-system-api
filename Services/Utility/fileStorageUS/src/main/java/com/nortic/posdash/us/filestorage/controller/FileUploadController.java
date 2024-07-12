package com.nortic.posdash.us.filestorage.controller;

import com.nortic.posdash.models.dto.FileUploadDTO;
import com.nortic.posdash.models.enums.FileTypeEnum;
import com.nortic.posdash.models.enums.StatusEnum;
import com.nortic.posdash.us.filestorage.service.FileUploadServiceImpl;
import com.nortic.posdash.us.filestorage.utils.CustomException;
import com.nortic.posdash.us.filestorage.utils.ResponseBody;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/us/fileUpload")
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    public final FileUploadServiceImpl fileUploadService;

    @Autowired
    FileUploadController(FileUploadServiceImpl fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/{fileUUID}/{fileType}")
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file, @PathVariable String fileUUID, @PathVariable FileTypeEnum fileType) {
        try {
            FileUploadDTO fileUploadDTO = fileUploadService.fileUpload(file, fileUUID, fileType);
            return ResponseEntity.ok().body(new ResponseBody<>(fileUploadDTO, StatusEnum.SUCCESS).getBody());
        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(e.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @Hidden
    @RequestMapping("/**")
    public ResponseEntity<?> handleInvalidRequests(HttpServletRequest request) {
        log.error("The url {} does not exist", request.getRequestURI());
        ResponseBody<Object> responseBody = new ResponseBody<>(new CustomException(request.getRequestURI()).getMessage(), StatusEnum.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody.getBody());
    }

}

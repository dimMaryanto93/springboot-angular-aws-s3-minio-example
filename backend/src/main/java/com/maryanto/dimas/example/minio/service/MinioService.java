package com.maryanto.dimas.example.minio.service;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import io.minio.messages.Tags;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

@Service
@Slf4j
public class MinioService {

    private final MinioClient minio;
    private final String bucketName;

    public MinioService(MinioClient minio, String bucketName) {
        this.minio = minio;
        this.bucketName = bucketName;
    }

    public boolean isBucketExists()
            throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        return this.minio.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
    }

    public ObjectWriteResponse upload(File file, String folder)
            throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        ObjectWriteResponse response = this.minio.uploadObject(UploadObjectArgs.builder()
                .bucket(this.bucketName)
                .filename(file.toPath().toString())
                .tags(Tags.newObjectTags(new HashMap<>()))
                .object(new StringBuilder(folder)
                        .append(File.separator)
                        .append(UUID.randomUUID())
                        .append(".")
                        .append(FilenameUtils.getExtension(file.getName())).toString())
                .build());
        return response;
    }
}

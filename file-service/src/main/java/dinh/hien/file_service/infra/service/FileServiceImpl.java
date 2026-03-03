package dinh.hien.file_service.infra.service;

import dinh.hien.file_service.application.usecase.upload.ASingleFileRequestInfo;
import dinh.hien.file_service.application.usecase.upload.UploadCommand;
import dinh.hien.file_service.domain.file.FileId;
import dinh.hien.file_service.domain.file.FileMetaData;
import dinh.hien.file_service.domain.file.FileSize;
import dinh.hien.file_service.domain.file.upload.FileUploadData;
import dinh.hien.file_service.domain.file.IFileService;
import dinh.hien.file_service.domain.file.upload.FileUploadPreparation;
import dinh.hien.file_service.infra.utils.SharedMethods;
import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements IFileService {

    private final MinioClient minioClient;

    @Value("${minio.images.bucket}")
    private String imageBucketName;

    @Value("${minio.videos.bucket}")
    private String videoBucketName;

    @Value("${minio.others.bucket}")
    private String otherBucketName;

    /**
     * auto init buckets on startup
     */
    @PostConstruct
    public void initBuckets() {
        createBucketIfNotExists(imageBucketName, true);
        createBucketIfNotExists(videoBucketName, false);
        createBucketIfNotExists(otherBucketName, false);
    }


    @Override
    public FileUploadPreparation uploadPreparation(UploadCommand command) {
        List<FileUploadData> data = new ArrayList<>();
        List<ASingleFileRequestInfo> files = command.getFiles();
        for (ASingleFileRequestInfo file : files) {
            String objectName = generateObjectName(file.getFileName());
            String storageKey = generateStorageKey(objectName, file.getContentType());
            FileMetaData fileMetaData = FileMetaData.of(FileId.generate()
                    , file.getFileName()
                    , FileSize.of(file.getSize())
                    , file.getContentType()
                    , storageKey);
            String presignUrl = generatePresignUrlForUpload(objectName, file.getContentType());
            data.add(new FileUploadData(fileMetaData, presignUrl));
        }
        return new FileUploadPreparation(data);
    }


    public String generateObjectName(String fileName) {
        return UUID.randomUUID() + "_" + fileName;
    }


    public String generateStorageKey(String objectName, String contentType) {
        String bucket = resolveBucket(contentType);
        return bucket + "/" + objectName;
    }


    public String generatePresignUrlForUpload(String objectName, String contentType) {
        String bucket = resolveBucket(contentType);
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(bucket)
                            .object(objectName)
                            .expiry(600)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate presign url", e);
        }
    }


    public boolean isCanUpload() {
        return SharedMethods.getAuthorityNames().contains("ADMIN");
    }

    /**
     * resolve bucket by contentType
     */
    private String resolveBucket(String contentType) {
        if (contentType == null) {
            return otherBucketName;
        }
        if (contentType.startsWith("image/")) {
            return imageBucketName;
        }
        if (contentType.startsWith("video/")) {
            return videoBucketName;
        }
        return otherBucketName;
    }

    /**
     * create bucket if not exists
     */
    private void createBucketIfNotExists(String bucketName, boolean isPublic) {
        try {
            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(bucketName)
                            .build()
            );
            if (!exists) {
                // create bucket
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(bucketName)
                                .build()
                );
                // set policy
                if (isPublic) {
                    setPublicBucket(bucketName);
                } else {
                    setPrivateBucket(bucketName);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create bucket: " + bucketName, e);
        }
    }

    /**
     * public bucket policy
     */
    private void setPublicBucket(String bucketName) throws Exception {
        String policy = """
                {
                  "Version":"2012-10-17",
                  "Statement":[
                    {
                      "Effect":"Allow",
                      "Principal":"*",
                      "Action":[
                        "s3:GetObject"
                      ],
                      "Resource":[
                        "arn:aws:s3:::%s/*"
                      ]
                    }
                  ]
                }
                """.formatted(bucketName);
        minioClient.setBucketPolicy(
                SetBucketPolicyArgs.builder()
                        .bucket(bucketName)
                        .config(policy)
                        .build()
        );
    }

    /**
     * private bucket policy
     */
    private void setPrivateBucket(String bucketName) throws Exception {
        String policy = """
                {
                  "Version":"2012-10-17",
                  "Statement":[]
                }
                """;
        minioClient.setBucketPolicy(
                SetBucketPolicyArgs.builder()
                        .bucket(bucketName)
                        .config(policy)
                        .build()
        );
    }


}
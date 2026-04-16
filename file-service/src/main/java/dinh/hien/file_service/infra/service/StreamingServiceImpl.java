package dinh.hien.file_service.infra.service;

import dinh.hien.file_service.domain.file.IStreamingService;
import dinh.hien.file_service.infra.exception.InfraError;
import dinh.hien.file_service.infra.exception.InfraException;
import dinh.hien.file_service.infra.model.FileMetadataDocument;
import dinh.hien.file_service.infra.persistence.repository.MongoFileRepository;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StreamingServiceImpl implements IStreamingService {
    private final MongoFileRepository mongoRepository;
    private final MinioClient minioClient;

    @Value("${minio.images.bucket}")
    private String imageBucketName;

    @Value("${minio.videos.bucket}")
    private String videoBucketName;

    @Value("${minio.others.bucket}")
    private String otherBucketName;

    @Override
    public String stream(UUID id) {
        FileMetadataDocument document = mongoRepository.findById(id.toString())
                .orElseThrow(() -> new InfraException(InfraError.FILE_NOT_FOUND));

        String objectKey = document.getStorageKey();
        if (objectKey == null || objectKey.isEmpty()) {
            throw new InfraException(InfraError.FILE_NOT_FOUND);
        }

        String bucket = resolveBucket(document.getContentType());

        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(resolveBucket(document.getContentType()))
                            .object(document.getStorageKey())
                            .expiry(60 * 60 * 2)
                            .build()
            );
        } catch (Exception e) {
            throw new InfraException(InfraError.GET_PRESIGNED_ERROR);
        }
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
}

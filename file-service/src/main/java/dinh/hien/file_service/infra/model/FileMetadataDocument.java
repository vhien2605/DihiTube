package dinh.hien.file_service.infra.model;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "files")
@Builder
public class FileMetadataDocument {
    @MongoId
    @Field("_id")
    private String id;

    @Field("file_name")
    private String fileName;

    @Field("file_size")
    private Long size;

    @Field("content_type")
    private String contentType;

    @Field("storage_key")
    private String storageKey;

    @Field("file_type")
    private String type;

    @Field("created_at")
    private Instant createdAt;

    @Field("is_private")
    private Boolean isPrivateFile;
}

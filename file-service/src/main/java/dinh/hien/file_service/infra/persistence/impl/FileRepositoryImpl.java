package dinh.hien.file_service.infra.persistence.impl;

import dinh.hien.file_service.domain.file.FileMetaData;
import dinh.hien.file_service.domain.file.IFileRepository;
import dinh.hien.file_service.infra.model.FileMetadataDocument;
import dinh.hien.file_service.infra.persistence.repository.MongoFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileRepositoryImpl implements IFileRepository {
    private final MongoFileRepository mongoFileRepository;

    @Override
    public void save(List<FileMetaData> files) {
        List<FileMetadataDocument> documents = files.stream()
                .map(file -> FileMetadataDocument.builder()
                        .id(file.getId().getValue().toString())
                        .size(file.getSize().getValue())
                        .type(file.getType().name())
                        .isPrivateFile(file.getIsPrivateFile())
                        .fileName(file.getFileName())
                        .storageKey(file.getStorageKey())
                        .contentType(file.getContentType())
                        .createdAt(file.getCreatedAt())
                        .build()).toList();
        mongoFileRepository.saveAll(documents);
    }
}

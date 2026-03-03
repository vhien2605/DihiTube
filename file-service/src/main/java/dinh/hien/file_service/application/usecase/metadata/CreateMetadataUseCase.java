package dinh.hien.file_service.application.usecase.metadata;


import dinh.hien.file_service.domain.file.FileMetaData;
import dinh.hien.file_service.domain.file.FileSize;
import dinh.hien.file_service.domain.file.IFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CreateMetadataUseCase {
    private final IFileRepository fileRepository;

    public String saveMetaData(MetaDataCommand metaDataCommand) {
        List<FileMetaData> domainEntities = new ArrayList<>();
        for (ApplicationFileMetaData data : metaDataCommand.getFiles()) {
            FileMetaData domainMetadata = FileMetaData.of(
                    data.getFileName(),
                    FileSize.of(data.getSize()),
                    data.getContentType(),
                    data.getStorageKey(),
                    data.getCreatedAt()
            );
            domainEntities.add(domainMetadata);
        }
        fileRepository.save(domainEntities);
        return "Saved ok";
    }
}

package dinh.hien.file_service.application.usecase.metadata;

import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetaDataCommand {
    private List<ApplicationFileMetaData> files;
}

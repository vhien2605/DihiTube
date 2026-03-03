package dinh.hien.file_service.application.usecase.download;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DownloadResult {
    private String downloadLink;
}

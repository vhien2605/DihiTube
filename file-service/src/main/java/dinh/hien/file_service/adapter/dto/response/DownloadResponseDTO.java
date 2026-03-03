package dinh.hien.file_service.adapter.dto.response;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DownloadResponseDTO {
    private String downloadLink;
}

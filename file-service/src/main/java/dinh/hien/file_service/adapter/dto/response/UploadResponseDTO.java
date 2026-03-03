package dinh.hien.file_service.adapter.dto.response;

import lombok.*;


import java.util.List;


@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadResponseDTO {
    private List<SingleFileResponse> singleFileResponseList;
}

package dinh.hien.file_service.application.usecase.upload;

import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadResult {
    private List<ASingleFileResponse> singleFileResponseList;
}

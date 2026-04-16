package dinh.hien.file_service.application.usecase.streaming;

import dinh.hien.file_service.domain.file.IStreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StreamingUseCase {
    private final IStreamingService streamingService;

    public String streaming(UUID id) {
        return streamingService.stream(id);
    }
}

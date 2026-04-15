package com.pm.streamingservice.appilcation;

import com.pm.streamingservice.domain.IProfileService;
import com.pm.streamingservice.domain.IStreamingService;
import com.pm.streamingservice.infra.exception.InfraError;
import com.pm.streamingservice.infra.exception.InfraException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StreamingUseCase {
    private final IStreamingService streamingService;
    private final IProfileService profileService;

    public String streaming(UUID id){
        boolean isMemberShip = profileService.isMembership();
        if (!isMemberShip){
            throw new InfraException(InfraError.PERMISSION_DENIED);
        }

        return streamingService.stream(id);
    }
}
